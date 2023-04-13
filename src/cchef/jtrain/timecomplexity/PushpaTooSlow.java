package cchef.jtrain.timecomplexity;

import java.io.*;
import java.util.*;
import java.util.stream.IntStream;
import java.util.stream.Stream;


class PushpaTooSlow {
static final String INPUT_PATH = "/home/kali/Documents/001_CC/in.txt";
static final String OUTPUT_PATH = "/home/kali/Documents/001_CC/out.txt";
static final FastWriter OUT = new FastWriter();
static final FastScanner IN = new FastScanner();
static final boolean FILE_WRITE = false;

public static void main(String[] args) throws Exception {
      int t = IN.nextInt();


      while (t-- > 0) {
            int caseLength = IN.nextInt();
            Integer[] arr = IN.readIntegerArray(caseLength);
            Integer[] arr2 = arr;
            ArrayList<Integer> getMax = new ArrayList<>(Arrays.asList(arr));
            int maxOnFirst = Collections.max(getMax);
            int maxOnIncrement = 0;
            int currentVal = 0;
            int towerIndex;
            boolean onARoll;


                  for ( int x = 0; x < caseLength; x++) {
                        currentVal = arr[x];
                        if (currentVal > maxOnIncrement)
                              maxOnIncrement = currentVal;
                        towerIndex = x;
                        onARoll = true;

                        while (onARoll) {
                              onARoll = false;
                              arr2 = incrementAndSetIndex(arr2, towerIndex, currentVal);
                              for( int z = 0; z < caseLength; z++ ) {
                                    if( arr2[z] == currentVal + 1 ) {
                                          currentVal = arr2[z];
                                          towerIndex = z;
                                          onARoll = true;
                                          break;
                                    }
                              }
                        }

                        arr2 = arr;
                  }
            OUT.println(Math.max(currentVal, maxOnFirst));


      }


      IN.close();
      OUT.close();
}


/*===========================================================================*/

private static Integer[] incrementAndSetIndex(Integer[] arr2, int towerIndex, int currentVal) {
      int len = arr2.length;
      Integer[] result = new Integer[len];
      for (int a = 0; a < len; a++)
      {
            result[a] = arr2[a] + 1;
      }
      result[towerIndex] = currentVal;
      return result;
}


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
                        bw1 = new BufferedWriter(new OutputStreamWriter(System.out));
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