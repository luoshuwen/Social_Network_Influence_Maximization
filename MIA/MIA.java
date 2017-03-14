package mia;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class MIA {
public static void main(String[] args)
{
	double[][] tu=new double[2500][2500];
	for(int i=0;i<2500;i++)
	{
		for(int j=0;j<2500;j++)
		{
			tu[i][j]=5;
		}
	}
	int nn=0;
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
					nn++;
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
	
	//floyd
	for(int k=1;k<=nn;k++)
		for(int i=1;i<=nn;i++)
			for(int j=1;j<=nn;j++)
				if(tu[i][j]>tu[i][k]+tu[k][j])
					tu[i][j]=tu[i][k]+tu[k][j];
	
    	
//theta = 0.05  <=3
//    System.out.println( Math.pow(Math.E,-2.65)  );
//	System.out.println(Math.log(0.05));
//	System.out.println( Math.pow(Math.E, tu[14][12]*-1)        );
	
	
	

}
}
