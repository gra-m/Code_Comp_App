package cchef.jtrain.self.code_comp.casetypes;

import cchef.jtrain.self.code_comp.casetypes.importinfotypes.SDIArray;
import cchef.jtrain.self.code_comp.casetypes.importinfotypes.SourceDataInfo;

/**
 * A container for a SourceDataInfo object and information on units of data (cases) such as how many lines/calls per
 * input and output and the total number of cases to be imported.
 */
public interface CaseType {

/**
 * The original reason for this application was the import of these files so they are represented here for ease of use.
 */
SDIArray DEFAULT_SDI_ARRAY =
      new SDIArray(
          new String[] {
            "/home/kali/Documents/001_CC/00fullIn.txt",
            "/home/kali/Documents/001_CC/00exp.txt",
            "/home/kali/Documents/001_CC/00act.txt"
          });

  StringType[] getStringTypeArray();

  String stringTypeDescription();

  int getLINES_PER_INPUT();

  int getLINES_PER_OUTPUT();

  long getTOTAL_CASES();

  SourceDataInfo getSourceDataInfo();

  boolean selfCheck();
}
