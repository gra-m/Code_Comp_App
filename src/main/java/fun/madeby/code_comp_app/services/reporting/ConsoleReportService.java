package fun.madeby.code_comp_app.services.reporting;

import fun.madeby.code_comp_app.services.datasource.DataSourceService;
import fun.madeby.code_comp_app.datatypes.DataTypeTemplate;
import fun.madeby.code_comp_app.services.datasource.LocalFilesService;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ConsoleReportService implements ReportService {


private final DataTypeTemplate DATA_TYPE_TEMPLATE;
private DataSourceService dataSourceService;
private DataTypeTemplate[] lastRunData;

/**
 * Given that data source testing has already occurred this constructor
 * extracts already existing Template for direct use of default interface methods.
 *
 * @param dataSourceService Used already proven at this point. todo there are no use cases
 */
public ConsoleReportService(DataSourceService dataSourceService) {
      this.dataSourceService = dataSourceService;
      this.DATA_TYPE_TEMPLATE = dataSourceService.getDataTypeTemplate();
}



/**
 * <p>
 * The default report for any ReportService Type is every ReportableCase_Field as column heading followed by the output for
 * the firstTen cases, if firstTen is false the results for all cases will be returned.
 * </p>
 * @See dataTemplateReportable to get
 * @param firstTen true = only first ten cases
 */
@Override
public void createDefaultReport(boolean firstTen) {

      if (dataSourceService instanceof LocalFilesService ) {
      lastRunData = ((LocalFilesService)this.dataSourceService).getOnTheFlyData();
          }

      ArrayList<String> headings = (ArrayList<String>) getDefaultReportHeaders();



      // if >0 -> use snapshot to create

      // else -> private createLiveDefaultFrom sources method to test viable and create from sources

}

private List<String> getDefaultReportHeaders() {

      LinkedHashMap<String, String[]> lhm = this.DATA_TYPE_TEMPLATE.getCASE_FieldsLinkedMap();
      ArrayList<String> returnMe = new ArrayList<>(lhm.size());

      for( Map.Entry<String, String[]> entry : lhm.entrySet())
            returnMe.add(entry.getKey());

      return returnMe;
}

}
