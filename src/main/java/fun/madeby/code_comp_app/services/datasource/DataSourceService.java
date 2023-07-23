package fun.madeby.code_comp_app.services.datasource;

import fun.madeby.code_comp_app.datatypes.DataTypeTemplate;
import fun.madeby.code_comp_app.datatypes.outputtypes.DTOutput;
import fun.madeby.code_comp_app.outputdata.DataSnapshot;

import java.time.ZonedDateTime;
import java.util.LinkedHashMap;

/** todo surely more like a service than a Driver? Yes, renamed
 * A DataSourceService can fetch data from a number of data-sources dependent on the CaseType of the DataType that is passed
 * to it. Each DataSourceService will interact with DataTypeTemplates to obtain the data for that DataType in its own specific
 * way. Given data availability each type of DataSourceService should be able to interact with any DataTypeTemplate.
 * /fixme this is just extra notes for now.
 * StringFromLocalFile  -> Local file source info == SDI |-> File String types -> CaseType  |-> Data processing -> Data Template |-> Reporting -> ReportService Type
 * IntegerFromLocalFile
 * JsonFromWebAPI       -> URLs and keys == SDI |-> File String types [after localising?]
 *
 * <p>
 * The end goal is the creation of a 'collection' of DataTypes from which the necessary reports can then be made.
 * Example:
 * CaseType CaseTypeInputExpectedActual is passed to StringFromLocalFile within a DTCodeChef DataType.
 * fetchFromDataSource() will gather the necessary input data based on the template provided by the CaseType and then
 * pass it to the DataType instance in order to create and return a DataType collection.
 * </p>
 */
public interface DataSourceService {
/**
 *
 * @param arrayDto
 * @return
 */
DTOutput getOnTheFlyData(boolean arrayDto);

/** WIP, 1st implementation of a ReportService requires what would have to be the datatypes default datasourceService
 * to return its DataTypeTemplate. Used in ReportService Constructor.
 *
 * @See public ConsoleReportServiceImpl(DataSourceService dataSourceService)
 *
 * @return final DataTypeTemplate if isTemplate == true
 */
DataTypeTemplate getDataTypeTemplate();

/**
 * Temporary shallow copy, update when deep
 * @return
 */
LinkedHashMap<ZonedDateTime, DataSnapshot> getCopyOfSnapshots();

void createNonAuditableSnapshot(boolean arrayDto);
      void createAuditableSnapshot(boolean arrayDto);
      // todo temp as void, will return string rather than console print calls on printSnapshots in DataTypeTemplate
      void printSnapshotInfo();



}
