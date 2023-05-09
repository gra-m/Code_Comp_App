package cchef.jtrain.self.code_comp.inputconsolidation;

import cchef.jtrain.self.code_comp.datatypes.SourceDataInfo;

import java.util.Objects;

public class CT_InputExpectedActual implements CaseType {

  private final long TOTAL_CASES;
  private final int LINES_PER_INPUT;
  private final int LINES_PER_OUTPUT;
  private final StringType INPUT;
  private final StringType EXPECTED;
  private final StringType ACTUAL;
  private final SourceDataInfo SOURCE_DATA_INFO;

  public CT_InputExpectedActual(
      final long TOTAL_CASES,
      final int LINES_PER_INPUT,
      final int LINES_PER_OUTPUT,
      final StringType INPUT,
      final StringType EXPECTED,
      final StringType ACTUAL,
      final SourceDataInfo SOURCE_DATA_INFO) {
    this.TOTAL_CASES = TOTAL_CASES;
    this.LINES_PER_INPUT = LINES_PER_INPUT;
    this.LINES_PER_OUTPUT = LINES_PER_OUTPUT;
    this.INPUT = INPUT;
    this.EXPECTED = EXPECTED;
    this.ACTUAL = ACTUAL;
    if (SOURCE_DATA_INFO.isEmptyOrNull())
      this.SOURCE_DATA_INFO = CaseType.DEFAULT_SDI_ARRAY;
    else
      this.SOURCE_DATA_INFO = SOURCE_DATA_INFO;
  }

  /**
   * @return
   */
  @Override
  public StringType[] getStringTypeArray() {
    return new StringType[] {INPUT, EXPECTED, ACTUAL};
  }

  /**
   * @return
   */
  @Override
  public String stringTypeDescription() {
    return String.format(
        "There are %d cases in total, inputs are %d line/s per case, output is %d line/s per case\n"
            + "Input is %S, expected and actual outputs are %S && %S",
        TOTAL_CASES, LINES_PER_INPUT, LINES_PER_OUTPUT, INPUT, EXPECTED, ACTUAL);
  }

  /**
   * @return
   */
  @Override
  public int getLinesPerInput() {
    return this.LINES_PER_INPUT;
  }

  /**
   * @return
   */
  @Override
  public int getLinesPerOutput() {
    return this.LINES_PER_OUTPUT;
  }

/**
* 
   * @return
*/
@Override
public boolean selfCheck() {
  return false;
}

/**
   * @return
   */
  public long getTotalCases() {
    return this.TOTAL_CASES;
  }

  @Override
  public String toString() {
    return "GenericCaseType{"
        + "TOTAL_CASES="
        + TOTAL_CASES
        + ", LINES_PER_INPUT="
        + LINES_PER_INPUT
        + ", LINES_PER_OUTPUT="
        + LINES_PER_OUTPUT
        + ", INPUT="
        + INPUT
        + ", EXPECTED="
        + EXPECTED
        + ", ACTUAL="
        + ACTUAL
        + '}';
  }
}
