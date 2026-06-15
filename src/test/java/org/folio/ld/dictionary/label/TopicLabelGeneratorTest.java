package org.folio.ld.dictionary.label;

import static org.assertj.core.api.Assertions.assertThat;
import static org.folio.ld.dictionary.ResourceTypeDictionary.PERSON;
import static org.folio.ld.dictionary.ResourceTypeDictionary.TOPIC;

import lombok.SneakyThrows;
import org.folio.ld.dictionary.label.generators.TopicLabelGenerator;
import org.folio.ld.dictionary.model.Resource;
import org.junit.jupiter.api.Test;
import tools.jackson.databind.json.JsonMapper;

class TopicLabelGeneratorTest {
  private final JsonMapper mapper = new JsonMapper();
  private final LabelGeneratorService generatorService = new LabelGeneratorService();
  private final TopicLabelGenerator generator = new TopicLabelGenerator();

  @Test
  void supports_returnsTrue_forTopicType() {
    var topic = new Resource().setId(1L).addType(TOPIC);

    assertThat(generator.supports(topic)).isTrue();
  }

  @Test
  void supports_returnsFalse_forNonTopicType() {
    var person = new Resource().setId(1L).addType(PERSON);

    assertThat(generator.supports(person)).isFalse();
  }

  @Test
  @SneakyThrows
  void getLabel_returnsNameProperty() {
    var topic = new Resource()
      .setId(1L)
      .addType(TOPIC)
      .setDoc(mapper.readTree("""
        {
          "http://bibfra.me/vocab/lite/name": ["Machine learning"]
        }
        """));

    assertThat(generatorService.getLabel(topic)).isEqualTo("Machine learning");
  }

  @Test
  @SneakyThrows
  void getLabel_fallsBackToResourceLabel_whenNoProperties() {
    var topic = new Resource()
      .setId(1L)
      .addType(TOPIC)
      .setLabel("fallback")
      .setDoc(mapper.readTree("{}"));

    assertThat(generatorService.getLabel(topic)).isEqualTo("fallback");
  }
}
