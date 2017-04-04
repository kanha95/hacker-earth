import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;


class edge {
    int x,wt;
    edge(int x,int wt){
        this.x=x;
        this.wt=wt;
    }
    
 
    
}
class graph{
    int v;
    LinkedList<edge>[] adj;
    
    graph(int v){
        this.v=v;
        adj=new LinkedList[v];

        for (int i = 0; i < v; i++) {
            adj[i]=new LinkedList();
        }
    }
    
    void addEdge(int x,int y,int wt){
        adj[x].add(new edge(y,wt));
        adj[y].add(new edge(x,wt));
    }
    
    class pair implements Comparable<pair>{
        int x,wt;
        pair(int x,int wt){
            this.x=x;
            this.wt=wt;
        }
        public int compareTo(pair that){
            return Integer.compare(this.wt, that.wt);
        }
        
    }
    int sub;
    void modD(int start,int end,boolean bb){
        boolean[] vis=new boolean[v];
        PriorityQueue<pair> pq=new PriorityQueue<>();
   
        
        int[] parent=new int[v];
        int[] cost=new int[v];
        
        for (int i = 0; i < v; i++) {
            parent[i]=i;
        }
        Arrays.fill(cost, Integer.MAX_VALUE);
        cost[start]=0;
        pq.add(new pair(start,cost[start]));
        while(!pq.isEmpty()){
            
            int x=pq.peek().x;
            int wt=pq.peek().wt;
            pq.poll();
            if(vis[x]) continue;
            
            vis[x]=true;
            
            for(edge e:adj[x]){
                
                if(wt+e.wt<cost[e.x]){
                    cost[e.x]=wt+e.wt;
                    parent[e.x]=x;
                    pq.add(new pair(e.x,cost[e.x]));
                }
                
              
                
                
            }
            
        }
        
        sub=cost[end];
        if(sub!=Integer.MAX_VALUE){
        String ans="";
        int u=end;
        while(parent[u]!=u){
            if(bb){
                bb=false;
                u=parent[u];
                continue;
            }
            ans=" "+(u+1)+ans;
            u=parent[u];
        }
        fans=(start+1)+""+ans+" "+fans;
        tans+=sub;
        }
        else{
            fans="null";
        }
    }
    long tans=0;
    String fans="";
}

class Main{
    
    public static void main(String[] args){
        InputReader sc=new InputReader(System.in);
        PrintWriter pw=new PrintWriter(System.out);
       
        int t=sc.nextInt();
        loop:
        while(t>0){
        
        int n=sc.nextInt();
        int r=sc.nextInt();
            
         graph g=new graph(n);
         
         for (int i = 0; i < r; i++) {
            g.addEdge(sc.nextInt()-1, sc.nextInt()-1, sc.nextInt());
        }
        
        int a=sc.nextInt();
        int b=sc.nextInt();
        int c=sc.nextInt();
       // System.out.println("y");
       g.modD(b-1,c-1,false);
       if(g.fans.equals("null")){
           System.out.println("No Train Found.");
           t--;
           continue;
       }
         //   System.out.println(g.fans);
        g.modD(a-1, b-1,true);
           if(g.fans.equals("null")){
           System.out.println("No Train Found.");
           t--;
           continue;
       }
            System.out.println(g.tans);
            System.out.println(g.fans);
        
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
