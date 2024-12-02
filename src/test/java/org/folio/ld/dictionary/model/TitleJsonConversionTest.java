package org.folio.ld.dictionary.model;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.folio.ld.dictionary.test.TestUtil.OBJECT_MAPPER;
import static org.folio.ld.dictionary.test.TestUtil.getParallelTitle;
import static org.folio.ld.dictionary.test.TestUtil.getPrimaryTitle;
import static org.folio.ld.dictionary.test.TestUtil.getVariantTitle;
import static org.folio.ld.dictionary.test.TestUtil.loadResourceAsString;

import com.fasterxml.jackson.core.JsonProcessingException;
import java.util.stream.Stream;
import org.folio.ld.dictionary.model.title.Title;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class TitleJsonConversionTest {

  private static Stream<Arguments> titles() {
    return Stream.of(
      Arguments.of(getPrimaryTitle(), "primary_title.json"),
      Arguments.of(getParallelTitle(), "parallel_title.json"),
      Arguments.of(getVariantTitle(), "variant_title.json")
    );
  }

  @ParameterizedTest
  @MethodSource("titles")
  void testSerialization(Title title, String expectedFilename) throws JsonProcessingException {
    // given
    var expected = loadResourceAsString(expectedFilename);

    // when
    var result = OBJECT_MAPPER.writeValueAsString(title);

    // then
    assertThat(result).isEqualTo(expected);
  }

  @ParameterizedTest
  @MethodSource("titles")
  void testDeserialization(Title expected, String sourceFilename) throws JsonProcessingException {
    // given
    var sourceJson = loadResourceAsString(sourceFilename);

    // when
    var result = OBJECT_MAPPER.readValue(sourceJson, Title.class);

    // then
    assertThat(result).isEqualTo(expected);
  }

}
