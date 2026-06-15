package org.folio.ld.dictionary.label;

import static org.assertj.core.api.Assertions.assertThat;
import static org.folio.ld.dictionary.ResourceTypeDictionary.PERSON;
import static org.folio.ld.dictionary.ResourceTypeDictionary.TEMPORAL;

import lombok.SneakyThrows;
import org.folio.ld.dictionary.label.generators.TemporalLabelGenerator;
import org.folio.ld.dictionary.model.Resource;
import org.junit.jupiter.api.Test;
import tools.jackson.databind.json.JsonMapper;

class TemporalLabelGeneratorTest {
  private final JsonMapper mapper = new JsonMapper();
  private final LabelGeneratorService generatorService = new LabelGeneratorService();
  private final TemporalLabelGenerator generator = new TemporalLabelGenerator();

  @Test
  void supports_returnsTrue_forTemporalType() {
    var temporal = new Resource().setId(1L).addType(TEMPORAL);

    assertThat(generator.supports(temporal)).isTrue();
  }

  @Test
  void supports_returnsFalse_forNonTemporalType() {
    var person = new Resource().setId(1L).addType(PERSON);

    assertThat(generator.supports(person)).isFalse();
  }

  @Test
  @SneakyThrows
  void getLabel_returnsNameProperty() {
    var temporal = new Resource()
      .setId(1L)
      .addType(TEMPORAL)
      .setDoc(mapper.readTree("""
        {
          "http://bibfra.me/vocab/lite/name": ["20th century"]
        }
        """));

    assertThat(generatorService.getLabel(temporal)).isEqualTo("20th century");
  }

  @Test
  @SneakyThrows
  void getLabel_fallsBackToResourceLabel_whenNoProperties() {
    var temporal = new Resource()
      .setId(1L)
      .addType(TEMPORAL)
      .setLabel("fallback")
      .setDoc(mapper.readTree("{}"));

    assertThat(generatorService.getLabel(temporal)).isEqualTo("fallback");
  }
}
