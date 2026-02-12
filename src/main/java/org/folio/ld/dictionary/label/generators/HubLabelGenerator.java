package org.folio.ld.dictionary.label.generators;

import static java.util.stream.Collectors.joining;
import static org.folio.ld.dictionary.PredicateDictionary.CREATOR;
import static org.folio.ld.dictionary.PredicateDictionary.LANGUAGE;
import static org.folio.ld.dictionary.PredicateDictionary.TITLE;
import static org.folio.ld.dictionary.PropertyDictionary.TERM;
import static org.folio.ld.dictionary.ResourceTypeDictionary.HUB;

import com.fasterxml.jackson.databind.JsonNode;
import java.util.Optional;
import java.util.stream.Stream;
import org.folio.ld.dictionary.PredicateDictionary;
import org.folio.ld.dictionary.PropertyDictionary;
import org.folio.ld.dictionary.label.LabelGenerator;
import org.folio.ld.dictionary.model.Resource;
import org.folio.ld.dictionary.model.ResourceEdge;

public class HubLabelGenerator implements LabelGenerator {
  @Override
  public boolean supports(Resource resource) {
    return resource.isOfType(HUB);
  }

  @Override
  public String getLabel(Resource resource) {
    var titleLabel = getOutgoingResourceLabel(resource, TITLE);
    var creatorLabel = getOutgoingResourceLabel(resource, CREATOR);
    var languageLabel = getOutgoingEdges(resource, LANGUAGE)
      .map(r -> getPropertyValue(r, TERM.getValue()).orElseGet(r::getLabel))
      .findFirst()
      .or(() -> getPropertyValue(resource, PropertyDictionary.LANGUAGE.getValue()));


    return Stream.of(creatorLabel, titleLabel, languageLabel)
      .filter(Optional::isPresent)
      .map(Optional::get)
      .collect(joining(". "));
  }

  private Optional<String> getOutgoingResourceLabel(Resource resource, PredicateDictionary predicate) {
    return getOutgoingEdges(resource, predicate)
      .map(Resource::getLabel)
      .findFirst();
  }

  private Stream<Resource> getOutgoingEdges(Resource resource, PredicateDictionary predicate) {
    return resource.getOutgoingEdges().stream()
      .filter(re -> re.getPredicate().equals(predicate))
      .map(ResourceEdge::getTarget);
  }

  private Optional<String> getPropertyValue(Resource resource, String property) {
    return Optional.of(resource)
      .map(Resource::getDoc)
      .map(doc -> doc.get(property))
      .map(node -> node.get(0))
      .map(JsonNode::asText);
  }
}
