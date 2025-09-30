package org.folio.ld.dictionary.util;

import static org.assertj.core.api.Assertions.assertThat;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import lombok.SneakyThrows;
import org.folio.ld.dictionary.PredicateDictionary;
import org.folio.ld.dictionary.PropertyDictionary;
import org.folio.ld.dictionary.ResourceTypeDictionary;
import org.folio.ld.dictionary.model.Resource;
import org.folio.ld.dictionary.model.ResourceEdge;
import org.junit.jupiter.api.Test;

class ResourceViewDeserializerTest {

  @Test
  @SneakyThrows
  void deserializeResourceView() {
    // given
    var input = this.getClass().getResourceAsStream("/resource_view.json");

    // when
    var om = new ObjectMapper();
    var sm = new SimpleModule();
    sm.addDeserializer(Resource.class, new ResourceViewDeserializer());
    om.registerModule(sm);

    var actualResource = om.readValue(input, Resource.class);

    // then
    var expectedResource = createResource();
    assertThat(actualResource).isEqualTo(expectedResource);
    validate(expectedResource, actualResource);
    validate(expectedResource.getOutgoingEdges().iterator().next().getTarget(),
      actualResource.getOutgoingEdges().iterator().next().getTarget());
  }

  private Resource createResource() {
    var resource = new Resource()
      .setId(832794024323664921L)
      .setLabel("Resilience Interventions for Youth in Diverse Populations")
      .addType(ResourceTypeDictionary.WORK);
    var doc = JsonNodeFactory.instance.objectNode();
    doc.put(PropertyDictionary.LABEL.getValue(), "Resilience Interventions for Youth in Diverse Populations");
    resource.setDoc(doc);
    var title = new Resource()
      .setId(-3971230252524682729L)
      .setLabel("Resilience Interventions for Youth in Diverse Populations")
      .addType(ResourceTypeDictionary.TITLE);
    var titleDoc = JsonNodeFactory.instance.objectNode();
    titleDoc.put(PropertyDictionary.MAIN_TITLE.getValue(), "Resilience Interventions for Youth in Diverse Populations");
    title.setDoc(titleDoc);
    resource.addOutgoingEdge(new ResourceEdge(
      resource, title, PredicateDictionary.TITLE));

    return resource;
  }

  private void validate(Resource expected, Resource actual) {
    assertThat(actual.getId()).isEqualTo(expected.getId());
    assertThat(actual.getLabel()).isEqualTo(expected.getLabel());
    assertThat(actual.getTypes().iterator().next().getUri()).isEqualTo(expected.getTypes().iterator().next().getUri());

    if (expected.getDoc() == null) {
      assertThat(actual.getDoc()).isNull();
    } else {
      assertThat(actual.getDoc()).hasSize(expected.getDoc().size());
    }

    assertThat(actual.getOutgoingEdges()).hasSize(expected.getOutgoingEdges().size());
  }
}
