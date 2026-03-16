package org.folio.ld.dictionary.label;

import static org.assertj.core.api.Assertions.assertThat;
import static org.folio.ld.dictionary.PredicateDictionary.TITLE;
import static org.folio.ld.dictionary.ResourceTypeDictionary.INSTANCE;
import static org.folio.ld.dictionary.ResourceTypeDictionary.VARIANT_TITLE;
import static org.folio.ld.dictionary.ResourceTypeDictionary.WORK;

import org.folio.ld.dictionary.PredicateDictionary;
import org.folio.ld.dictionary.ResourceTypeDictionary;
import org.folio.ld.dictionary.model.Resource;
import org.folio.ld.dictionary.model.ResourceEdge;
import org.junit.jupiter.api.Test;

class InstanceAndWorkLabelGeneratorTest {

  private final LabelGeneratorService generator = new LabelGeneratorService();

  @Test
  void getLabelForInstanceUsesFirstLabelAlphabetically() {
    var instance = resource(1L, INSTANCE, "Instance Label");

    instance.addOutgoingEdge(edge(instance, TITLE, resource(2L, ResourceTypeDictionary.TITLE, "Zoo")));
    instance.addOutgoingEdge(edge(instance, TITLE, resource(3L, ResourceTypeDictionary.TITLE, "Alpha")));

    assertThat(generator.getLabel(instance)).isEqualTo("Alpha");
  }

  @Test
  void getLabelForWorkUsesOnlyOutgoingTitleEdgesToTitleType() {
    var work = resource(1L, WORK, "Work Label");

    work.addOutgoingEdge(edge(work, TITLE, resource(2L, VARIANT_TITLE, "A Variant Title")));
    work.addOutgoingEdge(edge(work, TITLE, resource(3L, ResourceTypeDictionary.TITLE, "The Primary Title")));

    assertThat(generator.getLabel(work)).isEqualTo("The Primary Title");
  }

  @Test
  void getLabelFallsBackToResourceLabelWhenNoMatchingOutgoingTitle() {
    var work = resource(1L, WORK, "Work Label");

    work.addOutgoingEdge(edge(work, TITLE, resource(2L, VARIANT_TITLE, "A Variant Title")));

    assertThat(generator.getLabel(work)).isEqualTo("Work Label");
  }

  private ResourceEdge edge(Resource source, PredicateDictionary predicate, Resource target) {
    return new ResourceEdge(source, target, predicate);
  }

  private Resource resource(Long id, ResourceTypeDictionary type, String label) {
    return new Resource()
      .setId(id)
      .addType(type)
      .setLabel(label);
  }
}
