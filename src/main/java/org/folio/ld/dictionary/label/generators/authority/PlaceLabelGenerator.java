package org.folio.ld.dictionary.label.generators.authority;

import static org.folio.ld.dictionary.PropertyDictionary.CODE;
import static org.folio.ld.dictionary.PropertyDictionary.NAME;
import static org.folio.ld.dictionary.ResourceTypeDictionary.CONCEPT;
import static org.folio.ld.dictionary.ResourceTypeDictionary.PLACE;
import static org.folio.ld.dictionary.label.LabelHelper.getPropertyValue;

import org.folio.ld.dictionary.label.LabelGenerator;
import org.folio.ld.dictionary.model.Resource;

public class PlaceLabelGenerator implements LabelGenerator {

  @Override
  public boolean supports(Resource resource) {
    return resource.isOfType(PLACE) && resource.isNotOfType(CONCEPT);
  }

  @Override
  public String getLabel(Resource resource) {
    return getPropertyValue(resource, NAME.getValue())
      .or(() -> getPropertyValue(resource, CODE.getValue()))
      .orElse("");
  }
}
