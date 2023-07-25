package fun.madeby.code_comp_app.datatypes;

import fun.madeby.code_comp_app.casetypes.importinfotypes.SourceDataInfo;
import fun.madeby.code_comp_app.datatypes.outputtypes.DTOutput;
import fun.madeby.code_comp_app.outputdata.DataSnapshot;
import fun.madeby.code_comp_app.services.datasource.DataSourceService;
import fun.madeby.code_comp_app.services.reporting.ReportService;

import java.io.IOException;
import java.lang.reflect.Field;
import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * Objects Derived from this interface are used by different data Given source data info
 * SourceDataInfo [Data addresses] a CaseType [Definition of source data] Data can be checked for
 * consistency against its CaseType by a DataTypeTemplate and then data may be consolidated as
 * desired into an array or list [DTOutput] of DataTypeTemplate objects in a way that is defined by
 * the business logic of any given DataTemplate.
 */
public interface DataTypeTemplate {

  /**
   * The main reason for this DataTypes existence: creation of a report specific for this data type.
   * The report service passed decides the context of this report.
   *
   * @param reportService
   */
  void outputDefaultReport(ReportService reportService, DataSourceService dataSourceService) throws IOException;

  int getLINES_PER_INPUT();

  int getLINES_PER_OUTPUT();

  long getTOTAL_CASES();

  String[] getCASE_INPUT_DATA();

  boolean isTEMPLATE();

  /**
   * If there are no snapshots, one will need to be created, or a live report made, this gives
   * snapshot length.
   *
   * @return
   */
  int getSnapshotLength();

  /**
   * Temporary shallow copy => replace with deep to enable auditable reports.
   *
   * @return
   */
  LinkedHashMap<ZonedDateTime, DataSnapshot> getShallowCopyOfSnapshots();

  /**
   * @return
   */
  SourceDataInfo getSourceDataInfo();

  DTOutput consolidateDataToArray(final String[][] inputArray, final boolean asArray);

  void addNonAuditableSnapshot(
      final String[][] inputArray, String userTimeZone, final boolean asArray);
  // poss temp
  void printSnapshots();

  String[][] getSupportedReportsOfClass(ReportService reportServiceClass);

  LinkedHashMap<ReportService, String[][]> getAllSupportedReports();

  /** Reportable fields in a DataType should be final and begin with CASE_ */
  default void printCASE_Fields() {

    Class<?> objClass = this.getClass();
    for (Field field : objClass.getDeclaredFields()) {
      String name = field.getName();
      if (name.startsWith("CASE_")) {
        System.out.printf(
            "field = %s || AnnotatedType = %s || class = %s \n",
            field.getName(), field.getAnnotatedType(), field.getDeclaringClass().getSimpleName());
      }
    }
  }

  /**
   * Given that DataTypeTemplate reporting fields begin with "CASE_" this method returns an array of
   * them in the default order they have been listed in the template.
   *
   * <p>The first array returned contains the DeclaringClass().getSimpleName() eg DataTypeCodeChef,
   * and 'column headings' for the array contents.
   *
   * <p>Then each subsequent String[] contains:
   *
   * <p>[0] count == the default position/column number of the field within the template [1] "0" ==
   * user choice for the position of the field within a report (set at 0 at creation). When this
   * field is given a position != 0 it will be considered active within a report todo confirm this
   * is how reporting actually works [2] the CASE_ fields name e.g. CASE_INDEX [3] the CASE_ fields
   * annotated type, substringed to the type used in the template eg boolean
   *
   * @return String[][] an array for each Field of data-type and extra fields
   */
  default String[][] getCASE_Fields() {
    Class<?> objClass = this.getClass();
    String[][] reportable = new String[20][];
    String[] temp = new String[4];
    boolean firstPass = true;
    int count = 0;

    for (Field field : objClass.getDeclaredFields()) {

      if (firstPass) {
        temp[0] = "Default=FromFieldOrder";
        temp[1] = "0=ignore||>0userSpec";
        temp[2] = "ReportableCASE_Field";
        temp[3] = "Type";
        reportable[count++] = temp;
        firstPass = false;
      }

      String name = field.getName();
      if (name.startsWith("CASE_")) {
        temp = new String[4];
        temp[0] = Integer.toString(count);
        temp[1] = "0";
        temp[2] = field.getName();

        String packageName = field.getAnnotatedType().toString();
        if (packageName.contains("."))
          packageName = packageName.substring(packageName.lastIndexOf(".") + 1);
        temp[3] = packageName;
        reportable[count++] = temp;
      }
    }

    String[][] returnable = new String[count][];
    java.lang.System.arraycopy(reportable, 0, returnable, 0, count);
    return returnable;
  }

  /**
   * Given that DataTypeTemplate reporting fields begin with "CASE_" this method returns a
   * LinkedHashMap<String, String[]> with the CASE_ field name as the key, in all positions apart
   * from the first, which has a key of "Type".
   *
   * <p>The first key "Type" contains DeclaringClass().getSimpleName() eg. DataTypeCodeChef as its
   * value. Then: CASE_ field name and
   *
   * <p>String array: [0] count[default] == the default position/column number of the field within
   * the template [1] "0[choice] == user choice for the position of the field within a report (set
   * at 0 at creation, here) [2] the CASE_ fields annotated type, substringed to the type used in
   * the template eg boolean
   *
   * @return LinkedHashMap<String,String[]> first <K,V> == <"Type", DataTypeTemplate class name>
   *     then keys are CASE_ field and see above.
   */
  default LinkedHashMap<String, String[]> getCASE_FieldsLinkedMap() {
    Class<?> objClass = this.getClass();
    Field[] fieldArray = objClass.getDeclaredFields();

    List<Field> caseData =
        Arrays.stream(fieldArray).filter(field -> field.getName().startsWith("CASE_")).toList();

    LinkedHashMap<String, String[]> returnable = new LinkedHashMap<>(caseData.size() + 1);

    int count = 0;
    returnable.put("Type", new String[] {objClass.getSimpleName()});

    for (Field field : caseData) {
      count++;
      String packageName = field.getAnnotatedType().toString();
      if (packageName.contains("."))
        packageName = packageName.substring(packageName.lastIndexOf(".") + 1);
      returnable.put(field.getName(), new String[] {count + "[default]", "0[choice]", packageName});
    }

    return returnable;
  }
}
