package fun.madeby.code_comp_app;


import java.util.function.IntPredicate;

import fun.madeby.code_comp_app.casetypes.StringType;

public final class StringTypeDefineAndCheck {
private static String[] arrayFromDefineStringTypeMethod;

/**
 * A Utility class used to define the 'Type' of the String[] passed to it within the supported parameters from the
 * StringType Enum. So, a DataTypeTemplate can have it's the String Types it expects for it's input String[]'s allotted,
 * and any prospective input data can be checked against this 'expected' type.
 */
private StringTypeDefineAndCheck() {}

  public static boolean isAsExpected(StringType expectedType, StringType actualType){

    return (expectedType == actualType);
  }


/**
 * Passed the input String[]'s for a DataTypeTemplate and the acceptable fail amount (Strings that are known/expected
 * to be anomalous) == wiggle room this method attempts to identify the string type of the String array vs those defined
 * in the StringType Enum. In reality though, an experiment with Enums and int predicates.
 * @param inputArray prospective input array
 * @param failAmount   acceptable number of non conforming strings
 * @return Actively defined StringType or UNKNOWN
 */
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
