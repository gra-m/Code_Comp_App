package cchef.jtrain.self.getdata.data_consolidation;

public interface CaseType {
      StringType[] getStringTypeArray();
      String stringTypeDescription();
      int getLinesPerInput();
      int getLinesPerOutput();
}
