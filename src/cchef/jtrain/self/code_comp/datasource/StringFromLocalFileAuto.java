package cchef.jtrain.self.code_comp.datasource;

// todo encapsulate with only public interface methods
// populated with details of use eg. here passes file paths

import cchef.jtrain.self.code_comp.datatypes.outputtypes.DTOutput;
import cchef.jtrain.self.code_comp.casetypes.importinfotypes.SDIArray;
import cchef.jtrain.self.code_comp.datatypes.DataTypeTemplate;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static cchef.jtrain.self.code_comp.Utilz.getUserTimeZone;

public class StringFromLocalFileAuto implements CreateData {
  // SHARED
  static int linesPerOutput;
  private static String activePath = "";
  // New automated.
  private final DataTypeTemplate DATA_TYPE_TEMPLATE;
  private BufferedReader autoReader;
  private String[][] allInputData;

  // todo thoughts on how to make flexible test == variable number of input arrays
  public StringFromLocalFileAuto(final DataTypeTemplate DATA_TYPE_TEMPLATE) {

    this.DATA_TYPE_TEMPLATE = DATA_TYPE_TEMPLATE;
    autoReader = null;
  }

  private static Integer[] intArrToIntegerArr(int[] st01) throws NumberFormatException {

    IntStream st02 = Arrays.stream(st01);
    Stream<Integer> st03 = st02.boxed();

    return st03.toArray(Integer[]::new);
  }

  private static String cleanString(String raw) {
    return raw.replaceAll("[^\\d\\s]", "").trim();
  }

/**
 *
 * @return
 */
@Override
public void createNonAuditableSnapshot(boolean arrayDto) {
  // todo create checking method for this
  if (Objects.isNull(this.DATA_TYPE_TEMPLATE) || !this.DATA_TYPE_TEMPLATE.isTEMPLATE())
    throw new IllegalStateException(
        "@StringFromLocalFile/createNonAuditableSnapshot() cannot create snapshot with null DATA_TYPE");
  else if(!this.DATA_TYPE_TEMPLATE.isTEMPLATE()){
    throw new IllegalStateException(
        "@StringFromLocalFile/createNonAuditableSnapshot() cannot create snapshot with non-template DATA_TYPE");
  }
  // todo add java docs for process below
  String[] sources = getSources();
  String[][] inputArray = getInputDataFromSource(sources);
  String userTimeZone = getUserTimeZone();
  this.DATA_TYPE_TEMPLATE.addNonAuditableSnapshot(inputArray, userTimeZone, arrayDto);

}

/**
* 
   * @return
*/
@Override
public void createAuditableSnapshot(final boolean arrayDto) {
  if (Objects.isNull(this.DATA_TYPE_TEMPLATE) || !this.DATA_TYPE_TEMPLATE.isTEMPLATE())
    throw new IllegalStateException(
        "@StringFromLocalFile/createAuditableSnapshot() cannot create snapshot with null DATA_TYPE");
  else if(!this.DATA_TYPE_TEMPLATE.isTEMPLATE()){
    throw new IllegalStateException(
        "@StringFromLocalFile/createAuditableSnapshot() cannot create snapshot with non-template DATA_TYPE");
  }
  String[] sources = getSources();
  String[][] inputArray = getInputDataFromSource(sources);

}

/**
 * Used to print Snapshot info temporarily, this will probably return String.format
 * in the future
  */
@Override
public void printSnapshotInfo() {
  this.DATA_TYPE_TEMPLATE.printSnapshots();

}


/**
 *
 * @param arrayDto
 * @return
 */
@Override
public DTOutput getOnTheFlyData(final boolean arrayDto) {
  if (Objects.isNull(this.DATA_TYPE_TEMPLATE) || !this.DATA_TYPE_TEMPLATE.isTEMPLATE())
    throw new IllegalStateException(
        "@StringFromLocalFile/getOnTheFlyData() cannot getOnTheFlyData with null DATA_TYPE");
  else if(!this.DATA_TYPE_TEMPLATE.isTEMPLATE()){
    throw new IllegalStateException(
        "@StringFromLocalFile/getOnTheFlyData() cannot getOnTheFlyData with non-template DATA_TYPE");
  }

  String[] sources = getSources();
  String[][] inputArray = getInputDataFromSource(sources);

  return this.DATA_TYPE_TEMPLATE.consolidateDataToArray(inputArray, arrayDto);
}


/** Replaced below, Will be removed, kept for current run testing
   *
   *
   * @return
   */
  public DataTypeTemplate[] getOnTheFlyData() {
    if (Objects.isNull(this.DATA_TYPE_TEMPLATE) || this.DATA_TYPE_TEMPLATE.isTEMPLATE())
      throw new IllegalStateException(
          "@StringFromLocalFile/getOnTheFlyData() cannot populate with null or non-template DATA_TYPE");

    String[] sources = getSources();
    String[][] inputArray = getInputDataFromSource(sources);

     DTOutput dtOutput = this.DATA_TYPE_TEMPLATE.consolidateDataToArray(inputArray, true);

     return dtOutput.getArray(dtOutput);

  }




// todo CheckSources interface
private String[] getSources() {
    Object sourceDataInfoType = DATA_TYPE_TEMPLATE.getSourceDataInfo();

    if (sourceDataInfoType instanceof SDIArray) {
      SDIArray sdiArray = (SDIArray) sourceDataInfoType;
      return sdiArray.getStringArray();
    } else
      throw new RuntimeException(
          String.format(
              "@StringFromLocalFileAuto/getSources() expected SDIArray sourceDataInfoType but (%s) was retrieved from DATA_TYPE",
              sourceDataInfoType.getClass()));
  }






