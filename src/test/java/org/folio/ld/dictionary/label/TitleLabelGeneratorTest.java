package org.folio.ld.dictionary.label;

import static org.assertj.core.api.Assertions.assertThat;
import static org.folio.ld.dictionary.ResourceTypeDictionary.ABBREVIATED_TITLE;
import static org.folio.ld.dictionary.ResourceTypeDictionary.PARALLEL_TITLE;
import static org.folio.ld.dictionary.ResourceTypeDictionary.TITLE;
import static org.folio.ld.dictionary.ResourceTypeDictionary.VARIANT_TITLE;

import java.util.stream.Stream;
import lombok.SneakyThrows;
import org.folio.ld.dictionary.ResourceTypeDictionary;
import org.folio.ld.dictionary.model.Resource;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import tools.jackson.databind.json.JsonMapper;

class TitleLabelGeneratorTest {
  private final JsonMapper mapper = new JsonMapper();
  private final LabelGeneratorService generator = new LabelGeneratorService();

  @ParameterizedTest
  @MethodSource("supportedTitleTypes")
  @SneakyThrows
  void getLabel_joinsAvailableTitleProperties(ResourceTypeDictionary type) {
    var title = new Resource()
      .setId(1L)
      .addType(type)
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
  void getLabel_skipsMissingProperties() {
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

  private static Stream<ResourceTypeDictionary> supportedTitleTypes() {
    return Stream.of(TITLE, VARIANT_TITLE, PARALLEL_TITLE, ABBREVIATED_TITLE);
  }
}
