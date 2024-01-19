package org.folio.ld.dictionary;

import java.util.Arrays;
import java.util.Optional;
import lombok.Getter;

@Getter
public enum PropertyDictionary {

  ACCESSIBILITY_NOTE("http://bibfra.me/vocab/marc/accessibilityNote"),
  ADDITIONAL_PHYSICAL_FORM("http://bibfra.me/vocab/marc/additionalPhysicalForm"),
  AFFILIATION("http://bibfra.me/vocab/scholar/affiliation"),
  ASSIGNING_SOURCE("http://bibfra.me/vocab/marc/localIdAssigningSource"),
  ATTRIBUTION("http://bibfra.me/vocab/marc/attribution"),
  AUTHORITY_LINK("http://bibfra.me/vocab/lite/authorityLink"),
  BIBLIOGRAPHY_NOTE("http://bibfra.me/vocab/marc/bibliographyNote"),
  CHRONOLOGICAL_SUBDIVISION("http://bibfra.me/vocab/marc/chronologicalSubdivision"),
  CITATION_COVERAGE("http://bibfra.me/vocab/marc/citationCoverage"),
  CODE("http://bibfra.me/vocab/marc/code"),
  COMPUTER_DATA_NOTE("http://bibfra.me/vocab/marc/computerDataNote"),
  CONTROL_FIELD("http://bibfra.me/vocab/marc/controlField"),
  CREDITS_NOTE("http://bibfra.me/vocab/marc/creditsNote"),
  DATA_QUALITY("http://bibfra.me/vocab/marc/dataQuality"),
  DATE("http://bibfra.me/vocab/lite/date"),
  DATES_OF_PUBLICATION_NOTE("http://bibfra.me/vocab/marc/datesOfPublicationNote"),
  DESCRIPTION_SOURCE_NOTE("http://bibfra.me/vocab/marc/descriptionSourceNote"),
  DIMENSIONS("http://bibfra.me/vocab/marc/dimensions"),
  EAN_VALUE("http://bibfra.me/vocab/marc/ean"),
  EDITION_STATEMENT("http://bibfra.me/vocab/marc/edition"),
  ENTITY_AND_ATTRIBUTE_INFORMATION("http://bibfra.me/vocab/marc/entityAndAttributeInformation"),
  EQUIVALENT("http://bibfra.me/vocab/lite/equivalent"),
  EXHIBITIONS_NOTE("http://bibfra.me/vocab/marc/exhibitionsNote"),
  EXTENT("http://bibfra.me/vocab/lite/extent"),
  FIELD_LINK("http://bibfra.me/vocab/marc/fieldLink"),
  FORMER_TITLE_NOTE("http://bibfra.me/vocab/marc/formerTitleNote"),
  FORM_SUBDIVISION("http://bibfra.me/vocab/marc/formSubdivision"),
  FUNDING_INFORMATION("http://bibfra.me/vocab/marc/fundingInformation"),
  GENERAL_SUBDIVISION("http://bibfra.me/vocab/marc/generalSubdivision"),
  GEOGRAPHIC_COVERAGE("http://bibfra.me/vocab/marc/geographicCoverage"),
  GEOGRAPHIC_SUBDIVISION("http://bibfra.me/vocab/marc/geographicSubdivision"),
  GOVERNING_ACCESS_NOTE("http://bibfra.me/vocab/marc/governingAccessNote"),
  INFORMATION_ABOUT_DOCUMENTATION("http://bibfra.me/vocab/marc/informationAboutDocumentation"),
  INFORMATION_RELATING_TO_COPYRIGHT_STATUS("http://bibfra.me/vocab/marc/informationRelatingToCopyrightStatus"),
  ISSUANCE("http://bibfra.me/vocab/marc/issuance"),
  ISSUANCE_NOTE("http://bibfra.me/vocab/marc/issuanceNote"),
  ISSUING_BODY("http://bibfra.me/vocab/marc/issuingBody"),
  LABEL("http://bibfra.me/vocab/lite/label"),
  LABEL_RDF("http://www.w3.org/2000/01/rdf-schema#label"),
  LANGUAGE("http://bibfra.me/vocab/lite/language"),
  LANGUAGE_NOTE("http://bibfra.me/vocab/marc/languageNote"),
  LCNAF_ID("http://bibfra.me/vocab/marc/lcnafId"),
  LINK("http://bibfra.me/vocab/lite/link"),
  LINKAGE("http://bibfra.me/vocab/marc/linkage"),
  LOCAL_ID_VALUE("http://bibfra.me/vocab/marc/localId"),
  LOCATION_OF_EVENT("http://bibfra.me/vocab/marc/locationOfEvent"),
  LOCATION_OF_ORIGINALS_DUPLICATES("http://bibfra.me/vocab/marc/locationOfOriginalsDuplicates"),
  LOCATION_OF_OTHER_ARCHIVAL_MATERIAL("http://bibfra.me/vocab/marc/locationOfOtherArchivalMaterial"),
  MAIN_TITLE("http://bibfra.me/vocab/marc/mainTitle"),
  MATERIALS_SPECIFIED("http://bibfra.me/vocab/marc/materialsSpecified"),
  MISC_INFO("http://bibfra.me/vocab/marc/miscInfo"),
  NAME("http://bibfra.me/vocab/lite/name"),
  NAME_ALTERNATIVE("http://bibfra.me/vocab/lite/nameAlternative"),
  NON_SORT_NUM("http://bibfra.me/vocab/bflc/nonSortNum"),
  NOTE("http://bibfra.me/vocab/lite/note"),
  NUMERATION("http://bibfra.me/vocab/marc/numeration"),
  ORIGINAL_VERSION_NOTE("http://bibfra.me/vocab/marc/originalVersionNote"),
  OTHER_EVENT_INFORMATION("http://bibfra.me/vocab/marc/otherEventInformation"),
  PART_NAME("http://bibfra.me/vocab/marc/partName"),
  PART_NUMBER("http://bibfra.me/vocab/marc/partNumber"),
  PARTICIPANT_NOTE("http://bibfra.me/vocab/marc/participantNote"),
  PHYSICAL_DESCRIPTION("http://bibfra.me/vocab/marc/physicalDescription"),
  PROJECTED_PROVISION_DATE("http://bibfra.me/vocab/bflc/projectedProvisionDate"),
  PROVIDER_DATE("http://bibfra.me/vocab/lite/providerDate"),
  PUBLICATION_FREQUENCY("http://bibfra.me/vocab/marc/publicationFrequency"),
  QUALIFIER("http://bibfra.me/vocab/marc/qualifier"),
  REFERENCES("http://bibfra.me/vocab/marc/references"),
  RELATED_PARTS("http://bibfra.me/vocab/marc/relatedParts"),
  RELATOR_CODE("http://bibfra.me/vocab/marc/relator_code"),
  RELATOR_TERM("http://bibfra.me/vocab/marc/relator_term"),
  REPRODUCTION_NOTE("http://bibfra.me/vocab/marc/reproductionNote"),
  RESPONSIBILITY_STATEMENT("http://bibfra.me/vocab/marc/responsibilityStatement"),
  SCALE_NOTE("http://bibfra.me/vocab/marc/scaleNote"),
  SIMPLE_PLACE("http://bibfra.me/vocab/lite/place"),
  SOURCE("http://bibfra.me/vocab/marc/source"),
  STUDY_PROGRAM_NAME("http://bibfra.me/vocab/marc/studyProgramName"),
  SUBTITLE("http://bibfra.me/vocab/marc/subTitle"),
  SUMMARY("http://bibfra.me/vocab/marc/summary"),
  SUPPLEMENT("http://bibfra.me/vocab/marc/supplement"),
  SYSTEM_DETAILS("http://bibfra.me/vocab/marc/systemDetails"),
  SYSTEM_DETAILS_ACCESS_NOTE("http://bibfra.me/vocab/marc/systemDetailsAccessNote"),
  TABLE_OF_CONTENTS("http://bibfra.me/vocab/marc/tableOfContents"),
  TARGET_AUDIENCE("http://bibfra.me/vocab/marc/targetAudience"),
  TERM("http://bibfra.me/vocab/marc/term"),
  TITLES("http://bibfra.me/vocab/marc/titles"),
  TYPE_OF_REPORT("http://bibfra.me/vocab/marc/typeOfReport"),
  VARIANT_TYPE("http://bibfra.me/vocab/marc/variantType"),
  WITH_NOTE("http://bibfra.me/vocab/marc/withNote");

  private final String value;

  PropertyDictionary(java.lang.String value) {
    this.value = value;
  }

  public static Optional<PropertyDictionary> fromValue(String value) {
    return Arrays.stream(PropertyDictionary.values())
      .filter(property -> property.getValue().equals(value))
      .findFirst();
  }
}
