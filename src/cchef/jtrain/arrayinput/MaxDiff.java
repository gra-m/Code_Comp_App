package cchef.jtrain.arrayinput; /*
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
    static final String INPUT_PATH = "/home/kali/Documents/001_CC/input.txt";
    static FastWriter out = new FastWriter();
    static BufferedReader bri = getIDE();

    public static void main(String[] args) throws Exception {

        int t = Integer.parseInt(bri.readLine());

        while (t-- > 0) {
            String[] line1 = bri.readLine().split("\\s+");
            Integer n = Integer.parseInt(line1[0]);
            Integer k = Integer.parseInt(line1[1]);

            List<Integer> input = strArrToList(bri.readLine());
            Collections.sort(input);



            Integer minWeight = 0;
            //Integer totalWeight = input.stream().reduce(0, (a,b) -> a + b);
            Integer totalWeight = input.stream().reduce(0, Integer::sum);

            for (int i = 0; i < k; i++)
                minWeight += input.get(i);
            out.println(totalWeight - (totalWeight - minWeight));

        }
        bri.close();
        out.close();
    }

    private static List<Integer> strArrToList(String readLine) {
        int[] returned = strToIntArray(readLine);
        IntStream stream = Arrays.stream(returned);
        Stream<Integer> boxed = stream.boxed();
        Integer[] boxedFromStream = boxed.toArray(Integer[]::new);
        return Arrays.asList(boxedFromStream);
    }

   /* private static List<Integer> sortArray(int[] input) {
        List<Integer> temp = Arrays.asList(input) ;
    }*/

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