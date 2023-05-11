package cchef.jtrain.self.code_comp.datatypes.outputtypes;


import cchef.jtrain.self.code_comp.datatypes.DataTypeTemplate;

import java.util.List;

public class ListOutput implements DTOutput {
      private List<DataTypeTemplate> value;

      public ListOutput(List<DataTypeTemplate> value){
            this.value = value;
      }

      public List<DataTypeTemplate> getDataTypeList(){return value;};
}
