package binPackingSolver;


import java.util.ArrayList;

public class Bin implements BinPackingBin{ //TODO interface für bins und binobjects machen
	
	ArrayList<BinObject> contains;
	int maxCapacity; 
	int load; //current fill state
	String name;
	boolean used;
	
	
	public Bin (String name, int capacity)
	{
		contains = new ArrayList<BinObject>();
		this.maxCapacity = capacity;
		this.load = 0;
		this.name = name;
		this.used = false;
		
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
	
	/**
	 * this method empties the bin, so that there are no more objects in it
	 * 
	 */
	public void emptyBin ()
	{
		contains.clear();
		load = 0;
		used = false;
	}
	
	
	//Printing a List of all Objects in this current bin
	/**
	 * this method returns all objects and their name and weight parameter 
	 * used for printing all objects of a bin
	 * 
	 * @return returns a string that consists out of the object, the objects name and the weight of the object
	 */
	public String inputObjects()
	{	
		String objects;
		objects = (this.name +": ");
		for (BinObject o : contains){

		objects = objects +" " +(o.name + " " + o.weight);
		}
		return objects;
	}
	

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
	 * @return returns the current weight of the bin as an integer 
	 */
	public int getCurrentWeight()
	{
		return this.load;
	}	
	
	/**
	 * @return returns the maximum capacity of the bin as an integer 
	 */
	public int getMaxCapacity()
	{
		return this.maxCapacity;
	}
	
	/**
	 * @return returns an integer of the free space in the bin 
	 */
	public int getSpaceLeft()
	{
		return maxCapacity - getCurrentWeight();
	}

	/**
	 * @return returns the status of the current bin TRUE if used, FALSE if not used 
	 */
	public boolean getStatus(){
		return used;
	}

	public void setBinUsed(){
		used = true;
	}
	
	public void setBinFree(){
		used = false;
	}
}
