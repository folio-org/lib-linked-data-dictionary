package org.folio.ld.dictionary.label;

import static org.assertj.core.api.Assertions.assertThat;
import static org.folio.ld.dictionary.ResourceTypeDictionary.FAMILY;
import static org.folio.ld.dictionary.ResourceTypeDictionary.PERSON;

import lombok.SneakyThrows;
import org.folio.ld.dictionary.label.generators.FamilyLabelGenerator;
import org.folio.ld.dictionary.model.Resource;
import org.junit.jupiter.api.Test;
import tools.jackson.databind.json.JsonMapper;

class FamilyLabelGeneratorTest {
  private final JsonMapper mapper = new JsonMapper();
  private final LabelGeneratorService generatorService = new LabelGeneratorService();
  private final FamilyLabelGenerator generator = new FamilyLabelGenerator();

  @Test
  void supports_returnsTrue_forFamilyType() {
    var family = new Resource().setId(1L).addType(FAMILY);

    assertThat(generator.supports(family)).isTrue();
  }

  @Test
  void supports_returnsFalse_forNonFamilyType() {
    var person = new Resource().setId(1L).addType(PERSON);

    assertThat(generator.supports(person)).isFalse();
  }

  @Test
  @SneakyThrows
  void getLabel_joinsAllProperties() {
    var family = new Resource()
      .setId(1L)
      .addType(FAMILY)
      .setDoc(mapper.readTree("""
        {
          "http://bibfra.me/vocab/library/numeration": ["II"],
          "http://bibfra.me/vocab/lite/name": ["Bonaparte"],
          "http://bibfra.me/vocab/library/titles": ["family"],
          "http://bibfra.me/vocab/lite/nameAlternative": ["Buonaparte"],
          "http://bibfra.me/vocab/lite/date": ["1750-1850"]
        }
        """));

    assertThat(generatorService.getLabel(family)).isEqualTo("II, Bonaparte, family, Buonaparte, 1750-1850");
  }

  @Test
  @SneakyThrows
  void getLabel_skipsMissingNumeration() {
    var family = new Resource()
      .setId(1L)
      .addType(FAMILY)
      .setDoc(mapper.readTree("""
        {
          "http://bibfra.me/vocab/lite/name": ["Bonaparte"],
          "http://bibfra.me/vocab/library/titles": ["family"],
          "http://bibfra.me/vocab/lite/nameAlternative": ["Buonaparte"],
          "http://bibfra.me/vocab/lite/date": ["1750-1850"]
        }
        """));

    assertThat(generatorService.getLabel(family)).isEqualTo("Bonaparte, family, Buonaparte, 1750-1850");
  }

  @Test
  @SneakyThrows
  void getLabel_skipsMissingTitles() {
    var family = new Resource()
      .setId(1L)
      .addType(FAMILY)
      .setDoc(mapper.readTree("""
        {
          "http://bibfra.me/vocab/library/numeration": ["II"],
          "http://bibfra.me/vocab/lite/name": ["Bonaparte"],
          "http://bibfra.me/vocab/lite/nameAlternative": ["Buonaparte"],
          "http://bibfra.me/vocab/lite/date": ["1750-1850"]
        }
        """));

    assertThat(generatorService.getLabel(family)).isEqualTo("II, Bonaparte, Buonaparte, 1750-1850");
  }

  @Test
  @SneakyThrows
  void getLabel_skipsMissingDate() {
    var family = new Resource()
      .setId(1L)
      .addType(FAMILY)
      .setDoc(mapper.readTree("""
        {
          "http://bibfra.me/vocab/library/numeration": ["II"],
          "http://bibfra.me/vocab/lite/name": ["Bonaparte"],
          "http://bibfra.me/vocab/library/titles": ["family"],
          "http://bibfra.me/vocab/lite/nameAlternative": ["Buonaparte"]
        }
        """));

    assertThat(generatorService.getLabel(family)).isEqualTo("II, Bonaparte, family, Buonaparte");
  }

  @Test
  @SneakyThrows
  void getLabel_nameOnly() {
    var family = new Resource()
      .setId(1L)
      .addType(FAMILY)
      .setDoc(mapper.readTree("""
        {
          "http://bibfra.me/vocab/lite/name": ["Bonaparte"]
        }
        """));

    assertThat(generatorService.getLabel(family)).isEqualTo("Bonaparte");
  }

  @Test
  @SneakyThrows
  void getLabel_fallsBackToResourceLabel_whenNoProperties() {
    var family = new Resource()
      .setId(1L)
      .addType(FAMILY)
      .setLabel("fallback")
      .setDoc(mapper.readTree("{}"));

    assertThat(generatorService.getLabel(family)).isEqualTo("fallback");
  }
}
