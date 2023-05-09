package cchef.jtrain.self.code_comp.inputconsolidation;

import java.util.List;

public interface DataType {
      String contentDescription();
      int getLinesPerInput();
      int getLinesPerOutput();
      DataType[] consolidateData(final String[] input, final String[] expected, final String[] actual);
      List<DataType> consolidateDataToList(final String[] input, final String[] expected, final String[] actual);

}
