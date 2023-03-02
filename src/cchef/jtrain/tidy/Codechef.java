package cchef.jtrain.tidy;

import java.io.*;
import java.util.StringTokenizer;

/* Name of the class has to be "Main" only if the class is public. */
class Codechef {
    static FastScanner s = new FastScanner();
    static FastWriter out = new FastWriter();

    public static void main(String[] args) throws java.lang.Exception {
        int test = s.nextInt();
        for (int t = 1; t <= test; t++) {
            out.println(solve());
        }
        out.close();
    }

    public static int solve() {
        int a = s.nextInt();
        int b = s.nextInt();
        Pair p1 = new Pair(a, b);
        int c = s.nextInt();
        int d = s.nextInt();
        Pair p2 = new Pair(c, d);
        int e = s.nextInt();
        int f = s.nextInt();
        Pair p3 = new Pair(e, f);
        int one = p1.compareTo(p2);
        int two = p1.compareTo(p3);
        return one + two == 0 ? 0 : one > two ? 1 : 2;
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
            if (fp == o.fp && sp == o.sp || fp == o.sp && sp == o.fp)
                return 1;
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