package cchef.jtrain.self.getdata;


import java.util.function.IntPredicate;

public final class StringsInArray {
private static String[] arrayFromDefineStringTypeMethod;

private StringsInArray() {}

public enum StringType {
  // Based on ASCII Windows-1252
  NUMERIC {protected IntPredicate apply(){return arg -> (arg > 47 && arg < 58); }}, // [\d]
  NUMERIC_SP {protected IntPredicate apply(){return arg ->(arg > 47 && arg < 58 || arg == 32);}}, //[\d ]
  NUMERIC_OSEP {protected IntPredicate apply() {return arg -> (arg > 46 && arg < 58 || arg == 92 || arg == 124 || arg == 44);}},// [\d \\\,\/\|] {0-9\/,|}
  SINGLE_WORD_A_Z {protected IntPredicate apply() {return arg -> ((arg > 64 && arg < 91) || (arg > 96 && arg < 123));}}, //[a-zA-Z]
  OTHER {protected IntPredicate apply() {return arg -> ((arg > 0 && arg < 256));}},
  UNKNOWN{protected IntPredicate apply() {return arg -> false;}};

  //IS_NOT_ASCII{protected IntPredicate apply() {return arg -> (arg > 255);}}; // string.chars() checks for latin1 UTF16

  /*
  NUMERIC_DEC, // [\d .] 0-9 spaces and decimal points within numeric range
  MULTI_WORD_SP, // spaces but no punctuation
  TEXT; // spaces and punctuation
  */

  StringType() {}

  protected abstract IntPredicate apply();
}

  public static boolean isAsExpected(StringType expectedType, StringType actualType){

    return (expectedType == actualType);
  }

  public static StringType defineStringType(final String[] inputArray, final long failAmount) {

  long arrayLength = inputArray.length;
  if (inputArray.length == 0 || failAmount < 0)
    throw new IllegalStateException("Zero length array passed at defineStringType() \n OR fail amount was negative");

  arrayFromDefineStringTypeMethod = inputArray;

  long numericCount = arrayLength - failAmount;
  numericCount = numericCount - countLinesThatAre(StringType.NUMERIC);
  long numericSpCount = arrayLength - failAmount;
  numericSpCount = numericSpCount - countLinesThatAre(StringType.NUMERIC_SP);
  long numericOtherSepCount = arrayLength - failAmount;
  numericOtherSepCount = numericOtherSepCount - countLinesThatAre(StringType.NUMERIC_OSEP);
  long singleWordAZCount = arrayLength - failAmount;
  singleWordAZCount = singleWordAZCount - countLinesThatAre(StringType.SINGLE_WORD_A_Z);
  long otherWordCount = arrayLength - failAmount;
  otherWordCount = otherWordCount - countLinesThatAre(StringType.OTHER);

  if (numericCount <= 0)
    return StringType.NUMERIC;
  else if (numericSpCount <= 0)
    return StringType.NUMERIC_SP;
  else if (numericOtherSepCount <= 0)
    return StringType.NUMERIC_OSEP;
  else if (singleWordAZCount <= 0)
    return StringType.SINGLE_WORD_A_Z;
  else if (otherWordCount <= 0)
    return StringType.OTHER;

  return StringType.UNKNOWN;

  }

private static long countLinesThatAre(StringType stringType) {
  long count = 0;

  if (stringType == StringType.NUMERIC) {
    IntPredicate numeric = StringType.NUMERIC.apply();
    for (String str: arrayFromDefineStringTypeMethod ) {
      if ( checkGeneric(str, numeric))
        count++;
    }
  }
  else if (stringType == StringType.NUMERIC_SP) {
    IntPredicate numericSp = StringType.NUMERIC_SP.apply();
    for (String str: arrayFromDefineStringTypeMethod ) {
      if ( checkGeneric(str, numericSp))
        count++;
    }
   } else if (stringType == StringType.NUMERIC_OSEP) {
    IntPredicate numericOsep = StringType.NUMERIC_OSEP.apply();
    for (String str: arrayFromDefineStringTypeMethod ) {
      if (checkGeneric(str, numericOsep))
        count++;
      }
    } else if (stringType == StringType.SINGLE_WORD_A_Z) {
    IntPredicate singWordAz = StringType.SINGLE_WORD_A_Z.apply();
      for (String str: arrayFromDefineStringTypeMethod ) {
        if (checkGeneric(str, singWordAz))
          count++;
      }

    }

  return count;
}
private static boolean checkGeneric(final String currentStr,  final IntPredicate intPredicate) {
  return currentStr.chars().allMatch(intPredicate);
}
/*
private static boolean checkNumericOnly(final String currentStr) {
  IntPredicate  isNumeric =  arg -> (arg > 47 && arg < 58);
  return currentStr.chars().allMatch(isNumeric);
}

private static boolean checkNumericOrSpace(final String currentStr) {
  IntPredicate  isNumericOrSpace =  arg -> (arg > 47 && arg < 58 || arg == 32);
  return currentStr.chars().allMatch(isNumericOrSpace);
}

private static boolean checkNumericOtherSeparator(final String currentStr) {
    IntPredicate isNumericOrOSep = arg -> (arg > 46 && arg < 58 || arg == 92 || arg == 124 || arg == 44);
  return  currentStr.chars().allMatch(isNumericOrOSep);
}

private static boolean checkSingleWordA_Z(String currentStr) {
  IntPredicate isA_Z = arg -> ((arg > 64 && arg < 91) || (arg > 96 && arg < 123));
  return currentStr
      .chars()
      .allMatch(isA_Z);
}*/


}
