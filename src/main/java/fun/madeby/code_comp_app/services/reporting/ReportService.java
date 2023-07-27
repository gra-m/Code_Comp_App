package fun.madeby.code_comp_app.services.reporting;

import fun.madeby.code_comp_app.datatypes.DataTypeTemplate;
import fun.madeby.code_comp_app.services.datasource.DataSourceService;
import java.util.List;

/**
 * The default methods use a DataTypeTemplates intrinsic logic to quickly show the available
 * reportable fields implementers of this interface will contain logic to create reports of specific
 * types/ or with specific outputs/render methodologies.
 */
public interface ReportService {
  /**
   * Used a DataTypeTemplates intrinsic logic to output all of its reportable fields, implementers of this interface
   * could contain logic to create reports of specific types/ or with specific outputs/render methodologies.
   *
   * Below is the console printed output for a DataTypeCodeChef:
   *
   * |  Default=FromFieldOrder      | 0=ignore||>0userSpec        | ReportableCASE_Field        | Type                        |
   * |  1                           | 0                           | CASE_INDEX                  | long                        |
   * |  2                           | 0                           | CASE_ID                     | long                        |
   * |  3                           | 0                           | CASE_TYPE                   | CaseTypeInputExpectedActual |
   * |  4                           | 0                           | CASE_INPUT_DATA             | String[]                    |
   * |  5                           | 0                           | CASE_EXP_OUTPUT_DATA        | String[]                    |
   * |  6                           | 0                           | CASE_ACT_OUTPUT_DATA        | String[]                    |
   * |  7                           | 0                           | CASE_STATUS_PASSING         | boolean                     |
   */
  default String getDataTemplatesReportableCaseFields(DataTypeTemplate dataTypeTemplate, boolean printToConsole) {
    String[][] caseFields = dataTypeTemplate.getCASE_Fields();
    StringBuilder builder = new StringBuilder();
    
    builder.append(
        String.format(
            "Template is: %s with %s cases and the following reportable fields %n%n",
            dataTypeTemplate.getClass().getSimpleName(), dataTypeTemplate.getTOTAL_CASES()));

    for (String[] field : caseFields) {
      StringBuilder temp = new StringBuilder();
      boolean first = true;
      for (String contents : field) {
        if (first) temp.append("| ");
        first = false;
        temp.append(String.format(" %-27s" + " |", contents));
      }
      builder.append(temp).append("\n");
    }
    if (printToConsole) System.out.println(builder);

    return builder.toString();
  }

  /**
   * The default report for any ReportServiceImplementation, generally outputting a DataTypes
   * created data from input. Another way of describing this is that this is the equivalent of a
   * DataTypes save data, a way of extracting the work that has already been undertaken in a
   * DataType Template. It differs from create DataTypeTemplates default report in that this report
   * will contain all data that at DataTypes specific reports could be made from.
   *
   * @param firstTen true = only first ten cases
   */
  void createDefaultServiceImplReport(boolean firstTen, DataTypeTemplate dataTypeTemplate);

  /**
   * A client passes a dataTypeTemplate to a ReportService, that report service then passes itself
   * to the dataTypeTemplate and creates the correct contextual reports for that ReportService.
   */
  void createDataTypesDefaultReport(
      DataTypeTemplate dataTypeTemplate, DataSourceService dataSourceService);

  /**
   * @param formattedData
   */
  void output(List<String> formattedData);
}
