package org.folio.ld.dictionary;

public enum Predicate {
  ACCESS_LOCATION("http://bibfra.me/vocab/marc/accessLocation", -7597340833248531885L),
  CARRIER("http://bibfra.me/vocab/marc/carrier", -8262298498902758692L),
  CLASSIFICATION("http://bibfra.me/vocab/lite/classification", 554014919457463261L),
  CONTENT("http://bibfra.me/vocab/marc/content", -8400837019789777596L),
  CONTRIBUTOR("http://bibfra.me/vocab/lite/contributor", 6734473498479079135L),
  COPYRIGHT("http://bibfra.me/vocab/marc/copyright", -5763504959160201664L),
  CREATOR("http://bibfra.me/vocab/lite/creator", -6427036973279413520L),
  INSTANTIATES("http://bibfra.me/vocab/lite/instantiates", -5307605433297919406L),
  MAP("http://library.link/vocab/map", 3538761499444069943L),
  MEDIA("http://bibfra.me/vocab/marc/media", -1307538224118273019L),
  NULL(null, null),
  PE_DISTRIBUTION("http://bibfra.me/vocab/marc/distribution", -448045113972492966L),
  PE_MANUFACTURE("http://bibfra.me/vocab/marc/manufacture", -2828753452541607170L),
  PE_PRODUCTION("http://bibfra.me/vocab/marc/production", 2801478436215503730L),
  PE_PUBLICATION("http://bibfra.me/vocab/marc/publication", 9018504469354013109L),
  PLACE("http://bibfra.me/vocab/lite/place", -2442655877067158195L),
  PROVIDER_PLACE("http://bibfra.me/vocab/lite/providerPlace", -5991192843515972558L),
  STATUS("http://bibfra.me/vocab/marc/status", -6334780726339225629L),
  TITLE("http://bibfra.me/vocab/marc/title", 5549969427228480763L);

  public final String uri;
  public final Long hash;

  Predicate(String uri, Long hash) {
    this.uri = uri;
    this.hash = hash;
  }

}
