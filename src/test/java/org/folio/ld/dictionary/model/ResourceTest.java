package org.folio.ld.dictionary.model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.folio.ld.dictionary.PropertyDictionary.DIMENSIONS;
import static org.folio.ld.dictionary.PropertyDictionary.MAIN_TITLE;
import static org.folio.ld.dictionary.ResourceTypeDictionary.IDENTIFIER;
import static org.folio.ld.dictionary.ResourceTypeDictionary.ID_LCCN;
import static org.folio.ld.dictionary.ResourceTypeDictionary.INSTANCE;
import static org.folio.ld.dictionary.ResourceTypeDictionary.TITLE;
import static org.folio.ld.dictionary.ResourceTypeDictionary.WORK;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.folio.ld.dictionary.PredicateDictionary;
import org.junit.jupiter.api.Test;

class ResourceTest {

  @Test
  void addType_shouldAddType() {
    // given
    var resource = new Resource();

    // when
    resource.addType(ID_LCCN);
    resource.addType(IDENTIFIER);

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

  @Test
  void isOfType_shouldReturnTrueForMatchingType() {
    // given
    var resource = new Resource();
    resource.addType(WORK);

    // when
    boolean isOfType = resource.isOfType(WORK);

    // then
    assertThat(isOfType).isTrue();
  }

  @Test
  void isOfType_shouldReturnFalseForNonMatchingType() {
    // given
    var resource = new Resource();
    resource.addType(WORK);

    // when
    boolean isOfType = resource.isOfType(IDENTIFIER);

    // then
    assertThat(isOfType).isFalse();
  }

  @Test
  void isOfType_shouldReturnFalseForNoTypes() {
    // given
    var resource = new Resource();

    // when
    boolean isOfType = resource.isOfType(IDENTIFIER);

    // then
    assertThat(isOfType).isFalse();
  }

  @Test
  void serializationAndDeserializationTest() throws JsonProcessingException {
    // given
    var objectMapper = new ObjectMapper()
      .configure(SerializationFeature.USE_EQUALITY_FOR_OBJECT_ID, true);
    var instance = new Resource()
      .setId(123L)
      .addType(INSTANCE)
      .setLabel("INSTANCE")
      .setDoc(objectMapper.createArrayNode().add(objectMapper.createObjectNode().set(DIMENSIONS.getValue(),
        objectMapper.createArrayNode().add("1m"))));
    var title = new Resource()
      .setId(456L)
      .addType(TITLE)
      .setLabel("TITLE_LABEL")
      .setDoc(objectMapper.createArrayNode().add(objectMapper.createObjectNode().set(MAIN_TITLE.getValue(),
        objectMapper.createArrayNode().add("title"))));
    var work = new Resource()
      .setId(789L)
      .addType(WORK)
      .setLabel("WORK");
    var title2 = new Resource()
      .setId(456L)
      .addType(TITLE)
      .setLabel("TITLE_LABEL")
      .setDoc(objectMapper.createArrayNode().add(objectMapper.createObjectNode().set(MAIN_TITLE.getValue(),
        objectMapper.createArrayNode().add("title"))));
    work.addOutgoingEdge(new ResourceEdge(work, title2, PredicateDictionary.TITLE));
    instance.addOutgoingEdge(new ResourceEdge(instance, title, PredicateDictionary.TITLE));
    instance.addOutgoingEdge(new ResourceEdge(instance, work, PredicateDictionary.INSTANTIATES));

    // when
    var serialized = objectMapper.writeValueAsString(instance);
    assertThat(serialized).containsOnlyOnce("TITLE_LABEL");
    var deserializedInstance = objectMapper.readValue(serialized, Resource.class);

    // then
    assertThat(deserializedInstance.getId()).isEqualTo(instance.getId());
    assertThat(deserializedInstance.getLabel()).isEqualTo(instance.getLabel());
    assertThat(deserializedInstance.getDoc()).isEqualTo(instance.getDoc());
    assertThat(deserializedInstance.getTypes()).isEqualTo(instance.getTypes());
    assertThat(deserializedInstance.getOutgoingEdges()).hasSize(2);
    var edgeIterator = deserializedInstance.getOutgoingEdges().iterator();

    var deserializedInstanceToTitleEdge = edgeIterator.next();
    assertThat(deserializedInstanceToTitleEdge.getSource()).isSameAs(deserializedInstance);
    assertThat(deserializedInstanceToTitleEdge.getPredicate()).isEqualTo(PredicateDictionary.TITLE);

    var deserializedTitle = deserializedInstanceToTitleEdge.getTarget();
    assertThat(deserializedTitle.getId()).isEqualTo(title.getId());
    assertThat(deserializedTitle.getLabel()).isEqualTo(title.getLabel());
    assertThat(deserializedTitle.getDoc()).isEqualTo(title.getDoc());
    assertThat(deserializedTitle.getTypes()).isEqualTo(title.getTypes());

    var deserializedInstanceToWorkEdge = edgeIterator.next();
    assertThat(deserializedInstanceToWorkEdge.getSource()).isSameAs(deserializedInstance);
    assertThat(deserializedInstanceToWorkEdge.getPredicate()).isEqualTo(PredicateDictionary.INSTANTIATES);
    var deserializedWork = deserializedInstanceToWorkEdge.getTarget();
    assertThat(deserializedWork.getId()).isEqualTo(work.getId());
    assertThat(deserializedWork.getLabel()).isEqualTo(work.getLabel());
    assertThat(deserializedWork.getTypes()).isEqualTo(work.getTypes());
    assertThat(deserializedWork.getOutgoingEdges()).hasSize(1);
    var workTitleEdge = deserializedWork.getOutgoingEdges().iterator().next();
    assertThat(workTitleEdge.getSource()).isSameAs(deserializedWork);
    assertThat(workTitleEdge.getPredicate()).isEqualTo(PredicateDictionary.TITLE);
    assertThat(workTitleEdge.getTarget()).isEqualTo(deserializedTitle);
  }

  @Test
  void setLabel_shouldIgnoreNullArgument() {
    // given
    var r = new Resource();

    // when
    r.setLabel(null);

    // then
    assertThat(r.getLabel()).isEmpty();
  }
}
