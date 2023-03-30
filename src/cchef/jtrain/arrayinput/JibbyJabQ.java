package cchef.jtrain.arrayinput;
/*
N in queue, chef is in
Pth position from the front of the queue.
X == time to vacc a child 0
Y == time to vacc an elderly person [like Chef] 1
Receive binary array [a1, a2 etc] of N length where 1 denotes elderly person and 0 a child standing in iTH position in queue

T testcases
NPXY -line 1
N - line 2

Output -> number of minutes After which chef's jab will be completed.
 */

import java.io.*;
import java.util.StringTokenizer;

/* Name of the class has to be "Main" only if the class is public. 1-2*/
class JibbyJabQ {
    static FastScanner s = new FastScanner();
    static FastWriter out = new FastWriter();

  /*  static {
        try {
            s = new FastScanner();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }*/

    public static void main(String[] args) throws Exception {
        int test = s.nextInt();
        int[] npxy = new int[4];
        int[] bin;
        int qlen;

        for (int t = 1; t <= test; t++) {
            for (int u = 0; u < npxy.length; u++) {
                npxy[u] = s.nextInt();
            }
            qlen = npxy[0];
            bin = new int[qlen];
            for (int v = 0; v < qlen; v++) {
                bin[v] = s.nextInt();
            }
            out.println(solve(npxy, bin));
        }

        out.close();
    }

    public static int solve(int[] npxy, int[] bin) {
        int child = npxy[2];
        int adult = npxy[3];
        int chefP = npxy[1] - 1;
        int total = 0;
        for (int x = 0; x <= chefP; x++) {
            if (bin[x] == 0) total += child;
            else total += adult;
        }

        return total;
    }

    static class Pair implements Comparable<Pair> {
        int fp;
        int sp;

        public Pair(int fp, int sp) {
            this.fp = fp;
            this.sp = sp;
        }

        @Override
        public int compareTo(Pair o) {
            if (fp == o.fp && sp == o.sp || fp == o.sp && sp == o.fp) return 1;
            else return 0;
        }
    }

    /*===========================================================================*/

    static class FastScanner {
        BufferedReader br;
        StringTokenizer st;

        public FastScanner() {
            br = new BufferedReader(new InputStreamReader(System.in));
        }
        /*public FastScanner() throws FileNotFoundException {
            br = new BufferedReader(new FileReader("/home/kali/Documents/001_CC/input.txt"));
        }*/

        String next() {
            while (st == null || !st.hasMoreElements()) {
                try {
                    st = new StringTokenizer(br.readLine());
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
    }

    static class FastWriter {
        private final BufferedWriter bw;

        public FastWriter() {
            this.bw = new BufferedWriter(new OutputStreamWriter(System.out));
        }

        public void print(Object object) throws IOException {
            bw.append("" + object);
        }

        public void println(Object object) throws IOException {
            print(object);
            bw.append("\n");
        }

        public void close() throws IOException {
            bw.close();
        }
    }


}