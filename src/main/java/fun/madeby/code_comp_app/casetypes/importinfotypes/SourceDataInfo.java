package fun.madeby.code_comp_app.casetypes.importinfotypes;

/**
 * This interface is a container for SourceDataInfo in different forms. SourceDataInfo is String based data that contains
 * for example: addresses/internal filepaths, that enable a DataType to retrieve data.
 *
 * @See CaseType
 */
public interface SourceDataInfo {
  void sourceDataToConsole();

  boolean isEmptyOrNull();
}
