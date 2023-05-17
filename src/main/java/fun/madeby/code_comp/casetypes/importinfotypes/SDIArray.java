package fun.madeby.code_comp.casetypes.importinfotypes;

import java.util.Objects;

public class SDIArray implements SourceDataInfo {
  private static String[] sourceDataInfo;

  public SDIArray(String[] sourceDataInfo) {
        SDIArray.sourceDataInfo = sourceDataInfo;}

public String[] getStringArray() {
        return sourceDataInfo;
}

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
