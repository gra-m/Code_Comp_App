package fun.madeby.code_comp.datatypes;

import fun.madeby.code_comp.casetypes.importinfotypes.SourceDataInfo;
import fun.madeby.code_comp.datatypes.outputtypes.DTOutput;
import fun.madeby.code_comp.outputdata.DataSnapshot;

import java.time.ZonedDateTime;
import java.util.LinkedHashMap;

/**
 * Objects Derived from this interface are used by different data Given source data info
 * SourceDataInfo [Data addresses] a CaseType [Definition of source data] Data can be checked for
 * consistency against its CaseType by a DataTypeTemplate and then data may be consolidated as
 * desired into an array or list [DTOutput] of DataTypeTemplate objects in a way that is defined by
 * the business logic of any given DataTemplate.
 */
public interface DataTypeTemplate {

  int getLINES_PER_INPUT();

  int getLINES_PER_OUTPUT();

  long getTOTAL_CASES();

  boolean isTEMPLATE();

/**
 * Temporary shallow copy => replace with deep to enable auditable reports.
 * @return
 */
LinkedHashMap<ZonedDateTime, DataSnapshot> getShallowCopyOfSnapshots();

/**
   * @return
   */
  SourceDataInfo getSourceDataInfo();

  DTOutput consolidateDataToArray(final String[][] inputArray, final boolean asArray);

  void addNonAuditableSnapshot(
      final String[][] inputArray, String userTimeZone, final boolean asArray);
  // poss temp
  void printSnapshots();
}
