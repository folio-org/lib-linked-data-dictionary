package org.folio.ld.dictionary.label;

import static org.assertj.core.api.Assertions.assertThat;
import static org.folio.ld.dictionary.ResourceTypeDictionary.PERSON;
import static org.folio.ld.dictionary.ResourceTypeDictionary.PLACE;

import lombok.SneakyThrows;
import org.folio.ld.dictionary.label.generators.PlaceLabelGenerator;
import org.folio.ld.dictionary.model.Resource;
import org.junit.jupiter.api.Test;
import tools.jackson.databind.json.JsonMapper;

class PlaceLabelGeneratorTest {
  private final JsonMapper mapper = new JsonMapper();
  private final LabelGeneratorService generatorService = new LabelGeneratorService();
  private final PlaceLabelGenerator generator = new PlaceLabelGenerator();

  @Test
  void supports_returnsTrue_forPlaceType() {
    var place = new Resource().setId(1L).addType(PLACE);

    assertThat(generator.supports(place)).isTrue();
  }

  @Test
  void supports_returnsFalse_forNonPlaceType() {
    var person = new Resource().setId(1L).addType(PERSON);

    assertThat(generator.supports(person)).isFalse();
  }

  @Test
  @SneakyThrows
  void getLabel_returnsName_whenPresent() {
    var place = new Resource()
      .setId(1L)
      .addType(PLACE)
      .setDoc(mapper.readTree("""
        {
          "http://bibfra.me/vocab/lite/name": ["France"],
          "http://bibfra.me/vocab/library/code": ["fr"]
        }
        """));

    assertThat(generatorService.getLabel(place)).isEqualTo("France");
  }

  @Test
  @SneakyThrows
  void getLabel_fallsBackToCode_whenNameAbsent() {
    var place = new Resource()
      .setId(1L)
      .addType(PLACE)
      .setDoc(mapper.readTree("""
        {
          "http://bibfra.me/vocab/library/code": ["fr"]
        }
        """));

    assertThat(generatorService.getLabel(place)).isEqualTo("fr");
  }

  @Test
  @SneakyThrows
  void getLabel_fallsBackToResourceLabel_whenNoProperties() {
    var place = new Resource()
      .setId(1L)
      .addType(PLACE)
      .setLabel("fallback")
      .setDoc(mapper.readTree("{}"));

    assertThat(generatorService.getLabel(place)).isEqualTo("fallback");
  }
}
