package cchef.jtrain.self.code_comp.getdata;

// todo encapsulate with only public interface methods
// todo create final constant ImportInstructions class to be held by DataType classes that is
// populated with details of use eg. here passes file paths
// todo linked to above the ImportInstructions will allow e.g. any number of files to be imported.
// static finals below will become dynamic

import cchef.jtrain.self.code_comp.datatypes.outputtypes.DataTypeOutput;
import cchef.jtrain.self.code_comp.casetypes.importinfotypes.SDIArray;
import cchef.jtrain.self.code_comp.datatypes.DataType;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StringFromLocalFileAuto implements GetDataAs {
  private BufferedReader autoReader;
  private static String activePath = "";
  //SHARED
  static int linesPerOutput;
  private StringTokenizer tokenizer;
  // New automated.
  private DataType DATA_TYPE;
  private static String caseTypeActiveFilePath;
  private String[][] allInputData;



  // todo thoughts on how to make flexible
  public StringFromLocalFileAuto(final DataType DATA_TYPE) {
    // add user interaction, where are files coming from local/web dl / web api / cloud storage == GetInput Type
    // CaseType == number of filepaths/ api calls and other details to enable creation by a.. DataType
    // DataType == self contained creation of DataType objects that can then be turned into any required report
    // add flexible data type that adjusts to file inputs.
    // number of files from filepath array
    // add filepath array to CaseType
    this.DATA_TYPE = DATA_TYPE;
    autoReader = null;
  }


/** Given: Input is first sourcepath and so could have INPUT_FILE_HEADER
 *
 * @return
 * @throws FileNotFoundException
 */
@Override
  public DataType[] populate() {
    if ( Objects.isNull(this.DATA_TYPE))
      throw new IllegalStateException("@StringFromLocalFile/populate cannot populate with null DATA_TYPE");

    SDIArray sdiArray =  (SDIArray ) DATA_TYPE.getSourceDataInfo();
    String[] arrayOfPaths = sdiArray.getStringArray();
    int fileLength = 0;

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
        }
      else
        current = this.checkLinesReadStringArray((int)this.DATA_TYPE.getTOTAL_CASES());

      inputArray[i] = current;

    }
      catch( FileNotFoundException e ) {
        throw new RuntimeException(String.format("@StringFromFileAuto.populate() file %s was not found", activePath), e);
      }
      catch(IOException e) {
        throw new RuntimeException(String.format("@StringFromFileAuto.populate() file %s was found but another IOException occurred", activePath), e);
      }
    }
    return this.DATA_TYPE.consolidateData(inputArray);
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
public DataTypeOutput fetchFromDataSource() {
  return null;
}


String next() {
    while (tokenizer == null || !tokenizer.hasMoreElements()) {
      try {
        tokenizer = new StringTokenizer(autoReader.readLine());
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
    return tokenizer.nextToken();
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
  //TESTofDATA
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
