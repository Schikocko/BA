package binPackingSolver;
import java.util.ArrayList;

public class NextFit implements BinPacking{

	//input ArrayList with Objects
	int maxCapacity; //maximal Capacity of the bins
	ArrayList<Bin> Bins = new ArrayList<Bin>(); //ArrayList that contains all bins created
	ArrayList<BinObject> binObjects = new ArrayList<BinObject>(); //ArrayList of all objects to be placed into bins

	
	public NextFit (ArrayList<BinObject> o, int binCapacity)
	{
		binObjects = o;
		maxCapacity = binCapacity;
		
	}


	
	public void solveBinPacking ()
	{
		ArrayList<BinObject> o = binObjects;
		int numberOfBins = 0; //Indizes in the bin array
	
		Bins.add(new Bin ("bin"+numberOfBins, maxCapacity));
		
		Bin currentBin = Bins.get(numberOfBins); //creates first bin with index 0
		
		for (int i = 0; i <= o.size() -1 ; i=i+1)
		{
		
			if(currentBin.maxCapacity >= o.get(i).weight + currentBin.load)
				{currentBin.addObject(o.get(i));
				
				//System.out.println(numberOfBins +" "+ currentBin.load);
				}
			else
			{
				numberOfBins = numberOfBins + 1; 
				Bins.add(new Bin ("bin"+numberOfBins, maxCapacity));
				currentBin = Bins.get(numberOfBins);
				currentBin.addObject(o.get(i));
				//System.out.println(numberOfBins +" "+currentBin.load);
				
			}
			
		}
		//going over all initialized bins and prints their contained objects and weight
		for (Bin b : Bins)
		{
			b.inputObjects();
			System.out.println(b.load);
			System.out.println(b.spaceLeft());
		}
		System.out.println("success");
		System.out.println(searchBin(o.get(4)).name);
	}
	
	
	//iterates through the ArrayList of bins and checks for the wanted object, returns the bin if the fbject was found  
	public Bin searchBin(BinObject o)
	{
		Bin result = null;
		
		for (Bin b : Bins)
		{
			if (b.containsObject(o))
				{
				return result = b;
				}
			else{
			result = null;
			}
			
		}
		//System.out.println("searched" + result);
		return result;
		
		
		
	}
	
	// returns number of bins used to solve the problem
		public int binsUsed(){
			return Bins.size();
		}



}
