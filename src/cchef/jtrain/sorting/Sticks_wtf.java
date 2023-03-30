package cchef.jtrain.sorting;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.IntStream;
import java.util.stream.Stream;

class Sticks_wtf {
static final String INPUT_PATH = "/home/kali/Documents/001_CC/sticks.txt";
static FastWriter out = new FastWriter();
static FastScanner s = new FastScanner();

public static void main(String[] args) throws Exception {

	int t = s.nextInt();


	while (t-- > 0) {
		int n = s.nextInt();
		int [] sticks = s.readIntArr(n);
		Arrays.sort(sticks);

		int l=0;
		int b=0;

		for(int i=n-1; i>0; i--) {
			if (sticks[i] == sticks[i - 1] && l == 0) {
				l = sticks[i];
				i--;
			} else if (b == 0 && sticks[i] == sticks[i - 1]) {
				b = sticks[i];
				break;
			}
		}

		if(l!=0 && b!=0) System.out.println(l*b);
		else System.out.println(-1);

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