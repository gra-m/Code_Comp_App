package fun.madeby.code_comp.datasource;

// todo encapsulate with only public interface methods
// todo create final constant ImportInstructions class to be held by DataType classes that is
// populated with details of use eg. here passes file paths
// todo linked to above the ImportInstructions will allow e.g. any number of files to be imported.
// static finals below will become dynamic

import fun.madeby.code_comp.datatypes.DataTypeTemplate;
import fun.madeby.code_comp.datatypes.outputtypes.DTOutput;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StringFromLocalFileHardCoded {
  // ORIGiNAl LiKe FastWRiter
  static final String _1FULL_IN = "/home/kali/Documents/001_CC/00fullIn.txt";
  static final String _2EXP_IN = "/home/kali/Documents/001_CC/00exp.txt";
  static final String _3ACT_IN = "/home/kali/Documents/001_CC/00act.txt";
  private final BufferedReader READER;
  private String activePath = "";
  //SHARED
  static int linesPerOutput;
  private StringTokenizer tokenizer;
  // New automated.
  private DataTypeTemplate DATA_TYPE;
  private static String caseTypeActiveFilePath;
  private BufferedReader AutoReader;
  private String[][] allInputData;



  // todo thoughts on how to make flexible
  public StringFromLocalFileHardCoded(final DataTypeTemplate DATA_TYPE) {
    // add user interaction, where are files coming from local/web dl / web api / cloud storage == GetInput Type
    // CaseType == number of filepaths/ api calls and other details to enable creation by a.. DataType
    // DataType == self contained creation of DataType objects that can then be turned into any required report
    // add flexible data type that adjusts to file inputs.
    // number of files from filepath array
    // add filepath array to CaseType
    this.DATA_TYPE = DATA_TYPE;
    READER = null;
  }


/** Given: Input is first sourcepath and so could have INPUT_FILE_HEADER
 *
 * @return
 * @throws FileNotFoundException
 *//*

  @Override
  public DataType[] populate() throws FileNotFoundException {
    if ( Objects.isNull(this.DATA_TYPE))
      throw new IllegalStateException("@StringFromLocalFile/populate cannot populate with null DATA_TYPE");

    SDIArray sdiArray =  (SDIArray ) DATA_TYPE.getSourceDataInfo();
    String[] arrayOfPaths = sdiArray.getStringArray();
    int headerLines = DATA_TYPE.getInputFileHeaderSize();
    String[][] inputArray = new String[arrayOfPaths.length][];

    for (String str: arrayOfPaths ) {
      System.out.println(str);
      caseTypeActiveFilePath = str;
      AutoReader = new BufferedReader(new FileReader(caseTypeActiveFilePath));


    }
    return null;
  }
*/

  public StringFromLocalFileHardCoded(int pathNum) {
    BufferedReader reader;

    if (System.getProperty("ONLINE_JUDGE") == null) {
      try { // todo try with resources
        if (pathNum == 1) reader = new BufferedReader(new FileReader(activePath = _1FULL_IN));
        else if (pathNum == 2) reader = new BufferedReader(new FileReader(activePath = _2EXP_IN));
        else reader = new BufferedReader(new FileReader(activePath = _3ACT_IN));

      } catch (FileNotFoundException e) {
        throw new IllegalArgumentException(
            String.format("Active path is empty? %s", activePath), e);
      }
    } else {
      reader = new BufferedReader(new InputStreamReader(System.in));
    }
    this.READER = reader;
    this.DATA_TYPE = null;
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
public DTOutput fetchFromDataSource() {
  return null;
}


String next() {
    while (tokenizer == null || !tokenizer.hasMoreElements()) {
      try {
        tokenizer = new StringTokenizer(READER.readLine());
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
    if (activePath.isEmpty()) activePath = _1FULL_IN;

    try (Stream<String> stream = Files.lines(Path.of(activePath), StandardCharsets.UTF_8)) {
      return stream.count();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }



  public int nextInt() {
    return Integer.parseInt(next());
  }

  public long nextLong() {
    return Long.parseLong(next());
  }

  public double nextDouble() {
    return Double.parseDouble(next());
  }

  public List<Integer> readIntList(int n) {
    List<Integer> arr = new ArrayList<>();
    for (int i = 0; i < n; i++) arr.add(this.nextInt());
    return arr;
  }

  public List<Long> readLongList(int n) {
    List<Long> arr = new ArrayList<>();
    for (int i = 0; i < n; i++) arr.add(this.nextLong());
    return arr;
  }

  public int[] readIntArr(int n) {
    int[] arr = new int[n];
    for (int i = 0; i < n; i++) arr[i] = this.nextInt();
    return arr;
  }

  public Integer[] readIntegerArray(int n) {
    int[] arr = new int[n];
    for (int i = 0; i < n; i++) arr[i] = this.nextInt();
    return intArrToIntegerArr(arr);
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

  public long[] readLongArr(int n) {
    long[] arr = new long[n];
    for (int i = 0; i < n; i++) arr[i] = this.nextLong();
    return arr;
  }

  public String nextLine() {
    String str = "";
    try {
      str = READER.readLine();
    } catch (IOException e) {
      e.printStackTrace();
    }
    return str;
  }

  public String cleanDigitsSpaces() {
    String str = "";
    try {
      str = cleanString(READER.readLine().trim());
    } catch (IOException e) {
      e.printStackTrace();
    }
    return str;
  }

  public String[] nextLine_A(int n) {
    String[] arr = new String[n];
    try {
      return READER.readLine().trim().split("\\s+");
    } catch (IOException e) {
      e.printStackTrace();
    }
    return arr;
  }

  public void close() {
    try {
      READER.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
