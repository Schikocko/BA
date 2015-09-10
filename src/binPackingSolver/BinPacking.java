package binPackingSolver;

import java.util.ArrayList;

public interface BinPacking {
	
	
	/**
	    * This method has to be called to actually solve the problem with the given parameter in the constructor
	    *
	    */
	public void solveBinPacking ();
	
	/**
	 	* This method returns the bin which contains the searched BinObject
	 	* 
	    * @param BinObject o object that has to be found 
	    * 
	    */
	
	public Bin searchBin(BinObject o);
	
	
	 /**
	    * this method return an int that tells the amount of bins used to place all objects
	    */
	public int binsUsed();
	
	
	public void printBin(String name);
	
	public ArrayList<BinObject> sortDecreasing (ArrayList<BinObject> l);
}
