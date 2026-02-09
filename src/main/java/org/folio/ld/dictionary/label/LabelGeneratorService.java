package org.folio.ld.dictionary.label;

import java.util.List;
import org.folio.ld.dictionary.label.generators.ConceptLabelGenerator;
import org.folio.ld.dictionary.label.generators.HubLabelGenerator;
import org.folio.ld.dictionary.model.Resource;

public class LabelGeneratorService {

  private final List<LabelGenerator> generators;

  public LabelGeneratorService() {
    this.generators = List.of(
      new ConceptLabelGenerator(),
      new HubLabelGenerator()
    );
  }

  public String getLabel(Resource resource) {
    return generators.stream()
      .filter(g -> g.supports(resource))
      .map(g -> g.getLabel(resource))
      .filter(label -> label != null && !label.isBlank())
      .findFirst()
      .orElse(resource.getLabel());
  }
}
