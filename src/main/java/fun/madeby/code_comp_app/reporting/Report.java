package fun.madeby.code_comp_app.reporting;

import fun.madeby.code_comp_app.datatypes.DataTypeTemplate;

public interface Report {

  /**
   * If required a quick insight into the contents of the DataType it will after confirming, 'this template contains data,
   *  * what would you like to do :MENU: this would print each data point within the data type.
   *
   *  BUT! surely DataType knows its required format better than any one , and it is very specific business logic, where
   *  else would it best be than in DATATYPE, reports will therefore be output services?
   *
   *  NO. Report is about receiving a data type, looking at it and then performing any output you want with it so this stays
   *  BUT the default methods below get moved to data type
   * @param dataTypeTemplate
   * @return
   */
  default String getContentsAndTypes(DataTypeTemplate dataTypeTemplate) {
      return "hereGo!";
}

/**
 * Existing path = Driver/Service StringFromLocalFileAuto -> printSnapshotInfo -> Better off in DataType
 *
 * @param dataTypeTemplate
 * @return
 */
default String getSnapshotInfo(DataTypeTemplate dataTypeTemplate) {
      return "hereGo!";
}

}
