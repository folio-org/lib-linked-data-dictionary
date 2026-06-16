package org.folio.ld.dictionary.label;

import static org.assertj.core.api.Assertions.assertThat;
import static org.folio.ld.dictionary.ResourceTypeDictionary.FORM;
import static org.folio.ld.dictionary.ResourceTypeDictionary.PERSON;

import lombok.SneakyThrows;
import org.folio.ld.dictionary.label.generators.FormLabelGenerator;
import org.folio.ld.dictionary.model.Resource;
import org.junit.jupiter.api.Test;
import tools.jackson.databind.json.JsonMapper;

class FormLabelGeneratorTest {
  private final JsonMapper mapper = new JsonMapper();
  private final LabelGeneratorService generatorService = new LabelGeneratorService();
  private final FormLabelGenerator generator = new FormLabelGenerator();

  @Test
  void supports_returnsTrue_forFormType() {
    var form = new Resource().setId(1L).addType(FORM);

    assertThat(generator.supports(form)).isTrue();
  }

  @Test
  void supports_returnsFalse_forNonFormType() {
    var person = new Resource().setId(1L).addType(PERSON);

    assertThat(generator.supports(person)).isFalse();
  }

  @Test
  @SneakyThrows
  void getLabel_returnsNameProperty() {
    var form = new Resource()
      .setId(1L)
      .addType(FORM)
      .setDoc(mapper.readTree("""
        {
          "http://bibfra.me/vocab/lite/name": ["Poetry"]
        }
        """));

    assertThat(generatorService.getLabel(form)).isEqualTo("Poetry");
  }

  @Test
  @SneakyThrows
  void getLabel_fallsBackToResourceLabel_whenNoProperties() {
    var form = new Resource()
      .setId(1L)
      .addType(FORM)
      .setLabel("fallback")
      .setDoc(mapper.readTree("{}"));

    assertThat(generatorService.getLabel(form)).isEqualTo("fallback");
  }
}
