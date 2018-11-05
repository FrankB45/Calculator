//Frank Baiata
import java.math.*;
import java.util.ArrayList;
public class Model
{
	double lenOfShaft,shrModulus,shrStrgth,mxTorque,mxAOT,mxShaftSz,minShaftThicc;
	retPair small = new retPair(999.999,999.999);
	retPair large = new retPair(999.999,999.999);
	public Model()
	{
		
	}
	public void ingest(double lenOfShaft, double shrModulus, 
						double shrStrgth, double mxTorque, double mxAOT,
						double mxShaftSz, double minShaftThicc )
	{
		this.lenOfShaft = lenOfShaft;
		this.shrModulus = shrModulus;
		this.shrStrgth = shrStrgth;
		this.mxTorque = mxTorque;
		this.mxAOT = mxAOT;
		this.mxShaftSz = mxShaftSz;
		this.minShaftThicc = minShaftThicc;

		
	}
	
	public double MaxAngleOfTwist(retPair pair)
	{
		return (mxTorque*lenOfShaft)/(JFunc(pair)*shrModulus);
	}
	
	public double JFunc(retPair pair)
	{
		return ((Math.PI/2.0)*(Math.pow((pair.c0/2.0), 4.0)-Math.pow((pair.c1/2.0),4.0)));
	}
	
	public double strgthCalc(retPair pair)
	{
		return (mxTorque*(pair.c0/2.0))/JFunc(pair);
	}
	
	public double CSA(retPair r)
	{
		return (Math.PI/4)*(Math.pow(r.c0,2)-Math.pow(r.c1, 2));
	}
	
	public void calculate()
	{
		
		ArrayList<retPair> fittestH = new ArrayList<retPair>();
		
		for(double i = mxShaftSz;i>0.0;i=i-0.01)
		{
			for(double j = 0.01;j<i-0.01;j=j+0.01)
			{
				if( (MaxAngleOfTwist(new retPair(i,j)) < (mxAOT*(Math.PI/180))) && (strgthCalc(new retPair(i,j)) < shrStrgth) )
				{
					fittestH.add(new retPair(i,j));
					break;
				}
				
			}
		}

		double smallest=Double.MAX_VALUE;
		small = new retPair(999.999,999.999);
		for(retPair r : fittestH)
		{
			if(CSA(r)<smallest)
				small=r;
		}
		
		ArrayList<retPair> fittestW = new ArrayList<retPair>();
		
		for(double i = mxShaftSz;i>0.0;i=i-0.01)
		{
				if( (MaxAngleOfTwist(new retPair(i,0.0)) < (mxAOT*(Math.PI/180))) && (strgthCalc(new retPair(i,0.0)) < shrStrgth) )
				{
					fittestW.add(new retPair(i,0.0));
					break;
				}
				

		}

		double smallestW=Double.MAX_VALUE;
		large = new retPair(999.999,999.999);
		for(retPair r : fittestW)
		{
			if(CSA(r)<smallestW)
				large=r;
		}
		

		
		
	}
	


}

