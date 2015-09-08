package binPackingSolver;

import java.util.ArrayList;

public class Bin {
	
	ArrayList<BinObject> contains;
	int maxCapacity; 
	int load; //current fill state
	String name;
	
	
	public Bin (String name, int capacity)
	{
		contains = new ArrayList<BinObject>();
		this.maxCapacity = capacity;
		this.load = 0;
		this.name = name;
		
	}
	
	/**
	 * this method adds an objects to the bin
	 * 
	 * @param BinObject i has to be a BinObject that will be added to the bin, if it fits
	 * 
	 * @return returns true is successfully added, if it didn't fit due to the capacity it returns false 
	 */
	public boolean addObject (BinObject i)
	{
		if (load + i.weight <= maxCapacity)
			{	contains.add(i);
				load = load + i.weight;
				return true;
			}
		return false;
		
	}
	
	
	//Printing a List of all Objects in this current bin
	//TODO show in report
	public void inputObjects()
	{	
		System.out.println(this.name +": ");
		for (BinObject o : contains)
			
		
		System.out.println(o.name + " " + o.weight);
	}
	
//	//sends trace notes of all bins and its contains TODO
//		public void traceBin(boolean a)
//		{	
//			if(a){
//			sendTraceNote(this.name +": ");
//			for (BinObject o : contains)
//				
//			
//			sendTraceNote(o.name + " " + o.weight);
///		}
//		}
	/**
	 * this method searches through all objects in the bin and returns true if it is in this bin
	 * 
	 * @param BinObject i has to the BinObject that is searched for 
	 * 
	 * @return returns true if found in this bin, else false 
	 */	
	public boolean containsObject(BinObject o)
	{
		return contains.contains(o); //searching through all Objects in this bin to find o, returning TRUE if found, FALSE if not
	}

	/**
	 * 
	 * 
	 * @return returns the current weight of the bin as an integer 
	 */
	public int currentWeight()
	{
		return this.load;
	}
	
	/**
	 * 
	 * @return returns an integer of the free Space in the bin 
	 */
	public int spaceLeft()
	{
		return maxCapacity - currentWeight();
	}
//TODO Output
	
}
