package fun.madeby.code_comp_app.reporting;

import fun.madeby.code_comp_app.datatypes.DataTypeTemplate;

public class GenericStringToConsole implements Report{
private final DataTypeTemplate REPORT_SEED;

public GenericStringToConsole(DataTypeTemplate reportSeed) {
      this.REPORT_SEED = reportSeed;

}
}
