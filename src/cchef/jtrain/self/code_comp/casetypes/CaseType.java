package cchef.jtrain.self.code_comp.casetypes;

import cchef.jtrain.self.code_comp.casetypes.importinfotypes.SDIArray;
import cchef.jtrain.self.code_comp.casetypes.importinfotypes.SourceDataInfo;

public interface CaseType {
       SDIArray DEFAULT_SDI_ARRAY = new SDIArray(new String[] {"/home/kali/Documents/001_CC/00fullIn.txt",
          "/home/kali/Documents/001_CC/00exp.txt",
          "/home/kali/Documents/001_CC/00act.txt"});
      StringType[] getStringTypeArray();
      String stringTypeDescription();
      int getLINES_PER_INPUT();
      int getLINES_PER_OUTPUT();
      long getTOTAL_CASES();
      SourceDataInfo getSourceDataInfo();
      boolean selfCheck();
}
