package org.folio.ld.dictionary.label;

import static org.assertj.core.api.Assertions.assertThat;
import static org.folio.ld.dictionary.ResourceTypeDictionary.TITLE;

import lombok.SneakyThrows;
import org.folio.ld.dictionary.model.Resource;
import org.junit.jupiter.api.Test;
import tools.jackson.databind.json.JsonMapper;

class TitleLabelGeneratorTest {
  private final JsonMapper mapper = new JsonMapper();
  private final LabelGeneratorService generator = new LabelGeneratorService();

  @Test
  @SneakyThrows
  void getLabelJoinsAvailableTitlePropertiesWithSpace() {
    var title = new Resource()
      .setId(1L)
      .addType(TITLE)
      .setDoc(mapper.readTree("""
        {
          "http://bibfra.me/vocab/library/mainTitle": ["Main"],
          "http://bibfra.me/vocab/library/subTitle": ["Subtitle"],
          "http://bibfra.me/vocab/library/partNumber": ["Part 1"],
          "http://bibfra.me/vocab/library/partName": ["Part Name"]
        }
        """));

    assertThat(generator.getLabel(title)).isEqualTo("Main Subtitle Part 1 Part Name");
  }

  @Test
  @SneakyThrows
  void getLabelSkipsMissingPropertiesWithoutExtraSpaces() {
    var title = new Resource()
      .setId(1L)
      .addType(TITLE)
      .setDoc(mapper.readTree("""
        {
          "http://bibfra.me/vocab/library/mainTitle": ["Main"],
          "http://bibfra.me/vocab/library/partName": ["Part Name"]
        }
        """));

    assertThat(generator.getLabel(title)).isEqualTo("Main Part Name");
  }
}
