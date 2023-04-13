package cchef.jtrain.starters84;

/*

RUST?
    /*
This template is made by Naman Garg <naman.rustp@gmail.com>
GitHub : https://github.com/namanlp
GitLab : https://gitlab.com/namanlp
Website : https://rustp.org

You can visit https://rustp.org/basic-programs/basic-template/
for understanding the template

Feel free to copy the template, but not the solutions :D
Thank You


LOOK at inclusion Exclusion principle
HINT1:
First, recall some properties about the sum and OR of two integers.

Suppose you have X and Y.
What's the relation between X+Y and X∣Y In particular, when are
they equal?

Can you generalize this to three numbers X,Y,Z?

HINT2:

0 or 1 is less than or equal to 0 + 1 always
X∣Y≤X+Y always. Further, they are equal if and only if X and Y
don't have any common bits.

In fact, a very similar condition applies to three integers. X+Y+Z

equals X∣Y∣Z if and only all of X,Y,Z have distinct set bits.

1


Now, we want X+Y+Z=X∣Y∣Z=N. This means the set bits of N must be
distributed among X,Y,Z such that each one gets at least one bit (to satisfy
the 0 < X,Y,Z < N condition).

Do you see how to count this?

HINT3:

First, let's ignore the 0<X,Y,Z<N condition, and distribute bits freely.

Suppose N has K set bits. Each of these K has three options, going to one
 of X,Y,Z. This is 3K ways.

Now, let's subtract the ways in which some elements didn't get any bits.
If X=0 in the end, then each bit went to either Y or Z, for 2K
possibilities.

Similarly, Y=0 and Z=0 also have 2K possibilities each.

However, we've subtracted too much: if some distributed method has both X=0
and Y=0, we've subtracted it twice, for example.
Notice that there's only one such distribution for X=Y=0.
Similarly, there's one each for Y=Z=0 and X=Z=0, for a total of three ways.

This accounts for everything, and hence the answer is
3K - 3.2K + 3

* */

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.IntStream;
import java.util.stream.Stream;


class Codechef84_7_awesum_ {
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