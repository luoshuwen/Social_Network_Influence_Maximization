package greedy;
import java.io.*;

public class GREEDY {
	
	static double[][] tu=new double[10][10];
	static boolean[][] li=new boolean[10][10];
	static boolean[] lit=new boolean[10];
    static boolean[] see=new boolean[10];
    static int[] kk=new int[10];
    static boolean[] kkb=new boolean[10];
    static int kkp=0;

	
static int onelightup()
{
	for(int i=0;i<10;i++)
	{
		for(int j=0;j<10;j++)
		{
			li[i][j]=false;
		}
		lit[i]=false;
	}

    for(int i=0;i<10;i++)
    {
    	if(see[i]==true)
    	   lit[i]=true;
    }

	
	while(true)
	{
		int flag=0;
		for(int i=0;i<10;i++)
		{
			if(lit[i]==true)
			{
				for(int j=0;j<10;j++)
				{
					if(li[i][j]==false&&lit[j]==false&&tu[i][j]!=0)
					{
						if(Math.random()<tu[i][j])
						{
							lit[j]=true;
							li[i][j]=true;
						}
						else
						{
							li[i][j]=true;
						}
						flag=1;
					}
				}
			}
		}
		if(flag==0)
		{
			break;
		}
	}
	
    int su=0;
	for(int i=0;i<10;i++)
	{
		if(lit[i]==true)
			su++;
	}
	return su;
}
	
	
public static void main(String[] args)
{

	
	
    //initialization

	BufferedReader br;
	try {
		br = new BufferedReader(new FileReader("/root/workspace/greedy/src/greedy/filef"));
		String line = "";
		try {
			while((line = br.readLine()) != null){
			    //System.out.println(line);
				if(line.toString().trim().split(" |\\n|\\t")[0]!="")
				{
					int a=Integer.parseInt(line.toString().trim().split(" |\\n|\\t")[0]);
					int b=Integer.parseInt(line.toString().trim().split(" |\\n|\\t")[1]);
					double c=Double.parseDouble(line.toString().trim().split(" |\\n|\\t")[2]);
					//System.out.println(Integer.toString(a)+" "+Integer.toString(b));
					tu[a][b]=c;
				}

			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	} catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
//	for(int i=0;i<10;i++)
//	{
//		for(int j=0;j<10;j++)
//		{
//			if(tu[i][j]!=0)
//			{
//				System.out.println(Integer.toString(i)+" "+Integer.toString(j)+" "+Double.toString(tu[i][j]));
//			}
//		}
//	}
	
//	see[1]=true;
//	see[2]=true;
//	System.out.println(onelightup());
	
	double[] oneli=new double[10];
	
	while(kkp<3)
	{
		for(int i=1;i<=9;i++)
		{
			if(kkb[i]==false)
			{
				for(int j=0;j<10;j++)
				{
					see[j]=false;
				}
				for(int j=0;j<kkp;j++)
				{
					if(kk[j]!=0)
					{
						see[kk[j]]=true;
					}
				}
				
		        if(see[i]==false)
		        {
		        	see[i]=true;
		        	
		    		double sum=0;
		    		for(int k=0;k<10000;k++)
		    		{
		    			sum+=onelightup();
		    		}
		    		
		    		oneli[i]=sum/10000;
		        }
			}
	        
		}
	    
		int tt=0;
		double tts=0;
	    for(int i=0;i<10;i++)
	    {
	    	if(oneli[i]>tts)
	    	{
	    		tt=i;
	    		tts=oneli[i];
	    	}
	    }
	    kk[kkp++]=tt;
	    kkb[tt]=true;
	    for(int i=0;i<10;i++)
	    {
	    	oneli[i]=0;
	    }
	}

	for(int i=0;i<kkp;i++)
		System.out.println(kk[i]);
//    	System.out.println(oneli[i]);
	
}
}
