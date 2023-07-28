package fun.madeby.code_comp_app;


import java.util.Arrays;
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
 * NOTE this is a work in progress, in reality it could be more effectively replaced with an existing api
 * But it is a fun experiment in creating an ENUM with an apply method. A kind of self defining
 * Enum that I like the idea of, originates from an Effective Java example.
 *
 * note '900' is giving a false positive in numeric_OSEP but NumericSp is working acceptably, and will
 * not be changed until this is moved into a testing environment. Although numeric files are caught by four options,
 * they are acceptably defined at present because of ordering.
 *
 * Passed the input String[]'s for a DataTypeTemplate and the acceptable fail amount (Strings that are known/expected
 * to be anomalous) == wiggle room this method attempts to identify the string type of the String array vs those defined
 * in the StringType Enum. In reality though, an experiment with Enums and int predicates.
 *
 * @param inputArray prospective input array
 * @param allowedMismatches   acceptable number of non-matching strings
 * @return Actively defined StringType or UNKNOWN
 */
public static StringType defineStringType(final String[] inputArray, final long allowedMismatches) {

  long arrayLength = inputArray.length;
  if (inputArray.length == 0 || allowedMismatches < 0)
    throw new IllegalStateException("Zero length array passed at defineStringType() \n OR fail amount was negative");

  arrayFromDefineStringTypeMethod = inputArray;

    System.out.println("**NOTE**DefineStringType(String[], allowedMismatches) is responsible for this console mess: " +
    Arrays.deepToString(arrayFromDefineStringTypeMethod));

  long numericOnlyMatches = countLinesThatAre(StringType.NUMERIC);
  long numericOrSpaceMatches = countLinesThatAre(StringType.NUMERIC_SP);
  long numericOtherSepMatches = countLinesThatAre(StringType.NUMERIC_OSEP);
  long singleWordAZMatches = countLinesThatAre(StringType.SINGLE_WORD_A_Z);
  long otherWordMatches = countLinesThatAre(StringType.OTHER);

  if (numericOnlyMatches >= arrayLength - allowedMismatches)
    return StringType.NUMERIC;
  else if (numericOrSpaceMatches >= arrayLength - allowedMismatches)
    return StringType.NUMERIC_SP;
  else if (numericOtherSepMatches >= arrayLength - allowedMismatches)
    return StringType.NUMERIC_OSEP;
  else if (singleWordAZMatches >= arrayLength - allowedMismatches)
    return StringType.SINGLE_WORD_A_Z;
  else if (otherWordMatches >= arrayLength - allowedMismatches)
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
  currentStr.trim();

  /*boolean match = currentStr.chars().allMatch(intPredicate);
    if (match) return match;
    else {System.out.println(currentStr);
      return false;}*/
  return currentStr.chars().allMatch(intPredicate);
}

private static boolean checkGenTEST(final String currentStr, final IntPredicate intPredicate) {
   boolean match = currentStr.chars().allMatch(intPredicate);
    if (match) {
      System.out.println("**NOTE**checkGenTEST(String, intPredicate) is responsible for showing this anomalous match " +
      "against numericOtherSep\n" + currentStr);
      return true;}
    else
      return false;}



}
