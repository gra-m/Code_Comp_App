package fun.madeby.code_comp.datatypes;

import fun.madeby.code_comp.casetypes.CT_InputExpectedActual;
import fun.madeby.code_comp.casetypes.CaseType;
import fun.madeby.code_comp.casetypes.importinfotypes.SourceDataInfo;
import fun.madeby.code_comp.datatypes.outputtypes.ArrayOutput;
import fun.madeby.code_comp.datatypes.outputtypes.DTOutput;
import fun.madeby.code_comp.datatypes.outputtypes.ListOutput;
import fun.madeby.code_comp.outputdata.DataSnapshot;
import fun.madeby.code_comp.outputdata.NonAuditable;

import java.time.ZonedDateTime;
import java.util.*;

/**
 * Given data that matches this DataType Template, that is data that originates from the CodeChef
 * challenges input, expected, output file downloads, this class consolidates each case into a
 * non-template version of itself that can be instantly returned as a list or array or held as a
 * snapshot for further processing and reporting. Action taken during consolidation: Does actual
 * output match expected output for every given case: STATUS_PASSING
 */
public class DT_CC_Template implements DataTypeTemplate {
  private static final int INPUT_FILE_HEADER = 1; // the number of lines of header
  private static String[] INPUT_DATA;
  private static int inputIndex;
  private static String[] EXPECTED_DATA;
  private static int expIndex;
  private static String[] ACTUAL_DATA;
  private static int actIndex;

  private final long CASE_INDEX;
  private final long CASE_ID;
  private final CT_InputExpectedActual CASE_TYPE;
  private final boolean STATUS_PASSING;

  private final String[] CASE_INPUT_DATA;
  private final String[] CASE_EXP_OUTPUT_DATA;
  private final String[] CASE_ACT_OUTPUT_DATA;

  private final boolean TEMPLATE; // template -> <- report/case record


private final LinkedHashMap<ZonedDateTime, DataSnapshot> snapshots = new LinkedHashMap<>();


/**
 * Preserves the integrity of 'snapshots' but not the objects within it, should be used with care and eventually removed.
 * todo replace with deep copy import Apache Commons Serialization utils then copy = SerializationUtils.clone(otherHM);
 *
 * @return a shallow copy of snapshots
 */
@Override
public LinkedHashMap<ZonedDateTime, DataSnapshot> getShallowCopyOfSnapshots() {
  if (isTEMPLATE()) {
    LinkedHashMap<ZonedDateTime, DataSnapshot> copy = new LinkedHashMap<>(this.snapshots);

    return copy;

  } else
    throw new IllegalStateException("Cannot getSnapshots for non-template DataType");
}

  /**
   * WIP Creates a TEMPLATE [true] DT_CC_Template with (what should be) a fully formed CASE_TYPE for
   * the type of data that this template imports. Instructions from the CASE_TYPE are directly
   * pulled through into this DataTypeTemplate's fields, this may be revisited, but at present makes
   * this info externally available without digging.
   *
   * @param CASE_TYPE Must not be null
   * @throws NullPointerException if CASE_TYPE is null
   * @see #DT_CC_Template(long, long, CaseType, String[], String[], String[], boolean) the private
   *     constructor used to create Arrays of this type.
   */
  public DT_CC_Template(final CT_InputExpectedActual CASE_TYPE) {
    Objects.requireNonNull(CASE_TYPE);
    this.CASE_TYPE = CASE_TYPE;
    int linesPerOutput = this.CASE_TYPE.getLINES_PER_CASE_OUTPUT();
    this.CASE_INDEX = -1L;
    this.CASE_ID = -2L;
    this.CASE_INPUT_DATA = new String[this.CASE_TYPE.getLINES_PER_CASE_INPUT()];
    this.CASE_EXP_OUTPUT_DATA = new String[linesPerOutput];
    this.CASE_ACT_OUTPUT_DATA = new String[linesPerOutput];
    this.STATUS_PASSING = false;
    this.TEMPLATE = true;
  }

