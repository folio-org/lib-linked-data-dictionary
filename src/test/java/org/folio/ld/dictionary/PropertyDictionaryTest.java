package org.folio.ld.dictionary;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

class PropertyDictionaryTest {

  @ParameterizedTest
  @CsvSource({
    "http://bibfra.me/vocab/library/accessibilityNote, ACCESSIBILITY_NOTE",
    "http://bibfra.me/vocab/library/additionalPhysicalForm, ADDITIONAL_PHYSICAL_FORM",
    "http://bibfra.me/vocab/library/titles, TITLES",
    "http://bibfra.me/vocab/library/bibliographyNote, BIBLIOGRAPHY_NOTE",
    "http://bibfra.me/vocab/library/statementOfResponsibility, STATEMENT_OF_RESPONSIBILITY",
  })
  void fromValue_shouldReturnCorrectEnum(String value, PropertyDictionary expectedEnum) {
    // when
    var result = PropertyDictionary.fromValue(value);

    // then
    assertThat(result).contains(expectedEnum);
  }

  @Test
  void fromNullValue_shouldNotContainValue() {
    // when
    var result = PropertyDictionary.fromValue(null);

    // then
    assertThat(result)
      .isEmpty();
  }

  @ParameterizedTest
  @ValueSource(strings = {"", " ", "non existing value"})
  void fromInvalidValue_shouldNotContainValue(String value) {
    // when
    var result = PropertyDictionary.fromValue(value);

    // then
    assertThat(result)
      .isEmpty();
  }
}
