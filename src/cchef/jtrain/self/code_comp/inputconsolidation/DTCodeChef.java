package cchef.jtrain.self.code_comp.inputconsolidation;

import cchef.jtrain.self.code_comp.datatypes.DataTypeOutput;
import cchef.jtrain.self.code_comp.datatypes.SourceDataInfo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class DTCodeChef implements DataType {
  private static final int INPUT_FILE_HEADER = 1;
  private static String[] INPUT;
  private static int inputIndex;
  private static String[] EXPECTED;
  private static int expIndex;
  private static String[] ACTUAL;
  private static int actIndex;

  private final long CASE_INDEX;
  private final long CASE_ID;
  private final CT_InputExpectedActual CASE_TYPE;
  private final String[] CASE_INPUT_DATA;
  private final boolean status_passing;
  private final String[] EXP_OUTPUT_DATA;
  private final String[] ACT_OUTPUT_DATA;


  /**
   * Creates a template CodeChefDT with (what should be) a fully formed CASE_TYPE for the data being
   * imported.
   *
   * @param CASE_TYPE Must not be null
   * @throws NullPointerException if CASE_TYPE is null
   * @see DTCodeChef#DTCodeChef(long, long, CaseType, String[], String[], String[]) the private
   *     constructor used to create Arrays of this type.
   */
  public DTCodeChef(final CT_InputExpectedActual CASE_TYPE) {
    Objects.requireNonNull(CASE_TYPE);
    this.CASE_TYPE = CASE_TYPE;
    int linesPerOutput = this.CASE_TYPE.getLINES_PER_OUTPUT();
    this.CASE_INDEX = -1L;
    this.CASE_ID = -2L;
    this.CASE_INPUT_DATA = new String[this.CASE_TYPE.getLINES_PER_INPUT()];
    this.EXP_OUTPUT_DATA = new String[linesPerOutput];
    this.ACT_OUTPUT_DATA = new String[linesPerOutput];
    this.status_passing = false;
  }

  private DTCodeChef(
      final long CASE_INDEX,
      final long CASE_ID,
      final CaseType CASE_TYPE,
      final String[] CASE_INPUT_DATA,
      final String[] EXP_OUTPUT_DATA,
      final String[] ACT_OUTPUT_DATA) {
    this.CASE_INDEX = CASE_INDEX;
    this.CASE_ID = CASE_ID;
    this.CASE_TYPE = ( CT_InputExpectedActual ) CASE_TYPE;
    this.CASE_INPUT_DATA = CASE_INPUT_DATA;
    this.EXP_OUTPUT_DATA = EXP_OUTPUT_DATA;
    this.ACT_OUTPUT_DATA = ACT_OUTPUT_DATA;
    this.status_passing = expectedMatchesActual(this.EXP_OUTPUT_DATA, this.ACT_OUTPUT_DATA);
  }

/**
   * @return
   */
  @Override
  public String contentDescription() {
    return null;
  }

  /**
   * @return
   */
  @Override
  public int getLINES_PER_INPUT() {
    return this.CASE_TYPE.getLINES_PER_INPUT();
  }

  /**
   * @return
   */
  @Override
  public int getLINES_PER_OUTPUT() {
    return this.CASE_TYPE.getLINES_PER_OUTPUT();
  }

/**
* 
   * @return
*/
@Override
public long getTOTAL_CASES() {
  return this.CASE_TYPE.getTOTAL_CASES();
}

/**
* Used in StringFromLocalFile populate();
   * @return
*/
@Override
public int getInputFileHeaderSize() {
  return INPUT_FILE_HEADER;
}

/**
* 
   * @return
*/
@Override
public SourceDataInfo getSourceDataInfo() {
  return this.CASE_TYPE.getSourceDataInfo();
}


//todo for consolidated data return array/arraylist with unique id (timestamp) potentially Map<String, DataType[]> ???
  /**
   * @param input
   * @param expected
   * @param actual
   * @return
   */
  @Override
  public DataType[] consolidateData(String[] input, String[] expected, String[] actual) {
    if (logicCheckInputExpAct(input, expected, actual))
      setStaticArrayFields(input, expected, actual);
    else throw new IllegalStateException("Input expected and actual do not correlate");

    int totalCases = (int) CASE_TYPE.getTotalCases();
    int inDatLength = this.getLINES_PER_INPUT();
    int outDatLength = this.getLINES_PER_OUTPUT();
    inputIndex = 0;
    expIndex = 0;
    actIndex = 0;

    DataType[] consolidatedData = new DTCodeChef[totalCases];

    for (int i = 0; i < totalCases; i++) {
      String[] inputData = new String[inDatLength];
      getCaseInputArray(inputData, inDatLength);

      String[] expData = new String[outDatLength];
      getCaseExpectedArray(expData, outDatLength);

      String[] actData = new String[outDatLength];
      getCaseActualArray(actData, outDatLength);

      consolidatedData[i] = new DTCodeChef(i, (i + 1), this.CASE_TYPE, inputData, expData, actData);
    }

    return consolidatedData;
  }

  private String[] getCaseInputArray(String[] inputData, int inDatLength) {
    for (int in = 0; in < inDatLength; in++) {
      inputData[in] = INPUT[inputIndex];
      inputIndex++;
    }
    return inputData;
  }

  private String[] getCaseExpectedArray(String[] expData, int outDatLength) {
    for (int ex = 0; ex < outDatLength; ex++) {
      expData[ex] = EXPECTED[expIndex];
      expIndex++;
    }
    return expData;
  }

  private String[] getCaseActualArray(String[] actData, int outDatLength) {
    for (int act = 0; act < outDatLength; act++) {
      actData[act] = ACTUAL[actIndex];
      actIndex++;
    }
    return actData; //
  }

  private void setStaticArrayFields(
      final String[] INPUT, final String[] EXPECTED, final String[] ACTUAL) {
    DTCodeChef.INPUT = INPUT;
    DTCodeChef.EXPECTED = EXPECTED;
    DTCodeChef.ACTUAL = ACTUAL;
  }

  private boolean logicCheckInputExpAct(String[] input, String[] expected, String[] actual) {
    int total = (int) this.getTOTAL_CASES();
    int inputLines = this.getLINES_PER_INPUT() * total;
    int outputLines = this.getLINES_PER_OUTPUT() * total;
    int inputLength = removeSingleHeaderLineIfPresent(input, inputLines);
    return inputLines == inputLength && outputLines == expected.length && outputLines == actual.length;
  }

  private int removeSingleHeaderLineIfPresent(String[] input, int inputLines) {
    if (input.length - inputLines == INPUT_FILE_HEADER ) return inputLines;
    else
      throw new IllegalStateException("Extra codechef headerline was not present in input array");
  }


  /**
   * @param input
   * @param expected
   * @param actual
   * @return
   */
  @Override
  public List<DataType> consolidateDataToList(String[] input, String[] expected, String[] actual) {
    if (logicCheckInputExpAct(input, expected, actual))
      setStaticArrayFields(input, expected, actual);
    else throw new IllegalStateException("Input expected and actual do not correlate");

    int totalCases = (int) this.getTOTAL_CASES();
    int inDatLength = this.getLINES_PER_INPUT();
    int outDatLength = this.getLINES_PER_OUTPUT();
    inputIndex = 0;
    expIndex = 0;
    actIndex = 0;

    List<DataType> consolidatedData = new ArrayList<>(totalCases);

    for (int i = 0; i < totalCases; i++) {
      String[] inputData = new String[inDatLength];
      getCaseInputArray(inputData, inDatLength);

      String[] expData = new String[outDatLength];
      getCaseExpectedArray(expData, outDatLength);

      String[] actData = new String[outDatLength];
      getCaseActualArray(actData, outDatLength);

      consolidatedData.add(
          i, new DTCodeChef(i, (i + 1), this.CASE_TYPE, inputData, expData, actData));
    }

    return consolidatedData;
  }

/**
* 
   * @param
   * @param
   * @return
*/
public DataType[] consolidateData(String[][] inputArray) {
  if (Objects.nonNull(inputArray))
      return consolidateData(inputArray[0], inputArray[1], inputArray[2]);
  else
    throw new RuntimeException("@DTCodeChef consolidateData (String[][] inputArray [was null])");
}


private boolean expectedMatchesActual(String[] expected, String[] actual) {
    return Arrays.deepEquals(expected, actual);
  }

  @Override
  public String toString() {
    return "CodeChefDT{"
        + "CASE_INDEX="
        + CASE_INDEX
        + ", CASE_ID="
        + CASE_ID
        + ", CASE_TYPE="
        + CASE_TYPE
        + ", CASE_INPUT_DATA="
        + Arrays.toString(CASE_INPUT_DATA)
        + ", status_passing="
        + status_passing
        + ", EXP_OUTPUT_DATA="
        + Arrays.toString(EXP_OUTPUT_DATA)
        + ", ACT_OUTPUT_DATA="
        + Arrays.toString(ACT_OUTPUT_DATA)
        + '\''
        + '}';
  }
}
