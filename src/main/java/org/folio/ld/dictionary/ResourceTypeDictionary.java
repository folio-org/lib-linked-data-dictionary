package org.folio.ld.dictionary;

import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;
import lombok.Getter;
import org.folio.ld.dictionary.model.ResourceType;
import org.folio.ld.dictionary.util.HashUtils;

@Getter
public enum ResourceTypeDictionary implements ResourceType {
  ABBREVIATED_TITLE("http://bibfra.me/vocab/marc/AbbreviatedTitle"),
  AGENT("http://bibfra.me/vocab/lite/Agent"),
  ANNOTATION("http://bibfra.me/vocab/lite/Annotation"),
  BOOKS("http://bibfra.me/vocab/marc/Books"),
  CATEGORY("http://bibfra.me/vocab/lite/Category"),
  CATEGORY_SET("http://bibfra.me/vocab/lite/CategorySet"),
  CLASSIFICATION("http://bibfra.me/vocab/lite/Classification"),
  CONCEPT("http://bibfra.me/vocab/lite/Concept"),
  CONTINUING_RESOURCES("http://bibfra.me/vocab/marc/ContinuingResources"),
  COPYRIGHT_EVENT("http://bibfra.me/vocab/lite/CopyrightEvent"),
  DISSERTATION("http://bibfra.me/vocab/scholar/Dissertation"),
  EXTENT("http://bibfra.me/vocab/lite/Extent"),
  FAMILY("http://bibfra.me/vocab/lite/Family"),
  FORM("http://bibfra.me/vocab/lite/Form"),
  FREQUENCY("http://bibfra.me/vocab/lite/Frequency"),
  IDENTIFIER("http://bibfra.me/vocab/lite/Identifier"),
  ID_CODEN("http://library.link/identifier/CODEN"),
  ID_IAN("http://library.link/identifier/IAN"),
  ID_ISBN("http://library.link/identifier/ISBN"),
  ID_ISSN("http://library.link/identifier/ISSN"),
  ID_LCCN("http://library.link/identifier/LCCN"),
  ID_LOCAL("http://bibfra.me/vocab/lite/LocalId"),
  ID_STRN("http://library.link/identifier/STRN"),
  ID_UNKNOWN("http://library.link/identifier/UNKNOWN"),
  INSTANCE("http://bibfra.me/vocab/lite/Instance"),
  ITEM("http://bibfra.me/vocab/lite/Item"),
  JURISDICTION("http://bibfra.me/vocab/lite/Jurisdiction"),
  LANGUAGE_CATEGORY("http://bibfra.me/vocab/lite/LanguageCategory"),
  MEETING("http://bibfra.me/vocab/lite/Meeting"),
  ORGANIZATION("http://bibfra.me/vocab/lite/Organization"),
  PARALLEL_TITLE("http://bibfra.me/vocab/marc/ParallelTitle"),
  PERSON("http://bibfra.me/vocab/lite/Person"),
  PLACE("http://bibfra.me/vocab/lite/Place"),
  PROVIDER_EVENT("http://bibfra.me/vocab/lite/ProviderEvent"),
  SERIES("http://bibfra.me/vocab/lite/Series"),
  STATUS("http://bibfra.me/vocab/marc/Status"),
  SUPPLEMENTARY_CONTENT("http://bibfra.me/vocab/marc/SupplementaryContent"),
  TEMPORAL("http://bibfra.me/vocab/lite/Temporal"),
  TITLE("http://bibfra.me/vocab/marc/Title"),
  TOPIC("http://bibfra.me/vocab/lite/Topic"),
  VARIANT_TITLE("http://bibfra.me/vocab/marc/VariantTitle"),
  WORK("http://bibfra.me/vocab/lite/Work");

  private final String uri;
  private final Long hash;

  ResourceTypeDictionary(String uri) {
    this.uri = uri;
    this.hash = HashUtils.hash(uri);
  }

  public static Optional<ResourceTypeDictionary> fromUri(String uri) {
    return Arrays.stream(ResourceTypeDictionary.values())
      .filter(type -> Objects.equals(uri, type.getUri()))
      .findFirst();
  }
}
