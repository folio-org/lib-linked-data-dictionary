package org.folio.ld.dictionary.label;

import java.util.List;
import org.folio.ld.dictionary.label.generators.ConceptLabelGenerator;
import org.folio.ld.dictionary.label.generators.DissertationLabelGenerator;
import org.folio.ld.dictionary.label.generators.FamilyLabelGenerator;
import org.folio.ld.dictionary.label.generators.FormLabelGenerator;
import org.folio.ld.dictionary.label.generators.HubLabelGenerator;
import org.folio.ld.dictionary.label.generators.InstanceAndWorkLabelGenerator;
import org.folio.ld.dictionary.label.generators.JurisdictionLabelGenerator;
import org.folio.ld.dictionary.label.generators.MeetingLabelGenerator;
import org.folio.ld.dictionary.label.generators.OrganizationLabelGenerator;
import org.folio.ld.dictionary.label.generators.PersonLabelGenerator;
import org.folio.ld.dictionary.label.generators.PlaceLabelGenerator;
import org.folio.ld.dictionary.label.generators.ProviderEventLabelGenerator;
import org.folio.ld.dictionary.label.generators.TemporalLabelGenerator;
import org.folio.ld.dictionary.label.generators.TitleLabelGenerator;
import org.folio.ld.dictionary.label.generators.TopicLabelGenerator;
import org.folio.ld.dictionary.model.Resource;

public class LabelGeneratorService {

  private final List<LabelGenerator> generators;

  public LabelGeneratorService() {
    this.generators = List.of(
      new ConceptLabelGenerator(),
      new DissertationLabelGenerator(),
      new HubLabelGenerator(),
      new InstanceAndWorkLabelGenerator(),
      new ProviderEventLabelGenerator(),
      new TitleLabelGenerator(),
      new FamilyLabelGenerator(),
      new FormLabelGenerator(),
      new JurisdictionLabelGenerator(),
      new MeetingLabelGenerator(),
      new OrganizationLabelGenerator(),
      new PersonLabelGenerator(),
      new PlaceLabelGenerator(),
      new TemporalLabelGenerator(),
      new TopicLabelGenerator()
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
