package cchef.jtrain.self.code_comp.datatypes;


import cchef.jtrain.self.code_comp.inputconsolidation.DataType;

import java.util.List;

public class ListDataTypeOutput implements DataTypeOutput {
      private List<DataType> value;

      public ListDataTypeOutput(List<DataType> value){
            this.value = value;
      }

}
