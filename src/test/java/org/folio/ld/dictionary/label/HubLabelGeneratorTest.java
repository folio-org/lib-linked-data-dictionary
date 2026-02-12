package org.folio.ld.dictionary.label;

import static org.assertj.core.api.Assertions.assertThat;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.folio.ld.dictionary.PredicateDictionary;
import org.folio.ld.dictionary.PropertyDictionary;
import org.folio.ld.dictionary.ResourceTypeDictionary;
import org.folio.ld.dictionary.model.Resource;
import org.folio.ld.dictionary.model.ResourceEdge;
import org.junit.jupiter.api.Test;

class HubLabelGeneratorTest {
  private final ObjectMapper mapper = new ObjectMapper();
  private final LabelGeneratorService generator = new LabelGeneratorService();

  @Test
  void getLabelUsesOutgoingEdgesInOrder() {
    var resource = new Resource()
      .addType(ResourceTypeDictionary.HUB);

    resource.addOutgoingEdge(edge(resource, "The Title", PredicateDictionary.TITLE));
    resource.addOutgoingEdge(edge(resource, "Creator Name", PredicateDictionary.CREATOR));
    resource.addOutgoingEdge(edge(resource, "English", PredicateDictionary.LANGUAGE));

    assertThat(generator.getLabel(resource))
      .isEqualTo("Creator Name. The Title. English");
  }

  @Test
  @SneakyThrows
  void getLabelUsesLanguageTermBeforeLanguageLabel() {
    var resource = new Resource()
      .addType(ResourceTypeDictionary.HUB);

    var language = new Resource()
      .setLabel("English Label")
      .setDoc(mapper.readTree("{ \"" + PropertyDictionary.TERM.getValue() + "\": [\"English Term\"] }"));

    resource.addOutgoingEdge(edge(resource, "The Title", PredicateDictionary.TITLE));
    resource.addOutgoingEdge(edge(resource, "Creator Name", PredicateDictionary.CREATOR));
    resource.addOutgoingEdge(new ResourceEdge(resource, language, PredicateDictionary.LANGUAGE));

    assertThat(generator.getLabel(resource))
      .isEqualTo("Creator Name. The Title. English Term");
  }

  @Test
  @SneakyThrows
  void getLabelFallsBackToLanguagePropertyWhenNoLanguageEdge() {
    var resource = new Resource()
      .addType(ResourceTypeDictionary.HUB);

    resource.addOutgoingEdge(edge(resource, "The Title", PredicateDictionary.TITLE));
    resource.addOutgoingEdge(edge(resource, "Creator Name", PredicateDictionary.CREATOR));
    resource.setDoc(mapper.readTree("{ \"http://bibfra.me/vocab/lite/language\": [\"German\"] }"));

    assertThat(generator.getLabel(resource))
      .isEqualTo("Creator Name. The Title. German");
  }

  private ResourceEdge edge(Resource source, String targetLabel, PredicateDictionary predicate) {
    var target = new Resource()
      .setLabel(targetLabel);

    return new ResourceEdge(source, target, predicate);
  }
}
