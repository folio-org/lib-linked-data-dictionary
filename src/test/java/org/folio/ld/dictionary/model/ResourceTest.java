package org.folio.ld.dictionary.model;

import static org.assertj.core.api.Assertions.assertThat;

import org.folio.ld.dictionary.PredicateDictionary;
import org.folio.ld.dictionary.ResourceTypeDictionary;
import org.junit.jupiter.api.Test;

class ResourceTest {

  @Test
  void addType_shouldAddType() {
    // given
    var resource = new Resource();

    // when
    resource.addType(ResourceTypeDictionary.ID_LCCN);
    resource.addType(ResourceTypeDictionary.IDENTIFIER);

    // then
    var typeNames = resource.getTypeNames();
    assertThat(typeNames).containsExactlyInAnyOrder("ID_LCCN", "IDENTIFIER");
  }


  @Test
  void addOutgoingEdge_shouldAddOutgoingEdge() {
    // given
    var sourceResource = new Resource();
    var targetResource1 = new Resource();
    var targetResource2 = new Resource();

    var edge1 = new ResourceEdge(sourceResource, targetResource1, PredicateDictionary.AUTHOR);
    var edge2 = new ResourceEdge(sourceResource, targetResource2, PredicateDictionary.ARTIST);

    // when
    sourceResource.addOutgoingEdge(edge1);
    sourceResource.addOutgoingEdge(edge2);

    // then
    assertThat(sourceResource.getOutgoingEdges())
      .containsExactlyInAnyOrder(edge1, edge2);
  }
}
