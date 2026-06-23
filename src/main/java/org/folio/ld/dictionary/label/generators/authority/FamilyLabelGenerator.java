package org.folio.ld.dictionary.label.generators.authority;

import static java.util.stream.Collectors.joining;
import static org.folio.ld.dictionary.PropertyDictionary.DATE;
import static org.folio.ld.dictionary.PropertyDictionary.NAME;
import static org.folio.ld.dictionary.PropertyDictionary.NAME_ALTERNATIVE;
import static org.folio.ld.dictionary.PropertyDictionary.NUMERATION;
import static org.folio.ld.dictionary.PropertyDictionary.TITLES;
import static org.folio.ld.dictionary.ResourceTypeDictionary.CONCEPT;
import static org.folio.ld.dictionary.ResourceTypeDictionary.FAMILY;
import static org.folio.ld.dictionary.label.LabelHelper.getPropertyValue;

import java.util.Optional;
import java.util.stream.Stream;
import org.folio.ld.dictionary.label.LabelGenerator;
import org.folio.ld.dictionary.model.Resource;

public class FamilyLabelGenerator implements LabelGenerator {

  @Override
  public boolean supports(Resource resource) {
    return resource.isOfType(FAMILY) && resource.isNotOfType(CONCEPT);
  }

  @Override
  public String getLabel(Resource resource) {
    return Stream.of(
        getPropertyValue(resource, NUMERATION.getValue()),
        getPropertyValue(resource, NAME.getValue()),
        getPropertyValue(resource, TITLES.getValue()),
        getPropertyValue(resource, NAME_ALTERNATIVE.getValue()),
        getPropertyValue(resource, DATE.getValue())
      )
      .filter(Optional::isPresent)
      .map(Optional::get)
      .collect(joining(", "));
  }
}
