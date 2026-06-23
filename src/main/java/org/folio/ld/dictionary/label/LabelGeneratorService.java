package org.folio.ld.dictionary.label;

import java.util.List;
import org.folio.ld.dictionary.label.generators.DissertationLabelGenerator;
import org.folio.ld.dictionary.label.generators.HubLabelGenerator;
import org.folio.ld.dictionary.label.generators.InstanceAndWorkLabelGenerator;
import org.folio.ld.dictionary.label.generators.ProviderEventLabelGenerator;
import org.folio.ld.dictionary.label.generators.TitleLabelGenerator;
import org.folio.ld.dictionary.label.generators.authority.ConceptLabelGenerator;
import org.folio.ld.dictionary.label.generators.authority.FamilyLabelGenerator;
import org.folio.ld.dictionary.label.generators.authority.FormLabelGenerator;
import org.folio.ld.dictionary.label.generators.authority.JurisdictionLabelGenerator;
import org.folio.ld.dictionary.label.generators.authority.MeetingLabelGenerator;
import org.folio.ld.dictionary.label.generators.authority.OrganizationLabelGenerator;
import org.folio.ld.dictionary.label.generators.authority.PersonLabelGenerator;
import org.folio.ld.dictionary.label.generators.authority.PlaceLabelGenerator;
import org.folio.ld.dictionary.label.generators.authority.TemporalLabelGenerator;
import org.folio.ld.dictionary.label.generators.authority.TopicLabelGenerator;
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