  /**
   * The internal constructor for DataTypeTemplates of type DT_CC_Template when they are being
   * consolidated for quick reference or in order to create a
   *
   * @param CASE_INDEX the position of this case in any DataTypeTemplate[]
   * @param CASE_ID the case number from an external perspective starting at 1
   * @param CASE_TYPE
   * @see CaseType
   * @param CASE_INPUT_DATA the input data for this case
   * @param CASE_EXP_OUTPUT_DATA the expected output data for this case
   * @param CASE_ACT_OUTPUT_DATA the actual output data for this case
   * @param PASSING == true if expected and actual data are the same
   */
  private DT_CC_Template(
      final long CASE_INDEX,
      final long CASE_ID,
      final CaseType CASE_TYPE,
      final String[] CASE_INPUT_DATA,
      final String[] CASE_EXP_OUTPUT_DATA,
      final String[] CASE_ACT_OUTPUT_DATA,
      final boolean PASSING) {
    this.CASE_INDEX = CASE_INDEX;
    this.CASE_ID = CASE_ID;
    this.CASE_TYPE = (CT_InputExpectedActual) CASE_TYPE;
    this.CASE_INPUT_DATA = CASE_INPUT_DATA;
    this.CASE_EXP_OUTPUT_DATA = CASE_EXP_OUTPUT_DATA;
    this.CASE_ACT_OUTPUT_DATA = CASE_ACT_OUTPUT_DATA;
    this.STATUS_PASSING = PASSING;
    this.TEMPLATE = false;
  }

  /**
   * @return
   */
  @Override
  public SourceDataInfo getSourceDataInfo() {
    if (this.isTEMPLATE()) {
      return this.CASE_TYPE.getSourceDataInfo();
    } else
      throw new IllegalStateException("Cannot retrieve SourceDataInfo for non-template DataType");
  }


  /**
   * @param
   * @param
   * @return
   */
  public DTOutput consolidateDataToArray(String[][] inputArray, boolean asArray) {
    if (this.isTEMPLATE()) {
      if (Objects.nonNull(inputArray)) {
        if (asArray)
          return new ArrayOutput(
              consolidateDataToArray(inputArray[0], inputArray[1], inputArray[2]));
        else
          return new ListOutput(consolidateDataToList(inputArray[0], inputArray[1], inputArray[2]));
      } else
        throw new RuntimeException(
            "@DTCodeChef consolidateData(String[][] inputArray [was null], arrayOutput)");
    } else
      throw new IllegalStateException(
          "Cannot consolidateDataToArray(String[][] inputArray, boolean asArray) with non-template DataType");
  }

  /**
   * @param inputArray
   * @param userTimeZone
   * @param asArray
   */
  @Override
  public void addNonAuditableSnapshot(String[][] inputArray, String userTimeZone, boolean asArray) {
    if (this.isTEMPLATE()) {
      DTOutput dtOutput = consolidateDataToArray(inputArray, asArray);
      NonAuditable nonAuditableSnap = new NonAuditable(dtOutput, userTimeZone);
      this.snapshots.put(nonAuditableSnap.getCREATED(), nonAuditableSnap);

      printSnapshots();

    } else throw new IllegalStateException("Cannot create snapshot with non-template DataType");
  }

  /** Takes the current snapshots LinkedHashMap<ZoneDateTime, Snapshot> and prints to console. */
  @Override
  public void printSnapshots() {
    if (isTEMPLATE()) {
      for (Map.Entry<ZonedDateTime, DataSnapshot> entry : this.snapshots.entrySet()) {
        System.out.println(entry.getKey() + " " + entry.getValue().getInfo());
      }
    } else
      throw new IllegalStateException("Cannot consolidateDataToArray for non-template DataType");
  }

