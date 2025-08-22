package org.folio.ld.dictionary.specific;

import java.util.Optional;
import java.util.stream.Stream;
import org.junit.jupiter.params.provider.Arguments;

class IllustrationDictionaryTest extends SpecificDictionaryTest<Character, String> {

  @Override
  protected Optional<String> getValue(Character code) {
    return IllustrationDictionary.getValue(code);
  }

  @Override
  protected Optional<Character> getCode(String value) {
    return IllustrationDictionary.getCode(value);
  }

  @Override
  protected Stream<Arguments> codes() {
    return Stream.of(
      Arguments.of('a', "http://id.loc.gov/vocabulary/millus/ill"),
      Arguments.of('b', "http://id.loc.gov/vocabulary/millus/map"),
      Arguments.of('c', "http://id.loc.gov/vocabulary/millus/por"),
      Arguments.of(' ', null),
      Arguments.of('x', null),
      Arguments.of(null, null)
    );
  }

  @Override
  protected Stream<Arguments> values() {
    return Stream.of(
      Arguments.of("http://id.loc.gov/vocabulary/millus/ill", 'a'),
      Arguments.of("http://id.loc.gov/vocabulary/millus/map", 'b'),
      Arguments.of("http://id.loc.gov/vocabulary/millus/por", 'c'),
      Arguments.of("invalidValue", null),
      Arguments.of("", null),
      Arguments.of(null, null)
    );
  }
}
