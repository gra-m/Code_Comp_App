package cchef.jtrain.self.code_comp.datasource;

import cchef.jtrain.self.code_comp.datatypes.outputtypes.DTOutput;

/**
 * A DataSourceDriver can fetch data from a number of data-sources dependent on the CaseType of the DataType that is passed
 * to it. Each DataSourceDriver will interact with DataTypeTemplates to obtain the data for that DataType in its own specific
 * way. Given data availability each type of DataSourceDriver should be able to interact with any DataTypeTemplate.
 * /fixme this is just extra notes for now.
 * StringFromLocalFile  -> Local file source info == SDI |-> File String types -> CaseType  |-> Data processing -> Data Template |-> Reporting -> Report Type
 * IntegerFromLocalFile
 * JsonFromWebAPI       -> URLs and keys == SDI |-> File String types [after localising?]
 *
 * <p>
 * The end goal is the creation of a 'collection' of DataTypes from which the necessary reports can then be made.
 * Example:
 * CaseType CT_InputExpectedActual is passed to StringFromLocalFile within a DTCodeChef DataType.
 * fetchFromDataSource() will gather the necessary input data based on the template provided by the CaseType and then
 * pass it to the DataType instance in order to create and return a DataType collection.
 * </p>
 */
public interface DataSourceDriver {
      DTOutput getOnTheFlyData(boolean arrayDto);
      void createNonAuditableSnapshot(boolean arrayDto);
      void createAuditableSnapshot(boolean arrayDto);
      // todo temp as void, will return string rather than console print calls on printSnapshots in DataTypeTemplate
      void printSnapshotInfo();



}
