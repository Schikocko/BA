package binPackingSolver;

import java.util.ArrayList;

public interface BinPacking {
	
	
	/**
	    * This method has to be called to actually solve the problem with the given parameter in the constructor
	    * this method creates new bins with the size of int cap and puts the objects given in the constructor into them
	    *@param int cap the maximal capacity of a single bin, these bins get newly created for this bin packing case
	    */
	public void solveBinPacking (int cap);

	/**
    * This method has to be called to actually solve the problem with the given parameter in the constructor
    * this method puts the objects given in the constructor into the given list of bins  
    * @param ArrayList<Bin> the global list of Bins in which the, in the constructor given objects, shall be sorted
    */
	public boolean solveBinPacking (ArrayList<Bin> givenBins);
	
	 /**
	    * this method returns the ArrayList of all used bins and their objects  
	    * @return ArrayList<Bin> with all bins used to solve this bin packing problem. these bins preference the objects they contain  
	    */
	public ArrayList<Bin> getBins();
	
}
