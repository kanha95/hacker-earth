import java.io.*;
import java.util.*;
 


class graph{
    ArrayList al=new ArrayList();
    ArrayList bl=new ArrayList();
    int V;
    LinkedList<Integer>[] adj;
    
    graph(int v){
        V=v;
        adj=new LinkedList[v];
        
        for(int i=0;i<v;i++){
            adj[i]=new LinkedList();
        }
    }
    
    void addEdge(int u,int v){
        adj[u].add(v);
        adj[v].add(u);
    }

    void bfs(int start,int[] ar,int[] br,boolean[] visited){
        
       Deque<Integer> s=new ArrayDeque();
        s.add(start);
       al.add(ar[start]);
       bl.add(br[start]);
        while(!s.isEmpty()){
            int x=s.pollFirst();
            if(!visited[x]){
            al.add(ar[x]);
            bl.add(br[x]);
            }
            visited[x]=true;
            for(Integer i:adj[x]){
                if(!visited[i]){
                       
                    s.add(i);
                }
            }
            
            
        }
        
        
    }


}



 class jam
{
    
     
     
	public static void main(String args[]) throws Exception
	{
		InputReader sc=new InputReader(System.in);		
		PrintWriter pw=new PrintWriter(System.out);
      
           
                  int t=sc.nextInt();
                  
                  while(t>0){
                      
                      int n=sc.nextInt();
                      int m=sc.nextInt();
                      
                      int[] ar=new int[n];
                      int[] br=new int[n];
                      
                      for (int i = 0; i < n; i++) {
                          ar[i]=sc.nextInt();
                      }
                       for (int i = 0; i < n; i++) {
                          br[i]=sc.nextInt();
                      }
                      graph g=new graph(n);
                      g.al.clear();
                      g.bl.clear();
                      while(m>0){
                          g.addEdge(sc.nextInt()-1, sc.nextInt()-1);
                          
                          m--;
                      }
                      boolean[] visited=new boolean[n];
                      boolean ok=true;
                      for(int i=0;i<n;i++){
                          if(!visited[i]){
                              visited[i]=true;
                              g.al.clear();
                              g.bl.clear();
                             g.bfs(i,ar,br,visited);
                             Collections.sort(g.al);
                             Collections.sort(g.bl);
                             if(!g.al.equals(g.bl)){
                          //       System.out.println(g.al+"");
                            //   System.out.println(g.bl+"");
                                 ok=false;
                                 break;
                             }
                          }
                          
                      }
                      if(ok){
                          System.out.println("YES");
                      }else{
                          System.out.println("NO");
                      }
                      t--;
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
