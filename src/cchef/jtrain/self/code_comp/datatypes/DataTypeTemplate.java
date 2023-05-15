package cchef.jtrain.self.code_comp.datatypes;

import cchef.jtrain.self.code_comp.casetypes.importinfotypes.SourceDataInfo;
import cchef.jtrain.self.code_comp.datatypes.outputtypes.DTOutput;

import java.util.List;

/**
 * Objects Derived from this interface are used by different data
 * Given source data info SourceDataInfo [Data addresses] a CaseType [Definition of source data] Data can be checked for
 * consistency against its CaseType by a DataTypeTemplate and then data may be consolidated as desired into an array
 * or list [DTOutput] of DataTypeTemplate objects in a way that is defined by the business logic of any given DataTemplate.
 */

public interface DataTypeTemplate {

  int getLINES_PER_INPUT();

  int getLINES_PER_OUTPUT();

  long getTOTAL_CASES();

  boolean isTEMPLATE();

/**
 *
  * @return
 */
SourceDataInfo getSourceDataInfo();

  DataTypeTemplate[] consolidateDataToArray(
      final String[] input, final String[] expected, final String[] actual);

  List<DataTypeTemplate> consolidateDataToList(
      final String[] input, final String[] expected, final String[] actual);

  DTOutput consolidateDataToArray(final String[][] inputArray, final boolean asArray);

  void addNonAuditableSnapshot(final String[][] inputArray, String userTimeZone, final boolean asArray);
// poss temp
void printSnapshots();
}