package org.folio.ld.dictionary.label.generators;

import static java.util.stream.Collectors.joining;
import static org.folio.ld.dictionary.PropertyDictionary.DATE;
import static org.folio.ld.dictionary.PropertyDictionary.NAME;
import static org.folio.ld.dictionary.PropertyDictionary.PLACE;
import static org.folio.ld.dictionary.PropertyDictionary.SUBORDINATE_UNIT;
import static org.folio.ld.dictionary.ResourceTypeDictionary.MEETING;
import static org.folio.ld.dictionary.label.LabelHelper.getPropertyValue;

import java.util.Optional;
import java.util.stream.Stream;
import org.folio.ld.dictionary.label.LabelGenerator;
import org.folio.ld.dictionary.model.Resource;

public class MeetingLabelGenerator implements LabelGenerator {

  @Override
  public boolean supports(Resource resource) {
    return resource.isOfType(MEETING);
  }

  @Override
  public String getLabel(Resource resource) {
    return Stream.of(
        getPropertyValue(resource, NAME.getValue()),
        getPropertyValue(resource, DATE.getValue()),
        getPropertyValue(resource, PLACE.getValue()),
        getPropertyValue(resource, SUBORDINATE_UNIT.getValue())
      )
      .filter(Optional::isPresent)
      .map(Optional::get)
      .collect(joining(", "));
  }
}
