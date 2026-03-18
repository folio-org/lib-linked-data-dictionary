package org.folio.ld.dictionary.label;

import static org.assertj.core.api.Assertions.assertThat;
import static org.folio.ld.dictionary.ResourceTypeDictionary.DISSERTATION;

import lombok.SneakyThrows;
import org.folio.ld.dictionary.model.Resource;
import org.junit.jupiter.api.Test;
import tools.jackson.databind.json.JsonMapper;

class DissertationLabelGeneratorTest {
  private final JsonMapper mapper = new JsonMapper();
  private final LabelGeneratorService generator = new LabelGeneratorService();

  @Test
  @SneakyThrows
  void getLabel_joinsConfiguredProperties() {
    var dissertation = new Resource()
      .setId(1L)
      .addType(DISSERTATION)
      .setDoc(mapper.readTree("""
        {
          "http://bibfra.me/vocab/lite/note": ["Revision"],
          "http://bibfra.me/vocab/library/degree": ["PhD"],
          "http://bibfra.me/vocab/lite/date": ["2024"]
        }
        """));

    assertThat(generator.getLabel(dissertation)).isEqualTo("Revision PhD 2024");
  }

  @Test
  @SneakyThrows
  void getLabel_skipsMissingProperties() {
    var dissertation = new Resource()
      .setId(1L)
      .addType(DISSERTATION)
      .setDoc(mapper.readTree("""
        {
          "http://bibfra.me/vocab/library/degree": ["PhD"],
          "http://bibfra.me/vocab/lite/date": ["2024"]
        }
        """));

    assertThat(generator.getLabel(dissertation)).isEqualTo("PhD 2024");
  }
}
