package cchef.jtrain.sorting;
/*https://www.codechef.com/LP1TO204/problems/SIGNFLIP?tab=statement*/

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

class Signflip {
    static final String INPUT_PATH = "/home/kali/Documents/001_CC/sfin.txt";
    static FastWriter out = new FastWriter();
    static BufferedReader bri = getBufferedReader();



    public static void main(String[] args) throws Exception {

        int t = Integer.parseInt(bri.readLine());

        while (t-- > 0) {
            String[] line1 = bri.readLine().trim().split("\\s+");
            Integer n = Integer.parseInt(line1[0]);
            Integer k = Integer.parseInt(line1[1]);
            String[] line2 = bri.readLine().trim().split("\\s+");
            List<Integer> posOrZ = new ArrayList<>();
            List<Integer> neg = new ArrayList<>();

            int[] all = new int[n];
            for (int x =0; x < n; x++) {
                int temp = Integer.parseInt(line2[x]);
                all[x] = temp;
                if(temp >= 0) 
                    posOrZ.add(temp);
                else
                    neg.add(temp);
            }
            if (posOrZ.size() == n || k == 0) {
                out.println(posOrZ.stream().reduce(0, Integer::sum));
                continue;
            }

            Arrays.sort(all);

            int posTotal = posOrZ.stream()
                    .reduce(0, Integer::sum);
            int negSize = neg.size();
            int flipCount = negSize<k?negSize:(negSize - Math.abs(k - negSize));
            for(int y = 0; y < flipCount; y++) posTotal += all[y] * -1;

            out.println(posTotal);

        }
        bri.close();
        out.close();

    }
    private static int getSmallestDifference(List<Integer> input) throws IOException {
        int temp;
        int smallest = Integer.MAX_VALUE;
        int size = input.size();

        for (int i = 0; i < size - 1; i++) {
            temp = input.get(i + 1) - input.get(i);
            if(temp < smallest)
            smallest = temp;
        }

        return smallest;
    }



    /*===========================================================================*/

    private static int getSumOfSmallestPair(int[] input) {
        int temp1 = 1000000;
        int temp2 = 1000000;
        int n = input.length;

        for(int i = 0; i < n ; i++) {
            int temp3 = input[i];
            if(temp3 < temp1) {
                temp2 = temp1;
                temp1 = temp3;
            }
            else if(temp3 < temp2 && temp3 != temp1)
                temp2 = temp3;
        }

        return temp1 + temp2;
    }

    private static Integer[] strArrToIntGArr(String[] readLine, int expectedLength) throws NumberFormatException {
        if (readLine.length > expectedLength) throw new NumberFormatException("The array is larger than expected");

        int[] st01 = Arrays.stream(readLine).mapToInt(Integer::parseInt).toArray();
        IntStream st02 = Arrays.stream(st01);
        Stream<Integer> st03 = st02.boxed();

        return st03.toArray(Integer[]::new);

    }

    private static int[] strArrToIntArr(String[] readLine, int expectedLength) throws NumberFormatException {
        if (readLine.length > expectedLength) throw new NumberFormatException("The array is larger than expected");

        return Arrays.stream(readLine)
                .mapToInt(Integer::parseInt)
                .toArray();
    }

    private static void lengthenArray(String[] readLine, int expectedLength) {
        int requiredZeros = expectedLength - readLine.length;
        String[] temp = new String[expectedLength];
        int x = 0;

        for (; x < readLine.length; x++)
            temp[x] = readLine[x];
        for (int y = 0; y <= requiredZeros; y++)
            temp[x] = "0";

        throw new NumberFormatException("The array is shorter than expected");
        //return temp;
    }

    private static String cleanString(String raw) {
        // Remove everything except whitespace and digits, remove whitespace from ^ and $
        return raw.replaceAll("[^\\d\\s]", "").trim();
    }

    private static BufferedReader getBufferedReader() {
        int local = Integer.parseInt(System.getenv("IDE_RUN"));
        if(local == 1) {
            System.out.println("RUNNING LOCALLY courtesy of env var \"IDE_RUN\"");
            try {
                return new BufferedReader(new FileReader(INPUT_PATH));
            } catch (FileNotFoundException e) {
                return new BufferedReader(new InputStreamReader(System.in));
            }
        }else {
            return new BufferedReader(new InputStreamReader(System.in));
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