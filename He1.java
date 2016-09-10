package He1;
import java.util.*;
public class He1 {
int[] index;
int min;
    void find(String s){
        index=new int[s.length()];
        for(int k=0;k<s.length();k++){
            index[k]= -1;
        }
        char c=s.charAt(s.length()-1);
        int z=0;
        boolean[] b=new boolean[s.length()];
        for(int i=s.length()-2;i>=0;i--){
            if(s.charAt(i)<=c){
                
                min=i;
                
                c=s.charAt(i);
            }
        }
        for(int i=s.length()-1;i>=0;i--){
            if(s.charAt(i)==c){
                index[z]=i;
                z++;
            }
        }
        
    }
    String match(String sub,String s){
      
        for(int i=0;i<sub.length();i++){
        if(sub.charAt(i)<s.charAt(i)){
            return sub;
        }
            else if(sub.charAt(i)>s.charAt(i)){
                    return s;
                    }
        
    }
        return s;
    }
    String trim(String s,int tc,int x){
        find(s);
        String sm=s;
        for(int m=0;m<index.length;m++){
            if(index[m]!=-1){
            if(index[m]==0 || index[m]==s.length()-1){
        if(tc==1){
            s=""+s.charAt(index[0]);
        return s;
        }
            
            }
            }
        }
        for(int m=0;m<index.length;m++){
            if(index[m]!=-1){
            if(tc==1 && index[m]!=0 && index[m]!=s.length()-1){
           
                 for(int i=0;i<index.length;i++){
                if(index[i]!=-1)
                sm=match(s.substring(index[i]),sm);
            }
            return sm;
        
        
        }
            }
        }
       if(tc>1){
           s=""+s.charAt(index[0]);
           return s;
       }
        return s;
    }
   
 
    public static void main(String[] args) throws Exception {
     Scanner sc=new Scanner(System.in);
     He1 obj=new He1();
     int t=sc.nextInt();
     while(t>0){
         int l=sc.nextInt();
         int tc=sc.nextInt();
         String s=sc.next();
         System.out.println(obj.trim(s,tc,l));
         
         t--;
     }
    }
    
}
