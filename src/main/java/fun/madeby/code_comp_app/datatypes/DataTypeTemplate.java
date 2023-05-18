package fun.madeby.code_comp_app.datatypes;

import fun.madeby.code_comp_app.casetypes.importinfotypes.SourceDataInfo;
import fun.madeby.code_comp_app.datatypes.outputtypes.DTOutput;
import fun.madeby.code_comp_app.outputdata.DataSnapshot;

import java.lang.reflect.Field;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Objects Derived from this interface are used by different data Given source data info
 * SourceDataInfo [Data addresses] a CaseType [Definition of source data] Data can be checked for
 * consistency against its CaseType by a DataTypeTemplate and then data may be consolidated as
 * desired into an array or list [DTOutput] of DataTypeTemplate objects in a way that is defined by
 * the business logic of any given DataTemplate.
 */
public interface DataTypeTemplate {

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

  default String[][] getCASE_Fields() {
    Class<?> objClass = this.getClass();
    String[][] reportable = new String[20][];
    String[] temp = new String[1];
    boolean firstPass = true;
    int count = 0;

    for (Field field : objClass.getDeclaredFields()) {
      if (firstPass) {
        temp[count] = field.getDeclaringClass().getSimpleName();
        reportable[count] = temp;
        firstPass = false;
        count++;
      }
      String name = field.getName();
      if (name.startsWith("CASE_")) {
        temp = new String[4];
        temp[0] = count + "[default]";
        temp[1] = "0[choice]";
        temp[2] = field.getName();
        String packageName = field.getAnnotatedType().toString();
        if (packageName.contains("."))
          packageName = packageName.substring(packageName.lastIndexOf(".") + 1);
        temp[3] = packageName;
        reportable[count++] = temp;
      }
    }
    String[][] returnable = new String[count][];
    java.lang.System.arraycopy(reportable,0,returnable, 0, count);
    return returnable;
  }

  default LinkedHashMap<String, String[]> getCASE_FieldsLinkedMap() {
    Class<?> objClass = this.getClass();
    Field[] fieldArray = objClass.getDeclaredFields();

    List<Field> caseData = Arrays.stream(fieldArray)
                                 .filter(field -> field.getName().startsWith("CASE_"))
                                 .toList();

    LinkedHashMap<String, String[]> returnable = new LinkedHashMap<>(caseData.size() + 1);

    int count = 0;
    returnable.put("Type", new String[] { objClass.getSimpleName()});
    
    for (Field field : caseData) {
      count++;
      String packageName = field.getAnnotatedType().toString();
      if(packageName.contains("."))
        packageName = packageName.substring(packageName.lastIndexOf(".") + 1);
      returnable.put(field.getName(), new String[] {count + "[default]", "0[choice]", packageName});
    }

    return returnable;

       }




  int getLINES_PER_INPUT();

  int getLINES_PER_OUTPUT();

  long getTOTAL_CASES();

  boolean isTEMPLATE();

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
}