       // todo this is universal type 'test' add to CheckSources interface?
  private String[][] getInputDataFromSource(String[] arrayOfPaths) {

    int fileLength;

    String[][] inputArray = new String[arrayOfPaths.length][];
    String[] current;

    for (int i = 0; i < arrayOfPaths.length; i++) {
      System.out.println(arrayOfPaths[i]);
      activePath = arrayOfPaths[i];
      try (BufferedReader reader = new BufferedReader(new FileReader(activePath))) {
        autoReader = reader;
        if (i == 0) {
          fileLength = (int) this.countLines();
          current = this.readStringArray(fileLength);
        } else current = this.checkLinesReadStringArray((int) this.DATA_TYPE_TEMPLATE.getTOTAL_CASES());

        inputArray[i] = current;

      } catch (FileNotFoundException e) {
        throw new RuntimeException(
            String.format("@StringFromFileAuto.populate() file %s was not found", activePath), e);
      } catch (IOException e) {
        throw new RuntimeException(
            String.format(
                "@StringFromFileAuto.populate() file %s was found but another IOException occurred",
                activePath),
            e);
      }
    }
    return inputArray;
  }


  // TESTofDATA
  public boolean inputVsOutput(long expected) {
    // todo explicitly populate lines per output, it is known at time of return true below but is
    long linesInActivePathFile = countLines();
    if (expected == linesInActivePathFile) {
      linesPerOutput = 1;
      return true;
    } else
      throw new IllegalStateException(
          (String.format(
              "inputVsOutput(long expected):\nLine length of activePath file %s does not match number of expected cases %s",
              linesInActivePathFile, expected)));
  }
  // TESTofDATA
  public long countLines() {
    try (Stream<String> stream = Files.lines(Path.of(activePath), StandardCharsets.UTF_8)) {
      return stream.count();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  public String[] readStringArray(int n) {
    String[] arr = new String[n];
    for (int i = 0; i < n; i++) arr[i] = this.nextLine().trim();
    return arr;
  }

  public String[] checkLinesReadStringArray(int expectedLines) {
    inputVsOutput(expectedLines);
    System.out.println("Expected Lines = " + expectedLines);
    String[] arr = new String[expectedLines];
    for (int i = 0; i < expectedLines; i++) arr[i] = this.nextLine().trim();
    return arr;
  }

  public String nextLine() {
    String str = "";
    try {
      str = autoReader.readLine();
    } catch (IOException e) {
      e.printStackTrace();
    }
    return str;
  }

  public String cleanDigitsSpaces() {
    String str = "";
    try {
      str = cleanString(autoReader.readLine().trim());
    } catch (IOException e) {
      e.printStackTrace();
    }
    return str;
  }

  public String[] nextLine_A(int n) {
    String[] arr = new String[n];
    try {
      return autoReader.readLine().trim().split("\\s+");
    } catch (IOException e) {
      e.printStackTrace();
    }
    return arr;
  }

  public void close() {
    try {
      autoReader.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
