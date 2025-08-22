package org.folio.ld.dictionary.specific;

import java.util.Optional;
import java.util.stream.Stream;
import org.junit.jupiter.params.provider.Arguments;

class PublicationFrequencyDictionaryTest extends SpecificDictionaryTest<Character, String> {

  @Override
  protected Optional<String> getValue(Character code) {
    return PublicationFrequencyDictionary.getValue(code);
  }

  @Override
  protected Optional<Character> getCode(String value) {
    return PublicationFrequencyDictionary.getCode(value);
  }

  @Override
  protected Stream<Arguments> codes() {
    return Stream.of(
      Arguments.of('a', "http://id.loc.gov/vocabulary/frequencies/ann"),
      Arguments.of('b', "http://id.loc.gov/vocabulary/frequencies/bmn"),
      Arguments.of('c', "http://id.loc.gov/vocabulary/frequencies/swk"),
      Arguments.of(' ', null),
      Arguments.of('x', null),
      Arguments.of(null, null)
    );
  }

  @Override
  protected Stream<Arguments> values() {
    return Stream.of(
      Arguments.of("http://id.loc.gov/vocabulary/frequencies/ann", 'a'),
      Arguments.of("http://id.loc.gov/vocabulary/frequencies/bmn", 'b'),
      Arguments.of("http://id.loc.gov/vocabulary/frequencies/swk", 'c'),
      Arguments.of("invalidCode", null),
      Arguments.of("", null),
      Arguments.of("http://id.loc.gov/vocabulary/frequencies/aaa", null),
      Arguments.of(null, null)
    );
  }
}
