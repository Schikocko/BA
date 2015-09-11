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
	
	/**
	 * writes in the file "bin_report and prints each bin with all objects contained and their weight  
	 * aswell as the current load and the space left
	 * 
	 * @param Sting name give the name of the binbacking problem, to identify it
	 */
	public void printBin(String name);
	
	
	/**
	 * takes the given input and sorts in decreasing by objects weight, starting with the highest to the lowest 
	 * @param ArrayList<BinObject> l ArrayList of the given objects for the problem
	 * @return return a new sorted ArrayList<BinObject> that can be used as input for the solveBinPacking() method  
	 */
	public ArrayList<BinObject> sortDecreasing (ArrayList<BinObject> l);
}
