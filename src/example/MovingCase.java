package example;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import binPackingSolver.*;
import desmoj.core.simulator.*;

public class MovingCase extends SimProcess{
	
	private Moving myModel;
	ArrayList<BinObject> binObjects;
	int cap;
	int neededTrucks;
	double time;
	
	/**
	    * Constructor of a single moving case
	    *
	    *
	    * @param owner das Modell zu dem die Aufnahme gehört
	    * @param name Der Name der Behandlung
	    * @param showInTrace flag to indicate if this process shall produce output
	    *                    for the trace
	    */
	   public MovingCase(Model owner, String name, boolean showInTrace, ArrayList<BinObject> o, int binCapacity, double caseTime){
		   super (owner, name, showInTrace);
			 myModel = (Moving)owner;
			 binObjects = o;

			 cap = binCapacity;
			 //time needed to complete this order
			 time = caseTime;

	   }
	
	
	   /**
	    * lifecylce of a moving case
	    *
	    */
	   public void lifeCycle() {
		   
		   myModel.waitingCaseQueue.insert(this);
			  BinPacking pb = new FirstFit(binObjects, cap);
			  pb.solveBinPacking();	
			  pb.printBin(this.getName());
			  neededTrucks = pb.binsUsed();

		   
		   if(!myModel.waitingCompanyQueue.isEmpty())
		   {
			   MovingCompany company = myModel.waitingCompanyQueue.first();
			   myModel.waitingCompanyQueue.remove(company);
			   company.activateAfter(this);
		   }
		   
		   passivate();


			  myModel.solvedCases.add(pb);
			  myModel.useTrucks( neededTrucks );
			  sendTraceNote(this +" "+ "uses"+" "+ neededTrucks + " "+ "Trucks");
			  sendTraceNote(myModel.availableTrucks + " " + "Trucks"+ " "+ "left");
			  //this cases hold X trucks for time 
			  hold(new TimeSpan(time, TimeUnit.MINUTES));
			  myModel.setTrucksFree(neededTrucks);
			  
			  sendTraceNote(this +" "+ "releases"+" "+ neededTrucks + " "+ "Trucks");
			  sendTraceNote(myModel.availableTrucks + " " + "Trucks"+ " "+ "available");
			  
			  //check if the company is waiting for new trucks, if let it recheck the case if enough trucks are available 
			   if(!myModel.waitingForTrucksQueue.isEmpty())
			   {
				   MovingCompany company = myModel.waitingForTrucksQueue.first();
				   myModel.waitingForTrucksQueue.remove(company);
				   company.activateAfter(this);
			   }

			  

	   }

}
