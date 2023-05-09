package cchef.jtrain.self.code_comp.datatypes;


import cchef.jtrain.self.code_comp.inputconsolidation.DataType;

public class ArrayDataTypeOutput implements DataTypeOutput {
      private DataType[] value;
      
      public ArrayDataTypeOutput(DataType[] value){
            this.value = value;
      }

}
