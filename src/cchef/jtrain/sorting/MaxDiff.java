package cchef.jtrain.sorting; /*
Improved solution modified from sorabhtomar that uses Java recommended BufferedReader split("\\s+")
StringTokenizer == legacy.*/

import java.io.*;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

class MaxDiff {
    //static BufferedReader br = getOnline();
    static final String INPUT_PATH = "/home/kali/Documents/001_CC/inputM.txt";
    static FastWriter out = new FastWriter();
    static BufferedReader bri = getIDE();

    public static void main(String[] args) throws Exception {

        int t = Integer.parseInt(bri.readLine());

        while (t-- > 0) {
            String[] line1 = bri.readLine().trim().split("\\s+");
            Integer n = Integer.parseInt(line1[0]);
            Integer k = Integer.parseInt(line1[1]);

            String raw = bri.readLine();
            // Remove everything except whitespace and digits, remove whitespace from ^ and $
            String clean = raw.replaceAll("[^\\d\\s]", "").trim();
            List<Integer> input = Arrays.asList(strArrToIntArr(clean.split("\\s+"), n));
            Collections.sort(input);

            Integer minWeight = 0;
            Integer maxWeight = 0;
            int minCount = Math.min(k, n - k);


            for (int i = 0; i < minCount; i++)
                minWeight += input.get(i);
            for (int j = n - 1; j >= minCount; j--)
                maxWeight += input.get(j);
            int result = maxWeight - minWeight;


            out.println(result);


        }
        bri.close();
        out.close();
    }

    /*===========================================================================*/

    private static Integer[] strArrToIntArr(String[] readLine, int expectedLength) throws NumberFormatException {
        if (readLine.length < expectedLength) readLine = lengthenArray(readLine, expectedLength);
        if (readLine.length > expectedLength) throw new NumberFormatException("The array is larger than expected");

        int[] st01 = Arrays.stream(readLine).mapToInt(Integer::parseInt).toArray();
        IntStream st02 = Arrays.stream(st01);
        Stream<Integer> st03 = st02.boxed();

        return st03.toArray(Integer[]::new);

    }

    private static String[] lengthenArray(String[] readLine, int expectedLength) {
        int requiredZeros = expectedLength - readLine.length;
        String[] temp = new String[expectedLength];
        int x = 0;

        for (; x < readLine.length; x++)
            temp[x] = readLine[x];
        for (int y = 0; y <= requiredZeros; y++)
            temp[x] = "0";

        return temp;
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