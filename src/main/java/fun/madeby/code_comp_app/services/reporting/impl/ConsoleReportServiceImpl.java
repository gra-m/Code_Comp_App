package fun.madeby.code_comp_app.services.reporting.impl;

import fun.madeby.code_comp_app.datatypes.outputtypes.ArrayOutput;
import fun.madeby.code_comp_app.datatypes.outputtypes.DTOutput;
import fun.madeby.code_comp_app.services.datasource.DataSourceService;
import fun.madeby.code_comp_app.datatypes.DataTypeTemplate;
import fun.madeby.code_comp_app.services.datasource.LocalFilesService;
import fun.madeby.code_comp_app.services.reporting.ReportService;
import fun.madeby.code_comp_app.services.reporting.exception.ReportingServiceException;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


public class ConsoleReportServiceImpl implements ReportService {
static final FastWriter OUT = new FastWriter();

/**
 * <p>
 * The default report for any ReportService Type is every ReportableCase_Field as column heading followed by the output for
 * the firstTen cases, if firstTen is false the results for all cases will be returned.
 * </p>
 * @See dataTemplateReportable to get
 * @param firstTen true = only first ten cases
 */
@Override
public void createDefaultServiceImplReport(boolean firstTen, DataTypeTemplate dataTypeTemplate) {

      DataSourceService strFromLocal = new LocalFilesService(dataTypeTemplate);
      DTOutput populated = strFromLocal.getOnTheFlyData(true);
      ArrayOutput listPopulated;
      if ( populated instanceof ArrayOutput ) {
            listPopulated = (ArrayOutput) populated;

            DataTypeTemplate[] arrayList = listPopulated.getDataTypeArray();

            for ( DataTypeTemplate dt : arrayList) System.out.println(dt.toString());

      }
      }

/**
 * A client passes a dataTypeTemplate to a ReportService, that report service then passes itself to the
 * dataTypeTemplate and creates the correct contextual reports for that ReportService.
 *
 * Todo if the dataTypeTemplate has snapshot data use that? Or always OnTheFly?
 *
 * @param dataTypeTemplate
 */
@Override
public void createDataTypesDefaultReport(DataTypeTemplate dataTypeTemplate, DataSourceService dataSourceService) {
      try {
            dataTypeTemplate.outputDefaultReport(this, dataSourceService);
      }
      catch( IOException e ) {
      throw new ReportingServiceException("Error on outputDataTypeDefaultReport" , e);
      }

}

/**
*
   * @param formattedData
  */
@Override
public void output(List<String> formattedData) {
      try {
    System.out.println(formattedData.size());
    for (String str : formattedData) System.out.println(str);
    OUT.println("hello");
    }
      catch (IOException e) {
            throw new ReportingServiceException("failed output in ReportService " + this.getClass(), e);
      }
}

private List<String> getDefaultReportHeaders(DataTypeTemplate dataTypeTemplate) {

      LinkedHashMap<String, String[]> lhm = dataTypeTemplate.getCASE_FieldsLinkedMap();
      ArrayList<String> returnMe = new ArrayList<>(lhm.size());

      for( Map.Entry<String, String[]> entry : lhm.entrySet())
            returnMe.add(entry.getKey());

      return returnMe;
}


static class FastWriter {
      private final BufferedWriter BW;

      public FastWriter() {
                  this.BW = new BufferedWriter(new OutputStreamWriter(System.out));
      }


      public void print(Object object) throws IOException {
            BW.append(String.valueOf(object));
      }

      public void println(Object object) throws IOException {
            print(object);
            BW.append("\n");
      }

      public void close() throws IOException {
            BW.close();
      }
}

}

