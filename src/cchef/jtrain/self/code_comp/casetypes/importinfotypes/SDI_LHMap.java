package cchef.jtrain.self.code_comp.casetypes.importinfotypes;

import java.util.LinkedHashMap;
import java.util.Objects;


/**
 * For when source data information is more complex than a few local file paths
 */
public class SDI_LHMap implements SourceDataInfo {
  private final LinkedHashMap<String, String> sourceDataInfo;

  public SDI_LHMap(LinkedHashMap<String, String> sourceDataInfo) {
      this.sourceDataInfo = sourceDataInfo;
  }

  /** */
  @Override
  public void sourceDataToConsole() {
    int count = 0;
    if (Objects.nonNull(sourceDataInfo))
      for (var entry : sourceDataInfo.entrySet())
        System.out.printf("%d key is %s value is %s", count++, entry.getKey(), entry.getValue());
    else System.out.println("@sourceDataToConsole LinkedHashMap<String, String> is null");
  }

/**
* 
   * @return
*/
@Override
public boolean isEmptyOrNull() {
    return sourceDataInfo.isEmpty() || Objects.isNull(sourceDataInfo);
}
}
