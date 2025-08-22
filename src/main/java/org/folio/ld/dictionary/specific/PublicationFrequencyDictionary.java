package org.folio.ld.dictionary.specific;

import static java.util.Optional.ofNullable;

import com.google.common.collect.ImmutableBiMap;
import java.util.Optional;

public final class PublicationFrequencyDictionary {
  private static final ImmutableBiMap<Character, String> VALUE_MAP = new ImmutableBiMap.Builder<Character, String>()
    .put('a', "http://id.loc.gov/vocabulary/frequencies/ann")
    .put('b', "http://id.loc.gov/vocabulary/frequencies/bmn")
    .put('c', "http://id.loc.gov/vocabulary/frequencies/swk")
    .put('d', "http://id.loc.gov/vocabulary/frequencies/dyl")
    .put('e', "http://id.loc.gov/vocabulary/frequencies/bwk")
    .put('f', "http://id.loc.gov/vocabulary/frequencies/san")
    .put('g', "http://id.loc.gov/vocabulary/frequencies/bin")
    .put('h', "http://id.loc.gov/vocabulary/frequencies/ten")
    .put('i', "http://id.loc.gov/vocabulary/frequencies/ttw")
    .put('j', "http://id.loc.gov/vocabulary/frequencies/ttm")
    .put('k', "http://id.loc.gov/vocabulary/frequencies/con")
    .put('m', "http://id.loc.gov/vocabulary/frequencies/mon")
    .put('n', "http://id.loc.gov/vocabulary/frequencies/irr")
    .put('q', "http://id.loc.gov/vocabulary/frequencies/qrt")
    .put('s', "http://id.loc.gov/vocabulary/frequencies/smn")
    .put('t', "http://id.loc.gov/vocabulary/frequencies/tty")
    .put('w', "http://id.loc.gov/vocabulary/frequencies/wkl")
    .build();

  private PublicationFrequencyDictionary() {
  }

  public static Optional<String> getValue(Character code) {
    return ofNullable(VALUE_MAP.get(code));
  }

  public static Optional<Character> getCode(String link) {
    return ofNullable(VALUE_MAP.inverse().get(link));
  }

}
