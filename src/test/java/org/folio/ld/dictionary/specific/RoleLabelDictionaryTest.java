package org.folio.ld.dictionary.specific;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.folio.ld.dictionary.PredicateDictionary.ABRIDGER;
import static org.folio.ld.dictionary.PredicateDictionary.COMPLAINANT_APPELLANT;
import static org.folio.ld.dictionary.PredicateDictionary.METAL_ENGRAVER;

import java.util.Optional;
import java.util.stream.Stream;
import org.folio.ld.dictionary.PredicateDictionary;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class RoleLabelDictionaryTest {

  @ParameterizedTest
  @MethodSource("labels")
  void getValue_shouldReturnCorrectResult(String label, PredicateDictionary expectedValue) {
    assertThat(RoleLabelDictionary.getValue(label)).isEqualTo(Optional.ofNullable(expectedValue));
  }

  private static Stream<Arguments> labels() {
    return Stream.of(
      Arguments.of("abridger", ABRIDGER),
      Arguments.of("Abridger", ABRIDGER),
      Arguments.of("complainant-appellant", COMPLAINANT_APPELLANT),
      Arguments.of(" complainant appellant", COMPLAINANT_APPELLANT),
      Arguments.of("Complainant Appellant ", COMPLAINANT_APPELLANT),
      Arguments.of("metal-engraver", METAL_ENGRAVER),
      Arguments.of("Metal Engraver", METAL_ENGRAVER),
      Arguments.of("invalidLabel", null),
      Arguments.of("", null),
      Arguments.of(null, null),
      Arguments.of("NULL", null)
    );
  }
}
