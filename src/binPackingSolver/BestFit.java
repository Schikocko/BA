package binPackingSolver;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class BestFit implements BinPacking{

	//input ArrayList with Objects
	int maxCapacity; //maximal Capacity of the bins
	ArrayList<Bin> Bins = new ArrayList<Bin>(); //ArrayList that contains all bins created
	ArrayList<BinObject> binObjects = new ArrayList<BinObject>(); //ArrayList of all objects to be placed into bins
	boolean decrease = false;

	/**
	 * constructor 
	 *
	 *creates a new entity of BestFit, which solves the bin packing problem using the logic of the best fit algorithm
	 *@param ArrayList<BinObject> the list of objects that need to be fit into bins
	 *@param int binCapacity the maximal capacity of a single bin
	 *@param boolean decreasing if set on true the input ArrayList<BinObject> will get sorted decreasing before solving the problem
	 */
	
	public BestFit (ArrayList<BinObject> o, int binCapacity, boolean decreasing)
	{
		binObjects = o;
		maxCapacity = binCapacity;
		decrease = decreasing;
		
	}

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
		

		
		for (int i = 0; i <= o.size() -1 ; i=i+1)
		{
			ArrayList<Integer> space = new ArrayList<Integer>(); //list that stores all feasible open spaces
			ArrayList<Bin> minBin = new ArrayList<Bin>(); //all feasible bins
			//creates an arraylist with the space left for all bins if object i would be added
		    //iterates over all exciting bins 
			for (Bin b: Bins){
				int x=0;
				x = b.spaceLeft() - o.get(i).weight; 
				if(x >= 0) //if the space in the bin is >=0 AFTER the object is added it means the object fit into the bin
				{
				space.add(x); //add the space left to a list to find the minimum
				minBin.add(b); //add the bin to and according list, with matching index 
				}
			//do nothing if the object would not fit in the bin
			}
			
			if(space.isEmpty()){ // if the space list is empty, it means there are no bin, in which the object fitted -> create a new bin and put the ibject there
				numberOfBins = numberOfBins + 1; 
				Bins.add(new Bin ("bin"+numberOfBins, maxCapacity));
				currentBin = Bins.get(numberOfBins);
				currentBin.addObject(o.get(i));
				//System.out.println(numberOfBins +" "+currentBin.load);
				
			}else{//if there is atleast one element in the space list, fit object fits in a exciting bin
				int minIndex = space.indexOf(Collections.min(space)); //find the bin with the least space left, after the object would be added
				minBin.get(minIndex).addObject(o.get(i)); // but the object in the according bin
			}
			
			
		}

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
		public void printBin(String name){ //TODO reset file if restarted OR add to report

	    	try {
	    		 
	  	      File file = new File("./bin_report.txt");
	  	      
	  	      if (file.createNewFile()){
	  	      }else{
	  	    
	  	      }
	  	      
	      	} catch (IOException e) {
	  	      e.printStackTrace();
	  	}
	    	
	    	try{
	    	PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("bin_report.txt", true)));
			out.println("");
			out.println(name);
			for (Bin b : Bins)
			{
				out.println (b.inputObjects());
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
		 * @return return a new sorted ArrayList<BinObject> that can be used as input for the solveBinPacking() method  
		 */
		public ArrayList<BinObject> sortDecreasing(ArrayList<BinObject> l){
		
			Collections.sort(l, new Comparator<BinObject>() {
				public int compare (BinObject o1, BinObject o2){
					return o2.weight - o1.weight;
				}
			});
//			for (BinObject o : l) // outputs the new sorted objects 
//			{
//				System.out.println (o.weight); 
//			}
			return l;
		}


		}