package org.folio.ld.dictionary.model;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;
import static java.util.stream.Collectors.toCollection;
import static org.folio.ld.dictionary.PredicateDictionary.TITLE;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.folio.ld.dictionary.ResourceTypeDictionary;
import org.folio.ld.dictionary.model.title.Title;

@Data
@NoArgsConstructor
@Accessors(chain = true)
@EqualsAndHashCode(of = "id")
@SuppressWarnings("javaarchitecture:S7027")
public class Resource {

  private Long id;

  private String label;

  private ObjectNode doc;

  private FolioMetadata folioMetadata;

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

  public ObjectNode getDocWithoutTitles() {
    if (isNull(doc) || !doc.has(TITLE.getUri())) {
      return doc;
    }
    var docCopy = doc.deepCopy();
    docCopy.remove(TITLE.getUri());
    return docCopy;
  }

  public void addTitle(Title title, ObjectMapper objectMapper) throws JsonProcessingException {
    if (nonNull(title)) {
      var currentTitles = getTitles(objectMapper);
      currentTitles.add(title);
      var titleArrayNode = objectMapper.createArrayNode();
      for (Title currentTitle : currentTitles) {
        var node = objectMapper.valueToTree(currentTitle);
        titleArrayNode.add(node);
      }
      if (isNull(doc)) {
        doc = objectMapper.createObjectNode();
      }
      doc.set(TITLE.getUri(), titleArrayNode);
    }
  }

  public List<Title> getTitles(ObjectMapper objectMapper) throws JsonProcessingException {
    if (isNull(doc) || !doc.has(TITLE.getUri())) {
      return new ArrayList<>();
    }
    var titlesNode = doc.get(TITLE.getUri());
    return objectMapper.treeToValue(titlesNode, new TypeReference<>() {
    });
  }

}
