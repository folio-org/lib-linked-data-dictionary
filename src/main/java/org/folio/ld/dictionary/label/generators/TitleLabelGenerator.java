package org.folio.ld.dictionary.label.generators;

import static java.util.stream.Collectors.joining;
import static org.folio.ld.dictionary.PropertyDictionary.MAIN_TITLE;
import static org.folio.ld.dictionary.PropertyDictionary.PART_NAME;
import static org.folio.ld.dictionary.PropertyDictionary.PART_NUMBER;
import static org.folio.ld.dictionary.PropertyDictionary.SUBTITLE;
import static org.folio.ld.dictionary.ResourceTypeDictionary.ABBREVIATED_TITLE;
import static org.folio.ld.dictionary.ResourceTypeDictionary.PARALLEL_TITLE;
import static org.folio.ld.dictionary.ResourceTypeDictionary.TITLE;
import static org.folio.ld.dictionary.ResourceTypeDictionary.VARIANT_TITLE;
import static org.folio.ld.dictionary.label.LabelHelper.getPropertyValue;

import java.util.Optional;
import java.util.stream.Stream;
import org.folio.ld.dictionary.label.LabelGenerator;
import org.folio.ld.dictionary.model.Resource;

public class TitleLabelGenerator implements LabelGenerator {
  @Override
  public boolean supports(Resource resource) {
    return resource.isOfType(TITLE)
      || resource.isOfType(VARIANT_TITLE)
      || resource.isOfType(PARALLEL_TITLE)
      || resource.isOfType(ABBREVIATED_TITLE);
  }

  @Override
  public String getLabel(Resource resource) {
    var mainTitle = getPropertyValue(resource, MAIN_TITLE.getValue());
    var subtitle = getPropertyValue(resource, SUBTITLE.getValue());
    var partNumber = getPropertyValue(resource, PART_NUMBER.getValue());
    var partName = getPropertyValue(resource, PART_NAME.getValue());

    return Stream.of(mainTitle, subtitle, partNumber, partName)
      .filter(Optional::isPresent)
      .map(Optional::get)
      .collect(joining(" "));
  }
}
