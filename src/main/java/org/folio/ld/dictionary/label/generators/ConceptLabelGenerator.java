package org.folio.ld.dictionary.label.generators;

import static java.lang.String.CASE_INSENSITIVE_ORDER;
import static org.folio.ld.dictionary.PredicateDictionary.FOCUS;
import static org.folio.ld.dictionary.PredicateDictionary.SUB_FOCUS;
import static org.folio.ld.dictionary.ResourceTypeDictionary.CONCEPT;
import static org.folio.ld.dictionary.ResourceTypeDictionary.FORM;
import static org.folio.ld.dictionary.ResourceTypeDictionary.PLACE;
import static org.folio.ld.dictionary.ResourceTypeDictionary.TEMPORAL;
import static org.folio.ld.dictionary.ResourceTypeDictionary.TOPIC;

import java.util.Comparator;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import org.folio.ld.dictionary.ResourceTypeDictionary;
import org.folio.ld.dictionary.label.LabelGenerator;
import org.folio.ld.dictionary.model.Resource;
import org.folio.ld.dictionary.model.ResourceEdge;

public class ConceptLabelGenerator implements LabelGenerator {
  private static final String SEPARATOR_CONCEPT = " -- ";
  private static final Comparator<ResourceEdge> CONCEPT_EDGES_COMPARATOR = new ConceptEdgesComparator();

  @Override
  public boolean supports(Resource resource) {
    return resource.isOfType(CONCEPT);
  }

  @Override
  public String getLabel(Resource resource) {
    return resource.getOutgoingEdges()
      .stream()
      .filter(re -> re.getPredicate() == FOCUS || re.getPredicate() == SUB_FOCUS)
      .sorted(CONCEPT_EDGES_COMPARATOR)
      .map(ResourceEdge::getTarget)
      .map(Resource::getLabel)
      .collect(Collectors.joining(SEPARATOR_CONCEPT));
  }

  private static final class ConceptEdgesComparator implements Comparator<ResourceEdge> {
    private static final Map<ResourceTypeDictionary, Integer> TYPE_ORDER_INDEX = Map.of(
      TOPIC, 0,
      PLACE, 1,
      TEMPORAL, 2,
      FORM, 3
    );

    @Override
    public int compare(ResourceEdge edge1, ResourceEdge edge2) {
      return Comparator.comparing((ResourceEdge edge) -> edge.getPredicate().name())
        .thenComparingInt(this::getSubFocusTypeOrder)
        .thenComparing(e -> e.getTarget().getLabel(), Comparator.nullsFirst(CASE_INSENSITIVE_ORDER))
        .compare(edge1, edge2);
    }

    private int getSubFocusTypeOrder(ResourceEdge edge) {
      if (edge.getPredicate() != SUB_FOCUS) {
        return -1;
      }
      return edge.getTarget().getTypes().stream()
        .map(TYPE_ORDER_INDEX::get)
        .filter(Objects::nonNull)
        .min(Integer::compareTo)
        .orElse(TYPE_ORDER_INDEX.size());
    }
  }
}
