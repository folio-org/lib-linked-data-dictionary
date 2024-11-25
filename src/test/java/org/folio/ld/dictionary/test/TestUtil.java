package org.folio.ld.dictionary.test;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.nio.charset.StandardCharsets;
import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;
import org.folio.ld.dictionary.model.title.ParallelTitle;
import org.folio.ld.dictionary.model.title.PrimaryTitle;
import org.folio.ld.dictionary.model.title.VariantTitle;

@UtilityClass
public class TestUtil {

  public static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

  @SneakyThrows
  public static String loadResourceAsString(String resourceName) {
    try (var inputStream = TestUtil.class.getResourceAsStream("/" + resourceName)) {
      if (inputStream != null) {
        return new String(inputStream.readAllBytes(), StandardCharsets.UTF_8);
      }
    }
    return "";
  }

  public static PrimaryTitle getPrimaryTitle() {
    var pt = new PrimaryTitle();
    pt.mainTitles.add("main");
    pt.partNames.add("part name");
    pt.partNumbers.add("part number");
    pt.subTitles.add("sub");
    pt.nonSortNumbers.add("nonSortNum");
    return pt;
  }

  public static ParallelTitle getParallelTitle() {
    var pt = new ParallelTitle();
    pt.mainTitles.add("main");
    pt.partNames.add("part name");
    pt.partNumbers.add("part number");
    pt.subTitles.add("sub");
    pt.dates.add("date");
    pt.notes.add("note");
    return pt;
  }

  public static VariantTitle getVariantTitle() {
    var vt = new VariantTitle();
    vt.mainTitles.add("main");
    vt.partNames.add("part name");
    vt.partNumbers.add("part number");
    vt.subTitles.add("sub");
    vt.dates.add("date");
    vt.notes.add("note");
    vt.variantTypes.add("variantType");
    return vt;
  }
}
