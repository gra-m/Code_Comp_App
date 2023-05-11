package cchef.jtrain.self.code_comp.datasource;

import cchef.jtrain.self.code_comp.datatypes.outputtypes.DTOutput;
import cchef.jtrain.self.code_comp.outputdata.DataSnapshot;

/**
 * A GetDataAs can fetch data from a number of data-sources dependent on the CaseType of the DataType that is passed
 * to it.
 *
 * <p>
 * The end goal is the creation of a 'collection' of DataTypes from which the necessary reports can then be made.
 * Example:
 * CaseType CT_InputExpectedActual is passed to StringFromLocalFile within a DTCodeChef DataType.
 * fetchFromDataSource() will gather the necessary input data based on the template provided by the CaseType and then
 * pass it to the DataType instance in order to create and return a DataType collection.
 * </p>
 */
public interface GetDataAs {
      DTOutput getOnTheFlyData(boolean arrayDto);
      DataSnapshot createDataSnapshot(boolean normal);



}
