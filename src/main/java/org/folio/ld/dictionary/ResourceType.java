package org.folio.ld.dictionary;

public enum ResourceType {
  ANNOTATION("http://bibfra.me/vocab/lite/Annotation", -2716756821684516448L),
  CARRIER("http://bibfra.me/vocab/marc/Carrier", 421212736624329420L),
  CATEGORY("http://bibfra.me/vocab/lite/Category", -7237503330348194663L),
  COPYRIGHT_EVENT("http://bibfra.me/vocab/lite/CopyrightEvent", 7138672891049112604L),
  FAMILY("http://bibfra.me/vocab/lite/Family", -1114722094452060721L),
  ID_EAN("http://bibfra.me/vocab/identifier/Ean", 219046076164824264L),
  ID_ISBN("http://library.link/identifier/ISBN", 3962988838506018431L),
  ID_LCCN("http://library.link/identifier/LCCN", -5891299401617387451L),
  ID_LOCAL("http://bibfra.me/vocab/lite/LocalId", 4097848952412986786L),
  ID_UNKNOWN("http://library.link/identifier/UNKNOWN", -1259663971045664909L),
  INSTANCE("http://bibfra.me/vocab/lite/Instance", -6368122725352320482L),
  ITEM("http://bibfra.me/vocab/lite/Item", -1796261916093733980L),
  MEDIA("http://bibfra.me/vocab/marc/Media", 6927281723593087650L),
  MEETING("http://bibfra.me/vocab/lite/Meeting", 8312970713564449340L),
  ORGANIZATION("http://bibfra.me/vocab/lite/Organization", -2724163952136761329L),
  PARALLEL_TITLE("http://bibfra.me/vocab/marc/ParallelTitle", -7668047008344689015L),
  PERSON("http://bibfra.me/vocab/lite/Person", 3745950457458968285L),
  PLACE("http://bibfra.me/vocab/lite/Place", 7862842856693929565L),
  PROVIDER_EVENT("http://bibfra.me/vocab/lite/ProviderEvent", -1414513180123539856L),
  STATUS("http://bibfra.me/vocab/marc/Status", -5546355039147771611L),
  TITLE("http://bibfra.me/vocab/marc/Title", 1422402142581052159L),
  VARIANT_TITLE("http://bibfra.me/vocab/marc/VariantTitle", 4846800426734929825L),
  WORK("http://bibfra.me/vocab/lite/Work", -3114907246856331309L);

  public final String uri;
  public final Long hash;

  ResourceType(String uri, Long hash) {
    this.uri = uri;
    this.hash = hash;
  }

}
