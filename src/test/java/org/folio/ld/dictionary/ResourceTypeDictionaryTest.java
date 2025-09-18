package org.folio.ld.dictionary;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

class ResourceTypeDictionaryTest {

  @Test
  void fromNullUri_shouldContainNull() {
    // when
    var result = ResourceTypeDictionary.fromUri(null);

    // then
    assertThat(result)
      .isEmpty();
  }

  @ParameterizedTest
  @ValueSource(strings = {"", " ", "empty", "some source"})
  void fromUri_shouldNotContainValue(String uri) {
    // when
    var result = ResourceTypeDictionary.fromUri(uri);

    // then
    assertThat(result)
      .isEmpty();
  }

  @ParameterizedTest
  @CsvSource(value = {
    "http://bibfra.me/vocab/lite/Annotation, -2716756821684516448",
    "http://bibfra.me/vocab/lite/Category, -7237503330348194663",
    "http://bibfra.me/vocab/lite/CategorySet, -1236482431314522895",
    "http://bibfra.me/vocab/lite/Concept, 6012322771220013009",
    "http://bibfra.me/vocab/lite/CopyrightEvent, 7138672891049112604",
    "http://bibfra.me/vocab/lite/Family, -1114722094452060721",
    "http://bibfra.me/vocab/lite/Form, 6102579518025542780",
    "http://bibfra.me/vocab/lite/Identifier, 8535666185887263660",
    "http://library.link/identifier/IAN, -2689808084414358372",
    "http://library.link/identifier/ISBN, 3962988838506018431",
    "http://library.link/identifier/LCCN, -5891299401617387451",
    "http://bibfra.me/vocab/lite/LocalId, 4097848952412986786",
    "http://library.link/identifier/UNKNOWN, -1259663971045664909",
    "http://bibfra.me/vocab/lite/Instance, -6368122725352320482",
    "http://bibfra.me/vocab/lite/Item, -1796261916093733980",
    "http://bibfra.me/vocab/lite/Jurisdiction, 4072387342118066928",
    "http://bibfra.me/vocab/lite/Meeting, 8312970713564449340",
    "http://bibfra.me/vocab/lite/Organization, -2724163952136761329",
    "http://bibfra.me/vocab/library/ParallelTitle, -5865040616075866396",
    "http://bibfra.me/vocab/lite/Person, 3745950457458968285",
    "http://bibfra.me/vocab/lite/Place, 7862842856693929565",
    "http://bibfra.me/vocab/lite/ProviderEvent, -1414513180123539856",
    "http://bibfra.me/vocab/library/Status, -1841099618329130892",
    "http://bibfra.me/vocab/library/SupplementaryContent, -8967814508506610781",
    "http://bibfra.me/vocab/lite/Temporal, 4805333230697475193",
    "http://bibfra.me/vocab/library/Title, 720137805626297286",
    "http://bibfra.me/vocab/lite/Topic, -3096999901507785420",
    "http://bibfra.me/vocab/library/VariantTitle, -8412753012125353881",
    "http://bibfra.me/vocab/lite/Work, -3114907246856331309",
  })
  void fromUri_shouldContainValue(String uri, Long hash) {
    //when
    var type = ResourceTypeDictionary.fromUri(uri);

    //then
    assertThat(type)
      .isNotNull()
      .map(ResourceTypeDictionary::getHash)
      .contains(hash);
  }
}
