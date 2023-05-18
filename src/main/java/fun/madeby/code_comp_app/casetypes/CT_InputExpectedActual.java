package fun.madeby.code_comp_app.casetypes;

import fun.madeby.code_comp_app.casetypes.importinfotypes.SourceDataInfo;
import fun.madeby.code_comp_app.casetypes.importinfotypes.SDIArray;
import fun.madeby.code_comp_app.casetypes.importinfotypes.SDI_LHMap;

/**
 * A case type where three sources of input data in two forms 1[input] input 2[output] expected and actual are defined.
 * The number of lines per input and output and the number of cases should relate correctly. The stringtypes of the
 * input expected and actual files for this case-type can be ascertained by the checking algorithm.
 *
 *
 */
public class CT_InputExpectedActual implements CaseType {

  private final long TOTAL_CASES;
  private final int LINES_PER_CASE_INPUT;
  private final int LINES_PER_CASE_OUTPUT;
  private final StringType INPUT_ST;
  private final StringType EXPECTED_ST;
  private final StringType ACTUAL_ST;
  private final SourceDataInfo SOURCE_DATA_INFO;

  public CT_InputExpectedActual(
      final long TOTAL_CASES,
      final int LINES_PER_CASE_INPUT,
      final int LINES_PER_CASE_OUTPUT,
      final StringType INPUT_ST,
      final StringType EXPECTED_ST,
      final StringType ACTUAL_ST,
      final SourceDataInfo SOURCE_DATA_INFO) {
    this.TOTAL_CASES = TOTAL_CASES;
    this.LINES_PER_CASE_INPUT = LINES_PER_CASE_INPUT;
    this.LINES_PER_CASE_OUTPUT = LINES_PER_CASE_OUTPUT;
    this.INPUT_ST = INPUT_ST;
    this.EXPECTED_ST = EXPECTED_ST;
    this.ACTUAL_ST = ACTUAL_ST;
    if (SOURCE_DATA_INFO.isEmptyOrNull()) this.SOURCE_DATA_INFO = CaseType.DEFAULT_SDI_ARRAY;
    else this.SOURCE_DATA_INFO = SOURCE_DATA_INFO;
  }

  /**  Returns an array of this CaseTypes StringTypes
   * @return INPUT_ST, EXPECTED_ST, ACTUAL_ST being for example Numeric NumericSpace or NumericOtherSeparator
   */
  @Override
  public StringType[] getStringTypeArray() {
    return new StringType[] {INPUT_ST, EXPECTED_ST, ACTUAL_ST};
  }

  /**
   * @return
   */
  @Override
  public String caseTypeDescription() {
    return String.format(
        "There are %d cases in total, inputs are %d line/s per case, output is %d line/s per case\n"
            + "Input is %S, expected and actual are %S && %S",
        TOTAL_CASES, LINES_PER_CASE_INPUT, LINES_PER_CASE_OUTPUT, INPUT_ST, EXPECTED_ST, ACTUAL_ST);
  }

  /** Returns total cases for this CaseType this info will have been confirmed through testing or will be confirmed
   * against actual data.
   *
   * @return long, total cases for this CaseType
   */
  @Override
  public long getTOTAL_CASES() {
    return this.TOTAL_CASES;
  }

  /**Input and output lines in source files can differ any headers within files are handled within DataTypes
   * This is a confirmed or to be confirmed count of lines for this particular instance of this case type.
   * @return
   */
  @Override
  public int getLINES_PER_CASE_INPUT() {
    return this.LINES_PER_CASE_INPUT;
  }

  /**Input and output lines in source files can differ any headers within files are handled within DataTypes
   * This is a confirmed or to be confirmed count of lines for this particular instance of this case type.
   * @return
   */
  @Override
  public int getLINES_PER_CASE_OUTPUT() {
    return this.LINES_PER_CASE_OUTPUT;
  }

  /** SourceData is required by DataSource Drivers, and so is made available here, without making the SDI externally
   * available, though.. it is final.
   *<p>
   * See: {@link SDIArray}
   * </br>{@link SDI_LHMap}
   *
   * @return SourceDataInfo is either SDIArray or SDI_LHMap
   */
  @Override
  public SourceDataInfo getSourceDataInfo() {
    return this.SOURCE_DATA_INFO;
  }

  /** Not yet implemented, data checking may be consolidated, current @15/05/23 is not neatly implemented. todo
   * @return  boolean
   */
  @Override
  public boolean selfCheck() {
    return false;
  }

  @Override
  public String toString() {
    return "GenericCaseType{"
        + "TOTAL_CASES="
        + TOTAL_CASES
        + ", LINES_PER_INPUT="
        + LINES_PER_CASE_INPUT
        + ", LINES_PER_OUTPUT="
        + LINES_PER_CASE_OUTPUT
        + ", INPUT="
        + INPUT_ST
        + ", EXPECTED="
        + EXPECTED_ST
        + ", ACTUAL="
        + ACTUAL_ST
        + '}';
  }
}
