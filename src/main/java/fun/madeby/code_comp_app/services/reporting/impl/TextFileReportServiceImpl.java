package fun.madeby.code_comp_app.services.reporting.impl;

import fun.madeby.code_comp_app.datatypes.DataTypeTemplate;
import fun.madeby.code_comp_app.datatypes.outputtypes.ArrayOutput;
import fun.madeby.code_comp_app.datatypes.outputtypes.DTOutput;
import fun.madeby.code_comp_app.services.datasource.DataSourceService;
import fun.madeby.code_comp_app.services.datasource.LocalFilesService;
import fun.madeby.code_comp_app.services.reporting.ReportService;
import fun.madeby.code_comp_app.services.reporting.exception.ReportingServiceException;

import java.io.IOException;
import java.util.List;

public class TextFileReportServiceImpl implements ReportService {
  static final String OUTPUT_PATH = "/home/kali/Documents/001_CC/000App/Test01.txt";
  static final LocalOutputWriter localOutputWriter = new LocalOutputWriter(OUTPUT_PATH);

  /**
   * <p>
   * The default report for any ReportService Type is every ReportableCase_Field as the column heading followed by the output for
   * the firstTen cases, if firstTen is false the results for all cases will be returned.
   * </p>
   *
   * @param firstTen true = only first ten cases
   */
  @Override
  public void createDefaultServiceImplReport(boolean firstTen, DataTypeTemplate ccDataType) {
    DataSourceService strFromLocal = new LocalFilesService(ccDataType);
    DTOutput populated = strFromLocal.getOnTheFlyData(true);
    ArrayOutput listPopulated;
    if (populated instanceof ArrayOutput) {
      listPopulated = (ArrayOutput) populated;

      DataTypeTemplate[] arrayList = listPopulated.getDataTypeArray();

      try {
        // for ( DataTypeTemplate dt : arrayList) OUT.println(dt.toString());
        for (DataTypeTemplate dt : arrayList)
          localOutputWriter.out.println(dt.getCASE_INPUT_DATA().toString());
        localOutputWriter.close();

      } catch (IOException e) {
        throw new ReportingServiceException("Write to file failed", e);
      }
    } else System.out.println("it failed");
  }

  /**
   * A client passes a dataTypeTemplate to a ReportService, that report service then passes itself to the
   * dataTypeTemplate and creates the correct contextual reports for that ReportService.
   *
   * @param dataTypeTemplate
   */
  @Override
  public void createDataTypesDefaultReport(
      DataTypeTemplate dataTypeTemplate, DataSourceService dataSourceService) {
    try {
      dataTypeTemplate.outputDefaultReport(this, dataSourceService);
    } catch (IOException e) {
      throw new ReportingServiceException("Error on outputDataTypeDefaultReport", e);
    }
  }

  /**
   *
   * @param formattedData
   */
  @Override
  public void output(List<String> formattedData) {

    try {

      for (String str : formattedData) localOutputWriter.out.print(str);
      localOutputWriter.close();
    } catch (IOException e) {
      throw new ReportingServiceException("failed output in ReportService " + this.getClass(), e);
    }
  }
}