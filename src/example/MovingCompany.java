package example;
import binPackingSolver.*;


import desmoj.core.simulator.*;

public class MovingCompany extends SimProcess {
	
	private Moving myModel;
	
	/**
	    * Constructor of the MovingCompany
	    *
	    *
	    * @param owner das Modell zu dem die Aufnahme gehört
	    * @param name of the company
	    * @param showInTrace flag to indicate if this process shall produce output
	    *                    for the trace
	    */
	public MovingCompany(Model owner, String name, boolean showInTrace)
	{
		 super (owner, name, showInTrace);
		 myModel = (Moving)owner;
	}
	
	 /**
	    * lifecycle of the moving company
	    *
	    */
	   public void lifeCycle() {
		   int overallBins= 0;
		   //iterates over the global variable that stores all solved binpacking problems and "returns" the overall number of bins needed to store all objects 
		 for (BinPacking pb : myModel.solvedCases)
		 {
			 overallBins = overallBins + pb.binsUsed();
		 }
		 System.out.println("total bins:" + overallBins); 
	   }
	   }


