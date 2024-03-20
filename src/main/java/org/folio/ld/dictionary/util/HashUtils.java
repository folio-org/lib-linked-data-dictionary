package org.folio.ld.dictionary.util;

import com.google.common.hash.Hashing;
import java.nio.charset.StandardCharsets;
import javax.annotation.Nullable;
import lombok.experimental.UtilityClass;

@UtilityClass
public class HashUtils {

  @Nullable
  public Long hash(String value) {
    if (value == null) {
      return null;
    }
    return Hashing.murmur3_128().hashString(value, StandardCharsets.UTF_8).padToLong();
  }
}
