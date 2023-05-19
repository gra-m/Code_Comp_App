package fun.madeby.code_comp_app.services.reporting;

import fun.madeby.code_comp_app.datatypes.DataTypeTemplate;

/**
 * The default methods use a DataTypeTemplates intrinsic logic to quickly show the available reportable fields
 * implementers of this interface will contain logic to create reports of specific types/ or with specific outputs/render
 * methodologies.
 */
public interface ReportService {


  default String DataTemplateReportable(DataTypeTemplate dataTypeTemplate) {
        String[][] caseFields = dataTypeTemplate.getCASE_Fields();
        StringBuilder builder = new StringBuilder();
        builder.append(String.format("Template is: %s with %s cases and the following reportable fields %n%n",
            dataTypeTemplate.getClass().getSimpleName(), dataTypeTemplate.getTOTAL_CASES()));

    for (String[] field : caseFields) {
      StringBuilder temp = new StringBuilder();
      boolean first = true;
      for (String contents : field) {
            if (first) temp.append("| "); first = false;
            temp.append(String.format(" %-25s" + " |", contents));
      }
      builder.append(temp).append("\n");
    }
    return builder.toString();
  }

/** <p>
 * The default report for any ReportService Type is every ReportableCase_Field as the column heading followed by the output for
 * the firstTen cases, if firstTen is false the results for all cases will be returned.
 * </p>
 *
 * @param firstTen true = only first ten cases
 */
void createDefaultReport(boolean firstTen);


}
