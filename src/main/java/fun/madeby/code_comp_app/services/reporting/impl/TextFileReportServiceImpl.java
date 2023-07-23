package fun.madeby.code_comp_app.services.reporting.impl;

import fun.madeby.code_comp_app.datatypes.DataTypeTemplate;
import fun.madeby.code_comp_app.datatypes.outputtypes.ArrayOutput;
import fun.madeby.code_comp_app.datatypes.outputtypes.DTOutput;
import fun.madeby.code_comp_app.services.datasource.DataSourceService;
import fun.madeby.code_comp_app.services.datasource.LocalFilesService;
import fun.madeby.code_comp_app.services.reporting.ReportService;
import fun.madeby.code_comp_app.services.reporting.exception.ReportingServiceException;

import java.io.BufferedWriter;
import java.io.IOException;
public class TextFileReportServiceImpl implements ReportService {
static final String OUTPUT_PATH = "/home/kali/Documents/001_CC/000App/TEST01.txt";
static final FileWriter OUT;

static {
    try {
        OUT = new FileWriter();
    }
    catch( IOException e ) {
      throw new ReportingServiceException("FileWriter creation failed", e);
    }
}



/**
 * <p>
 * The default report for any ReportService Type is every ReportableCase_Field as the column heading followed by the output for
 * the firstTen cases, if firstTen is false the results for all cases will be returned.
 * </p>
 *
 * @param firstTen true = only first ten cases
 */
@Override
public void createDefaultServiceImplReport(boolean firstTen, DataTypeTemplate ccDataType) {
    DataSourceService strFromLocal = new LocalFilesService(ccDataType);
    DTOutput populated = strFromLocal.getOnTheFlyData(true);
    ArrayOutput listPopulated;
    if ( populated instanceof ArrayOutput ) {
        listPopulated = (ArrayOutput) populated;

        DataTypeTemplate[] arrayList = listPopulated.getDataTypeArray();


        try {
            for ( DataTypeTemplate dt : arrayList) OUT.println(dt.toString());
        }
        catch( IOException e ) {
            throw new ReportingServiceException("Write to file failed", e);
        }
    } else System.out.println("it failed");

}

/**
 * A client passes a dataTypeTemplate to a ReportService, that report service then passes itself to the
 * dataTypeTemplate and creates the correct contextual reports for that ReportService.
 *
 * @param dataTypeTemplate
 */
@Override
public void createDataTypesDefaultReport(DataTypeTemplate dataTypeTemplate) {
    dataTypeTemplate.createDataTypesDefaultReport(this);

}


static class FileWriter {
    private final BufferedWriter BW = new BufferedWriter(new java.io.FileWriter(OUTPUT_PATH));

    public FileWriter() throws IOException {
    }

    public void print(Object object) throws IOException {
        BW.append(String.valueOf(object));
    }

    public void println(Object object) throws IOException {
        print(object);
        BW.append("\n");
    }

    public void close() throws IOException {
        BW.close();
    }
}

}
