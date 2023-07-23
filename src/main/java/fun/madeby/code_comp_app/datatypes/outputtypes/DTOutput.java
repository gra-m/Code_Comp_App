package fun.madeby.code_comp_app.datatypes.outputtypes;

import fun.madeby.code_comp_app.datatypes.DataTypeTemplate;
import fun.madeby.code_comp_app.outputdata.DataSnapshot;
// todo move to maven and upgrade to 16+ swap classes for records where better.
/**
 * DTOutput is a container for more flexible return types for on-the-fly reporting linked to consolidated
 * data.
 * @see DataSnapshot
 * DTOutput is designed for immediate use compared DataSnapshot (s) which are intended to have separate funcionality
 * linked to reporting, report auditing and, eventually storage.
 *
 * note to self, this separation seems OTT at the moment, but I think it allows for more flexibility going
 * forward.
 */
public interface DTOutput {

      default DataTypeTemplate[] getArray(DTOutput dtOutput) {
            ArrayOutput arrayOutput;
            if(dtOutput instanceof ArrayOutput) {
                   arrayOutput = (ArrayOutput ) dtOutput;
            return arrayOutput.getDataTypeArray();
            } else throw new RuntimeException("DTOutput default getArray had wrong type passed to it");

      }
}
