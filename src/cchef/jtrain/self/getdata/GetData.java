package cchef.jtrain.self.getdata;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.IntStream;
import java.util.stream.Stream;


class GetData {

  static final String _1FULL_IN = "/home/kali/Documents/001_CC/00fullIn.txt";
  static final String _2EXP_IN = "/home/kali/Documents/001_CC/00exp.txt";
  static final String _3ACT_IN = "/home/kali/Documents/001_CC/00act.txt";
  static final String OUTPUT_PATH = "/home/kali/Documents/001_CC/out.txt";
  static final FastWriter OUT = new FastWriter();
  static final boolean FILE_WRITE = false;
  static FastScanner IN = new FastScanner(1);
  static int fileLength =  (int) IN.countLines(); // lossy but array[long] not available
  static int casesFromArray = 0;
  static int linesPerCase = 0;


  public static void main(String[] args) throws Exception {

    String [] fullIn = IN.readStringArray(fileLength) ;
    casesFromArray = Integer.parseInt(fullIn[0]);
    linesPerCase = Utilz.getCaseLength(fullIn);

    OUT.println(casesFromArray);
    OUT.println(linesPerCase);
    IN.close(); // todo create safe/auto close
    // END of Input Data import and check.



    /*
    int count = case_t;
    IN = new FastScanner(2);
    String[] exp = IN.readStringArray(t); // todo read string array / to lower / compare
    IN.close();
    IN = new FastScanner(3);
    String[] act = IN.readStringArray(t);
    IN.close();

    OUT.println(t);
    OUT.println(Arrays.toString(fullIn));
    OUT.println(Arrays.toString(exp));
    OUT.println(Arrays.toString(act));
    OUT.println(t);
     */

    // todo define exp and fullin as eg: string / string and decimal / decimal only / suspected double ie int and . found







    /*while (case_t-- > 0) {

      int caseNum = count - case_t;
      OUT.println(caseNum + " " + 1);
      OUT.println(caseNum + " " + 2);

    }*/

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
    // activePath used by countLines
    private String activePath = "";
    public FastScanner(int pathNum) {
      BufferedReader br1;

      if (System.getProperty("ONLINE_JUDGE") == null) {
        try { //todo try with resources
          if (pathNum == 1) br1 = new BufferedReader(new FileReader(activePath = _1FULL_IN));
          else if (pathNum == 2) br1 = new BufferedReader(new FileReader(activePath = _2EXP_IN));
          else br1 = new BufferedReader(new FileReader(activePath = _3ACT_IN));
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

    long countLines() {
      if (activePath.isEmpty())
        activePath = _1FULL_IN;

      try (Stream<String> stream = Files.lines(Path.of(activePath), StandardCharsets.UTF_8)) {
        return stream.count();
      }
      catch( IOException e ) {
        throw new RuntimeException(e);
      }
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

    String[] readStringArray(int n) {
      String[] arr = new String[n];
      for (int i = 0; i < n; i++) arr[i] = IN.nextLine().trim();
      return arr;
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
          bw1.flush();
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
