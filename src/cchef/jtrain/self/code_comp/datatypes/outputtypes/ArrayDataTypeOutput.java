package cchef.jtrain.self.code_comp.datatypes.outputtypes;


import cchef.jtrain.self.code_comp.datatypes.DataType;

public class ArrayDataTypeOutput implements DataTypeOutput {
      private DataType[] value;
      
      public ArrayDataTypeOutput(DataType[] value){
            this.value = value;
      }

      public DataType[] getDataTypeArray(){return value;};

}
