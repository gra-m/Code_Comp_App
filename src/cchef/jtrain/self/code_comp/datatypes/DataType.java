package cchef.jtrain.self.code_comp.datatypes;

import cchef.jtrain.self.code_comp.casetypes.importinfotypes.SourceDataInfo;

import java.util.List;

public interface DataType {
      String contentDescription();
      int getLINES_PER_INPUT();
      int getLINES_PER_OUTPUT();
      long getTOTAL_CASES();
      int getInputFileHeaderSize();
      SourceDataInfo getSourceDataInfo();
      DataType[] consolidateData(final String[] input, final String[] expected, final String[] actual);
      DataType[] consolidateData(final String[][] inputArray);
      List<DataType> consolidateDataToList(final String[] input, final String[] expected, final String[] actual);

}
