package org.folio.ld.dictionary.specific;

import static org.folio.ld.dictionary.PredicateDictionary.ABRIDGER;
import static org.folio.ld.dictionary.PredicateDictionary.ACTOR;
import static org.folio.ld.dictionary.PredicateDictionary.AUTHOR;
import static org.folio.ld.dictionary.PredicateDictionary.IS_PART_OF;
import static org.folio.ld.dictionary.PredicateDictionary.NULL;

import java.util.Optional;
import java.util.stream.Stream;
import org.folio.ld.dictionary.PredicateDictionary;
import org.junit.jupiter.params.provider.Arguments;

class RoleDictionaryTest extends SpecificDictionaryTest<String, PredicateDictionary> {

  @Override
  protected Optional<PredicateDictionary> getValue(String code) {
    return RoleDictionary.getValue(code);
  }

  @Override
  protected Optional<String> getCode(PredicateDictionary value) {
    return RoleDictionary.getCode(value);
  }

  @Override
  protected Stream<Arguments> codes() {
    return Stream.of(
      Arguments.of("abr", ABRIDGER),
      Arguments.of("act", ACTOR),
      Arguments.of("aut", AUTHOR),
      Arguments.of("invalidCode", null),
      Arguments.of("", null),
      Arguments.of(null, null)
    );
  }

  @Override
  protected Stream<Arguments> values() {
    return Stream.of(
      Arguments.of(ABRIDGER, "abr"),
      Arguments.of(ACTOR, "act"),
      Arguments.of(AUTHOR, "aut"),
      Arguments.of(IS_PART_OF, null),
      Arguments.of(NULL, null),
      Arguments.of(null, null)
    );
  }

}
