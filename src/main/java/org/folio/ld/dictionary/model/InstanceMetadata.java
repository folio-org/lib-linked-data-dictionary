package org.folio.ld.dictionary.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@NoArgsConstructor
@Accessors(chain = true)
public class InstanceMetadata {
  private String inventoryId;
  private String srsId;
  private ResourceSource source;
}
