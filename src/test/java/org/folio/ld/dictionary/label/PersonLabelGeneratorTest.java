package org.folio.ld.dictionary.label;

import static org.assertj.core.api.Assertions.assertThat;
import static org.folio.ld.dictionary.ResourceTypeDictionary.CONCEPT;
import static org.folio.ld.dictionary.ResourceTypeDictionary.FAMILY;
import static org.folio.ld.dictionary.ResourceTypeDictionary.PERSON;

import lombok.SneakyThrows;
import org.folio.ld.dictionary.label.generators.authority.PersonLabelGenerator;
import org.folio.ld.dictionary.model.Resource;
import org.junit.jupiter.api.Test;
import tools.jackson.databind.json.JsonMapper;

class PersonLabelGeneratorTest {
  private final JsonMapper mapper = new JsonMapper();
  private final LabelGeneratorService generatorService = new LabelGeneratorService();
  private final PersonLabelGenerator generator = new PersonLabelGenerator();

  @Test
  void supports_returnsTrue_forPersonType() {
    var person = new Resource().setId(1L).addType(PERSON);

    assertThat(generator.supports(person)).isTrue();
  }

  @Test
  void supports_returnsFalse_forConcept() {
    var concept = new Resource().setId(1L).addType(PERSON).addType(CONCEPT);

    assertThat(generator.supports(concept)).isFalse();
  }

  @Test
  void supports_returnsFalse_forNonPersonType() {
    var family = new Resource().setId(1L).addType(FAMILY);

    assertThat(generator.supports(family)).isFalse();
  }

  @Test
  @SneakyThrows
  void getLabel_joinsAllProperties() {
    var person = new Resource()
      .setId(1L)
      .addType(PERSON)
      .setDoc(mapper.readTree("""
        {
          "http://bibfra.me/vocab/library/numeration": ["II"],
          "http://bibfra.me/vocab/lite/name": ["Elizabeth"],
          "http://bibfra.me/vocab/library/titles": ["Queen"],
          "http://bibfra.me/vocab/lite/nameAlternative": ["Elizabeth Alexandra Mary"],
          "http://bibfra.me/vocab/lite/date": ["1926-2022"]
        }
        """));

    assertThat(generatorService.getLabel(person))
      .isEqualTo("II, Elizabeth, Queen, Elizabeth Alexandra Mary, 1926-2022");
  }

  @Test
  @SneakyThrows
  void getLabel_skipsMissingProperties() {
    var person = new Resource()
      .setId(1L)
      .addType(PERSON)
      .setDoc(mapper.readTree("""
        {
          "http://bibfra.me/vocab/lite/name": ["Shakespeare, William"],
          "http://bibfra.me/vocab/lite/date": ["1564-1616"]
        }
        """));

    assertThat(generatorService.getLabel(person)).isEqualTo("Shakespeare, William, 1564-1616");
  }

  @Test
  @SneakyThrows
  void getLabel_nameOnly() {
    var person = new Resource()
      .setId(1L)
      .addType(PERSON)
      .setDoc(mapper.readTree("""
        {
          "http://bibfra.me/vocab/lite/name": ["Shakespeare, William"]
        }
        """));

    assertThat(generatorService.getLabel(person)).isEqualTo("Shakespeare, William");
  }

  @Test
  @SneakyThrows
  void getLabel_fallsBackToResourceLabel_whenNoProperties() {
    var person = new Resource()
      .setId(1L)
      .addType(PERSON)
      .setLabel("fallback")
      .setDoc(mapper.readTree("{}"));

    assertThat(generatorService.getLabel(person)).isEqualTo("fallback");
  }
}
