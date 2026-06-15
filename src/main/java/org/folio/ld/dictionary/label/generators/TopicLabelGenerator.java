package org.folio.ld.dictionary.label.generators;

import static org.folio.ld.dictionary.PropertyDictionary.NAME;
import static org.folio.ld.dictionary.ResourceTypeDictionary.TOPIC;
import static org.folio.ld.dictionary.label.LabelHelper.getPropertyValue;

import org.folio.ld.dictionary.label.LabelGenerator;
import org.folio.ld.dictionary.model.Resource;

public class TopicLabelGenerator implements LabelGenerator {

  @Override
  public boolean supports(Resource resource) {
    return resource.isOfType(TOPIC);
  }

  @Override
  public String getLabel(Resource resource) {
    return getPropertyValue(resource, NAME.getValue()).orElse("");
  }
}
