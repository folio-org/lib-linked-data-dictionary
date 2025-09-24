package org.folio.ld.dictionary.serializer;

import static org.assertj.core.api.Assertions.assertThat;
import static org.folio.ld.dictionary.PredicateDictionary.INSTANTIATES;
import static org.folio.ld.dictionary.PredicateDictionary.TITLE;
import static org.folio.ld.dictionary.ResourceTypeDictionary.INSTANCE;
import static org.folio.ld.dictionary.ResourceTypeDictionary.WORK;
import static org.folio.ld.dictionary.model.ResourceSource.LINKED_DATA;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import java.util.Set;
import org.folio.ld.dictionary.model.FolioMetadata;
import org.folio.ld.dictionary.model.RawMarc;
import org.folio.ld.dictionary.model.Resource;
import org.folio.ld.dictionary.model.ResourceEdge;
import org.junit.jupiter.api.Test;

class ResourceSerializerTest {

  @Test
  void testResourceSerialization() throws Exception {
    var mapper = new ObjectMapper()
      .registerModule(new SimpleModule().addSerializer(Resource.class, new ResourceSerializer()));

    var resource1 = new Resource()
      .setId(1L)
      .setLabel("Main Resource")
      .setTypes(Set.of(INSTANCE))
      .setFolioMetadata(new FolioMetadata().setInventoryId("inv-123").setSource(LINKED_DATA).setSrsId("srs-456"))
      .setUnmappedMarc(new RawMarc().setContent("{ '008': 'some data' }"))
      .setDoc(mapper.readTree("{ \"someField\": \"someValue\" }"));

    var resource2 = new Resource()
      .setId(2L)
      .setLabel("Target Resource")
      .setTypes(Set.of(WORK));

    var edge = new ResourceEdge(resource1, resource2, INSTANTIATES);
    resource2.setIncomingEdges(Set.of(edge));
    resource1.addOutgoingEdge(edge);

    var cycleEdge = new ResourceEdge(resource2, resource1, TITLE);
    resource1.setIncomingEdges(Set.of(cycleEdge));
    resource2.addOutgoingEdge(cycleEdge);

    var resultJson = mapper.writeValueAsString(resource1);

    var expectedJson = """
      {
        "id": "1",
        "label": "Main Resource",
        "doc": { "someField": "someValue" },
        "folioMetadata": { "inventoryId": "inv-123", "source": "LINKED_DATA", "srsId": "srs-456" },
        "unmappedMarc": { "content": "{ '008': 'some data' }" },
        "types": [ "http://bibfra.me/vocab/lite/Instance" ],
        "outgoingEdges": {
          "http://bibfra.me/vocab/lite/instantiates": [
            {
              "id": "2",
              "label": "Target Resource",
              "types": [ "http://bibfra.me/vocab/lite/Work" ],
              "outgoingEdges": {
                "http://bibfra.me/vocab/library/title": [
                  {
                    "id": "1"
                  }
                ]
              }
            }
          ]
        }
      }""";

    assertThat(mapper.readTree(resultJson)).isEqualTo(mapper.readTree(expectedJson));
  }
}
