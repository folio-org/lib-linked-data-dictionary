package org.folio.ld.dictionary.model.title;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.ArrayList;
import java.util.List;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class VariantTitle extends Title {

  @JsonProperty("http://bibfra.me/vocab/lite/note")
  public final List<String> notes = new ArrayList<>();

  @JsonProperty("http://bibfra.me/vocab/lite/date")
  public final List<String> dates = new ArrayList<>();

  @JsonProperty("http://bibfra.me/vocab/marc/variantType")
  public final List<String> variantTypes = new ArrayList<>();

}
