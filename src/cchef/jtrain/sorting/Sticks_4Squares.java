package cchef.jtrain.sorting;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.IntStream;
import java.util.stream.Stream;

class Sticks_4Squares {
static final String INPUT_PATH = "/home/kali/Documents/001_CC/sticks.txt";
static FastWriter out = new FastWriter();
static FastScanner s = new FastScanner();

public static void main(String[] args) throws Exception {

	int t = s.nextInt();
	int cases = t;
	int count = 3;
	// 3 of a kind and 0 == -1


	while (t-- > 0) {
		int n = s.nextInt();
		int[] sticks = s.readIntArr(n);
		Arrays.sort(sticks);
		int pair1 = 0;
		int pair2 = 0;
		int pair3 = 0;
		int pair4 = 0;
		int sideCount = 0;

		for (int i = 0; i < n; i++) {
			sideCount = 0;

			for (int x = i + 1; x < n; x++) {
				int sticksX = sticks[x];

				if (sticksX == sticks[i]) {
					sideCount++;
					if (sticks[i] > pair1) {
						pair2 = pair1;
						pair1 = sticksX;
					} else if (sticks[i] > pair2) {
						pair3 = pair2;
						pair2 = sticksX;
					} else if (sticks[i] > pair3) {
						pair4 = pair3;
						pair3 = sticksX;
					} else if (sticks[i] > pair4 && sideCount == 3)
						pair4 = sticksX;
				}
			}


			for (int y = 0; y < i; y++) {
				int sticksY = sticks[y];
				if (sticks[y] == sticks[i]) {
					sideCount++;
					if (sticks[i] > pair1) {
						pair2 = pair1;
						pair1 = sticksY;
					} else if (sticks[i] > pair2) {
						pair3 = pair2;
						pair2 = sticksY;
					} else if (sticks[i] > pair3) {
						pair4 = pair3;
						pair3 = sticksY;
					} else if (sticks[i] > pair4 && sideCount == 3)
						pair4 = sticksY;

				}

			}


		}//end outer
		out.print(count + " " + pair1 + " " + pair2 + " " + pair3 + " " + pair4);

		count += 2;

		if (pair4 != 0) {
			if(pair1 == pair4)
			out.print(" 4ofakind = " + pair1 * pair3);
			else
				out.print(" 3ofkindWithPair || Pair= " + pair1 * pair4);
		}
		else if(pair1 != pair3)
			out.print(" 2 pair = " + pair1 *pair3 );
		else
			out.println(" 3ofkind only = " + -1);

		out.println("");
	}

	s.close();
	out.close();
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
	BufferedReader br;
	StringTokenizer st;

	public FastScanner() {
		if (System.getProperty("ONLINE_JUDGE") == null) {
			try {
				br = new BufferedReader(new FileReader(INPUT_PATH));
			} catch (FileNotFoundException e) {
				br = new BufferedReader(new InputStreamReader(System.in));
			}
		} else {
			br = new BufferedReader(new InputStreamReader(System.in));
		}
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

	List<Integer> readIntList(int n) {
		List<Integer> arr = new ArrayList<>();
		for (int i = 0; i < n; i++) arr.add(s.nextInt());
		return arr;
	}

	List<Long> readLongList(int n) {
		List<Long> arr = new ArrayList<>();
		for (int i = 0; i < n; i++) arr.add(s.nextLong());
		return arr;
	}

	int[] readIntArr(int n) {
		int[] arr = new int[n];
		for (int i = 0; i < n; i++) arr[i] = s.nextInt();
		return arr;
	}

	Integer[] readIntegerArray(int n) {
		int[] arr = new int[n];
		for (int i = 0; i < n; i++) arr[i] = s.nextInt();
		return intArrToIntegerArr(arr);
	}

	long[] readLongArr(int n) {
		long[] arr = new long[n];
		for (int i = 0; i < n; i++) arr[i] = s.nextLong();
		return arr;
	}

	String nextLine() {
		String str = "";
		try {
			str = br.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return str;
	}

	String cleanDigitsSpaces() {
		String str = "";
		try {
			str = cleanString(br.readLine().trim());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return str;
	}

	String[] nextLine_A(int n) {
		String[] arr = new String[n];
		try {
			return br.readLine().trim().split("\\s+");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return arr;
	}


	void close() {
		try {
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}


static class FastWriter {
	private final BufferedWriter bw;

	public FastWriter() {
		this.bw = new BufferedWriter(new OutputStreamWriter(System.out));
	}

	public void print(Object object) throws IOException {
		bw.append(String.valueOf(object));
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