  /**
   * Takes the input requirements of this DataTypeTemplate and retrieves the data instructions held
   * within this DataTypeTemplate and consolidates them into a DataTypeTemplate[] within which each
   * newly created DataTypeTemplate contains the data for a single CodeChef case. Expected and
   * Actual output for each case are compared and if they are the same the boolean 'PASSING' is true
   * at the time of creation.
   *
   * <p>Example
   *
   * <pre>
   *             String[] input = new String[]{"2", "1 2 3", "2, 2, 1"}; // fixme confirm whether "2" is in place here
   *             String[] expected = new String[]{"3", "2"};
   *             String[] actual = new String[]{"3", "1"};
   *
   *       </pre>
   *
   * @param input String[]
   * @param expected String[]
   * @param actual String[]
   * @return An array of DT_CC_Templates as long as the number of cases, with each cases information
   *     and whether it is passing ** @See List<DataTypeTemplate> consolidateDataToList(String[]
   *     input, String[] expected, String[] actual)
   */
  private DataTypeTemplate[] consolidateDataToArray(
      String[] input, String[] expected, String[] actual) {

    if (isTEMPLATE()) {

      if (logicCheckInputExpAct(input, expected, actual))
        setStaticArrayFields(input, expected, actual);
      else throw new IllegalStateException("Input expected and actual do not correlate");

      int totalCases = (int) this.getTOTAL_CASES();
      int inDatLength = this.getLINES_PER_INPUT();
      int outDatLength = this.getLINES_PER_OUTPUT();
      inputIndex = 0;
      expIndex = 0;
      actIndex = 0;

      DataTypeTemplate[] consolidatedData = new DT_CC_Template[totalCases];

      for (int i = 0; i < totalCases; i++) {
        String[] inputData = new String[inDatLength];
        populateCaseInputArray(inputData, inDatLength);

        String[] expData = new String[outDatLength];
        populateCaseExpectedArray(expData, outDatLength);

        String[] actData = new String[outDatLength];
        populateCaseActualArray(actData, outDatLength);

        consolidatedData[i] =
            new DT_CC_Template(
                i,
                (i + 1),
                this.CASE_TYPE,
                inputData,
                expData,
                actData,
                expectedMatchesActual(expData, actData));
      }

      return consolidatedData;

    } else
      throw new IllegalStateException("Cannot consolidateDataToArray for non-template DataType");
  }

  /**
   * @param input
   * @param expected
   * @param actual
   * @return
   */
  private List<DataTypeTemplate> consolidateDataToList(
      String[] input, String[] expected, String[] actual) {

    if (isTEMPLATE()) {

      if (logicCheckInputExpAct(input, expected, actual))
        setStaticArrayFields(input, expected, actual);
      else throw new IllegalStateException("Input expected and actual do not correlate");

      int totalCases = (int) this.CASE_TYPE.getTOTAL_CASES();
      int inDatLength = this.CASE_TYPE.getLINES_PER_CASE_INPUT();
      int outDatLength = this.CASE_TYPE.getLINES_PER_CASE_OUTPUT();
      inputIndex = 0;
      expIndex = 0;
      actIndex = 0;

      List<DataTypeTemplate> consolidatedData = new ArrayList<>(totalCases);

      for (int i = 0; i < totalCases; i++) {
        String[] inputData = new String[inDatLength];
        populateCaseInputArray(inputData, inDatLength);

        String[] expData = new String[outDatLength];
        populateCaseExpectedArray(expData, outDatLength);

        String[] actData = new String[outDatLength];
        populateCaseActualArray(actData, outDatLength);

        consolidatedData.add(
            i,
            new DT_CC_Template(
                i,
                (i + 1),
                this.CASE_TYPE,
                inputData,
                expData,
                actData,
                expectedMatchesActual(expData, actData)));
      }

      return consolidatedData;

    } else
      throw new IllegalStateException("Cannot consolidateDataToList for non-template DataType");
  }

  private void populateCaseInputArray(String[] inputData, int inDatLength) {
    for (int in = 0; in < inDatLength; in++) {
      inputData[in] = INPUT_DATA[inputIndex++];
    }
  }

  private void populateCaseExpectedArray(String[] expData, int outDatLength) {
    for (int ex = 0; ex < outDatLength; ex++) {
      expData[ex] = EXPECTED_DATA[expIndex++];
    }
  }

  private void populateCaseActualArray(String[] actData, int outDatLength) {
    for (int act = 0; act < outDatLength; act++) {
      actData[act] = ACTUAL_DATA[actIndex++];
    }
  }

