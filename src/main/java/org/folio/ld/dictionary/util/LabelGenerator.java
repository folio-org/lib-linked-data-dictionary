package org.folio.ld.dictionary.util;

import static java.util.Comparator.comparing;
import static org.folio.ld.dictionary.PredicateDictionary.FOCUS;
import static org.folio.ld.dictionary.PredicateDictionary.SUB_FOCUS;
import static org.folio.ld.dictionary.ResourceTypeDictionary.CONCEPT;

import java.util.Comparator;
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
    CONCEPT, conceptLabelGenerator()
  );

  public static String generateLabel(Resource resource) {
    return resource.getTypes()
      .stream()
      .filter(LABEL_GENERATORS_PER_TYPE::containsKey)
      .findFirst()
      .map(t -> LABEL_GENERATORS_PER_TYPE.get(t).apply(resource))
      .orElseGet(resource::getLabel);
  }

  private static Function<Resource, String> conceptLabelGenerator() {
    return resource -> resource.getOutgoingEdges()
      .stream()
      .filter(re -> re.getPredicate() == FOCUS || re.getPredicate() == SUB_FOCUS)
      .sorted((o1, o2) -> {
        Comparator<ResourceEdge> comparing = comparing(o -> o.getPredicate().name());
        return comparing.thenComparing(re -> re.getTarget().getLabel())
          .compare(o1, o2);
      })
      .map(ResourceEdge::getTarget)
      .map(Resource::getLabel)
      .collect(Collectors.joining(SEPARATOR_CONCEPT));
  }

}
