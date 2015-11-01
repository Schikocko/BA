package binPackingSolver;

public interface BinPackingBin {
	
	/**
	 * this method adds an objects to the bin
	 * 
	 * @param BinObject i has to be a BinObject that will be added to the bin, if it fits
	 * 
	 * @return returns true is successfully added, if it didn't fit due to the capacity it returns false 
	 */
	public boolean addObject (BinObject i);
	
	/**
	 * this method empties the bin, so that there are no more objects in it
	 * 
	 */
	public void emptyBin ();
	
	/**
	 * this method returns all objects and their name and weight parameter 
	 * used for printing all objects of a bin
	 * 
	 * @return returns a string that consists out of the object, the objects name and the weight of the object
	 */
	public String inputObjects();
	
	/**
	 * this method searches through all objects in the bin and returns true if it is in this bin
	 * 
	 * @param BinObject i has to the BinObject that is searched for 
	 * 
	 * @return returns true if found in this bin, else false 
	 */	
	public boolean containsObject(BinObject o);
	
	/**
	 * @return returns the current weight of the bin as an integer 
	 */
	public int getCurrentWeight();
	
	/**
	 * @return returns the maximum capacity of the bin as an integer 
	 */
	public int getMaxCapacity();
	
	/**
	 * @return returns an integer of the free space in the bin 
	 */
	public int getSpaceLeft();
	
	/**
	 * only used for global bin lists
	 * @return returns the status of the current bin TRUE if used, FALSE if not used 
	 */
	public boolean getStatus();
	
	/**
	 * changes the status parameter of the bin
	 * only used for global bin lists
	 */
	public void setBinUsed();
	
	/**
	 * changes the status parameter of the bin
	 * only used for global bin lists
	 */
	public void setBinFree();

}
