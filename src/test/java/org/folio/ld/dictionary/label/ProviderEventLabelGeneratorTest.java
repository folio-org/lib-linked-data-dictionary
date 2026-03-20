package org.folio.ld.dictionary.label;

import static org.assertj.core.api.Assertions.assertThat;
import static org.folio.ld.dictionary.ResourceTypeDictionary.PROVIDER_EVENT;

import lombok.SneakyThrows;
import org.folio.ld.dictionary.model.Resource;
import org.junit.jupiter.api.Test;
import tools.jackson.databind.json.JsonMapper;

class ProviderEventLabelGeneratorTest {
  private final JsonMapper mapper = new JsonMapper();
  private final LabelGeneratorService generator = new LabelGeneratorService();

  @Test
  @SneakyThrows
  void getLabel_joinsNamePlaceAndDateWithCommas() {
    var providerEvent = new Resource()
      .setId(1L)
      .addType(PROVIDER_EVENT)
      .setDoc(mapper.readTree("""
        {
          "http://bibfra.me/vocab/lite/name": ["Publisher"],
          "http://bibfra.me/vocab/lite/place": ["Boston", "MA"],
          "http://bibfra.me/vocab/lite/date": ["2024"]
        }
        """));

    assertThat(generator.getLabel(providerEvent)).isEqualTo("Publisher, Boston MA, 2024");
  }

  @Test
  @SneakyThrows
  void getLabel_skipsMissingValues() {
    var providerEvent = new Resource()
      .setId(1L)
      .addType(PROVIDER_EVENT)
      .setDoc(mapper.readTree("""
        {
          "http://bibfra.me/vocab/lite/name": ["Manufacturer"],
          "http://bibfra.me/vocab/lite/date": ["2024"]
        }
        """));

    assertThat(generator.getLabel(providerEvent)).isEqualTo("Manufacturer, 2024");
  }

  @Test
  @SneakyThrows
  void getLabel_usesProviderDateWhenDateIsMissing() {
    var providerEvent = new Resource()
      .setId(1L)
      .addType(PROVIDER_EVENT)
      .setDoc(mapper.readTree("""
        {
          "http://bibfra.me/vocab/lite/name": ["Distributor"],
          "http://bibfra.me/vocab/lite/place": ["Boston", "MA"],
          "http://bibfra.me/vocab/lite/providerDate": ["2025"]
        }
        """));

    assertThat(generator.getLabel(providerEvent)).isEqualTo("Distributor, Boston MA, 2025");
  }
}
