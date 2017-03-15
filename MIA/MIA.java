package mia;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class MIA {
	
static	double[][] tu=new double[2500][2500];
static int[][] pp=new int[2500][2500];
static boolean[][] de=new boolean[2500][2500];
static	boolean[] ff=new boolean[2500];
static	boolean[] ss=new boolean[100];
static	int ssp=0,kkk=4;
static	int nn=0,mm=0;
static double theta=Math.abs(Math.log(0.05));//~~3
static int flag2=0;
static int[][] tree=new int[2500][2500];
	
public static double ap(int num,int depth)
{

	if(ss[num]==true)
	{
		return 1;
	}
	
	if(depth!=1)
	{
		int flag1=0;
		for(int i=1;i<=nn;i++)
		{
			if(tree[num][i]==1)
			{
				flag1=1;
			}
		}
		if(flag1==0)//leaf
		{
			return 0;
		}
	}
	
	int[] shouji=new int[2500];
	int sjp=0;
	for(int i=1;i<=nn;i++)
	{
		if(tu[i][num]<theta&&i!=num)
		{
			shouji[sjp++]=i;
		}
	}
	
	if(depth==1)
	{
		for(int i=0;i<=nn;i++)
			for(int j=0;j<=nn;j++)
				tree[i][j]=0;

//		for(int i=0;i<sjp;i++)
//			for(int j=0;j<sjp;j++)
//				if(shouji[i]!=shouji[j]&&de[shouji[i]][shouji[j]]==true)
//					tree[shouji[i]][shouji[j]]=1;
		
		for(int i=0;i<sjp;i++)
		{
			int td=shouji[i];
			while(true)
			{
				if(pp[td][num]==num)
				{
					tree[num][td]=1;
					break;
				}
				else
				{
					tree[pp[td][num]][td]=1;
					td=pp[td][num];
				}
			}
		}
		
	}
	
	double app=0;
	
////	boolean flag0=false;
//	int[] shouji=new int[2500];
//	int sjp=0;
//	for(int i=1;i<=nn;i++)
//	{
//		if(tu[i][num]<theta&&i!=num)
//		{
////			flag0=true;
//			shouji[sjp++]=i;
//		}
//	}
//	
//	if(flag0==false)
//		return 0;
	double temp0=1;
    for(int i=1;i<=nn;i++)
		{
			if(tree[num][i]==1)
			{
				temp0*=(1-ap(i,depth+1)*(Math.pow(Math.E, tu[i][num]*-1)));
			}
		}
	app=1-temp0;
	return app;
}
	
public static void main(String[] args)
{

	
	
	
	for(int i=0;i<2500;i++)
	{
		for(int j=0;j<2500;j++)
		{
			tu[i][j]=5;
			if(i==j)
				tu[i][j]=0;
		}
	}
	for(int i=1;i<2500;i++)
		for(int j=1;j<2500;j++)
			pp[i][j]=j;

	BufferedReader br;
	try {
		br = new BufferedReader(new FileReader("/root/workspace/mia/src/filef"));
		String line = "";
		try {
			while((line = br.readLine()) != null){
			    //System.out.println(line);
				if(line.toString().trim().split(" |\\n|\\t")[0]!="")
				{
					int a=Integer.parseInt(line.toString().trim().split(" |\\n|\\t")[0]);
					int b=Integer.parseInt(line.toString().trim().split(" |\\n|\\t")[1]);
					double c=  Math.abs(Math.log(Double.parseDouble(line.toString().trim().split(" |\\n|\\t")[2])));
					//System.out.println(Integer.toString(a)+" "+Integer.toString(b));
					tu[a][b]=c;
					mm++;
					ff[a]=true;
					ff[b]=true;
					de[a][b]=true;
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
	
	for(int i=0;i<2500;i++)
		if(ff[i]==true)
			nn++;
	//floyd
	for(int k=1;k<=nn;k++)
		for(int i=1;i<=nn;i++)
			for(int j=1;j<=nn;j++)
				if(tu[i][j]>tu[i][k]+tu[k][j])
				{
					tu[i][j]=tu[i][k]+tu[k][j];
					pp[i][j]=pp[i][k];
				}

	
//	System.out.println(nn);
//	for(int i=1;i<=nn;i++)
//	{
//		String tt="";
//		for(int j=1;j<=nn;j++)
//			tt=tt+Integer.toString(pp[i][j])+"\t";
////			tt=tt+Double.toString(  (double)Math.round(tu[i][j]*100)/100  )+"\t";
//		System.out.println(tt);
//	}
	
	//digui
	
//	nn=9;
	
  while(ssp<kkk)
  {
	  //caculate sigmaSmax
	  double sigmaS=0;
	  int sigmaSp=0;
	  //select a seed
	  for(int i=1;i<=nn;i++)//for every node,find who has the biggest increment
	  {
		  if(ss[i]!=true)//is not seed
		  {
			  //
			  ss[i]=true;
			  
			  //caculate sigmaS
			  double sums=0;
			  for(int ii=1;ii<=nn;ii++)
			  {
				  sums+=ap(ii,1);
//				  if(i==1)
//				  {
////					  System.out.println(ap(ii,1));
//				  }
			  }			  
			  
//			  System.out.println(sums);
			  
			  if(sums>sigmaS)
			  {
				  sigmaS=sums;
				  sigmaSp=i;
			  }
			  
			  ss[i]=false;
		  }
	  }
	  ss[sigmaSp]=true;
	  ssp++;
  }
	
  for(int i=0;i<100;i++)
  {
	  if(ss[i]==true)
	  {
		  System.out.println(i);
	  }
  }

}
}
