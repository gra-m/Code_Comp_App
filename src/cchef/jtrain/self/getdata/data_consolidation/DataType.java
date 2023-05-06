package cchef.jtrain.self.getdata.data_consolidation;

import java.util.List;

public interface DataType {
      String contentDescription();
      void writeCaseData(String[] inputExpectedActual);
      boolean ascertainStatus();
      int getLinesPerInput();
      int getLinesPerOutput();
      DataType[] consolidateData(final String[] input, final String[] expected, final String[] actual);
      List<DataType> consolidateDataToList(final String[] input, final String[] expected, final String[] actual);

}
