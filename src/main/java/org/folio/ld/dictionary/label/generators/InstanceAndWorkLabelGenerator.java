package org.folio.ld.dictionary.label.generators;

import static org.folio.ld.dictionary.PredicateDictionary.TITLE;
import static org.folio.ld.dictionary.ResourceTypeDictionary.INSTANCE;
import static org.folio.ld.dictionary.ResourceTypeDictionary.WORK;

import java.util.Comparator;
import org.folio.ld.dictionary.ResourceTypeDictionary;
import org.folio.ld.dictionary.label.LabelGenerator;
import org.folio.ld.dictionary.model.Resource;
import org.folio.ld.dictionary.model.ResourceEdge;

public class InstanceAndWorkLabelGenerator implements LabelGenerator {

  @Override
  public boolean supports(Resource resource) {
    return resource.isOfType(INSTANCE) || resource.isOfType(WORK);
  }

  @Override
  public String getLabel(Resource resource) {
    return resource.getOutgoingEdges().stream()
      .filter(edge -> edge.getPredicate().equals(TITLE))
      .map(ResourceEdge::getTarget)
      .filter(target -> target.isOfType(ResourceTypeDictionary.TITLE))
      .map(Resource::getLabel)
      .filter(label -> label != null && !label.isBlank())
      .min(Comparator.naturalOrder())
      .orElse("");
  }
}
