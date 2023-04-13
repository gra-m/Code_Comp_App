package cchef.jtrain.timecomplexity;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.IntStream;
import java.util.stream.Stream;


class GiftShopCoupon {
static final String INPUT_PATH = "/home/kali/Documents/001_CC/in.txt";
static final String OUTPUT_PATH = "/home/kali/Documents/001_CC/out.txt";
static final FastWriter OUT = new FastWriter();
static final FastScanner IN = new FastScanner();
static final boolean FILE_WRITE = false;

public static void main(String[] args) throws Exception {

      int t = IN.nextInt();
      int caseLength = 2;
      int kase = 0;


      while( t-- > 0 ) {
            kase++;
            String[] arr = IN.nextLine_A(caseLength);
            int n = Integer.parseInt(arr[0]); // num of items
            int s = Integer.parseInt(arr[1]); // money
            int[] items = IN.readIntArr(n);
            int total = 0;

            Arrays.sort(items);

            // total with recursion:
            int sum = sumOfArray(items, items.length - 1);


            if( kase > 0 ) {
                  OUT.println(Arrays.toString(arr) + " total items " + n + " "
			+ " cash " + s);
                  OUT.println(Arrays.toString(items));
                  OUT.println(sum);

                  if( sum <= s ) OUT.println(items.length);
                  for( int i = 0; i < items.length; i++ ) {
                        total += items[i];

				if (total >= s) {
					double discount = Math.floor(( double ) items[i] / 2);
					OUT.println("discount is " + discount + " total is " + total + " i is " + i);

					if( (total - discount) <= s ) {
						OUT.println(i + 1);
						break;
					}
					else {
						OUT.println(i);
						break;
					}
				}
                  }
            }
      }

      IN.close();
      OUT.close();
}


/*===========================================================================*/

// Using recursion, just for the of it
private static int sumOfArray(int[] a, int n) {
      if( n == 0 ) return a[n];
      else {
            int result = a[n] + sumOfArray(a, n - 1);
            return result;
      }
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