package org.folio.ld.dictionary.label;

import static org.assertj.core.api.Assertions.assertThat;
import static org.folio.ld.dictionary.PredicateDictionary.CREATOR;
import static org.folio.ld.dictionary.PredicateDictionary.LANGUAGE;
import static org.folio.ld.dictionary.PredicateDictionary.TITLE;
import static org.folio.ld.dictionary.ResourceTypeDictionary.ABBREVIATED_TITLE;
import static org.folio.ld.dictionary.ResourceTypeDictionary.HUB;
import static org.folio.ld.dictionary.ResourceTypeDictionary.JURISDICTION;
import static org.folio.ld.dictionary.ResourceTypeDictionary.LANGUAGE_CATEGORY;
import static org.folio.ld.dictionary.ResourceTypeDictionary.ORGANIZATION;
import static org.folio.ld.dictionary.ResourceTypeDictionary.PERSON;
import static org.folio.ld.dictionary.ResourceTypeDictionary.VARIANT_TITLE;

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
  void getLabelUsesOnlyPrimaryTitleFromOutgoingEdges() {
    var hub = resource(1L, HUB, "Resource Label");

    hub.addOutgoingEdge(edge(hub, TITLE, resource(2L, ABBREVIATED_TITLE, "The Abbreviated Title")));
    hub.addOutgoingEdge(edge(hub, TITLE, resource(3L, ResourceTypeDictionary.TITLE, "The Title")));
    hub.addOutgoingEdge(edge(hub, TITLE, resource(4L, VARIANT_TITLE, "The Variant Title")));
    hub.addOutgoingEdge(edge(hub, CREATOR, resource(5L, PERSON, "Creator Name")));
    hub.addOutgoingEdge(edge(hub, LANGUAGE, resource(6L, LANGUAGE_CATEGORY, "English")));

    assertThat(generator.getLabel(hub))
      .isEqualTo("Creator Name. The Title. English");
  }

  @Test
  @SneakyThrows
  void getLabelUsesLanguageTermBeforeLanguageLabel() {
    var hub = resource(1L, HUB, "Resource Label");

    var language = resource(6L, LANGUAGE_CATEGORY, "English")
      .setDoc(mapper.readTree("{ \"" + PropertyDictionary.TERM.getValue() + "\": [\"English Term\"] }"));

    hub.addOutgoingEdge(edge(hub, TITLE, resource(2L, VARIANT_TITLE, "The Variant Title")));
    hub.addOutgoingEdge(edge(hub, TITLE, resource(3L, ResourceTypeDictionary.TITLE, "The Title")));
    hub.addOutgoingEdge(edge(hub, CREATOR, resource(4L, ORGANIZATION, "Creator Name")));
    hub.addOutgoingEdge(edge(hub, LANGUAGE, language));

    assertThat(generator.getLabel(hub))
      .isEqualTo("Creator Name. The Title. English Term");
  }

  @Test
  @SneakyThrows
  void getLabelFallsBackToLanguagePropertyWhenNoLanguageEdge() {
    var hub = resource(1L, HUB, "Resource Label")
      .setDoc(mapper.readTree("{ \"http://bibfra.me/vocab/lite/language\": [\"German\"] }"));

    hub.addOutgoingEdge(edge(hub, TITLE, resource(2L, ResourceTypeDictionary.TITLE, "The Title")));
    hub.addOutgoingEdge(edge(hub, CREATOR, resource(3L, JURISDICTION, "Creator Name")));

    assertThat(generator.getLabel(hub))
      .isEqualTo("Creator Name. The Title. German");
  }

  private ResourceEdge edge(Resource source,
                            PredicateDictionary predicate,
                            Resource target) {
    return new ResourceEdge(source, target, predicate);
  }

  private Resource resource(Long id, ResourceTypeDictionary type, String label) {
    return new Resource()
      .setId(id)
      .addType(type)
      .setLabel(label);
  }
}