  /**
   * Prior to consolidateDataToList/Array or the templates static arrays
   *
   * @param INPUT
   * @param EXPECTED
   * @param ACTUAL
   */
  private void setStaticArrayFields(
      final String[] INPUT, final String[] EXPECTED, final String[] ACTUAL) {
    DT_CC_Template.INPUT_DATA = INPUT;
    DT_CC_Template.EXPECTED_DATA = EXPECTED;
    DT_CC_Template.ACTUAL_DATA = ACTUAL;
  }

  /**
   * Checks the actual length of passed input expected and actual arrays prior to setting the static
   * array fields INPUT_DATA EXPECTED_DATA and ACTUAL_DATA
   *
   * @param input the passed input data for a CC problem normally includes 1 header line
   * @param expected the expected output
   * @param actual the actual output obtained
   * @return true if Input Expected and Actual are as specified
   */
  private boolean logicCheckInputExpAct(String[] input, String[] expected, String[] actual) {
    int totalCases = (int) this.getTOTAL_CASES();
    int expectedInputLength = this.getLINES_PER_INPUT() * totalCases;
    int outputLines = this.getLINES_PER_OUTPUT() * totalCases;

    return checkWithoutHeaders(input.length, expectedInputLength)
        && outputLines == expected.length
        && outputLines == actual.length;
  }

  /**
   * A confirmation that the input length (no of lines) is what is expected for the number of cases,
   * having first removed any headerlines (INPUT_FILE_HEADER);
   *
   * @param inputLength the length of the array passed to this template
   * @param expectedInputLines the number of lines from the expected total cases * lines per input
   * @return true if it is correct
   */
  private boolean checkWithoutHeaders(int inputLength, int expectedInputLines) {
    int inputLengthMinusHeaders = inputLength - INPUT_FILE_HEADER;
    if (inputLengthMinusHeaders == expectedInputLines) return true;
    else
      throw new IllegalStateException(
          String.format(
              "inputLength %d - INPUT_FILE_HEADER %d = (%d) does not equal expectedInputLines %d",
              inputLength, INPUT_FILE_HEADER, inputLengthMinusHeaders, expectedInputLines));
  }

  private boolean expectedMatchesActual(String[] expected, String[] actual) {
    return Arrays.deepEquals(expected, actual);
  }

  /**
   * Makes LINES_PER_INPUT for this DT_CC_Template CASE_TYPE externally accessible
   *
   * @return int
   */
  @Override
  public int getLINES_PER_INPUT() {
    if (isTEMPLATE()) {
      return this.CASE_TYPE.getLINES_PER_CASE_INPUT();
    } else
      throw new IllegalStateException("Cannot getLINES_PER_CASE_INPUT for non-template DataType");
  }

  /**
   * Makes LINES_PER_OUTPUT for this DT_CC_Template CASE_TYPE externally accessible
   *
   * @return int
   */
  @Override
  public int getLINES_PER_OUTPUT() {
    if (isTEMPLATE()) {
      return this.CASE_TYPE.getLINES_PER_CASE_OUTPUT();
    } else
      throw new IllegalStateException("Cannot getLINES_PER_CASE_OUTPUT for non-template DataType");
  }

  /**
   * Makes TOTAL_CASES for this DT_CC_Template CASE_TYPE externally accessible
   *
   * @return long
   */
  @Override
  public long getTOTAL_CASES() {
    if (isTEMPLATE()) {
      return this.CASE_TYPE.getTOTAL_CASES();

    } else throw new IllegalStateException("Cannot getTOTAL_CASES for non-template DataType");
  }

  /**
   * @return
   */
  @Override
  public boolean isTEMPLATE() {
    return this.TEMPLATE;
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
        + STATUS_PASSING
        + ", EXP_OUTPUT_DATA="
        + Arrays.toString(CASE_EXP_OUTPUT_DATA)
        + ", ACT_OUTPUT_DATA="
        + Arrays.toString(CASE_ACT_OUTPUT_DATA)
        + '\''
        + '}';
  }
}
