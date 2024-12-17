package org.folio.ld.dictionary.model.title;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import java.util.ArrayList;
import java.util.List;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@ToString
@EqualsAndHashCode
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
@JsonSubTypes({
  @JsonSubTypes.Type(value = ParallelTitle.class, name = "Parallel"),
  @JsonSubTypes.Type(value = PrimaryTitle.class, name = "Primary"),
  @JsonSubTypes.Type(value = VariantTitle.class, name = "Variant")
})
public abstract class Title {

  @JsonProperty("http://bibfra.me/vocab/marc/partName")
  public final List<String> partNames = new ArrayList<>();

  @JsonProperty("http://bibfra.me/vocab/marc/partNumber")
  public final List<String> partNumbers = new ArrayList<>();

  @JsonProperty("http://bibfra.me/vocab/marc/mainTitle")
  public final List<String> mainTitles = new ArrayList<>();

  @JsonProperty("http://bibfra.me/vocab/marc/subTitle")
  public final List<String> subTitles = new ArrayList<>();

}
