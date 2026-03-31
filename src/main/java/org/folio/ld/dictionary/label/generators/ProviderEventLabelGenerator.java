package org.folio.ld.dictionary.label.generators;

import static java.util.stream.Collectors.joining;
import static org.folio.ld.dictionary.PropertyDictionary.DATE;
import static org.folio.ld.dictionary.PropertyDictionary.NAME;
import static org.folio.ld.dictionary.PropertyDictionary.PROVIDER_DATE;
import static org.folio.ld.dictionary.PropertyDictionary.SIMPLE_PLACE;
import static org.folio.ld.dictionary.ResourceTypeDictionary.PROVIDER_EVENT;
import static org.folio.ld.dictionary.label.LabelHelper.getPropertyValue;

import java.util.Optional;
import java.util.stream.Stream;
import org.folio.ld.dictionary.label.LabelGenerator;
import org.folio.ld.dictionary.model.Resource;

public class ProviderEventLabelGenerator implements LabelGenerator {
  @Override
  public boolean supports(Resource resource) {
    return resource.isOfType(PROVIDER_EVENT);
  }

  @Override
  public String getLabel(Resource resource) {
    return Stream.of(
        getPropertyValue(resource, NAME.getValue()),
        getPropertyValue(resource, SIMPLE_PLACE.getValue()),
        getPropertyValue(resource, DATE.getValue()).or(() -> getPropertyValue(resource, PROVIDER_DATE.getValue()))
      )
      .filter(Optional::isPresent)
      .map(Optional::get)
      .collect(joining(", "));
  }
}
