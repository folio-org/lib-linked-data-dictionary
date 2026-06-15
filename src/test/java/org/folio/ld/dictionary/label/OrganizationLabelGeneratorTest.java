package org.folio.ld.dictionary.label;

import static org.assertj.core.api.Assertions.assertThat;
import static org.folio.ld.dictionary.ResourceTypeDictionary.ORGANIZATION;
import static org.folio.ld.dictionary.ResourceTypeDictionary.PERSON;

import lombok.SneakyThrows;
import org.folio.ld.dictionary.label.generators.OrganizationLabelGenerator;
import org.folio.ld.dictionary.model.Resource;
import org.junit.jupiter.api.Test;
import tools.jackson.databind.json.JsonMapper;

class OrganizationLabelGeneratorTest {
  private final JsonMapper mapper = new JsonMapper();
  private final LabelGeneratorService generatorService = new LabelGeneratorService();
  private final OrganizationLabelGenerator generator = new OrganizationLabelGenerator();

  @Test
  void supports_returnsTrue_forOrganizationType() {
    var organization = new Resource().setId(1L).addType(ORGANIZATION);

    assertThat(generator.supports(organization)).isTrue();
  }

  @Test
  void supports_returnsFalse_forNonOrganizationType() {
    var person = new Resource().setId(1L).addType(PERSON);

    assertThat(generator.supports(person)).isFalse();
  }

  @Test
  @SneakyThrows
  void getLabel_joinsAllProperties() {
    var organization = new Resource()
      .setId(1L)
      .addType(ORGANIZATION)
      .setDoc(mapper.readTree("""
        {
          "http://bibfra.me/vocab/lite/name": ["United Nations"],
          "http://bibfra.me/vocab/library/subordinateUnit": ["General Assembly"],
          "http://bibfra.me/vocab/library/place": ["New York"],
          "http://bibfra.me/vocab/lite/date": ["1945"]
        }
        """));

    assertThat(generatorService.getLabel(organization)).isEqualTo("United Nations, General Assembly, New York, 1945");
  }

  @Test
  @SneakyThrows
  void getLabel_skipsMissingProperties() {
    var organization = new Resource()
      .setId(1L)
      .addType(ORGANIZATION)
      .setDoc(mapper.readTree("""
        {
          "http://bibfra.me/vocab/lite/name": ["United Nations"],
          "http://bibfra.me/vocab/lite/date": ["1945"]
        }
        """));

    assertThat(generatorService.getLabel(organization)).isEqualTo("United Nations, 1945");
  }

  @Test
  @SneakyThrows
  void getLabel_nameOnly() {
    var organization = new Resource()
      .setId(1L)
      .addType(ORGANIZATION)
      .setDoc(mapper.readTree("""
        {
          "http://bibfra.me/vocab/lite/name": ["United Nations"]
        }
        """));

    assertThat(generatorService.getLabel(organization)).isEqualTo("United Nations");
  }

  @Test
  @SneakyThrows
  void getLabel_fallsBackToResourceLabel_whenNoProperties() {
    var organization = new Resource()
      .setId(1L)
      .addType(ORGANIZATION)
      .setLabel("fallback")
      .setDoc(mapper.readTree("{}"));

    assertThat(generatorService.getLabel(organization)).isEqualTo("fallback");
  }
}
