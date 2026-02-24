package org.folio.ld.dictionary.label;

import java.util.Optional;
import lombok.experimental.UtilityClass;
import org.folio.ld.dictionary.model.Resource;
import tools.jackson.databind.JsonNode;

@UtilityClass
public class LabelHelper {
  public static Optional<String> getPropertyValue(Resource resource, String property) {
    return Optional.of(resource)
      .map(Resource::getDoc)
      .map(doc -> doc.get(property))
      .map(node -> node.get(0))
      .map(JsonNode::asString);
  }
}
