package binPackingSolver;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
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
//		for (Bin b : Bins)
//		{
//			System.out.println (b.inputObjects()); //TODO make this go into the report file
//			System.out.println(b.load);
//			System.out.println(b.spaceLeft());
//		}
//		System.out.println("success");
	//	System.out.println(searchBin(o.get(4)).name);
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
		/**
		 * creates a new file and prints each bin and all objects contained 
		 * aswell as the current load and the space left
		 */
		public void printBin(){ //TODO reset file if restarted OR add to report

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
			out.println("");
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



		}
