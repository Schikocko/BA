package example;

import java.util.ArrayList;



import binPackingSolver.*;
import desmoj.core.simulator.*;

public class MovingCase extends SimProcess{
	
	private Moving myModel;
	ArrayList<BinObject> binObjects;
	int cap;
	
	/**
	    * Constructor of a single moving case
	    *
	    *
	    * @param owner das Modell zu dem die Aufnahme gehört
	    * @param name Der Name der Behandlung
	    * @param showInTrace flag to indicate if this process shall produce output
	    *                    for the trace
	    */
	   public MovingCase(Model owner, String name, boolean showInTrace, ArrayList<BinObject> o, int binCapacity){
		   super (owner, name, showInTrace);
			 myModel = (Moving)owner;
			 binObjects = o;
			 cap = binCapacity;

	   }
	
	
	   /**
	    * lifecylce of a moving case
	    *
	    */
	   public void lifeCycle() {

			  BinPacking pb = new NextFit(binObjects, cap);
			  pb.solveBinPacking();	
			  
			  myModel.solvedCases.add(pb);
			  

	   }

}
