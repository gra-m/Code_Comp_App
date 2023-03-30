package cchef.jtrain.arrayinput; /*
Improved solution modified from sorabhtomar that uses Java recommended BufferedReader split("\\s+")
StringTokenizer == legacy.*/

import java.io.*;
import java.util.Arrays;

class JibbyJabQ_improved {
    //static BufferedReader br = getOnline();
    static final String INPUT_PATH = "/home/kali/Documents/001_CC/input.txt";
    static FastWriter out = new FastWriter();
    static BufferedReader bri = getIDE();

    public static void main(String[] args) throws Exception {

        int t = Integer.parseInt(bri.readLine());

        while (t-- > 0) {
            String[] line1 = bri.readLine().split("\\s+");
            int n = Integer.parseInt(line1[0]);
            int p = Integer.parseInt(line1[1]);
            int x = Integer.parseInt(line1[2]);
            int y = Integer.parseInt(line1[3]);

            int[] input = strToIntArray(bri.readLine());

            int time = 0;

            for (int i = 0; i < p; i++)
                time += input[i] == 0 ? x : y;
            out.println(time);

        }
        bri.close();
        out.close();
    }

    private static int[] strToIntArray(String readLine) {
        return Arrays.stream(readLine.split("\\s+")).mapToInt(Integer::parseInt).toArray();
    }

    private static BufferedReader getIDE() {
        try {
            return new BufferedReader(new FileReader(INPUT_PATH));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private static BufferedReader getOnline() {
        return new BufferedReader(new InputStreamReader(System.in));
    }



    /*===========================================================================*/

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