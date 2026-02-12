package org.folio.ld.dictionary.label;

import org.folio.ld.dictionary.model.Resource;

public interface LabelGenerator {
  boolean supports(Resource resource);

  String getLabel(Resource resource);
}
