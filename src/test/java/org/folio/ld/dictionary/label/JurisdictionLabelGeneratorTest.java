package org.folio.ld.dictionary.label;

import static org.assertj.core.api.Assertions.assertThat;
import static org.folio.ld.dictionary.ResourceTypeDictionary.JURISDICTION;
import static org.folio.ld.dictionary.ResourceTypeDictionary.PERSON;

import lombok.SneakyThrows;
import org.folio.ld.dictionary.label.generators.JurisdictionLabelGenerator;
import org.folio.ld.dictionary.model.Resource;
import org.junit.jupiter.api.Test;
import tools.jackson.databind.json.JsonMapper;

class JurisdictionLabelGeneratorTest {
  private final JsonMapper mapper = new JsonMapper();
  private final LabelGeneratorService generatorService = new LabelGeneratorService();
  private final JurisdictionLabelGenerator generator = new JurisdictionLabelGenerator();

  @Test
  void supports_returnsTrue_forJurisdictionType() {
    var jurisdiction = new Resource().setId(1L).addType(JURISDICTION);

    assertThat(generator.supports(jurisdiction)).isTrue();
  }

  @Test
  void supports_returnsFalse_forNonJurisdictionType() {
    var person = new Resource().setId(1L).addType(PERSON);

    assertThat(generator.supports(person)).isFalse();
  }

  @Test
  @SneakyThrows
  void getLabel_joinsAllProperties() {
    var jurisdiction = new Resource()
      .setId(1L)
      .addType(JURISDICTION)
      .setDoc(mapper.readTree("""
        {
          "http://bibfra.me/vocab/lite/name": ["United States"],
          "http://bibfra.me/vocab/library/subordinateUnit": ["Congress"],
          "http://bibfra.me/vocab/library/place": ["Washington"],
          "http://bibfra.me/vocab/lite/date": ["1990"]
        }
        """));

    assertThat(generatorService.getLabel(jurisdiction)).isEqualTo("United States, Congress, Washington, 1990");
  }

  @Test
  @SneakyThrows
  void getLabel_skipsMissingProperties() {
    var jurisdiction = new Resource()
      .setId(1L)
      .addType(JURISDICTION)
      .setDoc(mapper.readTree("""
        {
          "http://bibfra.me/vocab/lite/name": ["United States"],
          "http://bibfra.me/vocab/lite/date": ["1990"]
        }
        """));

    assertThat(generatorService.getLabel(jurisdiction)).isEqualTo("United States, 1990");
  }

  @Test
  @SneakyThrows
  void getLabel_nameOnly() {
    var jurisdiction = new Resource()
      .setId(1L)
      .addType(JURISDICTION)
      .setDoc(mapper.readTree("""
        {
          "http://bibfra.me/vocab/lite/name": ["United States"]
        }
        """));

    assertThat(generatorService.getLabel(jurisdiction)).isEqualTo("United States");
  }

  @Test
  @SneakyThrows
  void getLabel_fallsBackToResourceLabel_whenNoProperties() {
    var jurisdiction = new Resource()
      .setId(1L)
      .addType(JURISDICTION)
      .setLabel("fallback")
      .setDoc(mapper.readTree("{}"));

    assertThat(generatorService.getLabel(jurisdiction)).isEqualTo("fallback");
  }
}
