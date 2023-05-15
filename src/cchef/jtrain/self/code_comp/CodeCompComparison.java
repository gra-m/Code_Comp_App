package cchef.jtrain.self.code_comp;

import java.io.*;
import java.util.Arrays;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import cchef.jtrain.self.code_comp.casetypes.CT_InputExpectedActual;
import cchef.jtrain.self.code_comp.casetypes.CaseType;
import cchef.jtrain.self.code_comp.casetypes.StringType;
import cchef.jtrain.self.code_comp.datatypes.outputtypes.ArrayOutput;
import cchef.jtrain.self.code_comp.datatypes.outputtypes.DTOutput;
import cchef.jtrain.self.code_comp.datasource.DataSourceDriver;
import cchef.jtrain.self.code_comp.datasource.StringFromLocalFileAuto;
import cchef.jtrain.self.code_comp.datatypes.*;
import cchef.jtrain.self.code_comp.datasource.StringFromLocalFileHardCoded;

import static cchef.jtrain.self.code_comp.casetypes.StringType.*;

class CodeCompComparison {

  static final String OUTPUT_PATH = "/home/kali/Documents/001_CC/out.txt";
  static final FastWriter OUT = new FastWriter();
  static final StringType expectedInputStringType = NUMERIC_SP;
  static final StringType expectedOutputStringType = NUMERIC;
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
