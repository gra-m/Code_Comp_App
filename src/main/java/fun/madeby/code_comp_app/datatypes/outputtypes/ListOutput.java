package fun.madeby.code_comp_app.datatypes.outputtypes;


import fun.madeby.code_comp_app.datatypes.DataTypeTemplate;

import java.util.List;

public class ListOutput implements DTOutput {
      private List<DataTypeTemplate> value;

      public ListOutput(List<DataTypeTemplate> value){
            this.value = value;
      }

      public List<DataTypeTemplate> getDataTypeList(){return value;};
}
