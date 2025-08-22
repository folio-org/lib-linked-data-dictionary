package org.folio.ld.dictionary.specific;

import static java.util.Optional.ofNullable;

import com.google.common.collect.ImmutableBiMap;
import java.util.Optional;

public final class IllustrationDictionary {
  private static final ImmutableBiMap<Character, String> VALUE_MAP = new ImmutableBiMap.Builder<Character, String>()
    .put('a', "http://id.loc.gov/vocabulary/millus/ill")
    .put('b', "http://id.loc.gov/vocabulary/millus/map")
    .put('c', "http://id.loc.gov/vocabulary/millus/por")
    .put('d', "http://id.loc.gov/vocabulary/millus/chr")
    .put('e', "http://id.loc.gov/vocabulary/millus/pln")
    .put('f', "http://id.loc.gov/vocabulary/millus/plt")
    .put('g', "http://id.loc.gov/vocabulary/millus/mus")
    .put('h', "http://id.loc.gov/vocabulary/millus/fac")
    .put('i', "http://id.loc.gov/vocabulary/millus/coa")
    .put('j', "http://id.loc.gov/vocabulary/millus/gnt")
    .put('k', "http://id.loc.gov/vocabulary/millus/for")
    .put('l', "http://id.loc.gov/vocabulary/millus/sam")
    .put('m', "http://id.loc.gov/vocabulary/millus/pho")
    .put('o', "http://id.loc.gov/vocabulary/millus/pht")
    .put('p', "http://id.loc.gov/vocabulary/millus/ilm")
    .build();

  private IllustrationDictionary() {
  }

  public static Optional<String> getValue(Character code) {
    return ofNullable(VALUE_MAP.get(code));
  }

  public static Optional<Character> getCode(String value) {
    return ofNullable(VALUE_MAP.inverse().get(value));
  }
}
