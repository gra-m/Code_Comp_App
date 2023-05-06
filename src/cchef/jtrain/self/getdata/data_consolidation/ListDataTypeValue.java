package cchef.jtrain.self.getdata.data_consolidation;


import java.util.List;

public class ListDataTypeValue implements DynamicDataTypeValue {
      private List<DataType> value;

      public ListDataTypeValue(List<DataType> value){
            this.value = value;
      }

}
