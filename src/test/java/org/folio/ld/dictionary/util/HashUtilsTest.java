package org.folio.ld.dictionary.util;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.ValueSource;

class HashUtilsTest {

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
}
