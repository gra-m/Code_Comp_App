package cchef.jtrain.self.getdata;

import static cchef.jtrain.self.getdata.GetData.OUT;

import java.io.IOException;

public final class Utilz {

  private Utilz() {}

  public static int checkStrings(String[] strArray) {
    return 1;
  }
  

/**
   * Gets case length and confirms file validity in that the quoted cases are divisible into total
   * data lines in file (array length - 1).
   */
  public static int getCaseLength(final String[] arrayOfFullInput) {
    if (arrayOfFullInput.length == 0)
      throw new RuntimeException("String[] is empty @ getCaseLength");

    int cases = Integer.parseInt(arrayOfFullInput[0]);
    int arrayLength = arrayOfFullInput.length;

    try {
      if ((arrayLength - 1) % cases == 0) {
        return (arrayLength - 1) / cases;
      } else
        throw new IllegalStateException("Array length -1 not divisible by cases as whole number");
    } catch (IllegalStateException e) {
      System.out.println("----------------\n" + e.getMessage() + "\n\n");
      e.printStackTrace();
      throw new RuntimeException("Thrown purposely from getCaseLength()");
    }
  }

  /**
   * Create input files for testing
   *
   * @param cases the number of cases you require
   * @param caseLength the length of each case (lines in output file)
   * @param countAtStart whether the number of cases is to be quoted at the start of the output file
   * @throws IOException
   */
  public static void createTestInput(int cases, int caseLength, boolean countAtStart)
      throws IOException {
    int count = cases;
    if (countAtStart) OUT.println(cases);
    while (cases-- > 0) {

      int caseNum = count - cases;
      for (int i = 1; i <= caseLength; i++) OUT.println(caseNum + " " + i);
    }
  }
}
