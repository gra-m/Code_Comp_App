package fun.madeby.code_comp;

import fun.madeby.code_comp.app_gui_menus.ConsoleApp;
import fun.madeby.code_comp.casetypes.CT_InputExpectedActual;
import fun.madeby.code_comp.casetypes.CaseType;
import fun.madeby.code_comp.casetypes.StringType;
import fun.madeby.code_comp.datasource.DataSourceDriver;
import fun.madeby.code_comp.datasource.StringFromLocalFileAuto;
import fun.madeby.code_comp.datasource.StringFromLocalFileHardCoded;
import fun.madeby.code_comp.datatypes.DT_CC_Template;
import fun.madeby.code_comp.datatypes.DataTypeTemplate;
import fun.madeby.code_comp.datatypes.outputtypes.ArrayOutput;
import fun.madeby.code_comp.datatypes.outputtypes.DTOutput;
import fun.madeby.code_comp.outputdata.DataSnapshot;
import java.io.*;
import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.IntStream;
import java.util.stream.Stream;

class CodeCompComparison {

  static final String OUTPUT_PATH = "/home/kali/Documents/001_CC/out.txt";
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
    IN = new StringFromLocalFileHardCoded(1);
    fileLength = (int) IN.countLines();

    String[] fullIn = IN.readStringArray(fileLength);
    casesFromArray = Integer.parseInt(fullIn[0]);
    linesPerCase = Utilz.getCaseLength(fullIn);

    IN.close(); // todo create safe/auto close
    // END of Input Data import and check.
    IN = new StringFromLocalFileHardCoded(2);
    String[] exp = IN.checkLinesReadStringArray(casesFromArray);
    //linesPerOutput = Utilz.getOutputLength(exp);
    IN.close();
    // END import of expected Output
    IN = new StringFromLocalFileHardCoded(3);
    String[] act = IN.checkLinesReadStringArray(casesFromArray);
    IN.close();
    // END import of actual Output

    //Checking before CT confirmed
    OUT.println(StringTypeDefineAndCheck.isAsExpected(expectedInputStringType, expectedOutputStringType));
    OUT.println(StringTypeDefineAndCheck.defineStringType(fullIn, 0));
    OUT.println(StringTypeDefineAndCheck.defineStringType(act, 0));

    // Define CaseType from info we have:
    CT_InputExpectedActual thisImportsCaseType =
        new CT_InputExpectedActual(
            casesFromArray,
            linesPerCase,
            linesPerOutput,
            //These could be enum after check
            StringTypeDefineAndCheck.defineStringType(fullIn, 0),
            StringTypeDefineAndCheck.defineStringType(exp, 0),
            StringTypeDefineAndCheck.defineStringType(act, 0),
            CaseType.DEFAULT_SDI_ARRAY
            );

    System.out.println(thisImportsCaseType.caseTypeDescription());

    DataTypeTemplate ccDataType = new DT_CC_Template(thisImportsCaseType);

    System.out.println(ccDataType);

    // Passing DTCodeChef object to GetDataAs -> StringFromLocalFile
    DataSourceDriver strFromLocal = new StringFromLocalFileAuto(ccDataType);
    DTOutput populated = strFromLocal.getOnTheFlyData(true);
    ArrayOutput listPopulated;
    if ( populated instanceof ArrayOutput ) {
      listPopulated = (ArrayOutput) populated;

      DataTypeTemplate[] arrayList = listPopulated.getDataTypeArray();


      for ( DataTypeTemplate dt : arrayList) System.out.println(dt.toString());

    } else System.out.println("it failed");

    // Test non auditable creation array:
    strFromLocal.createNonAuditableSnapshot(true);
    LinkedHashMap<ZonedDateTime, DataSnapshot> retrievedShallowCopy = strFromLocal.getCopyOfSnapshots();
    DataTypeTemplate[] retrievedReportData = new DataTypeTemplate[900];

    for ( Map.Entry<ZonedDateTime, DataSnapshot> entry : retrievedShallowCopy.entrySet()) {
      DTOutput retrieved = entry.getValue().getDT_OUTPUT();
      if (retrieved instanceof ArrayOutput) {
         retrievedReportData = ((ArrayOutput)retrieved).getDataTypeArray();
      }
      break;
    }

    System.out.println("-----------------HERE They come again");

    //for ( DataTypeTemplate dt : retrievedReportData) System.out.println(dt.toString()); // this worked OK

    // -------------------------------------------//
    System.out.println("==========APP STARTED -> From reporting==========");

    ConsoleApp consoleApp = new ConsoleApp();

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
    private final BufferedWriter BW;

    public FastWriter() {
      BufferedWriter bw1;
      if (System.getProperty("ONLINE_JUDGE") == null && FILE_WRITE) {
        try {
          bw1 = new BufferedWriter(new FileWriter(OUTPUT_PATH));
          bw1.flush();
        } catch (IOException e) {
          bw1 = new BufferedWriter(new OutputStreamWriter(System.out));
        }
      } else {
        bw1 = new BufferedWriter(new OutputStreamWriter(System.out));
      }
      this.BW = bw1;
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

