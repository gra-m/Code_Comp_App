package cchef.jtrain.self.getdata;


import java.util.function.IntPredicate;

public final class StringsInArray {

private StringsInArray() {}

  public enum StringType {
    NON_DECIMAL,
    DECIMAL,
    MIX,
    DOUBLE,
    UNKNOWN
  }

  public static boolean isAsExpected(StringType expectedType, StringType actualType){

    return (expectedType == actualType);
  }

  public static StringType defineStringType(final String[] inputArray, final int failAmount) {
  int arrayLength = inputArray.length;
  if (arrayLength == 0 || failAmount < 0)
    throw new IllegalStateException("Zero length array passed at defineStringType() \n OR fail amount was negative");
  int decimalCount = arrayLength - failAmount;

  for (int i = 0; i < arrayLength; i++ ){
    String current = inputArray[i];
    if (checkDecimal(current))
      decimalCount--;
  }

  if (decimalCount <= 0)
    return StringType.DECIMAL;

  return StringType.UNKNOWN;

  }

private static boolean checkDecimal(String current) {
  IntPredicate  is0to9 =  arg -> (arg > 47 && arg < 58);
  return current
      .toLowerCase()
      .chars()
      .allMatch(is0to9);

}
}
