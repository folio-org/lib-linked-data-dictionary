package org.folio.ld.dictionary;

import lombok.Getter;
import org.folio.ld.dictionary.model.ResourceType;

@Getter
public enum ResourceTypeDictionary implements ResourceType {
  ANNOTATION("http://bibfra.me/vocab/lite/Annotation", -2716756821684516448L),
  CARRIER("http://bibfra.me/vocab/marc/Carrier", 421212736624329420L),
  CATEGORY("http://bibfra.me/vocab/lite/Category", -7237503330348194663L),
  CATEGORY_SET("http://bibfra.me/vocab/lite/CategorySet", -1236482431314522895L),
  CONCEPT("http://bibfra.me/vocab/lite/Concept", 6012322771220013009L),
  COPYRIGHT_EVENT("http://bibfra.me/vocab/lite/CopyrightEvent", 7138672891049112604L),
  FAMILY("http://bibfra.me/vocab/lite/Family", -1114722094452060721L),
  FORM("http://bibfra.me/vocab/lite/Form", 6102579518025542780L),
  IDENTIFIER("http://bibfra.me/vocab/lite/Identifier", 8535666185887263660L),
  ID_EAN("http://bibfra.me/vocab/identifier/Ean", 219046076164824264L),
  ID_ISBN("http://library.link/identifier/ISBN", 3962988838506018431L),
  ID_LCCN("http://library.link/identifier/LCCN", -5891299401617387451L),
  ID_LOCAL("http://bibfra.me/vocab/lite/LocalId", 4097848952412986786L),
  ID_UNKNOWN("http://library.link/identifier/UNKNOWN", -1259663971045664909L),
  INSTANCE("http://bibfra.me/vocab/lite/Instance", -6368122725352320482L),
  ITEM("http://bibfra.me/vocab/lite/Item", -1796261916093733980L),
  JURISDICTION("http://bibfra.me/vocab/lite/Jurisdiction", 4072387342118066928L),
  MEDIA("http://bibfra.me/vocab/marc/Media", 6927281723593087650L),
  MEETING("http://bibfra.me/vocab/lite/Meeting", 8312970713564449340L),
  ORGANIZATION("http://bibfra.me/vocab/lite/Organization", -2724163952136761329L),
  PARALLEL_TITLE("http://bibfra.me/vocab/marc/ParallelTitle", -7668047008344689015L),
  PERSON("http://bibfra.me/vocab/lite/Person", 3745950457458968285L),
  PLACE("http://bibfra.me/vocab/lite/Place", 7862842856693929565L),
  PROVIDER_EVENT("http://bibfra.me/vocab/lite/ProviderEvent", -1414513180123539856L),
  STATUS("http://bibfra.me/vocab/marc/Status", -5546355039147771611L),
  SUPPLEMENTARY_CONTENT("http://bibfra.me/vocab/marc/SupplementaryContent", 3849426639441431097L),
  TEMPORAL("http://bibfra.me/vocab/lite/Temporal", 4805333230697475193L),
  TITLE("http://bibfra.me/vocab/marc/Title", 1422402142581052159L),
  TOPIC("http://bibfra.me/vocab/lite/Topic", -3096999901507785420L),
  VARIANT_TITLE("http://bibfra.me/vocab/marc/VariantTitle", 4846800426734929825L),
  WORK("http://bibfra.me/vocab/lite/Work", -3114907246856331309L);

  private final String uri;
  private final Long hash;

  ResourceTypeDictionary(String uri, Long hash) {
    this.uri = uri;
    this.hash = hash;
  }

}
