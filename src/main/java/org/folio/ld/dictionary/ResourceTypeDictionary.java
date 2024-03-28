package org.folio.ld.dictionary;

import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;
import lombok.Getter;
import org.folio.ld.dictionary.model.ResourceType;
import org.folio.ld.dictionary.util.HashUtils;

@Getter
public enum ResourceTypeDictionary implements ResourceType {
  ANNOTATION("http://bibfra.me/vocab/lite/Annotation"),
  CATEGORY("http://bibfra.me/vocab/lite/Category"),
  CATEGORY_SET("http://bibfra.me/vocab/lite/CategorySet"),
  CONCEPT("http://bibfra.me/vocab/lite/Concept"),
  COPYRIGHT_EVENT("http://bibfra.me/vocab/lite/CopyrightEvent"),
  FAMILY("http://bibfra.me/vocab/lite/Family"),
  FORM("http://bibfra.me/vocab/lite/Form"),
  IDENTIFIER("http://bibfra.me/vocab/lite/Identifier"),
  ID_EAN("http://bibfra.me/vocab/identifier/Ean"),
  ID_ISBN("http://library.link/identifier/ISBN"),
  ID_LCCN("http://library.link/identifier/LCCN"),
  ID_LOCAL("http://bibfra.me/vocab/lite/LocalId"),
  ID_UNKNOWN("http://library.link/identifier/UNKNOWN"),
  INSTANCE("http://bibfra.me/vocab/lite/Instance"),
  ITEM("http://bibfra.me/vocab/lite/Item"),
  JURISDICTION("http://bibfra.me/vocab/lite/Jurisdiction"),
  MEETING("http://bibfra.me/vocab/lite/Meeting"),
  ORGANIZATION("http://bibfra.me/vocab/lite/Organization"),
  PARALLEL_TITLE("http://bibfra.me/vocab/marc/ParallelTitle"),
  PERSON("http://bibfra.me/vocab/lite/Person"),
  PLACE("http://bibfra.me/vocab/lite/Place"),
  PROVIDER_EVENT("http://bibfra.me/vocab/lite/ProviderEvent"),
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
