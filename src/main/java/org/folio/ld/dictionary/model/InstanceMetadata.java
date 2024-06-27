package org.folio.ld.dictionary.model;

import java.util.UUID;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@NoArgsConstructor
@Accessors(chain = true)
public class InstanceMetadata {
  private UUID inventoryId;
  private UUID srsId;
  private ResourceSource source;
}
