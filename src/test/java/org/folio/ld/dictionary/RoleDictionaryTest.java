package org.folio.ld.dictionary;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class RoleDictionaryTest {

  @ParameterizedTest
  @MethodSource("validRoles")
  void getRole_shouldReturnCorrectPredicateDictionary_forValidCode(String code, PredicateDictionary predicate) {
    // when
    var result = RoleDictionary.getRole(code);

    // then
    assertThat(result).isEqualTo(predicate);
  }

  @ParameterizedTest
  @MethodSource("invalidRoleCodes")
  void getRole_shouldReturnNull_forInvalidCode(String code) {
    // when
    var result = RoleDictionary.getRole(code);

    // then
    assertThat(result).isNull();
  }

  @ParameterizedTest
  @MethodSource("validRoles")
  void getCodeShouldReturnCorrectCodeForValidPredicateDictionary(String code, PredicateDictionary predicate) {
    // when
    var result = RoleDictionary.getCode(predicate);

    // then
    assertThat(result).isEqualTo(code);
  }

  @ParameterizedTest
  @MethodSource("invalidRoles")
  void getCodeShouldReturnNullForInvalidPredicateDictionary(PredicateDictionary role) {
    // when
    var result = RoleDictionary.getCode(role);

    // then
    assertThat(result).isNull();
  }

  private static Stream<Arguments> validRoles() {
    return Stream.of(
      Arguments.of("abr", PredicateDictionary.ABRIDGER),
      Arguments.of("act", PredicateDictionary.ACTOR),
      Arguments.of("aut", PredicateDictionary.AUTHOR)
    );
  }

  private static Stream<Arguments> invalidRoleCodes() {
    return Stream.of(
      Arguments.of("invalidCode"),
      Arguments.of("")
    );
  }

  private static Stream<Arguments> invalidRoles() {
    return Stream.of(
      Arguments.of(PredicateDictionary.NULL),
      Arguments.of(PredicateDictionary.TITLE)
    );
  }
}
