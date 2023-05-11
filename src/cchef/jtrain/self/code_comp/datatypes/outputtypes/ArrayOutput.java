package cchef.jtrain.self.code_comp.datatypes.outputtypes;


import cchef.jtrain.self.code_comp.datatypes.DataTypeTemplate;

public class ArrayOutput implements DTOutput {
      private DataTypeTemplate[] value;
      
      public ArrayOutput(DataTypeTemplate[] value){
            this.value = value;
      }

      public DataTypeTemplate[] getDataTypeArray(){return value;};

}
