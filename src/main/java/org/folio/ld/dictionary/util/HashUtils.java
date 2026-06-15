package org.folio.ld.dictionary.util;

import static java.nio.ByteOrder.BIG_ENDIAN;
import static java.nio.ByteOrder.LITTLE_ENDIAN;
import static java.nio.charset.StandardCharsets.UTF_8;
import static java.util.Objects.isNull;

import com.google.common.hash.HashCode;
import com.google.common.hash.Hashing;
import java.nio.ByteBuffer;
import java.util.Base64;
import lombok.experimental.UtilityClass;

@UtilityClass
public class HashUtils {

  public Long hash(String input) {
    if (input == null) {
      return null;
    }
    return hashInternal(input).padToLong();
  }

  public String base64Url(String input) {
    if (isNull(input)) {
      return null;
    }
    var hashCode = hashInternal(input);
    var buf = ByteBuffer.wrap(hashCode.asBytes(), 0, 8).order(LITTLE_ENDIAN);
    long basis = buf.getLong();
    var rawHash = ByteBuffer.allocate(8).order(BIG_ENDIAN);
    rawHash.putLong(basis);
    return Base64.getUrlEncoder()
      .withoutPadding()
      .encodeToString(rawHash.array());
  }

  private HashCode hashInternal(String input) {
    return Hashing.murmur3_128().hashBytes(input.getBytes(UTF_8));
  }

}
