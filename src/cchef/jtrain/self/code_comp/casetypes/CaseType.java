package cchef.jtrain.self.code_comp.casetypes;

import cchef.jtrain.self.code_comp.casetypes.importinfotypes.SDIArray;
import cchef.jtrain.self.code_comp.casetypes.importinfotypes.SourceDataInfo;

/**
 * A container for a SourceDataInfo object (needed to retrieve data) and instructions on how to interpret retrieved
 * data that enables a DataType object to consolidate that information into the units of data that are desired.
 * <p>
 * It is envisaged that CaseTypes will be ascertained automatically, based on retrieving String data and running contents
 * through type recognition logic.
 * <p>
 * A CaseType like InputExpectedActual represents a Type from which many instances with different parameters can exist.
 * For example TotalCases lines per input and lines per output can differ from query to query
 *
 * @see StringType
 */
public interface CaseType {

/**
 * The original reason for this application was the import of these files, represented here for ease of use though
 * this should be moved to SourceDataInfo as that is where this type of data originates and is stored.
 * todo move default to SourceDataInfo Interface.
 */
SDIArray DEFAULT_SDI_ARRAY =
      new SDIArray(
          new String[] {
            "/home/kali/Documents/001_CC/00fullIn.txt",
            "/home/kali/Documents/001_CC/00exp.txt",
            "/home/kali/Documents/001_CC/00act.txt"
          });

  StringType[] getStringTypeArray();

  String caseTypeDescription();

  int getLINES_PER_CASE_INPUT();

  int getLINES_PER_CASE_OUTPUT();

  long getTOTAL_CASES();

  SourceDataInfo getSourceDataInfo();

  boolean selfCheck();
}
