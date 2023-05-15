package cchef.jtrain.self.code_comp.datatypes.outputtypes;

import cchef.jtrain.self.code_comp.datatypes.DataTypeTemplate;
// todo move to maven and upgrade to 16+ swap classes for records where better.
/**
 * DTOutput is a container for more flexible return types for on-the-fly reporting linked to consolidate
 * data.
 * @see cchef.jtrain.self.code_comp.outputdata.DataSnapshot
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