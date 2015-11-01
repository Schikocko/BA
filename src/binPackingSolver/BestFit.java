package binPackingSolver;


import java.util.ArrayList;
import java.util.Collections;


public class BestFit implements BinPacking{

	//input ArrayList with Objects
	ArrayList<Bin> Bins = new ArrayList<Bin>(); //ArrayList that contains all bins created
	ArrayList<BinObject> binObjects = new ArrayList<BinObject>(); //ArrayList of all objects to be placed into bins

	/**
	 * constructor 
	 *
	 *creates a new entity of BestFit, which solves the bin packing problem using the logic of the best fit algorithm
	 *@param ArrayList<BinObject> the list of objects that need to be fit into bins
	 *@param int binCapacity the maximal capacity of a single bin
	 *@param boolean decreasing if set on true the input ArrayList<BinObject> will get sorted decreasing before solving the problem
	 */
	
	public BestFit (ArrayList<BinObject> o, boolean decreasing)
	{
		if(decreasing) // if the algorithm should be decreasing, the input list will be sorted
		{
			binObjects = BinPackingHandler.sortDecreasing(o);
		}else // if it shouldn't be decreasing, the list is given unchanged to the solver
		{
			binObjects = o; 
		}
		
	}

	/**
	 * this method has to be called to solve this actual case of a bin packing problem with the given parameter in the constructor  
	 *@param int cap the maximal capacity of a single bin, these bins get newly created for this bin packing case
	 */
	
	public void solveBinPacking (int cap)
	{
		ArrayList<BinObject> o;
		int maxCapacity =cap;
		

		o = binObjects; 

		
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
				x = b.getSpaceLeft() - o.get(i).getWeight(); 
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
				
			}else{//if there is atleast one element in the space list, fit object fits in a exciting bin
				int minIndex = space.indexOf(Collections.min(space)); //find the bin with the least space left, after the object would be added
				minBin.get(minIndex).addObject(o.get(i)); // but the object in the according bin
			}
			
			
		}

	}
	
	/**
	 * this method returns the solved ArrayList of Bins 
	 * @return ArrayList of Bins
	 */
	public ArrayList<Bin> getBins(){
		return Bins;
	}

	
	/**
	 * this method has to be called to solve this actual case of a bin packing problem with the given parameter in the constructor  
	 * 
	 * @param ArrayList<Bin> givenBins the global list of bins which all solver take their bins out
	 * @return returns true if the algorithm was successful or false if either, there are not enough bins or the objects are too big for the bins left
	 */
	
	public boolean solveBinPacking (ArrayList<Bin>  givenBins)
	{
		ArrayList<BinObject> o;
		ArrayList<Bin> availableBins = new ArrayList<Bin>(); //ArrayList for all available Bins
		

		o = binObjects; 

		
		for (Bin b : givenBins){ //adds all available bins to an extra ArrayList
			if(!b.getStatus()){
				availableBins.add(b);
			}
		}
		
		int numberOfBins = 0; //Indizes in the bin array
	
		Bin currentBin; //creates first bin with index 0
		
		boolean firstBin = false;
		//puts the first bin in which the first item fits into the Bins list and starts the algorithm
		//searches the first new bin it which the current item fits 
		for (int j = 0; j <= availableBins.size() -1 ; j=j+1){//checks for all available bins until it finds a fitting one
			
			if(availableBins.get(j).getMaxCapacity() >= o.get(0).getWeight() && firstBin == false)//stops after it found a fitting bin
			{ 
				Bins.add(availableBins.get(j));
				availableBins.get(j).setBinUsed();//first fitting bin of the available will be used
				availableBins.remove(j);
				firstBin= true;
			}		
		}
		if (firstBin == false) //if no fitting bin is found, the algorithm stops and returns false
			return false;
		
		currentBin = Bins.get(numberOfBins);
		currentBin.addObject(o.get(0));
		
		for (int i = 0; i <= o.size() -1 ; i=i+1)
		{
			if(!availableBins.isEmpty()){ //as long as the number of used bins is <= the number of available bins do the algorithm
			
			ArrayList<Integer> space = new ArrayList<Integer>(); //list that stores all feasible open spaces
			ArrayList<Bin> minBin = new ArrayList<Bin>(); //all feasible bins
			//creates an arraylist with the space left for all bins if object i would be added
		    //iterates over all exciting bins 
			for (Bin b: Bins){
				
				int x=0;
				x = b.getSpaceLeft() - o.get(i).getWeight();
				
				if(x >= 0) //if the space in the bin is >=0 AFTER the object is added it means the object fit into the bin
				{
					space.add(x); //add the space left to a list to find the minimum
					minBin.add(b); //add the bin to and according list, with matching index 
				}
			//do nothing if the object would not fit in the bin
			}
			
			if(space.isEmpty()){ // if the space list is empty, it means there are no bin, in which the object fitted -> create a new bin and put the ibject there
				boolean newBin = false;
				//searches the first new bin it which the current item fits 
				for (int j = 0; j <= availableBins.size() -1 ; j=j+1){//checks for all available bins until it finds a fitting one
					
					if(availableBins.get(j).getMaxCapacity() >= o.get(i).getWeight() && newBin == false)//stops after it found a fitting bin
					{ 
						numberOfBins = numberOfBins + 1;
						Bins.add(availableBins.get(j)); //adds the fitting bin to the list of bins used for this case
						availableBins.get(j).setBinUsed();//sets this bin globally on used status, it can't be used by other bin packing until it's freed again 
						currentBin = Bins.get(numberOfBins);
						currentBin.addObject(o.get(i));
						availableBins.remove(j); //removes the used bin from the available bin list
						newBin= true;
					}
						
						
				}
				if (newBin == false)
				{
					BinPackingHandler.setAllBinsFree(this);//sets all bins free again, since the algorithm didn't go through
					return false;
				}
				
				
			}else{//if there is atleast one element in the space list, fit object fits in a exciting bin
				int minIndex = space.indexOf(Collections.min(space)); //find the bin with the least space left, after the object would be added
				minBin.get(minIndex).addObject(o.get(i)); // but the object in the according bin
			}
			
			}else// if there are not enough bin available the algorithm stops and resets already used bins 
			{
				BinPackingHandler.setAllBinsFree(this);
				return false;
			}
		}
		return true;
	}
}