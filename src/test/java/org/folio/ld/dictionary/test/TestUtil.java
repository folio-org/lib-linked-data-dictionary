package org.folio.ld.dictionary.test;

import static java.nio.charset.StandardCharsets.UTF_8;

import java.util.Objects;
import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;

@UtilityClass
public class TestUtil {

  @SneakyThrows
  public static String loadResourceAsString(String resourceName) {
    var classLoader = TestUtil.class.getClassLoader();
    var is = Objects.requireNonNull(classLoader.getResourceAsStream(resourceName));
    return new String(is.readAllBytes(), UTF_8);
  }
}
