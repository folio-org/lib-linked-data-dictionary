package org.folio.ld.dictionary.model;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum ResourceSource {
  LINKED_DATA("LINKED_DATA"),
  MARC("MARC");

  private final String value;
}
