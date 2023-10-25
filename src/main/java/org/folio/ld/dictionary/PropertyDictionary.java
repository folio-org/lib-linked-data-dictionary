package org.folio.ld.dictionary;

import lombok.Getter;

@Getter
public enum PropertyDictionary {

  ASSIGNING_SOURCE("http://bibfra.me/vocab/marc/localIdAssigningSource"),
  CODE("http://bibfra.me/vocab/marc/code"),
  DATE("http://bibfra.me/vocab/lite/date"),
  DIMENSIONS("http://bibfra.me/vocab/marc/dimensions"),
  EAN_VALUE("http://bibfra.me/vocab/marc/ean"),
  EDITION_STATEMENT("http://bibfra.me/vocab/marc/edition"),
  EXTENT("http://bibfra.me/vocab/lite/extent"),
  ISSUANCE("http://bibfra.me/vocab/marc/issuance"),
  LABEL("http://bibfra.me/vocab/lite/label"),
  LABEL_RDF("http://www.w3.org/2000/01/rdf-schema#label"),
  LANGUAGE("http://bibfra.me/vocab/lite/language"),
  LCNAF_ID("http://bibfra.me/vocab/marc/lcnafId"),
  LINK("http://bibfra.me/vocab/lite/link"),
  LOCAL_ID_VALUE("http://bibfra.me/vocab/marc/localId"),
  MAIN_TITLE("http://bibfra.me/vocab/marc/mainTitle"),
  NAME("http://bibfra.me/vocab/lite/name"),
  NON_SORT_NUM("http://bibfra.me/vocab/bflc/nonSortNum"),
  NOTE("http://bibfra.me/vocab/lite/note"),
  PART_NAME("http://bibfra.me/vocab/marc/partName"),
  PART_NUMBER("http://bibfra.me/vocab/marc/partNumber"),
  PROJECTED_PROVISION_DATE("http://bibfra.me/vocab/bflc/projectedProvisionDate"),
  PROVIDER_DATE("http://bibfra.me/vocab/lite/providerDate"),
  QUALIFIER("http://bibfra.me/vocab/marc/qualifier"),
  STATEMENT_OF_RESPONSIBILITY("http://bibfra.me/vocab/marc/statementOfResponsibility"),
  RESPONSIBILITY_STATEMENT("http://bibfra.me/vocab/marc/responsibilityStatement"),
  SIMPLE_PLACE("http://bibfra.me/vocab/lite/place"),
  SOURCE("http://bibfra.me/vocab/marc/source"),
  SUBTITLE("http://bibfra.me/vocab/marc/subTitle"),
  SUMMARY("http://bibfra.me/vocab/marc/summary"),
  TABLE_OF_CONTENTS("http://bibfra.me/vocab/marc/tableOfContents"),
  TARGET_AUDIENCE("http://bibfra.me/vocab/marc/targetAudience"),
  TERM("http://bibfra.me/vocab/marc/term"),
  VARIANT_TYPE("http://bibfra.me/vocab/marc/variantType");

  private final String value;

  PropertyDictionary(java.lang.String value) {
    this.value = value;
  }
}
