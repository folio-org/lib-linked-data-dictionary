package org.folio.ld.dictionary.label.generators;

import static java.util.Comparator.comparing;
import static org.folio.ld.dictionary.PredicateDictionary.FOCUS;
import static org.folio.ld.dictionary.PredicateDictionary.SUB_FOCUS;
import static org.folio.ld.dictionary.ResourceTypeDictionary.CONCEPT;

import java.util.Comparator;
import java.util.stream.Collectors;
import org.folio.ld.dictionary.label.LabelGenerator;
import org.folio.ld.dictionary.model.Resource;
import org.folio.ld.dictionary.model.ResourceEdge;

public class ConceptLabelGenerator implements LabelGenerator {
  private static final String SEPARATOR_CONCEPT = " -- ";

  @Override
  public boolean supports(Resource resource) {
    return resource.isOfType(CONCEPT);
  }

  @Override
  public String getLabel(Resource resource) {
    return resource.getOutgoingEdges()
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
