import java.util.*;
import java.io.*;
import java.math.BigInteger;

public class Main
{
    static final long mod=(int)1e9+7;
    static int[][] q;
    static long[] q1;
    static Set<Integer> set;
    public static void main(String[] args) throws Exception
    {
    	FastReader in=new FastReader();
    	PrintWriter pw=new PrintWriter(System.out);
    	int test=in.nextInt();
    	while(test-->0)
    	{
    		int n=in.nextInt();
    		q=new int[n][n+1];
    		q1=new long[n];
    		for(int i=0;i<n;i++)
    		{
    			long a=0;
    			for(int j=1;j<n;j++)
    			{
    				q[i][j]=in.nextInt();
    				a=a*10+q[i][j];
    				a%=mod;
    			}
    			q1[i]=a;
    		}
    		int pos=0;
    		for(int i=1;i<=n;i++)
    		{
    			int a=0,b=0;
    			for(int j=0;j<n;j++)
    			{
    				if(q[j][i-1]==n-1)
    					a++;
    				else if(q[j][i]==n-1)
    					b++;
    			}
    			if(a>=i-1 && b>=n-i)
    			{
    				pos=i;
    				break;
    			}
    		}
    		// System.out.println(pos);
    		for(int i=0;i<n;i++)
    		{
    			int[] p=new int[n+1];
    			for(int j=1;j<pos;j++)
    				p[j]=q[i][j];
    			p[pos]=n;
    			for(int j=pos+1;j<=n;j++)
    				p[j]=q[i][j-1];
    			boolean f=true;
    			set=new HashSet();
    			for(int j=1;j<=n;j++)
    			{
    				int[] p1=new int[n];
    				int k=1;
    				while(k<n && p[k]!=j)
    				{
    					p1[k]=p[k]>=j?p[k]-1:p[k];
    					k++;
    				}
    				while(k<n)
    				{
    					p1[k]=p[k+1]>=j?p[k+1]-1:p[k+1];
    					k++;
    				}
    				// System.out.println(Arrays.toString(p1)+" "+i);
    				if(!match(p1))
    				{
    					f=false;
    					break;
    				}
    			}
    			if(!f)
    				continue;
    			for(int j=1;j<=n;j++)
    				pw.print(p[j]+" ");
    			break;
    		}
    		pw.println();
    	}
    	pw.flush();
    }

    public static boolean match(int[] p1)
    {
    	long b=0;
    	for(int i=1;i<p1.length;i++)
    	{
    		b=b*10+p1[i];
    		b%=mod;
    	}
    	boolean f=false;
    	for(int i=0;i<q1.length;i++)
    	{
    		if(q1[i]!=b || set.contains(i))
    			continue;
    		f=true;
    		for(int j=1;j<q[i].length-1;j++)
    		{
    			if(p1[j]!=q[i][j])
    			{
    				f=false;
    				break;
    			}
    		}
    		if(f)
    		{
    			set.add(i);
    			break;
    		}
    	}

    	return f;
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