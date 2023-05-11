package cchef.jtrain.self.code_comp.datatypes;

import cchef.jtrain.self.code_comp.casetypes.importinfotypes.SourceDataInfo;
import cchef.jtrain.self.code_comp.datatypes.outputtypes.DTOutput;

import java.util.List;

public interface DataTypeTemplate {

  int getLINES_PER_INPUT();

  int getLINES_PER_OUTPUT();

  long getTOTAL_CASES();

  boolean isTEMPLATE();

  SourceDataInfo getSourceDataInfo();

  DataTypeTemplate[] consolidateDataToArray(final String[] input, final String[] expected, final String[] actual);

  List<DataTypeTemplate> consolidateDataToList(
      final String[] input, final String[] expected, final String[] actual);

  DTOutput consolidateDataToArray(final String[][] inputArray, boolean arrayOutput);

  // creates DataSnapshot[I] object timestamp/DTOutput/DataTypeTemplate [Template only]
  void createSnapshot_N();

  // creates DataSnapshot[I] obj timestamp/DTOutput/DataTypeTemplate [Template + original data]
  void createSnapshot_A();

}
