import java.io.*;
import java.util.*;

class pair{
    int x;
    int y;
    pair(int a,int b){
        x=a;
        y=b;
    }
}

 class jam
{
    static boolean[][] visited;
    static char[][] mat;
    static ArrayList<pair> al=new ArrayList();
     
    static void dfs(int i,int j,int r,int c){
        
        if(i>=r || i<0 || j>=c || j<0) return;
        
        if(mat[i][j]=='.' || visited[i][j]){
            return;
        }
        visited[i][j]=true; //optional
        mat[i][j]='.';
        al.add(new pair(i,j));
        
        dfs(i+1,j,r,c);
        dfs(i-1,j,r,c);
        dfs(i,j+1,r,c);
        dfs(i,j-1,r,c);
        
    }
    
    static void move(){
        int r=mat.length;
        int c=mat[0].length;
        visited=new boolean[r][c];
        
        for(int i=0;i<r;i++){
            for (int j = 0; j < c; j++) {
              if(mat[i][j]=='x' && !visited[i][j]){
                  al.clear();
                  dfs(i,j,r,c);              
        
                  int shift=r;
                  
                  for (int k = 0; k < al.size(); k++) {
                      int len=0;
                      for ( len = al.get(k).x+1; len < r && mat[len][al.get(k).y]=='.'; len++);
                      shift=Math.min(shift, len-al.get(k).x-1);
           
                  }
                  
                  for (int k = 0; k < al.size(); k++) {
                      mat[al.get(k).x+shift][al.get(k).y]='x';
                      visited[al.get(k).x+shift][al.get(k).y]=true;
                      
                  }
              }
                
            }
        }
        
    }
    
    
	public static void main(String args[]) throws Exception
	{
		InputReader sc=new InputReader(System.in);		
		PrintWriter pw=new PrintWriter(new FileWriter("F:\\wer2.txt"));
      
          int r=sc.nextInt();
          int c=sc.nextInt();
               
          
          mat=new char[r][c];
         
            for (int i = 0; i < r; i++) {
                    mat[i]=sc.next().toCharArray();
                }
            
         int q=sc.nextInt();
         
            for (int i = 0; i < q; i++) {
                int h=sc.nextInt();
                h=r-h;
                int j=0;
                //alice
                if(i%2==0){
                    for(j=0;j<c;j++){
                        if(mat[h][j]=='x'){
                            break;
                        }
                    }
                    
                    
                }
                //bob
                else{
                    for(j=c-1;j>=0;j--){
                        if(mat[h][j]=='x'){
                            break;
                        }
                    }
                }
                if(j!=c && j!=-1){
                    mat[h][j]='.';
                    move();         
                }
                
                
            }
            for (int i = 0; i < r; i++) {
                for (int j = 0; j < c; j++) {
                    System.out.print(mat[i][j]+"");
                }
                System.out.println("");
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
