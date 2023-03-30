package cchef.jtrain.utils.archive;

/*LoudSilence Copied from codechef -darcy_official DISTOPPSUMS  91242231
Original Imports:
import java.io.*;
import java.util.*;
import static java.lang.Math.*;
 */
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

class SolutionTemplate {
    /*----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------*/
    static FastScanner s = new FastScanner();
    static FastWriter out = new FastWriter();
    final static int mod = (int)1e9 + 7;
    final static int INT_MAX = Integer.MAX_VALUE;
    final static int INT_MIN = Integer.MIN_VALUE;
    final static long LONG_MAX = Long.MAX_VALUE;
    final static long LONG_MIN = Long.MIN_VALUE;
    final static double DOUBLE_MAX = Double.MAX_VALUE;
    final static double DOUBLE_MIN = Double.MIN_VALUE;
    final static float FLOAT_MAX = Float.MAX_VALUE;
    final static float FLOAT_MIN = Float.MIN_VALUE;

    /*----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------*/
    static class FastScanner{BufferedReader br;StringTokenizer st;
        public FastScanner() {
            if(System.getProperty("ONLINE_JUDGE") == null) {
                try {
                    br = new BufferedReader(new FileReader("/home/kali/Documents/input.txt"));}
        catch (FileNotFoundException e) {br = new BufferedReader(new InputStreamReader(System.in));}}else{br = new BufferedReader(new InputStreamReader(System.in));}}
        String next(){while (st == null || !st.hasMoreElements()){try{st = new StringTokenizer(br.readLine());}catch (IOException  e){e.printStackTrace();}}return st.nextToken();}
        int nextInt(){return Integer.parseInt(next());}
        long nextLong(){return Long.parseLong(next());}
        double nextDouble(){return Double.parseDouble(next());}
        List<Integer> readIntList(int n){List<Integer> arr = new ArrayList<>(); for(int i = 0; i < n; i++) arr.add(s.nextInt()); return arr;}
        List<Long> readLongList(int n){List<Long> arr = new ArrayList<>(); for(int i = 0; i < n; i++) arr.add(s.nextLong()); return arr;}
        int[] readIntArr(int n){int[] arr = new int[n]; for (int i = 0; i < n; i++) arr[i] = s.nextInt(); return arr;}
        long[] readLongArr(int n){long[] arr = new long[n]; for (int i = 0; i < n; i++) arr[i] = s.nextLong(); return arr;}
        String nextLine(){String str = "";try{str = br.readLine();}catch (IOException e){e.printStackTrace();}return str;}}

    static class FastWriter{
        private BufferedWriter bw;
        public FastWriter(){
            if(System.getProperty("ONLINE_JUDGE") == null) {
                try {
                    this.bw = new BufferedWriter(new FileWriter("/home/kali/Documents/output.txt"));}
    catch (IOException e) {
                this.bw = new BufferedWriter(new OutputStreamWriter(System.out));}
            }
            else{this.bw = new BufferedWriter(new OutputStreamWriter(System.out));}}
        public void print(Object object) throws IOException{bw.append(""+ object);}
        public void println(Object object) throws IOException{print(object);bw.append("\n");}
        public void debug(int[] object) throws IOException{bw.append("["); for(int i = 0; i < object.length; i++){if(i != object.length-1){print(object[i]+", ");}else{print(object[i]);}}bw.append("]\n");}
        public void debug(long[] object) throws IOException{bw.append("["); for(int i = 0; i < object.length; i++){if(i != object.length-1){print(object[i]+", ");}else{print(object[i]);}}bw.append("]\n");}
        public void close() throws IOException{bw.close();}}
    public static void println(Object str) throws IOException{out.println(""+str);}

    public static void println(Object str, int nextLine) throws IOException{out.print(""+str);}

    /*----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------*/
    public static ArrayList<Integer> seive(int n){ArrayList<Integer> list = new ArrayList<>();
        int[] arr = new int[n+1];for(long i = 2; i <= n; i++) {if(arr[(int)i] == 1) {continue;}else {list.add((int)i);for(long j = i*i; j <= n; j = j + i) {arr[(int)j] = 1;}}}return list;}
    public static long gcd(long a, long b){if(a > b) {a = (a+b)-(b=a);}if(a == 0L){return b;}return gcd(b%a, a);}
    public static void swap(int[] arr, int i, int j) {arr[i] = arr[i] ^ arr[j]; arr[j] = arr[j] ^ arr[i]; arr[i] = arr[i] ^ arr[j];}
    public static boolean isPrime(long n){if(n < 2){return false;}if(n == 2 || n == 3){return true;}if(n%2 == 0 || n%3 == 0){return false;}long sqrtN = (long)Math.sqrt(n)+1;for(long i = 6L; i <= sqrtN; i += 6) {if(n%(i-1) == 0 || n%(i+1) == 0) return false;}return true;}
    public static long mod_add(long a, long b){ return (a%mod + b%mod)%mod;}
    public static long mod_sub(long a, long b){ return (a%mod - b%mod + mod)%mod;}
    public static long mod_mul(long a, long b){ return (a%mod * b%mod)%mod;}
    public static long modInv(long a, long b){ return expo(a, b-2)%b;}
    public static long mod_div(long a, long b){return mod_mul(a, modInv(b, mod));}
    public static long expo (long a, long n){if(n == 0){return 1;}long recAns = expo(mod_mul(a,a), n/2);if(n % 2 == 0){return recAns;}else{return mod_mul(a, recAns);}}
    /*----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------*/

    // Code begins
    public static void solve() throws IOException {
        int n = s.nextInt();
        for(int i = n-1; i >= 1; i-=2){
            println(i+" ",1);
        }
        for(int i = 2; i <= n; i+=2){
            println(i+" ",1);
        }
        println("");
    }

    /*----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------*/
    @SuppressWarnings("DuplicatedCode")
    public static void main(String[] args) throws IOException {
        long srtTime = System.currentTimeMillis();
        int test = s.nextInt();

        for(int t = 1; t <= test; t++) {
            solve();
        }
        long endTime = System.currentTimeMillis();

        println("DATA:\nONLINE_JUDGE = "+ System.getProperty("ONLINE_JUDGE") +"\nOPERATION TOOK: " +(endTime-srtTime)+" ms ");
        out.close();
    }
}
