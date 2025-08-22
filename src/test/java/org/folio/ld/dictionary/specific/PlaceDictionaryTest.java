package org.folio.ld.dictionary.specific;

import java.util.Optional;
import java.util.stream.Stream;
import org.junit.jupiter.params.provider.Arguments;

class PlaceDictionaryTest extends SpecificDictionaryTest<String, String> {

  @Override
  protected Optional<String> getValue(String code) {
    return PlaceDictionary.getValue(code);
  }

  @Override
  protected Optional<String> getCode(String value) {
    return PlaceDictionary.getCode(value);
  }

  @Override
  protected Stream<Arguments> codes() {
    return Stream.of(
      Arguments.of("aa", "Albania"),
      Arguments.of("abc", "Alberta"),
      Arguments.of("aca", "Australian Capital Territory"),
      Arguments.of("invalidCode", null),
      Arguments.of("", null),
      Arguments.of(null, null)
    );
  }

  @Override
  protected Stream<Arguments> values() {
    return Stream.of(
      Arguments.of("Albania", "aa"),
      Arguments.of("Alberta", "abc"),
      Arguments.of("Australian Capital Territory", "aca"),
      Arguments.of("invalidValue", null),
      Arguments.of("", null),
      Arguments.of(null, null)
    );
  }

}
