import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.InputMismatchException;

class D2 {
	InputStream is;
	PrintWriter out;
	String INPUT = "";
	
	void solve()
	{
		int[] P = new int[100001];
		for(int a = -1000, sig = 1;a <= 1000;a++,sig=-sig){
			int v = a*(3*a-1)/2;
			if(v >= 0 && v <= 100000){
				P[v] = sig;
			}
		}
		
		for(int T = ni();T > 0;T--){
			long n = nl()-1;
			int K = ni();
			if(n == 0 && K == 1){
				out.println(0);
				continue;
			}
			// a(3a-1)/2 = c
			int mod = 1000000007;
			CMan cm = new CMan(n+K-0, K, mod);
			long ret = 0;
			for(int c = 0;c <= K;c++){
				ret += P[c] * cm.val();
				if(c < K){
					cm.decn();
					cm.decr();
				}
			}
			ret %= mod;
			if(ret < 0)ret += mod;
			out.println(ret);
		}
	}
	
	static class CMan
	{
		long val;
		int e;
		int mod;
		long N, R;
		
		private void mul(long u)
		{
			if(u == 0){
				e += 10000;
				return;
			}
			while(u % mod == 0){
				e++;
				u /= mod;
			}
			val = val * u % mod;
		}
		
		private void div(long u)
		{
			assert u > 0;
			while(u % mod == 0){
				e--;
				u /= mod;
			}
			val = val * invl(u, mod) % mod;
		}
		
		public CMan(long n, int r, int mod) {
			this.mod = mod;
			val = 1;
			e = 0;
			this.N = n;
			this.R = r;
			for(int i = 0;i < r;i++)mul(n-i);
			long f = 1;
			for(int i = 1;i <= r;i++){
				f = f * i % mod;
			}
			val = val * invl(f, mod) % mod;
		}
		
		public long val()
		{
			return e > 0 ? 0 : val;
		}
		
		public long incr()
		{
			if(N-R >= 0)mul(N-R);
			div(R+1);
			R++;
			return val();
		}
		
		public long decr()
		{
			if(N-R+1 >= 0)div(N-R+1);
			mul(R);
			R--;
			return val();
		}
		
		public long decn()
		{
			if(N-R >= 0)mul(N-R);
			div(N);
			N--;
			
			return val();
		}
	}
	
	public static long invl(long a, long mod) {
		long b = mod;
		long p = 1, q = 0;
		while (b > 0) {
			long c = a / b;
			long d;
			d = a;
			a = b;
			b = d % b;
			d = p;
			p = q;
			q = d - c * q;
		}
		return p < 0 ? p + mod : p;
	}

	
	public static int[][] enumFIF(int n, int mod) {
		int[] f = new int[n + 1];
		int[] invf = new int[n + 1];
		f[0] = 1;
		for (int i = 1; i <= n; i++) {
			f[i] = (int) ((long) f[i - 1] * i % mod);
		}
		long a = f[n];
		long b = mod;
		long p = 1, q = 0;
		while (b > 0) {
			long c = a / b;
			long d;
			d = a;
			a = b;
			b = d % b;
			d = p;
			p = q;
			q = d - c * q;
		}
		invf[n] = (int) (p < 0 ? p + mod : p);
		for (int i = n - 1; i >= 0; i--) {
			invf[i] = (int) ((long) invf[i + 1] * (i + 1) % mod);
		}
		return new int[][] { f, invf };
	}


	
	void run() throws Exception
	{
		is = INPUT.isEmpty() ? System.in : new ByteArrayInputStream(INPUT.getBytes());
		out = new PrintWriter(System.out);
		
		long s = System.currentTimeMillis();
		solve();
		out.flush();
		if(!INPUT.isEmpty())tr(System.currentTimeMillis()-s+"ms");
	}
	
	public static void main(String[] args) throws Exception { new D2().run(); }
	
	private byte[] inbuf = new byte[1024];
	private int lenbuf = 0, ptrbuf = 0;
	
	private int readByte()
	{
		if(lenbuf == -1)throw new InputMismatchException();
		if(ptrbuf >= lenbuf){
			ptrbuf = 0;
			try { lenbuf = is.read(inbuf); } catch (IOException e) { throw new InputMismatchException(); }
			if(lenbuf <= 0)return -1;
		}
		return inbuf[ptrbuf++];
	}
	
	private boolean isSpaceChar(int c) { return !(c >= 33 && c <= 126); }
	private int skip() { int b; while((b = readByte()) != -1 && isSpaceChar(b)); return b; }
	
	private double nd() { return Double.parseDouble(ns()); }
	private char nc() { return (char)skip(); }
	
	private String ns()
	{
		int b = skip();
		StringBuilder sb = new StringBuilder();
		while(!(isSpaceChar(b))){ // when nextLine, (isSpaceChar(b) && b != ' ')
			sb.appendCodePoint(b);
			b = readByte();
		}
		return sb.toString();
	}
	
	private char[] ns(int n)
	{
		char[] buf = new char[n];
		int b = skip(), p = 0;
		while(p < n && !(isSpaceChar(b))){
			buf[p++] = (char)b;
			b = readByte();
		}
		return n == p ? buf : Arrays.copyOf(buf, p);
	}
	
	private char[][] nm(int n, int m)
	{
		char[][] map = new char[n][];
		for(int i = 0;i < n;i++)map[i] = ns(m);
		return map;
	}
	
	private int[] na(int n)
	{
		int[] a = new int[n];
		for(int i = 0;i < n;i++)a[i] = ni();
		return a;
	}
	
	private int ni()
	{
		int num = 0, b;
		boolean minus = false;
		while((b = readByte()) != -1 && !((b >= '0' && b <= '9') || b == '-'));
		if(b == '-'){
			minus = true;
			b = readByte();
		}
		
		while(true){
			if(b >= '0' && b <= '9'){
				num = num * 10 + (b - '0');
			}else{
				return minus ? -num : num;
			}
			b = readByte();
		}
	}
	
	private long nl()
	{
		long num = 0;
		int b;
		boolean minus = false;
		while((b = readByte()) != -1 && !((b >= '0' && b <= '9') || b == '-'));
		if(b == '-'){
			minus = true;
			b = readByte();
		}
		
		while(true){
			if(b >= '0' && b <= '9'){
				num = num * 10 + (b - '0');
			}else{
				return minus ? -num : num;
			}
			b = readByte();
		}
	}
	
	private static void tr(Object... o) { System.out.println(Arrays.deepToString(o)); }
}
