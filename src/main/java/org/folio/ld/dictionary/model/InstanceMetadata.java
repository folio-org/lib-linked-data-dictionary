package org.folio.ld.dictionary.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class InstanceMetadata {
  private String inventoryId;
  private String srsId;
  private ResourceSource source;
}
