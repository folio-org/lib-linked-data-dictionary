package org.folio.ld.dictionary.model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.folio.ld.dictionary.PredicateDictionary.TITLE;
import static org.folio.ld.dictionary.test.TestUtil.OBJECT_MAPPER;
import static org.folio.ld.dictionary.test.TestUtil.getParallelTitle;
import static org.folio.ld.dictionary.test.TestUtil.getPrimaryTitle;
import static org.folio.ld.dictionary.test.TestUtil.getVariantTitle;
import static org.folio.ld.dictionary.test.TestUtil.loadResourceAsString;

import com.fasterxml.jackson.core.JsonProcessingException;
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

  @Test
  void addTitle_shouldWorkCorrectly_withEmptyDoc() throws JsonProcessingException {
    // given
    var resource = new Resource();
    var primaryTitle = getPrimaryTitle();
    var parallelTitle = getParallelTitle();
    var variantTitle = getVariantTitle();

    // when
    resource.addTitle(primaryTitle, OBJECT_MAPPER);
    resource.addTitle(parallelTitle, OBJECT_MAPPER);
    resource.addTitle(variantTitle, OBJECT_MAPPER);

    // then
    assertThat(resource.getDoc()).isNotEmpty();
    assertThat(resource.getDoc().has(TITLE.getUri())).isTrue();
    assertThat(resource.getDoc().get(TITLE.getUri())).hasSize(3);
    assertThat(resource.getDoc().get(TITLE.getUri()).get(0).toString())
      .isEqualTo(loadResourceAsString("primary_title.json"));
    assertThat(resource.getDoc().get(TITLE.getUri()).get(1).toString())
      .isEqualTo(loadResourceAsString("parallel_title.json"));
    assertThat(resource.getDoc().get(TITLE.getUri()).get(2).toString())
      .isEqualTo(loadResourceAsString("variant_title.json"));
  }

  @Test
  void addTitle_shouldWorkCorrectly_withNotEmptyDoc() throws JsonProcessingException {
    // given
    var resource = new Resource().setDoc(OBJECT_MAPPER.createObjectNode());
    var someProperty = "some property";
    var someValue = "some value";
    resource.getDoc().put(someProperty, someValue);
    var primaryTitle = getPrimaryTitle();
    var parallelTitle = getParallelTitle();
    var variantTitle = getVariantTitle();

    // when
    resource.addTitle(primaryTitle, OBJECT_MAPPER);
    resource.addTitle(parallelTitle, OBJECT_MAPPER);
    resource.addTitle(variantTitle, OBJECT_MAPPER);

    // then
    assertThat(resource.getDoc()).isNotEmpty();
    assertThat(resource.getDoc().has(TITLE.getUri())).isTrue();
    assertThat(resource.getDoc().get(TITLE.getUri())).hasSize(3);
    assertThat(resource.getDoc().get(TITLE.getUri()).get(0).toString())
      .isEqualTo(loadResourceAsString("primary_title.json"));
    assertThat(resource.getDoc().get(TITLE.getUri()).get(1).toString())
      .isEqualTo(loadResourceAsString("parallel_title.json"));
    assertThat(resource.getDoc().get(TITLE.getUri()).get(2).toString())
      .isEqualTo(loadResourceAsString("variant_title.json"));
    assertThat(resource.getDoc().get(someProperty).textValue()).isEqualTo(someValue);
  }

  @Test
  void getTitles_shouldReturnAllTitles() throws JsonProcessingException {
    // given
    var resource = new Resource().setDoc(OBJECT_MAPPER.createObjectNode());
    var someProperty = "some property";
    var someValue = "some value";
    resource.getDoc().put(someProperty, someValue);
    var titleArrayNode = OBJECT_MAPPER.createArrayNode();
    var primaryTitle = getPrimaryTitle();
    titleArrayNode.add(OBJECT_MAPPER.valueToTree(primaryTitle));
    var parallelTitle = getParallelTitle();
    titleArrayNode.add(OBJECT_MAPPER.valueToTree(parallelTitle));
    var variantTitle = getVariantTitle();
    titleArrayNode.add(OBJECT_MAPPER.valueToTree(variantTitle));
    resource.getDoc().set(TITLE.getUri(), titleArrayNode);

    // when
    var titles = resource.getTitles(OBJECT_MAPPER);

    // then
    assertThat(titles).hasSize(3);
    assertThat(titles.get(0)).isEqualTo(primaryTitle);
    assertThat(titles.get(1)).isEqualTo(parallelTitle);
    assertThat(titles.get(2)).isEqualTo(variantTitle);
    assertThat(resource.getDoc().get(someProperty).textValue()).isEqualTo(someValue);
  }

  @Test
  void getTitles_shouldReturnEmptyList_IfNoTitles() throws JsonProcessingException {
    // given
    var resource = new Resource().setDoc(OBJECT_MAPPER.createObjectNode());
    var someProperty = "some property";
    var someValue = "some value";
    resource.getDoc().put(someProperty, someValue);

    // when
    var titles = resource.getTitles(OBJECT_MAPPER);

    // then
    assertThat(titles).isEmpty();
    assertThat(resource.getDoc().get(someProperty).textValue()).isEqualTo(someValue);
  }

  @Test
  void getTitles_shouldReturnEmptyList_IfNoDoc() throws JsonProcessingException {
    // given
    var resource = new Resource();

    // when
    var titles = resource.getTitles(OBJECT_MAPPER);

    // then
    assertThat(titles).isEmpty();
  }

  @Test
  void getDocWithoutTitles_shouldReturnDocAsIs_ifNoDoc() {
    // given
    var resource = new Resource();

    // when
    var docWithoutTitles = resource.getDocWithoutTitles();

    // then
    assertThat(docWithoutTitles).isNull();
  }

  @Test
  void getDocWithoutTitles_shouldReturnDocAsIs_ifEmptyDoc() {
    // given
    var resource = new Resource().setDoc(OBJECT_MAPPER.createObjectNode());

    // when
    var docWithoutTitles = resource.getDocWithoutTitles();

    // then
    assertThat(docWithoutTitles).isEqualTo(resource.getDoc());
  }

  @Test
  void getDocWithoutTitles_shouldReturnDocAsIs_ifNoTitles() {
    // given
    var resource = new Resource().setDoc(OBJECT_MAPPER.createObjectNode());
    var someProperty = "some property";
    var someValue = "some value";
    resource.getDoc().put(someProperty, someValue);

    // when
    var docWithoutTitles = resource.getDocWithoutTitles();

    // then
    assertThat(docWithoutTitles).isEqualTo(resource.getDoc());
  }

  @Test
  void getDocWithoutTitles_shouldReturnDocWithNoTitles_ifThereAreTitles() {
    // given
    var resource = new Resource().setDoc(OBJECT_MAPPER.createObjectNode());
    var someProperty = "some property";
    var someValue = "some value";
    resource.getDoc().put(someProperty, someValue);
    var titleArrayNode = OBJECT_MAPPER.createArrayNode();
    var primaryTitle = getPrimaryTitle();
    titleArrayNode.add(OBJECT_MAPPER.valueToTree(primaryTitle));
    var parallelTitle = getParallelTitle();
    titleArrayNode.add(OBJECT_MAPPER.valueToTree(parallelTitle));
    var variantTitle = getVariantTitle();
    titleArrayNode.add(OBJECT_MAPPER.valueToTree(variantTitle));
    resource.getDoc().set(TITLE.getUri(), titleArrayNode);

    // when
    var docWithoutTitles = resource.getDocWithoutTitles();

    // then
    assertThat(docWithoutTitles).hasSize(1);
    assertThat(docWithoutTitles.get(someProperty).textValue()).isEqualTo(someValue);
  }
}
