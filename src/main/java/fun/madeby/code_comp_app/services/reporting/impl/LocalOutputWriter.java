package fun.madeby.code_comp_app.services.reporting.impl;

import fun.madeby.code_comp_app.services.reporting.exception.ReportingServiceException;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
/**
 * A class providing a FastWriter BufferedWriter that either writes locally to console or to a provided String
 * OUTPUT_PATH.
 */
public class LocalOutputWriter {
FastWriter out;

/**
 * Used by TextFileReportService
 * @param OUTPUT_PATH
 */
LocalOutputWriter(final String OUTPUT_PATH) {
    out = new FastWriter(OUTPUT_PATH);
}

LocalOutputWriter() {
    out = new FastWriter();
}

void close() {
    try {
    out.close();
    } catch( IOException e ) {
        throw new ReportingServiceException("Error closing LocalOutputWriter", e);
    }
}

static class FastWriter {
    private final BufferedWriter BW;

    public FastWriter(final String OUTPUT_PATH) {
        BufferedWriter bw1;

            try {
                bw1 = new BufferedWriter(new FileWriter(OUTPUT_PATH));

            }
            catch( IOException e ) {
                bw1 = new BufferedWriter(new OutputStreamWriter(System.out));
                throw new ReportingServiceException("FileWriter(OUTPUT_PATH) creation failed console " +
                "OutputStreamWriter created instead", e);
            }
        this.BW = bw1;

    }

    public FastWriter() {
        this.BW = new BufferedWriter(new OutputStreamWriter(System.out));
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
