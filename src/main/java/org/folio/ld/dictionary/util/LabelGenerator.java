package org.folio.ld.dictionary.util;

import static java.util.Comparator.comparing;
import static org.folio.ld.dictionary.PredicateDictionary.FOCUS;
import static org.folio.ld.dictionary.PredicateDictionary.SUB_FOCUS;
import static org.folio.ld.dictionary.ResourceTypeDictionary.CONCEPT;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import lombok.experimental.UtilityClass;
import org.folio.ld.dictionary.ResourceTypeDictionary;
import org.folio.ld.dictionary.model.Resource;
import org.folio.ld.dictionary.model.ResourceEdge;

@UtilityClass
public class LabelGenerator {
  private static final String SEPARATOR_CONCEPT = " -- ";

  private static final Map<ResourceTypeDictionary, Function<Resource, String>> LABEL_GENERATORS_PER_TYPE = Map.of(
    CONCEPT, resource -> resource.getOutgoingEdges()
      .stream()
      .filter(re -> re.getPredicate() == FOCUS || re.getPredicate() == SUB_FOCUS)
      .sorted(comparing(o -> o.getPredicate().name()))
      .map(ResourceEdge::getTarget)
      .map(Resource::getLabel)
      .collect(Collectors.joining(SEPARATOR_CONCEPT))
  );

  public static String generateLabel(Resource resource) {
    return resource.getTypes()
      .stream()
      .filter(LABEL_GENERATORS_PER_TYPE::containsKey)
      .findFirst()
      .map(t -> LABEL_GENERATORS_PER_TYPE.get(t).apply(resource))
      .orElseGet(resource::getLabel);
  }
}
