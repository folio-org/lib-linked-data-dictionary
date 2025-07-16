package org.folio.ld.dictionary;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class PlaceDictionaryTest {

  @ParameterizedTest
  @MethodSource("validPlaces")
  void getName_shouldReturnCorrectName_forValidCode(String code, String name) {
    // when
    var result = PlaceDictionary.getName(code);

    // then
    assertThat(result)
      .isPresent()
      .hasValueSatisfying(v -> assertThat(v).isEqualTo(name));
  }

  @ParameterizedTest
  @MethodSource("invalidPlaceCodes")
  void getName_shouldReturnEmptyOptional_forInvalidCode(String code) {
    // when
    var result = PlaceDictionary.getName(code);

    // then
    assertThat(result).isEmpty();
  }

  private static Stream<Arguments> validPlaces() {
    return Stream.of(
      Arguments.of("aa", "Albania"),
      Arguments.of("abc", "Alberta"),
      Arguments.of("aca", "Australian Capital Territory")
    );
  }

  private static Stream<Arguments> invalidPlaceCodes() {
    return Stream.of(
      Arguments.of("invalidCode"),
      Arguments.of("")
    );
  }

}
