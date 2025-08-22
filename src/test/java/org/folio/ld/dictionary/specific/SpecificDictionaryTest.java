package org.folio.ld.dictionary.specific;

import static java.util.Optional.ofNullable;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.TestInstance.Lifecycle.PER_CLASS;

import java.util.Optional;
import java.util.stream.Stream;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

@TestInstance(PER_CLASS)
abstract class SpecificDictionaryTest<C, V> {

  @ParameterizedTest
  @MethodSource("codes")
  void getValue_shouldReturnCorrectResult(C code, V expectedValue) {
    // when
    var result = getValue(code);

    // then
    assertThat(result).isEqualTo(ofNullable(expectedValue));
  }

  @ParameterizedTest
  @MethodSource("values")
  void getCode_shouldReturnCorrectResult(V value, C expectedCode) {
    // when
    var result = getCode(value);

    // then
    assertThat(result).isEqualTo(ofNullable(expectedCode));
  }

  protected abstract Optional<V> getValue(C code);

  protected abstract Optional<C> getCode(V value);

  protected abstract Stream<Arguments> codes();

  protected abstract Stream<Arguments> values();
}
