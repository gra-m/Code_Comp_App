package cchef.jtrain.starters84;

/*
Diff 1604..

HINT 1:
First, sort the array and compute Z as mentioned in the statement.

Now, let M1 be the largest element of A and M2 be the second largest.
Of course, Z=M1+M2

In any valid rearrangement, no instance of M1 should be next to any instance of M2.
Any element that's strictly smaller than M2 doesn't matter,
it can never reach a sum of Z.

We want to check if this is possible.

Now, there are two possibilities: M1=M2 , and M1≠M2.

Analyze each case separately and see what conditions are required.

HINT 2:

First, consider M1=M2M

Since the two largest elements are equal, this means no two occurrences of this
maximum should be next to each other.
The absolute best we can do in terms of separation is to place them at indices
1,3,5,7,…

This gives us ⌈N/2⌉ positions where
they can be placed. Here, ⌈ ⌉ is the ceiling function.

So, if the maximum occurs more than ⌈N2⌉ times, the answer is No; otherwise,
the answer is Yes.

Can you analyze the M1≠M2M case similarly?
*

Hint 3:

If M1≠M2, there's only one occurrence of M1, and we want to separate it
from all occurrences of M2.
If every other element equals M2, this is obviously impossible.

Otherwise, it's not hard to see that it's always possible to separate them:
place M1 at one end of the array, all the occurrences of M2 at the other end,
and everything else between them.

So, in this case, the answer is No if M2 occurs N−1 times in A, and Yes otherwise.
*
*
* */

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.IntStream;
import java.util.stream.Stream;


class Codechef84_6_chfadjsum {
static final String INPUT_PATH = "/home/kali/Documents/001_CC/in.txt";
static final String OUTPUT_PATH = "/home/kali/Documents/001_CC/out.txt";
static final FastWriter OUT = new FastWriter();
static final FastScanner IN = new FastScanner();
static final boolean FILE_WRITE = false;

public static void main(String[] args) throws Exception {

	int t = IN.nextInt();
	int caseLength = 2;
	int kase = 0;


	while (t-- > 0) {
		kase ++;
		String[] arr = IN.nextLine_A(caseLength);
		int n = Integer.parseInt(arr[0]);
		int s = Integer.parseInt(arr[1]);



		if(kase > 0) {
			if (s < n || n == s)
				OUT.println(s);
			else
				OUT.println(n - (s-n));
		}


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