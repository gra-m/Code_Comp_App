package cchef.jtrain.self.code_comp.datatypes;

import java.util.Objects;

public class SDIArray implements SourceDataInfo {
  private final String[] sourceDataInfo;

  public SDIArray(String[] sourceDataInfo) {this.sourceDataInfo = sourceDataInfo;}

/**
*
  */
@Override
public void sourceDataToConsole() {
    if (Objects.nonNull(sourceDataInfo)) for (String str : sourceDataInfo ) System.out.println(str);
    else System.out.println("@sourceDataToConsole String[] is null");
}

/**
* 
   * @return
*/
@Override
public boolean isEmptyOrNull() {
      return sourceDataInfo.length == 0 || Objects.isNull(sourceDataInfo);
}
}
