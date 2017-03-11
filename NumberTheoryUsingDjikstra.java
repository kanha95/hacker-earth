import java.io.*;
import java.util.*;


class pair implements Comparable<pair>{
    long x;
    int y;
    pair(long x,int y){
        this.x=x;
        this.y=y;
    }
    public int compareTo(pair that){
        return Long.compare(this.x, that.x);
    }
    
}




 class jam
{
       
            
        
	public static void main(String args[]) throws Exception
	{
		InputReader sc=new InputReader(System.in);		
	//	PrintWriter pw=new PrintWriter(new FileWriter("F:\\wer2.txt"));
      
           
            int n=sc.nextInt();
            int q=sc.nextInt();
            
         int[] ar=new int[n];
         int min=Integer.MAX_VALUE;
            for (int i = 0; i < n; i++) {
                ar[i]=sc.nextInt();
                min=Math.min(min, ar[i]);
            }
            long[] f=new long[min];
            Arrays.fill(f, Integer.MAX_VALUE);
            f[0]=0;
            
            
            PriorityQueue<pair> pq=new PriorityQueue();
            
            pq.add(new pair(0,0));
            
  
            
            while(!pq.isEmpty()){
                long dist=pq.peek().x;
                int ver=pq.peek().y;
                pq.poll();
               if(f[ver]<dist) continue;
                
               for(int i=0;i<n;i++){
                 int nxt=(ver+ar[i])%min;    
                    
                if(f[nxt]>f[ver]+ar[i]){
                    f[nxt]=f[ver]+ar[i];
                    pq.add(new pair(f[nxt],nxt));
                    
                }    
                    
                }
                
                
            }
            
            
             for (int i = 0; i < q; i++) {
                int x=sc.nextInt();
                
             int ver=x%min;
             
             if(f[ver]<=x){
                 System.out.println("YES");
             }else{
                 System.out.println("NO");
             }
                 
                 
                 
                 
            }
   
 }
 }
	 class InputReader
	{
		private InputStream stream;
		private byte[] buf = new byte[1024];
		private int curChar;
		private int numChars;
		private SpaceCharFilter filter;
		
		public InputReader(InputStream stream)
		{
			this.stream = stream;
		}
		
		public int read()
		{
			if (numChars==-1) 
				throw new InputMismatchException();
			
			if (curChar >= numChars)
			{
				curChar = 0;
				try 
				{
					numChars = stream.read(buf);
				}
				catch (IOException e)
				{
					throw new InputMismatchException();
				}
				
				if(numChars <= 0)				
					return -1;
			}
			return buf[curChar++];
		}
	 
		public String nextLine()
		{
			BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
			String str = "";
            try
            {
                str = br.readLine();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
            return str;
		}
		public int nextInt()
		{
			int c = read();
			
			while(isSpaceChar(c)) 
				c = read();
			
			int sgn = 1;
			
			if (c == '-') 
			{
				sgn = -1;
				c = read();
			}
			
			int res = 0;
			do 
			{
				if(c<'0'||c>'9') 
					throw new InputMismatchException();
				res *= 10;
				res += c - '0';
				c = read();
			}
			while (!isSpaceChar(c)); 
			
			return res * sgn;
		}
		
		public long nextLong() 
		{
			int c = read();
			while (isSpaceChar(c))
				c = read();
			int sgn = 1;
			if (c == '-') 
			{
				sgn = -1;
				c = read();
			}
			long res = 0;
			
			do 
			{
				if (c < '0' || c > '9')
					throw new InputMismatchException();
				res *= 10;
				res += c - '0';
				c = read();
			}
			while (!isSpaceChar(c));
				return res * sgn;
		}
		
		public double nextDouble() 
		{
			int c = read();
			while (isSpaceChar(c))
				c = read();
			int sgn = 1;
			if (c == '-') 
			{
				sgn = -1;
				c = read();
			}
			double res = 0;
			while (!isSpaceChar(c) && c != '.') 
			{
				if (c == 'e' || c == 'E')
					return res * Math.pow(10, nextInt());
				if (c < '0' || c > '9')
					throw new InputMismatchException();
				res *= 10;
				res += c - '0';
				c = read();
			}
			if (c == '.') 
			{
				c = read();
				double m = 1;
				while (!isSpaceChar(c)) 
				{
					if (c == 'e' || c == 'E')
						return res * Math.pow(10, nextInt());
					if (c < '0' || c > '9')
						throw new InputMismatchException();
					m /= 10;
					res += (c - '0') * m;
					c = read();
				}
			}
			return res * sgn;
		}
		
		public String readString() 
		{
			int c = read();
			while (isSpaceChar(c))
				c = read();
			StringBuilder res = new StringBuilder();
			do 
			{
				res.appendCodePoint(c);
				c = read();
			} 
			while (!isSpaceChar(c));
			
			return res.toString();
		}
	 
		public boolean isSpaceChar(int c) 
		{
			if (filter != null)
				return filter.isSpaceChar(c);
			return c == ' ' || c == '\n' || c == '\r' || c == '\t' || c == -1;
		}
	 
		public String next() 
		{
			return readString();
		}
		
		public interface SpaceCharFilter 
		{
			public boolean isSpaceChar(int ch);
		}
	}
