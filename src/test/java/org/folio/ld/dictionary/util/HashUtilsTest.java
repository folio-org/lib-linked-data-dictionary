package org.folio.ld.dictionary.util;

import static org.assertj.core.api.Assertions.assertThat;
import static org.folio.ld.dictionary.test.TestUtil.loadResourceAsString;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

class HashUtilsTest {

  private static Stream<Arguments> provideInputAndExpectedBase64Url() {
    return Stream.of(
      Arguments.of("family.json", "jn6Zj1ZKlyM"),
      Arguments.of("form.json", "qltONOF0dsk"),
      Arguments.of("jurisdiction.json", "r6tXEArUyN4"),
      Arguments.of("meeting.json", "OuXkFaFFUww"),
      Arguments.of("organization.json", "0Y52jqlXs-Q"),
      Arguments.of("person.json", "WFcTSHUJd0E"),
      Arguments.of("place.json", "sljlirD2NAk"),
      Arguments.of("temporal.json", "pmdE_1EWFaU"),
      Arguments.of("topic.json", "qDzeEji3Cj4")
    );
  }

  @Test
  void hashNull_shouldReturnNull() {
    //when
    var hash = HashUtils.hash(null);

    //then
    assertThat(hash)
      .isNull();
  }

  @ParameterizedTest
  @EmptySource
  @ValueSource(strings = {" ", "empty", "some source"})
  void hashValue_shouldReturnNotNull(String input) {
    //when
    var hash = HashUtils.hash(input);

    //then
    assertThat(hash)
      .isNotNull();
  }

  @ParameterizedTest
  @MethodSource("provideInputAndExpectedBase64Url")
  void testLegacyFingerprintHash(String fileName, String expectedHash) {
    // given
    var input = loadResourceAsString("examples/" + fileName);

    // when
    var result = HashUtils.base64Url(input);

    // then
    assertEquals(expectedHash, result);
  }
}
