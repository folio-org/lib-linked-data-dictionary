package org.folio.ld.dictionary;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class PublicationFrequencyDictionaryTest {

  @ParameterizedTest
  @MethodSource("validValues")
  void getLink_shouldReturnCorrectLink_forValidCode(String code, String link) {
    // when
    var result = PublicationFrequencyDictionary.getLink(code);

    // then
    assertThat(result).contains(link);
  }

  @ParameterizedTest
  @MethodSource("invalidValues")
  void getLink_shouldReturnNull_forInvalidCode(String code) {
    // when
    var result = PublicationFrequencyDictionary.getLink(code);

    // then
    assertThat(result).isEmpty();
  }

  @ParameterizedTest
  @MethodSource("validValues")
  void getCodeShouldReturnCorrectCodeForValidLink(String code, String link) {
    // when
    var result = PublicationFrequencyDictionary.getCode(link);

    // then
    assertThat(result).contains(code);
  }

  @ParameterizedTest
  @MethodSource("invalidValues")
  void getCodeShouldReturnNullForInvalidLink(String link) {
    // when
    var result = PublicationFrequencyDictionary.getCode(link);

    // then
    assertThat(result).isEmpty();
  }

  private static Stream<Arguments> validValues() {
    return Stream.of(
      Arguments.of("a", "http://id.loc.gov/vocabulary/frequencies/ann"),
      Arguments.of("b", "http://id.loc.gov/vocabulary/frequencies/bmn"),
      Arguments.of("c", "http://id.loc.gov/vocabulary/frequencies/swk")
    );
  }

  private static Stream<Arguments> invalidValues() {
    return Stream.of(
      Arguments.of("invalidCode"),
      Arguments.of(""),
      Arguments.of("http://id.loc.gov/vocabulary/frequencies/aaa")
    );
  }
}
