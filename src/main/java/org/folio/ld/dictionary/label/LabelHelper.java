package org.folio.ld.dictionary.label;

import static java.util.stream.Collectors.joining;

import java.util.Optional;
import java.util.stream.StreamSupport;
import lombok.experimental.UtilityClass;
import org.folio.ld.dictionary.model.Resource;
import tools.jackson.databind.JsonNode;

@UtilityClass
public class LabelHelper {
  public static Optional<String> getPropertyValue(Resource resource, String property) {
    return Optional.of(resource)
      .map(Resource::getDoc)
      .map(doc -> doc.get(property))
      .map(node -> StreamSupport.stream(node.spliterator(), false)
        .map(JsonNode::asString)
        .map(String::trim)
        .collect(joining(" ")));
  }
}
