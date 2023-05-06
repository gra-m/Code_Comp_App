package cchef.jtrain.self.getdata.data_consolidation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class CodeChefDT implements DataType {
  private static final int EXPECTED_CC_INPUT_HEADER = 1;
  private static String[] INPUT;
  private static int inputIndex;
  private static String[] EXPECTED;
  private static int expIndex;
  private static String[] ACTUAL;
  private static int actIndex;

  private final long CASE_INDEX;
  private final long CASE_ID;
  private final GenericCaseType CASE_TYPE;
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
   * @see CodeChefDT#CodeChefDT(long, long, CaseType, String[], String[], String[]) the private
   *     constructor used to create Arrays of this type.
   */
  public CodeChefDT(final GenericCaseType CASE_TYPE) {
    Objects.requireNonNull(CASE_TYPE);
    this.CASE_TYPE = CASE_TYPE;
    int linesPerOutput = this.CASE_TYPE.getLinesPerOutput();
    this.CASE_INDEX = -1L;
    this.CASE_ID = -2L;
    this.CASE_INPUT_DATA = new String[this.CASE_TYPE.getLinesPerInput()];
    this.EXP_OUTPUT_DATA = new String[linesPerOutput];
    this.ACT_OUTPUT_DATA = new String[linesPerOutput];
    this.status_passing = true;
  }

  private CodeChefDT(
      final long CASE_INDEX,
      final long CASE_ID,
      final CaseType CASE_TYPE,
      final String[] CASE_INPUT_DATA,
      final String[] EXP_OUTPUT_DATA,
      final String[] ACT_OUTPUT_DATA) {
    this.CASE_INDEX = CASE_INDEX;
    this.CASE_ID = CASE_ID;
    this.CASE_TYPE = (GenericCaseType) CASE_TYPE;
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
  public int getLinesPerInput() {
    return this.CASE_TYPE.getLinesPerInput();
  }

  /**
   * @return
   */
  @Override
  public int getLinesPerOutput() {
    return this.CASE_TYPE.getLinesPerOutput();
  }

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
    int inDatLength = CASE_TYPE.getLinesPerInput();
    int outDatLength = CASE_TYPE.getLinesPerOutput();
    inputIndex = 0;
    expIndex = 0;
    actIndex = 0;

    DataType[] consolidatedData = new CodeChefDT[totalCases];

    for (int i = 0; i < totalCases; i++) {
      String[] inputData = new String[inDatLength];
      getCaseInputArray(inputData, inDatLength);

      String[] expData = new String[outDatLength];
      getCaseExpectedArray(expData, outDatLength);

      String[] actData = new String[outDatLength];
      getCaseActualArray(actData, outDatLength);

      consolidatedData[i] = new CodeChefDT(i, (i + 1), this.CASE_TYPE, inputData, expData, actData);
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
    CodeChefDT.INPUT = INPUT;
    CodeChefDT.EXPECTED = EXPECTED;
    CodeChefDT.ACTUAL = ACTUAL;
  }

  private boolean logicCheckInputExpAct(String[] input, String[] expected, String[] actual) {
    int total = (int) CASE_TYPE.getTotalCases();
    int inputLines = CASE_TYPE.getLinesPerInput() * total;
    int outputLines = CASE_TYPE.getLinesPerOutput() * total;
    int inputLength = removeSingleHeaderLineIfPresent(input, inputLines);
    return inputLines == inputLength && outputLines == expected.length && outputLines == actual.length;
  }

  private int removeSingleHeaderLineIfPresent(String[] input, int inputLines) {
    if (input.length - inputLines == EXPECTED_CC_INPUT_HEADER) return inputLines;
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

    int totalCases = (int) CASE_TYPE.getTotalCases();
    int inDatLength = CASE_TYPE.getLinesPerInput();
    int outDatLength = CASE_TYPE.getLinesPerOutput();
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
          i, new CodeChefDT(i, (i + 1), this.CASE_TYPE, inputData, expData, actData));
    }

    return consolidatedData;
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
