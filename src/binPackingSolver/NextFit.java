package binPackingSolver;


import java.util.ArrayList;




public class NextFit implements BinPacking{

	//input ArrayList with Objects
	ArrayList<Bin> Bins = new ArrayList<Bin>(); //ArrayList that contains all bins created
	ArrayList<BinObject> binObjects = new ArrayList<BinObject>(); //ArrayList of all objects to be placed into bins


	
	/**
	 * constructor 
	 *
	 *creates a new entity of NextFit, which solves the bin packing problem using the logic of the next fit algorithm
	 *@param ArrayList<BinObject> the list of objects that need to be fit into bins
	 *@param boolean decreasing if set on true the input ArrayList<BinObject> will get sorted decreasing before solving the problem
	 */
	
	public NextFit (ArrayList<BinObject> o, boolean decreasing)
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
		int maxCapacity = cap;
		
		o = binObjects; 

		
		int numberOfBins = 0; //Indizes in the bin array
	
		Bins.add(new Bin ("bin"+numberOfBins, maxCapacity));
		
		Bin currentBin = Bins.get(numberOfBins); //creates first bin with index 0
		
		for (int i = 0; i <= o.size() -1 ; i=i+1)
		{
		
			if(currentBin.getMaxCapacity() >= o.get(i).getWeight() + currentBin.load)
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
	public boolean solveBinPacking (ArrayList<Bin> givenBins)
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
		
			if(currentBin.getMaxCapacity() >= o.get(i).getWeight() + currentBin.load)
				{currentBin.addObject(o.get(i));
				
				//System.out.println(numberOfBins +" "+ currentBin.load);
				}
			else
			{
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
				
		

				//System.out.println(numberOfBins +" "+currentBin.load);
				
			}
			
		}else	{
			BinPackingHandler.setAllBinsFree(this); //sets all bins free again, since the algorithm didn't go through
			return false;
		} //returns false if more bins would be used then available 
		}
		
		return true;
	}
}
