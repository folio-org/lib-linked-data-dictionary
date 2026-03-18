package org.folio.ld.dictionary.label.generators;

import static java.util.stream.Collectors.joining;
import static org.folio.ld.dictionary.PropertyDictionary.DATE;
import static org.folio.ld.dictionary.PropertyDictionary.DEGREE;
import static org.folio.ld.dictionary.PropertyDictionary.NOTE;
import static org.folio.ld.dictionary.ResourceTypeDictionary.DISSERTATION;
import static org.folio.ld.dictionary.label.LabelHelper.getPropertyValue;

import java.util.Optional;
import java.util.stream.Stream;
import org.folio.ld.dictionary.label.LabelGenerator;
import org.folio.ld.dictionary.model.Resource;

public class DissertationLabelGenerator implements LabelGenerator {
  @Override
  public boolean supports(Resource resource) {
    return resource.isOfType(DISSERTATION);
  }

  @Override
  public String getLabel(Resource resource) {
    return Stream.of(
        getPropertyValue(resource, NOTE.getValue()),
        getPropertyValue(resource, DEGREE.getValue()),
        getPropertyValue(resource, DATE.getValue())
      )
      .filter(Optional::isPresent)
      .map(Optional::get)
      .collect(joining(" "));
  }
}
