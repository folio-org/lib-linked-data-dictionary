package org.folio.ld.dictionary.label;

import static org.assertj.core.api.Assertions.assertThat;
import static org.folio.ld.dictionary.ResourceTypeDictionary.MEETING;
import static org.folio.ld.dictionary.ResourceTypeDictionary.PERSON;

import lombok.SneakyThrows;
import org.folio.ld.dictionary.label.generators.MeetingLabelGenerator;
import org.folio.ld.dictionary.model.Resource;
import org.junit.jupiter.api.Test;
import tools.jackson.databind.json.JsonMapper;

class MeetingLabelGeneratorTest {
  private final JsonMapper mapper = new JsonMapper();
  private final LabelGeneratorService generatorService = new LabelGeneratorService();
  private final MeetingLabelGenerator generator = new MeetingLabelGenerator();

  @Test
  void supports_returnsTrue_forMeetingType() {
    var meeting = new Resource().setId(1L).addType(MEETING);

    assertThat(generator.supports(meeting)).isTrue();
  }

  @Test
  void supports_returnsFalse_forNonMeetingType() {
    var person = new Resource().setId(1L).addType(PERSON);

    assertThat(generator.supports(person)).isFalse();
  }

  @Test
  @SneakyThrows
  void getLabel_joinsAllProperties() {
    var meeting = new Resource()
      .setId(1L)
      .addType(MEETING)
      .setDoc(mapper.readTree("""
        {
          "http://bibfra.me/vocab/lite/name": ["World Economic Forum"],
          "http://bibfra.me/vocab/lite/date": ["2024"],
          "http://bibfra.me/vocab/library/place": ["Davos"],
          "http://bibfra.me/vocab/library/subordinateUnit": ["Panel on AI"]
        }
        """));

    assertThat(generatorService.getLabel(meeting)).isEqualTo("World Economic Forum, 2024, Davos, Panel on AI");
  }

  @Test
  @SneakyThrows
  void getLabel_skipsMissingProperties() {
    var meeting = new Resource()
      .setId(1L)
      .addType(MEETING)
      .setDoc(mapper.readTree("""
        {
          "http://bibfra.me/vocab/lite/name": ["World Economic Forum"],
          "http://bibfra.me/vocab/library/place": ["Davos"]
        }
        """));

    assertThat(generatorService.getLabel(meeting)).isEqualTo("World Economic Forum, Davos");
  }

  @Test
  @SneakyThrows
  void getLabel_nameOnly() {
    var meeting = new Resource()
      .setId(1L)
      .addType(MEETING)
      .setDoc(mapper.readTree("""
        {
          "http://bibfra.me/vocab/lite/name": ["World Economic Forum"]
        }
        """));

    assertThat(generatorService.getLabel(meeting)).isEqualTo("World Economic Forum");
  }

  @Test
  @SneakyThrows
  void getLabel_fallsBackToResourceLabel_whenNoProperties() {
    var meeting = new Resource()
      .setId(1L)
      .addType(MEETING)
      .setLabel("fallback")
      .setDoc(mapper.readTree("{}"));

    assertThat(generatorService.getLabel(meeting)).isEqualTo("fallback");
  }
}
