import java.util.*;
import java.io.*;
import java.math.BigInteger;

public class Main
{
    static final long mod=(int)1e9+7;
    static int r=0,b=0,ans=0;
    static boolean[] visited;
    static int[] color;
    static ArrayList<Integer>[] adj;
    public static void main(String[] args) throws Exception
    {
    	FastReader in=new FastReader();
    	PrintWriter pw=new PrintWriter(System.out);
    	int n=in.nextInt();
    	color=new int[n+1];
    	visited=new boolean[n+1];
    	adj=new ArrayList[n+1];
    	for(int i=1;i<=n;i++)
    	{
    		color[i]=in.nextInt();
    		if(color[i]==1)
    			r++;

    		else if(color[i]==2)
    			b++;
    		adj[i]=new ArrayList();
    	}
    	for(int i=1;i<n;i++)
    	{
    		int a=in.nextInt();
    		int b=in.nextInt();
    		adj[a].add(b);
    		adj[b].add(a);	
    	}
    	solve(1);
    	pw.println(ans);
    	pw.flush();
    }

    public static int[] solve(int i)
    {
    	visited[i]=true;
    	int[] a;
    	int r1=0,b1=0;

    	for(int j:adj[i])
    	{
    		if(!visited[j])
    		{
    			a=solve(j);
    			if(a[0]==r && a[1]==0)
    				ans++;
    			else if(a[0]==0 && a[1]==b)
    				ans++;
    			r1+=a[0];
    			b1+=a[1];
    		}
    	}

    	if(color[i]==1)
    		r1++;
    	if(color[i]==2)
    		b1++;
    	return new int[]{r1,b1};
    }
}

class FastReader
{
    BufferedReader br;
    StringTokenizer st;
 
    public FastReader()
    {
        br=new BufferedReader(new InputStreamReader(System.in));
    }
 
    public String next() throws IOException
    {
        if(st==null || !st.hasMoreElements())
        {
            st=new StringTokenizer(br.readLine());
        }
        return st.nextToken();
    }
 
    public int nextInt() throws IOException
    {
        return Integer.parseInt(next());
    }
 
    public long nextLong() throws IOException
    {
        return Long.parseLong(next());
    }
}