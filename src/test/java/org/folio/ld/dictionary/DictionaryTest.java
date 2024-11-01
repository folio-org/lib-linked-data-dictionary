package org.folio.ld.dictionary;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.HashSet;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class DictionaryTest {

  private static Stream<Arguments> dictionaries() {
    return Stream.of(
      Arguments.of(
        PropertyDictionary.class,
        (Function<PropertyDictionary, String>) PropertyDictionary::getValue
      ),
      Arguments.of(
        PredicateDictionary.class,
        (Function<PredicateDictionary, String>) PredicateDictionary::getUri
      ),
      Arguments.of(
        ResourceTypeDictionary.class,
        (Function<ResourceTypeDictionary, String>) ResourceTypeDictionary::getUri
      ));
  }

  @ParameterizedTest
  @MethodSource("dictionaries")
  <E> void dictionariesShouldNotHaveDuplicatedValues(Class<E> enumClass, Function<E, String> uniqueFunction) {
    shouldNotHaveDuplicatedValues(enumClass, uniqueFunction);
  }

  <E> void shouldNotHaveDuplicatedValues(Class<E> enumClass, Function<E, String> uniqueFunction) {
    var uniques = new HashSet<>();
    var duplicates = Stream.of(enumClass.getEnumConstants())
      .map(uniqueFunction)
      .filter(val -> !uniques.add(val))
      .collect(Collectors.toSet());

    assertThat(duplicates)
      .as("%s contains duplicated property values", enumClass)
      .isEmpty();
  }
}
