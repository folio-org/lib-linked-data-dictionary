package org.folio.ld.dictionary.label;

import static org.assertj.core.api.Assertions.assertThat;
import static org.folio.ld.dictionary.PredicateDictionary.FOCUS;
import static org.folio.ld.dictionary.PredicateDictionary.SUB_FOCUS;
import static org.folio.ld.dictionary.ResourceTypeDictionary.CONCEPT;
import static org.folio.ld.dictionary.ResourceTypeDictionary.FORM;
import static org.folio.ld.dictionary.ResourceTypeDictionary.INSTANCE;
import static org.folio.ld.dictionary.ResourceTypeDictionary.PLACE;
import static org.folio.ld.dictionary.ResourceTypeDictionary.TEMPORAL;
import static org.folio.ld.dictionary.ResourceTypeDictionary.TOPIC;

import org.folio.ld.dictionary.model.Resource;
import org.folio.ld.dictionary.model.ResourceEdge;
import org.junit.jupiter.api.Test;

class ConceptLabelGeneratorTest {
  private final LabelGeneratorService generator = new LabelGeneratorService();

  @Test
  void generateLabel_shouldReturnDefaultLabelForUnsupportedType() {
    // given
    var resource = new Resource()
      .setId(1L)
      .addType(INSTANCE)
      .setLabel("Instance Label");

    // when
    var label = generator.getLabel(resource);

    // then
    assertThat(label).isEqualTo("Instance Label");
  }

  @Test
  void generateLabel_shouldGenerateLabelForConceptWithFocus() {
    // given
    var focusResource = new Resource()
      .setId(2L)
      .setLabel("Focus Label");

    var concept = new Resource()
      .setId(1L)
      .addType(CONCEPT);

    var focusEdge = new ResourceEdge(concept, focusResource, FOCUS);
    concept.addOutgoingEdge(focusEdge);

    // when
    var label = generator.getLabel(concept);

    // then
    assertThat(label).isEqualTo("Focus Label");
  }

  @Test
  void generateLabel_shouldGenerateLabelForConceptWithSubFocus() {
    // given
    var subFocusResource = new Resource()
      .setId(2L)
      .setLabel("SubFocus Label");

    var concept = new Resource()
      .setId(1L)
      .addType(CONCEPT);

    var subFocusEdge = new ResourceEdge(concept, subFocusResource, SUB_FOCUS);
    concept.addOutgoingEdge(subFocusEdge);

    // when
    var label = generator.getLabel(concept);

    // then
    assertThat(label).isEqualTo("SubFocus Label");
  }

  @Test
  void generateLabel_shouldGenerateLabelForConceptWithFocusAndSubFocus() {
    // given
    var focusResource = new Resource()
      .setId(2L)
      .setLabel("Focus Label");

    var subFocusResource = new Resource()
      .setId(3L)
      .setLabel("SubFocus Label");

    var concept = new Resource()
      .setId(1L)
      .addType(CONCEPT);

    var focusEdge = new ResourceEdge(concept, focusResource, FOCUS);
    var subFocusEdge = new ResourceEdge(concept, subFocusResource, SUB_FOCUS);
    concept.addOutgoingEdge(focusEdge);
    concept.addOutgoingEdge(subFocusEdge);

    // when
    var label = generator.getLabel(concept);

    // then
    assertThat(label).isEqualTo("Focus Label -- SubFocus Label");
  }

  @Test
  void generateLabel_shouldReturnEmptyStringForConceptWithNoFocusOrSubFocus() {
    // given
    var concept = new Resource()
      .setId(1L)
      .addType(CONCEPT);

    // when
    var label = generator.getLabel(concept);

    // then
    assertThat(label).isEmpty();
  }

  @Test
  void generateLabel_shouldGenerateLabelForConceptWithFocusAndMultipleSortedSubFocus() {
    // given
    var focusResource1 = new Resource()
      .setId(2L)
      .setLabel("Focus 1");

    var focusResource2 = new Resource()
      .setId(3L)
      .setLabel("Focus 2");

    var subFocusResource1 = new Resource()
      .setId(4L)
      .setLabel("zebra")
      .addType(FORM);

    var subFocusResource2 = new Resource()
      .setId(5L)
      .setLabel("Beta")
      .addType(TEMPORAL);

    var subFocusResource3 = new Resource()
      .setId(6L)
      .setLabel("alpha")
      .addType(TEMPORAL);

    var subFocusResource4 = new Resource()
      .setId(7L)
      .setLabel("topic-a")
      .addType(TOPIC);

    var subFocusResource5 = new Resource()
      .setId(8L)
      .setLabel("place-a")
      .addType(PLACE);

    var concept = new Resource()
      .setId(1L)
      .addType(CONCEPT);

    concept.addOutgoingEdge(new ResourceEdge(concept, subFocusResource5, SUB_FOCUS));
    concept.addOutgoingEdge(new ResourceEdge(concept, subFocusResource2, SUB_FOCUS));
    concept.addOutgoingEdge(new ResourceEdge(concept, focusResource2, FOCUS));
    concept.addOutgoingEdge(new ResourceEdge(concept, subFocusResource3, SUB_FOCUS));
    concept.addOutgoingEdge(new ResourceEdge(concept, focusResource1, FOCUS));
    concept.addOutgoingEdge(new ResourceEdge(concept, subFocusResource1, SUB_FOCUS));
    concept.addOutgoingEdge(new ResourceEdge(concept, subFocusResource4, SUB_FOCUS));

    // when
    var label = generator.getLabel(concept);

    // then
    assertThat(label).isEqualTo("Focus 1 -- Focus 2 -- topic-a -- place-a -- alpha -- Beta -- zebra");
  }
}
