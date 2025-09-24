package org.folio.ld.dictionary.serializer;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.mapping;
import static java.util.stream.Collectors.toList;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import java.io.IOException;
import java.util.LinkedHashMap;
import org.folio.ld.dictionary.model.Resource;
import org.folio.ld.dictionary.model.ResourceEdge;

public class ResourceSerializer extends StdSerializer<Resource> {

  private static final String FIELD_ID = "id";
  private static final String FIELD_LABEL = "label";
  private static final String FIELD_DOC = "doc";
  private static final String FIELD_FOLIO_METADATA = "folioMetadata";
  private static final String FIELD_UNMAPPED_MARC = "unmappedMarc";
  private static final String FIELD_CREATED_DATE = "createdDate";
  private static final String FIELD_UPDATED_DATE = "updatedDate";
  private static final String FIELD_TYPES = "types";
  private static final String FIELD_OUTGOING_EDGES = "outgoingEdges";

  public ResourceSerializer() {
    this(Resource.class);
  }

  public ResourceSerializer(Class<Resource> t) {
    super(t);
  }

  @Override
  public void serialize(Resource resource, JsonGenerator gen, SerializerProvider provider) throws IOException {
    gen.writeStartObject();
    gen.writeStringField(FIELD_ID, String.valueOf(resource.getId()));
    gen.writeStringField(FIELD_LABEL, resource.getLabel());

    if (resource.getDoc() != null) {
      gen.writeObjectField(FIELD_DOC, resource.getDoc());
    }
    if (resource.getFolioMetadata() != null) {
      provider.defaultSerializeField(FIELD_FOLIO_METADATA, resource.getFolioMetadata(), gen);
    }
    if (resource.getUnmappedMarc() != null) {
      provider.defaultSerializeField(FIELD_UNMAPPED_MARC, resource.getUnmappedMarc(), gen);
    }
    if (resource.getCreatedDate() != null) {
      gen.writeObjectField(FIELD_CREATED_DATE, resource.getCreatedDate());
    }
    if (resource.getUpdatedDate() != null) {
      gen.writeObjectField(FIELD_UPDATED_DATE, resource.getUpdatedDate());
    }

    writeTypesArray(resource, gen);
    writeOutgoingEdges(resource, gen, provider);

    gen.writeEndObject();
  }

  private void writeOutgoingEdges(Resource resource,
                                  JsonGenerator gen,
                                  SerializerProvider provider) throws IOException {
    if (resource.getOutgoingEdges() == null) {
      return;
    }

    gen.writeObjectFieldStart(FIELD_OUTGOING_EDGES);

    var predicateAndTargets = resource.getOutgoingEdges().stream()
      .collect(groupingBy(
        e -> e.getPredicate().getUri(),
        LinkedHashMap::new,
        mapping(ResourceEdge::getTarget, toList())
      ));
    for (var predicateAndTarget : predicateAndTargets.entrySet()) {
      gen.writeArrayFieldStart(predicateAndTarget.getKey());
      for (var target : predicateAndTarget.getValue()) {
        provider.defaultSerializeValue(target, gen);
      }
      gen.writeEndArray();
    }

    gen.writeEndObject();
  }

  private void writeTypesArray(Resource resource, JsonGenerator gen) throws IOException {
    gen.writeArrayFieldStart(FIELD_TYPES);
    for (var type : resource.getTypes()) {
      gen.writeString(type.getUri());
    }
    gen.writeEndArray();
  }
}
