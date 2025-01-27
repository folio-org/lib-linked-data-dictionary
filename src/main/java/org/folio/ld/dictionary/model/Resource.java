package org.folio.ld.dictionary.model;

import static java.util.Objects.isNull;
import static java.util.stream.Collectors.toCollection;

import com.fasterxml.jackson.databind.JsonNode;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.Set;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.folio.ld.dictionary.ResourceTypeDictionary;

@Data
@NoArgsConstructor
@Accessors(chain = true)
@EqualsAndHashCode(of = "id")
@SuppressWarnings("javaarchitecture:S7027")
public class Resource {

  private Long id;

  private String label;

  private JsonNode doc;

  private FolioMetadata folioMetadata;

  private RawMarc unmappedMarc;

  private Date createdDate;

  private Date updatedDate;

  private Set<ResourceTypeDictionary> types = new LinkedHashSet<>();

  @ToString.Exclude
  private Set<ResourceEdge> outgoingEdges = new LinkedHashSet<>();

  @ToString.Exclude
  private Set<ResourceEdge> incomingEdges = new LinkedHashSet<>();

  public Resource addType(ResourceTypeDictionary type) {
    if (isNull(types)) {
      types = new LinkedHashSet<>();
    }
    types.add(type);
    return this;
  }

  public Resource addOutgoingEdge(ResourceEdge resourceEdge) {
    if (isNull(outgoingEdges)) {
      outgoingEdges = new LinkedHashSet<>();
    }
    outgoingEdges.add(resourceEdge);
    return this;
  }

  public Set<String> getTypeNames() {
    return types.stream().map(Enum::name).collect(toCollection(LinkedHashSet::new));
  }

}
