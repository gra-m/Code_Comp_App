package cchef.jtrain.self.code_comp;


import java.util.function.IntPredicate;

import cchef.jtrain.self.code_comp.casetypes.StringType;

import static cchef.jtrain.self.code_comp.casetypes.StringType.*;

public final class StringTypeDefineAndCheck {
private static String[] arrayFromDefineStringTypeMethod;

private StringTypeDefineAndCheck() {}

  public static boolean isAsExpected(StringType expectedType, StringType actualType){

    return (expectedType == actualType);
  }

  public static StringType defineStringType(final String[] inputArray, final long failAmount) {

  long arrayLength = inputArray.length;
  if (inputArray.length == 0 || failAmount < 0)
    throw new IllegalStateException("Zero length array passed at defineStringType() \n OR fail amount was negative");

  arrayFromDefineStringTypeMethod = inputArray;

  long numericCount = arrayLength - failAmount;
  numericCount = numericCount - countLinesThatAre(NUMERIC);
  long numericSpCount = arrayLength - failAmount;
  numericSpCount = numericSpCount - countLinesThatAre(NUMERIC_SP);
  long numericOtherSepCount = arrayLength - failAmount;
  numericOtherSepCount = numericOtherSepCount - countLinesThatAre(NUMERIC_OSEP);
  long singleWordAZCount = arrayLength - failAmount;
  singleWordAZCount = singleWordAZCount - countLinesThatAre(SINGLE_WORD_A_Z);
  long otherWordCount = arrayLength - failAmount;
  otherWordCount = otherWordCount - countLinesThatAre(OTHER);

  if (numericCount <= 0)
    return NUMERIC;
  else if (numericSpCount <= 0)
    return NUMERIC_SP;
  else if (numericOtherSepCount <= 0)
    return NUMERIC_OSEP;
  else if (singleWordAZCount <= 0)
    return SINGLE_WORD_A_Z;
  else if (otherWordCount <= 0)
    return OTHER;

  return StringType.UNKNOWN;

  }

private static long countLinesThatAre(StringType stringType) {
  long count = 0;

  if (stringType == NUMERIC) {
    IntPredicate numeric = NUMERIC.apply();
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
    }else if (stringType == StringType.OTHER) {
    IntPredicate other = StringType.OTHER.apply();
    for (String str: arrayFromDefineStringTypeMethod ) {
      if (checkGeneric(str, other))
        count++;
    }

  }
  return count;
}

private static boolean checkGeneric(final String currentStr,  final IntPredicate intPredicate) {
  return currentStr.chars().allMatch(intPredicate);
}


}
