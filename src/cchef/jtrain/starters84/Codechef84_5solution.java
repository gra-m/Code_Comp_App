package cchef.jtrain.starters84;

/*
Todo XOR exclusive or
10 = 1
01 = 1
00 = 0
11 = 0

Xi +1 = Xi ^ Si

Build two strings, one wher Xi is 0 another where Xi is 1, follow the
construction of the rest of the strings using the formula above.

Which of your constructed strings [using this formula] contains the most 1's.

Used to show variety of bits

Todo XNOR Not exclusive or
10 = 0
01 = 0
00 = 1
11 = 1



Suppose you fixed the value of X1.
Can you then find X2, X3 and so on?
Does this help solve the problem?

Once X1 is fixed, every other character of the string is fixed too based on
the statement, since

    X2=S1⊕X1
    X3=S2⊕X2
and so on.

So, fixing X1 fixes the rest of the string.

Since X is a binary string, there are only two choice for X1
Try them both, construct the respective strings,
and see which one has more ones
* */

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.IntStream;
import java.util.stream.Stream;


class Codechef84_5solution {
static final String INPUT_PATH = "/home/kali/Documents/001_CC/in.txt";
static final String OUTPUT_PATH = "/home/kali/Documents/001_CC/out.txt";
static final FastWriter OUT = new FastWriter();
static final FastScanner IN = new FastScanner();
static final boolean FILE_WRITE = false;

public static void main(String[] args) throws Exception {

      int t = IN.nextInt();

      while( t-- > 0 ) {
            int binLength = IN.nextInt();
            StringBuilder originalBin =
                new StringBuilder(IN.cleanDigitsSpaces());
            StringBuilder zeroStart = new StringBuilder("0");
            StringBuilder oneStart = new StringBuilder("1");


            completeXOROperation(zeroStart, originalBin, binLength);
            completeXOROperation(oneStart, originalBin, binLength);
            OUT.println(Math.max(countOnes(zeroStart), countOnes(oneStart)));
      }

      IN.close();
      OUT.close();
}

private static long countOnes(StringBuilder sbInput) {
      return sbInput.chars().filter(ch -> ch == '1').count();
}

private static void completeXOROperation(StringBuilder zeroStart,
                                         StringBuilder originalBinary,
                                         int binLength) {
      for( int i = 0; i < binLength - 1; i++ )
            zeroStart.append(zeroStart.charAt(i) == originalBinary.charAt(i)
                ? "0" : "1");
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
            if( System.getProperty("ONLINE_JUDGE") == null ) {
                  try {
                        br1 = new BufferedReader(new FileReader(INPUT_PATH));
                  }
                  catch( FileNotFoundException e ) {
                        br1 =
                            new BufferedReader(new InputStreamReader(System.in));
                  }
            }
            else {
                  br1 = new BufferedReader(new InputStreamReader(System.in));
            }
            this.BR = br1;
      }

      String next() {
            while( st == null || !st.hasMoreElements() ) {
                  try {
                        st = new StringTokenizer(BR.readLine());
                  }
                  catch( IOException e ) {
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
            for( int i = 0; i < n; i++ ) arr.add(IN.nextInt());
            return arr;
      }

      List<Long> readLongList(int n) {
            List<Long> arr = new ArrayList<>();
            for( int i = 0; i < n; i++ ) arr.add(IN.nextLong());
            return arr;
      }

      int[] readIntArr(int n) {
            int[] arr = new int[n];
            for( int i = 0; i < n; i++ ) arr[i] = IN.nextInt();
            return arr;
      }

      Integer[] readIntegerArray(int n) {
            int[] arr = new int[n];
            for( int i = 0; i < n; i++ ) arr[i] = IN.nextInt();
            return intArrToIntegerArr(arr);
      }

      long[] readLongArr(int n) {
            long[] arr = new long[n];
            for( int i = 0; i < n; i++ ) arr[i] = IN.nextLong();
            return arr;
      }

      String nextLine() {
            String str = "";
            try {
                  str = BR.readLine();
            }
            catch( IOException e ) {
                  e.printStackTrace();
            }
            return str;
      }

      String cleanDigitsSpaces() {
            String str = "";
            try {
                  str = cleanString(BR.readLine().trim());
            }
            catch( IOException e ) {
                  e.printStackTrace();
            }
            return str;
      }

      String[] nextLine_A(int n) {
            String[] arr = new String[n];
            try {
                  return BR.readLine().trim().split("\\s+");
            }
            catch( IOException e ) {
                  e.printStackTrace();
            }
            return arr;
      }


      void close() {
            try {
                  BR.close();
            }
            catch( IOException e ) {
                  e.printStackTrace();
            }
      }
}


static class FastWriter {
      private final BufferedWriter BW;

      public FastWriter() {
            BufferedWriter bw1;
            if( System.getProperty("ONLINE_JUDGE") == null && FILE_WRITE ) {
                  try {
                        bw1 = new BufferedWriter(new FileWriter(OUTPUT_PATH));
                  }
                  catch( IOException e ) {
                        bw1 =
                            new BufferedWriter(new OutputStreamWriter(System.out));
                  }
            }
            else {
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