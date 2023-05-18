package fun.madeby.code_comp_app.datatypes.outputtypes;


import fun.madeby.code_comp_app.datatypes.DataTypeTemplate;

public class ArrayOutput implements DTOutput {
      private DataTypeTemplate[] value;
      
      public ArrayOutput(DataTypeTemplate[] value){
            this.value = value;
      }

      public DataTypeTemplate[] getDataTypeArray(){return value;};

}
