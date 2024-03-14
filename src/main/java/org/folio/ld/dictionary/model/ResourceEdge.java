package org.folio.ld.dictionary.model;

import static java.util.Optional.ofNullable;

import java.util.Objects;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.folio.ld.dictionary.PredicateDictionary;

@Data
@Accessors(chain = true)
@RequiredArgsConstructor
public class ResourceEdge {

  @ToString.Exclude
  private final Resource source;
  private final Resource target;
  private final PredicateDictionary predicate;
  @ToString.Exclude
  private ResourceEdgePk id = new ResourceEdgePk();

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ResourceEdge that = (ResourceEdge) o;
    return Objects.equals(getSourceHash(), that.getSourceHash())
      && Objects.equals(getTargetHash(), that.getTargetHash())
      && Objects.equals(getPredicateHash(), that.getPredicateHash());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getSourceHash(), getTargetHash(), getPredicateHash());
  }

  private Long getSourceHash() {
    return ofNullable(id)
      .map(ResourceEdgePk::getSourceHash)
      .or(() -> ofNullable(source).map(Resource::getResourceHash))
      .orElse(null);
  }

  private Long getTargetHash() {
    return ofNullable(id)
      .map(ResourceEdgePk::getTargetHash)
      .or(() -> ofNullable(target).map(Resource::getResourceHash))
      .orElse(null);
  }

  private Long getPredicateHash() {
    return ofNullable(id)
      .map(ResourceEdgePk::getPredicateHash)
      .or(() -> ofNullable(predicate).map(Predicate::getHash))
      .orElse(null);
  }
}
