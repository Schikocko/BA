package example;
import java.util.concurrent.TimeUnit;

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
		   while(true){
		   
		   if (myModel.waitingCaseQueue.isEmpty()){
			   myModel.waitingCompanyQueue.insert(this);
			   passivate();
			   
		   }else
		   {
			   
			   MovingCase nextCase = myModel.waitingCaseQueue.first();
			   if(myModel.getAvailableTrucks() - nextCase.neededTrucks >= 0 )
			   {
			   myModel.waitingCaseQueue.remove(nextCase);
			   nextCase.activateAfter(this);
			   hold(new TimeSpan(0.01, TimeUnit.MINUTES)); //waiting for available truck to refresh, since its done in the MovingCase
			   }else
			   {
			   sendTraceNote("not enough trucks!");
			   sendTraceNote(nextCase.neededTrucks +" " +"trucks " + "needed" + " "+ "but" +" " + "only" + myModel.availableTrucks + " " + "trucks "+ "available");
			   myModel.waitingForTrucksQueue.insert(this);
			   passivate(); //wait for a case to terminate, then check again if enough trucks are available for the case 
			   }
		   }
		   }
		   
		   
		   
		   
		   
		   
		   
		   
		   
		   
//		   int overallBins= 0;
//		   //iterates over the global variable that stores all solved binpacking problems and "returns" the overall number of bins needed to store all objects 
		 //for (BinPacking pb : myModel.solvedCases)
		 //{
//			 overallBins = overallBins + pb.binsUsed();
///		 }
//		 System.out.println("total bins:" + overallBins); 
		   }
	   }


