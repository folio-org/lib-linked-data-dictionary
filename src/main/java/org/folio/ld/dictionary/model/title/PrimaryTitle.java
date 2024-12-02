package org.folio.ld.dictionary.model.title;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.ArrayList;
import java.util.List;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class PrimaryTitle extends Title {

  @JsonProperty("http://bibfra.me/vocab/bflc/nonSortNum")
  public final List<String> nonSortNumbers = new ArrayList<>();

}
