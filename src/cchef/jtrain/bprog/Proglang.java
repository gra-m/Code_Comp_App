package cchef.jtrain.bprog;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.IntStream;
import java.util.stream.Stream;


class Proglang {
static final String INPUT_PATH = "/home/kali/Documents/001_CC/in.txt";
static final String OUTPUT_PATH = "/home/kali/Documents/001_CC/out.txt";
static final FastWriter OUT = new FastWriter();
static final FastScanner IN = new FastScanner();
static final boolean FILE_WRITE = false;

public static void main(String[] args) throws Exception {

	int t = IN.nextInt();
	int caseLength = 6;
	int kase = 0;


	while( t-- > 0 ) {
		kase++;
		String[] arr = IN.nextLine_A(caseLength);
		int a = Integer.parseInt(arr[0]);
		int b = Integer.parseInt(arr[1]);
		int[] first = {Integer.parseInt(arr[2]), Integer.parseInt(arr[3])};
		int[] sec = {Integer.parseInt(arr[4]), Integer.parseInt(arr[5])};

		boolean check1 = false;
		boolean check2 = false;

		if((a == first[0] || b == first[0]) && (a == first[1] || b == first[1]))
			check1 = true;
		if((a == sec[0] || b == sec[0]) && (a == sec[1] || b == sec[1]))
			check2 = true;

		OUT.println(!check1 && !check2?0:(check1?1:2));

	}


	IN.close();
	OUT.close();
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
	private final BufferedReader BR;
	private StringTokenizer st;

	public FastScanner() {
		BufferedReader br1;
		if (System.getProperty("ONLINE_JUDGE") == null) {
			try {
				br1 = new BufferedReader(new FileReader(INPUT_PATH));
			} catch (FileNotFoundException e) {
				br1 = new BufferedReader(new InputStreamReader(System.in));
			}
		} else {
			br1 = new BufferedReader(new InputStreamReader(System.in));
		}
		this.BR = br1;
	}

	String next() {
		while (st == null || !st.hasMoreElements()) {
			try {
				st = new StringTokenizer(BR.readLine());
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
		for (int i = 0; i < n; i++) arr.add(IN.nextInt());
		return arr;
	}

	List<Long> readLongList(int n) {
		List<Long> arr = new ArrayList<>();
		for (int i = 0; i < n; i++) arr.add(IN.nextLong());
		return arr;
	}

	int[] readIntArr(int n) {
		int[] arr = new int[n];
		for (int i = 0; i < n; i++) arr[i] = IN.nextInt();
		return arr;
	}

	Integer[] readIntegerArray(int n) {
		int[] arr = new int[n];
		for (int i = 0; i < n; i++) arr[i] = IN.nextInt();
		return intArrToIntegerArr(arr);
	}

	long[] readLongArr(int n) {
		long[] arr = new long[n];
		for (int i = 0; i < n; i++) arr[i] = IN.nextLong();
		return arr;
	}

	String nextLine() {
		String str = "";
		try {
			str = BR.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return str;
	}

	String cleanDigitsSpaces() {
		String str = "";
		try {
			str = cleanString(BR.readLine().trim());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return str;
	}

	String[] nextLine_A(int n) {
		String[] arr = new String[n];
		try {
			return BR.readLine().trim().split("\\s+");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return arr;
	}


	void close() {
		try {
			BR.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}


static class FastWriter {
	private final BufferedWriter BW;

	public FastWriter() {
		BufferedWriter bw1;
		if (System.getProperty("ONLINE_JUDGE") == null && FILE_WRITE) {
			try {
				bw1 = new BufferedWriter(new FileWriter(OUTPUT_PATH));
			} catch (IOException e) {
				bw1 = new BufferedWriter(new OutputStreamWriter(System.out));
			}
		} else {
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