package fun.madeby.code_comp_app;

import fun.madeby.code_comp_app.app_gui_menus.ConsoleApp;
import fun.madeby.code_comp_app.casetypes.CaseTypeInputExpectedActual;
import fun.madeby.code_comp_app.casetypes.CaseType;
import fun.madeby.code_comp_app.casetypes.StringType;
import fun.madeby.code_comp_app.datatypes.DataTypeCodeChef;
import fun.madeby.code_comp_app.services.datasource.DataSourceService;
import fun.madeby.code_comp_app.services.datasource.LocalFilesService;
import fun.madeby.code_comp_app.services.datasource.StringFromLocalFileHardCoded;
import fun.madeby.code_comp_app.datatypes.DataTypeTemplate;
import fun.madeby.code_comp_app.datatypes.outputtypes.ArrayOutput;
import fun.madeby.code_comp_app.datatypes.outputtypes.DTOutput;
import fun.madeby.code_comp_app.outputdata.DataSnapshot;
import fun.madeby.code_comp_app.services.reporting.impl.ConsoleReportServiceImpl;
import fun.madeby.code_comp_app.services.reporting.impl.TextFileReportServiceImpl;

import java.io.*;
import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.IntStream;
import java.util.stream.Stream;
class CodeCompComparison {

  static final FastWriter OUT = new FastWriter();
  static final StringType expectedInputStringType = StringType.NUMERIC_SP;
  static final StringType expectedOutputStringType = StringType.NUMERIC;
  static final boolean FILE_WRITE = false;
  static StringFromLocalFileHardCoded IN;   // this was FastScanner
  static int fileLength; // not lossy int level input is max for Arrays and ArrayLists
  static int casesFromArray = 0;
  static int linesPerCase = 0;
  static int linesPerOutput = 1;

  public static void main(String[] args) throws Exception {

    //01 Data import and check
    // Get 00fullIn.txt from hardcoded path
    IN = new StringFromLocalFileHardCoded(1);
    fileLength = (int) IN.countLines();

    String[] fullIn = IN.readStringArray(fileLength);
    casesFromArray = Integer.parseInt(fullIn[0]);
    linesPerCase = Utilz.getCaseLength(fullIn);

    IN.close(); // todo create safe/auto close
    // 01 END of Input Data import and check.

    // 02 Import expected output
    IN = new StringFromLocalFileHardCoded(2);
    String[] exp = IN.checkLinesReadStringArray(casesFromArray);
    //linesPerOutput = Utilz.getOutputLength(exp);
    IN.close();
    // 02 END import of expected Output

    // 03 Import actual Output
    IN = new StringFromLocalFileHardCoded(3);
    String[] act = IN.checkLinesReadStringArray(casesFromArray);
    IN.close();
    // 03 END import of actual Output

    // 04 Checking before Case Type is confirmed
    OUT.println("\n\n04 -> Checking before Case Type is confirmed");
    OUT.println(
        StringTypeDefineAndCheck.isAsExpected(expectedInputStringType, expectedOutputStringType));
    OUT.println(StringTypeDefineAndCheck.defineStringType(fullIn, 0));
    OUT.println(StringTypeDefineAndCheck.defineStringType(act, 0));
    // 04 END

    // 05 Define CaseType from info that has been confirmed via validated input files:
    OUT.println("\n\n05 -> Defining Case Type From Input");
    CaseTypeInputExpectedActual thisImportsCaseType =
        new CaseTypeInputExpectedActual(
            casesFromArray,
            linesPerCase,
            linesPerOutput,
            //These could be enum after check
            StringTypeDefineAndCheck.defineStringType(fullIn, 0),
            StringTypeDefineAndCheck.defineStringType(exp, 0),
            StringTypeDefineAndCheck.defineStringType(act, 0),
            CaseType.DEFAULT_SDI_ARRAY
            );

    OUT.println(thisImportsCaseType.caseTypeDescription());

    // 06 Now that a specific caseType has been validated (CaseTypeInputExpectedActual) this can now be passed to a
    // DataTypeTemplate that suits it (i.e. with a constructor for that CaseType).

    DataTypeTemplate ccDataType = new DataTypeCodeChef(thisImportsCaseType);

    OUT.println("#################--Printing ccDataType just created--#################\n" + ccDataType);

    OUT.println("#################--Printing ccDataType just created END--#################\n");
    

    // 07 Create a DataSourceService from which onTheFlyData data can be created or Auditable/NonAuditable Snapshots
    // created and saved in the DataTypeTemplates LinkedHashMap of snapshots.
    DataSourceService localFilesService = new LocalFilesService(ccDataType);
    ArrayOutput populated = (ArrayOutput ) localFilesService.getOnTheFlyData(true);

      DataTypeTemplate[] arrayList = populated.getDataTypeArray();

     // Arrays.stream(arrayList).filter(dt -> dt.)

      // Print all:
      for ( DataTypeTemplate dt : arrayList) OUT.println(dt.toString());

    


    // Test non auditable creation array:
    localFilesService.createNonAuditableSnapshot(true);
    LinkedHashMap<ZonedDateTime, DataSnapshot> retrievedShallowCopy = localFilesService.getCopyOfSnapshots();
    DataTypeTemplate[] retrievedReportData = new DataTypeTemplate[900];

    for ( Map.Entry<ZonedDateTime, DataSnapshot> entry : retrievedShallowCopy.entrySet()) {
      DTOutput retrieved = entry.getValue().getDT_OUTPUT();
      if (retrieved instanceof ArrayOutput) {
         retrievedReportData = ((ArrayOutput)retrieved).getDataTypeArray();
      }
      break;
    }


    OUT.println("================NOw in rEpOrT type==============");

    /*TextFileReportServiceImpl textFileReportServiceImpl = new TextFileReportServiceImpl();
    textFileReportServiceImpl.createDefaultServiceImplReport(true, ccDataType);*/

    ConsoleReportServiceImpl consoleReportService = new ConsoleReportServiceImpl();

  /*  OUT.println("-------------Removed while working on REporT SerVicEs----HERE They come again");

    //for ( DataTypeTemplate dt : retrievedReportData) System.out.println(dt.toString()); // this worked OK*/
/*
    // ------------------------------------------Console App on hold-//
    OUT.println("==========APP STARTED -> From reporting==========");

    ConsoleApp consoleApp = new ConsoleApp();

    String[][] caseFields = ccDataType.getCASE_Fields();

    for (String[] field : caseFields) for (String contents : field) System.out.println(contents);

    LinkedHashMap<String, String[]> caseFieldsMapped = ccDataType.getCASE_FieldsLinkedMap();

    for (Map.Entry<String, String[]> entry: caseFieldsMapped.entrySet()) {
      OUT.println(entry.getKey() + " " + Arrays.toString(entry.getValue()));
    }*/


    IN.close();
    OUT.close();
  }




  /*===========================================================================*/

  private static Integer[] intArrToIntegerArr(int[] st01) throws NumberFormatException {

    IntStream st02 = Arrays.stream(st01);
    Stream<Integer> st03 = st02.boxed();

    return st03.toArray(Integer[]::new);
  }

  private static String cleanString(String raw) {
    return raw.replaceAll("[^\\d\\s]", "").trim();
  }


static class FastWriter {
  private final BufferedWriter BW =  new BufferedWriter(new OutputStreamWriter(System.out));

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

