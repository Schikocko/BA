package example;
import binPackingSolver.*;

import java.util.concurrent.TimeUnit;


import example.MovingCase;
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
		   
		 for (BinPacking pb : myModel.solvedCases)
		 {
			 overallBins = overallBins + pb.binsUsed();
		 }
		 System.out.println("total bins:" + overallBins); 
	   }
	   }


