package org.folio.ld.dictionary.model;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.stream.Stream;
import org.folio.ld.dictionary.PredicateDictionary;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class ResourceEdgeTest {

  @ParameterizedTest
  @MethodSource("provideResourceEdgesForEqualsTest")
  void testEquals(ResourceEdge edge1, ResourceEdge edge2, boolean expected) {
    assertThat(edge1.equals(edge2)).isEqualTo(expected);
  }

  static Stream<Arguments> provideResourceEdgesForEqualsTest() {
    return Stream.of(
      Arguments.of(
        new ResourceEdge(new Resource().setId(1L), new Resource().setId(2L), PredicateDictionary.AUTHOR),
        new ResourceEdge(new Resource().setId(1L), new Resource().setId(2L), PredicateDictionary.AUTHOR),
        true
      ),

      // Different predicate
      Arguments.of(
        new ResourceEdge(new Resource().setId(1L), new Resource().setId(2L), PredicateDictionary.AUTHOR),
        new ResourceEdge(new Resource().setId(1L), new Resource().setId(2L), PredicateDictionary.ARTIST),
        false
      ),

      // Different source
      Arguments.of(
        new ResourceEdge(new Resource().setId(1L), new Resource().setId(2L), PredicateDictionary.AUTHOR),
        new ResourceEdge(new Resource().setId(3L), new Resource().setId(2L), PredicateDictionary.AUTHOR),
        false
      ),

      // Different target
      Arguments.of(
        new ResourceEdge(new Resource().setId(1L), new Resource().setId(2L), PredicateDictionary.AUTHOR),
        new ResourceEdge(new Resource().setId(1L), new Resource().setId(3L), PredicateDictionary.AUTHOR),
        false
      )
    );
  }
}
