package cchef.jtrain.starters86.wups;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.IntStream;
import java.util.stream.Stream;

class Codechef_powerM {
  static final String INPUT_PATH = "/home/kali/Documents/001_CC/in.txt";
  static final String OUTPUT_PATH = "/home/kali/Documents/001_CC/out.txt";
  static final FastWriter OUT = new FastWriter();
  static final FastScanner IN = new FastScanner();
  static final boolean FILE_WRITE = false;
  // PMD7 Power M divisibility by 7
  public static void main(String[] args) throws Exception {

    int t = IN.nextInt();
    int caseLength = 2;

    while (t-- > 0) {
      String[] arr = IN.nextLine_A(caseLength);
      String x = arr[0];
      int z = Integer.parseInt(arr[1]);
      int m = z == 0 ? 1: z;

      //multiply each digit of x to the power of m % 10 to create a new number
      //reverse that new number and remove any leading zeros
      //if resulting number is divisible by 7 print yes otherwise print no

      OUT.println(x);
      OUT.println(m);

        }

    IN.close();
    OUT.close();
  }

  /*===========================================================================*/

  private static Integer[] intArrToIntegerArr(int[] st01) throws NumberFormatException {

    IntStream st02 = Arrays.stream(st01);
    Stream<Integer> st03 = st02.boxed();

    return st03.toArray(Integer[]::new);
  }

  private static String cleanString(String raw) {
    return raw.replaceAll("[^\\d\\s]", "").trim();
  }

  static class FastScanner {
    private final BufferedReader BR;
    private StringTokenizer st;

    public FastScanner() {
      BufferedReader br1;
      if (System.getProperty("ONLINE_JUDGE") == null) {
        try {
          br1 = new BufferedReader(new FileReader(INPUT_PATH));
        } catch (FileNotFoundException e) {
          br1 = new BufferedReader(new InputStreamReader(System.in));
        }
      } else {
        br1 = new BufferedReader(new InputStreamReader(System.in));
      }
      this.BR = br1;
    }

    String next() {
      while (st == null || !st.hasMoreElements()) {
        try {
          st = new StringTokenizer(BR.readLine());
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
      return st.nextToken();
    }

    int nextInt() {
      return Integer.parseInt(next());
    }

    long nextLong() {
      return Long.parseLong(next());
    }

    double nextDouble() {
      return Double.parseDouble(next());
    }

    List<Integer> readIntList(int n) {
      List<Integer> arr = new ArrayList<>();
      for (int i = 0; i < n; i++) arr.add(IN.nextInt());
      return arr;
    }

    List<Long> readLongList(int n) {
      List<Long> arr = new ArrayList<>();
      for (int i = 0; i < n; i++) arr.add(IN.nextLong());
      return arr;
    }

    int[] readIntArr(int n) {
      int[] arr = new int[n];
      for (int i = 0; i < n; i++) arr[i] = IN.nextInt();
      return arr;
    }

    Integer[] readIntegerArray(int n) {
      int[] arr = new int[n];
      for (int i = 0; i < n; i++) arr[i] = IN.nextInt();
      return intArrToIntegerArr(arr);
    }

    long[] readLongArr(int n) {
      long[] arr = new long[n];
      for (int i = 0; i < n; i++) arr[i] = IN.nextLong();
      return arr;
    }

    String nextLine() {
      String str = "";
      try {
        str = BR.readLine();
      } catch (IOException e) {
        e.printStackTrace();
      }
      return str;
    }

    String cleanDigitsSpaces() {
      String str = "";
      try {
        str = cleanString(BR.readLine().trim());
      } catch (IOException e) {
        e.printStackTrace();
      }
      return str;
    }

    String[] nextLine_A(int n) {
      String[] arr = new String[n];
      try {
        return BR.readLine().trim().split("\\s+");
      } catch (IOException e) {
        e.printStackTrace();
      }
      return arr;
    }

    void close() {
      try {
        BR.close();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }

  static class FastWriter {
    private final BufferedWriter BW;

    public FastWriter() {
      BufferedWriter bw1;
      if (System.getProperty("ONLINE_JUDGE") == null && FILE_WRITE) {
        try {
          bw1 = new BufferedWriter(new FileWriter(OUTPUT_PATH));
        } catch (IOException e) {
          bw1 = new BufferedWriter(new OutputStreamWriter(System.out));
        }
      } else {
        bw1 = new BufferedWriter(new OutputStreamWriter(System.out));
      }
      this.BW = bw1;
    }

    public void print(Object object) throws IOException {
      BW.append(String.valueOf(object));
    }

    public void println(Object object) throws IOException {
      print(object);
      BW.append("\n");
    }

    public void close() throws IOException {
      BW.close();
    }
  }
}
