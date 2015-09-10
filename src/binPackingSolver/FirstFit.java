package binPackingSolver;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class FirstFit implements BinPacking {
	
	int maxCapacity; //maximal Capacity of the bins
	ArrayList<Bin> Bins = new ArrayList<Bin>(); //ArrayList that contains all bins created
	ArrayList<BinObject> binObjects = new ArrayList<BinObject>(); //ArrayList of all objects to be placed into bins
	boolean decrease = false;

	/**
	 * constructor 
	 *
	 *creates a new entity of FirstFit, which solves the bin packing problem using the logic of the first fit algorithm
	 *@param ArrayList<BinObject> the list of objects that need to be fit into bins
	 *@param int binCapacity the maximal capacity of a single bin	
	 *@param boolean decreasing if set on true the input ArrayList<BinObject> will get sorted decreasing before solving the problem
	 */
	public FirstFit (ArrayList<BinObject> o, int binCapacity, boolean decreasing)
	{
		binObjects = o;
		maxCapacity = binCapacity;
		decrease = decreasing;
		
	}
	
	//TODO change void to ArrayList<Bin>
	/**
	 * this method has to be called to solve this actual case of a bin packing problem with the given parameter in the constructor  
	 */
	public void solveBinPacking ()
	{
		ArrayList<BinObject> o;
		
		if(decrease) // if the algorithm should be decreasing, the input list will be sorted
		{
			o = sortDecreasing(binObjects);
		}else // if it shouldn't be decreasing, the list is given unchanged to the solver
		{
			o = binObjects; 
		}
		
		int numberOfBins = 0; //Indizes in the bin array
	
		Bins.add(new Bin ("bin"+numberOfBins, maxCapacity));
		
		Bin currentBin = Bins.get(numberOfBins); //creates first bin with index 0
		
		for (int i = 0; i <= o.size() -1 ; i=i+1) // takes the first object of the set 
		{
			boolean matched = false; //variable to stop as soon, as it fits into a bin
			
			for (int j = 0; j <= numberOfBins; j++) //iterates over all existing bins
			{
		
				currentBin = Bins.get(j);

				if(matched == false) //if not fit into a bin already 
				{
					if(currentBin.maxCapacity >= o.get(i).weight + currentBin.load) //test if it fits in the current bin j
						{currentBin.addObject(o.get(i));
						matched = true;  //if it does, set matched true, so we can proceed with the next item
						
						//System.out.println(numberOfBins +" "+ currentBin.load);
						}else 
					matched = false;  // if no bin fits, matched stays false
				}
			
			}
				
					
					if (matched == false) // if no bin in the current set fits, we create a new bin and but the current object into it
					{
						numberOfBins = numberOfBins + 1; 
						Bins.add(new Bin ("bin"+numberOfBins, maxCapacity));
						currentBin = Bins.get(numberOfBins);
						currentBin.addObject(o.get(i));
						//System.out.println(numberOfBins +" "+currentBin.load);	
					}
		
		}
		
		//TODO real return Bins
		
		
		//going over all initialized bins and prints their contained objects and weight
//		for (Bin b : Bins)
//		{
//			System.out.println (b.inputObjects()); //TODO make this go into the report file
//			System.out.println(b.load);
//			System.out.println(b.spaceLeft());
//		}
//		System.out.println("success");
////		System.out.println(searchBin(o.get(4)).name);
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
	
	
	/**
	 * creates a new file and prints each bin and all objects contained 
	 * aswell as the current load and the space left
	 */
	public void printBin(String name){//TODO reset file if restarted OR add to report


    	
    	try{
    	PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("bin_report.txt", true)));
		out.println("");
		out.println(name);
		for (Bin b : Bins)
		{
			out.println (b.inputObjects());//TODO make this go into the report file
			out.println("load: " + b.load);
			out.println("space left: " + b.spaceLeft());
		}

    	out.close();
    	}catch (IOException e) {
  	      e.printStackTrace();
  	}
	}
	
	
	/**
	 * takes the given input and sorts in decreasing by objects weight, starting with the highest to the lowest 
	 * @param ArrayList<BinObject> l ArrayList of the given objects for the problem
	 */
	public ArrayList<BinObject> sortDecreasing(ArrayList<BinObject> l){
	
		Collections.sort(l, new Comparator<BinObject>() {
			public int compare (BinObject o1, BinObject o2){
				return o2.weight - o1.weight;
			}
		});
//		for (BinObject o : l) // outputs the new sorted objects 
//		{
//			System.out.println (o.weight); 
//		}
		return l;
	}


}
