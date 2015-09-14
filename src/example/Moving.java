package example;


import java.util.ArrayList;

import desmoj.core.dist.ContDistExponential;
import desmoj.core.simulator.*;
import java.util.concurrent.TimeUnit;


import binPackingSolver.*;






public class Moving extends Model {

	/**
	    * Process constructor.
	    *
	    * Creates a new Process model via calling
	    * the constructor of the superclass.
	    *
	    * @param owner the model this model is part of (set to null when there is 
	    *              no such model)
	    * @param modelName this model's name
	    * @param showInReport flag to indicate if this model shall produce output 
	    *                     to the report file
	    * @param showInTrace flag to indicate if this model shall produce output 
	    *                    to the trace file
	    */
	   public Moving(Model owner, String modelName, boolean showInReport, boolean showInTrace) 
	   {
	      super(owner, modelName, showInReport, showInTrace);
	   }
	   
	   /**
	    * Model parameter: number of moving trucks available for the company 
	    */
	   public int availableTrucks = 50;
	   
	   /**
	    * Model parameter: the maximum capacity of a truck
	    */
	   public int truckSize = 50;
	   
	   /**
	    * Model parameter: number of moving cases
	    */
	   protected static int NUM_COMP = 1;	  
	   
	   /**
	    * Model parameter: number of moving cases
	    */
	   protected static int NUM_CREATE = 1;
	      
	   
	   /**
	    * time needed to complete a case
	    */
	   private desmoj.core.dist.ContDistExponential orderCompletionTime;
	   
	   /**
	    * time between incoming corders
	    */
	   private desmoj.core.dist.ContDistExponential newOrderTime;
	   
	   /**
	    * Queue for movingCase waiting for enough trucks to get available 
	    */
	   protected desmoj.core.simulator.ProcessQueue<MovingCase> waitingCaseQueue;
	   
	   /**
	    * Queue for MovingCompany waiting for new cases
	    */
	   protected desmoj.core.simulator.ProcessQueue<MovingCompany> waitingCompanyQueue;
	   
	   /**
	    * Queue for MovingCompany waiting for trucks to get released from cases
	    */
	   protected desmoj.core.simulator.ProcessQueue<MovingCompany> waitingForTrucksQueue;
	   
	   /**
	    * a list in which all cases are placed, after the job is finish (for future notice)
	    */   
	   protected ArrayList<BinPacking> solvedCases;
	
	   /**
	    * 
	    * @return the max capacity of a truck
	    */
	   
		public int getTruckSize(){
			return truckSize; 
		}
	   
	   /**
	    * 
	    * @return gives number of available trucks
	    */
	   
		public int getAvailableTrucks(){
			return availableTrucks; 
		}
	   
	   /**
	    * 
	    * reduces number of available trucks
	    */
	   
		public void useTrucks(int i){
			availableTrucks = availableTrucks - i;
		}
		
	   /**
	    * 
	    * increases number of available trucks
	    */
	   
		public void setTrucksFree(int i){
			availableTrucks = availableTrucks + i;
		}
	   
	   /**
	    * 
	    * @return double returns a sample of the random time needed for an single order
	    */
	   public double getOrderCompletionTime() 
	   {
	      return orderCompletionTime.sample();
	   }
	   
	   /**
	    * 
	    * @return double returns a sample of the random time needed for an single order
	    */
	   public double getNewOrderTime() 
	   {
	      return newOrderTime.sample();
	   }
	   
	   /**
	    * Returns a description of the model to be used in the report.
	    * @return model description as a string
	    */
	   public String description() {
		   return "Einfaches Modell eines Umzugs zum testen der BinPacking Alogrithmen";
	   }
	   
	   
	   /**
	    * Activates dynamic model components (simulation processes).
	    *
	    * This method is used to place all events or processes on the
	    * internal event list of the simulator which are necessary to start
	    * the simulation.
	    *
	    * 
	    * 
	    */
	   public void doInitialSchedules()
	   {
		   
		// creates and activates the number of moving cases
		   for (int i=0; i < NUM_COMP; i++)
		   {
			     
		      MovingCompany company = new MovingCompany(this, "Hallo Welt Umzug", true);
		      company.activate(new TimeSpan(0));
		         // gets activated immediately  
		   }
		   
			// creates and activates the number of moving cases
		   for (int i=0; i < NUM_CREATE; i++)
		   {
			     
		      CaseCreater cCreater = new CaseCreater(this, "Case Creater", true);
		      cCreater.activate(new TimeSpan(0));
		         // gets activated immediately  
		   }
		   
	   }
	   
	   
	   /**
	    * Initialises static model components: distributions and queues.
	    */
	   public void init() 
	   {

		   
		   //init the list for all solved cases
		   solvedCases = new ArrayList<BinPacking>();
		   
			// creates the order time
		   // Parameters:
		   // this                     = belongs to this model
		   // "orderCompletionTime"	   = the name of the stream
		   // 10                      = average time to complete an order
		   // true                     = show in report?
		   // false                    = show in trace?
		   orderCompletionTime = new ContDistExponential(this, "OrderCompletionTime", 15 , true, false);
		   //completion times can't be negative
		   orderCompletionTime.setNonNegative (true);
		   
			// creates the time between orders
		   // Parameters:
		   // this                     = belongs to this model
		   // "newOrderTime"	   = the name of the stream
		   // 10                      = average time to complete an order
		   // true                     = show in report?
		   // false                    = show in trace?
		   newOrderTime = new ContDistExponential(this, "newOrderTime", 5 , true, false);
		   //completion times can't be negative
		   newOrderTime.setNonNegative (true);
		   
		   // creates a queue for cases that are waiting for available trucks
		   // Parameters:
		   // this          = belongs to this model
		   // "waitingCaseQueue" = the name of the Queue
		   // true          = show in report?
		   // true          = show in trace?
		   waitingCaseQueue = new ProcessQueue<MovingCase>(this, "waitingCaseQueue", true, true);
		   
		   
		   // creates a queue for MovingCompany that is waiting for new Cases
		   // Parameters:
		   // this          = belongs to this model
		   // "waitingCaseQueue" = the name of the Queue
		   // true          = show in report?
		   // true          = show in trace?
		   waitingCompanyQueue = new ProcessQueue<MovingCompany>(this, "waitingCompanyQueue", true, true);
		   
		   // creates a queue for MovingCompany that is waiting for available trucks
		   // Parameters:
		   // this          = belongs to this model
		   // "waitingCaseQueue" = the name of the Queue
		   // true          = show in report?
		   // true          = show in trace?
		   waitingForTrucksQueue = new ProcessQueue<MovingCompany>(this, "waitingForTrucksQueue", true, true);
	   }
	   
	   public static void main(java.lang.String[] args) 
	   {
		   //creates the report file and resets it with each new run
		   BinPackingHandler.createBinReportFile();
		   
		// creates model and experiment
		   Moving model = new Moving(null, "Modell eines Umzugs", true, true);
		  
		   //creates the experiment
		   Experiment exp = new Experiment("Umzug", TimeUnit.SECONDS, TimeUnit.MINUTES, null);
		   
		   //connects the model and experiment
		   model.connectToExperiment(exp);
		 //  exp.setSeedGenerator(6857521);
		   
		// sets experiment parameters
		   exp.setShowProgressBar(true);  
		   exp.stop(new TimeInstant(350, TimeUnit.MINUTES));   // 
		   exp.tracePeriod(new TimeInstant(0), new TimeInstant(350, TimeUnit.MINUTES));
		                                              
		   exp.debugPeriod(new TimeInstant(0), new TimeInstant(10, TimeUnit.MINUTES));  
		   
		   //starts the experiment at model time 0.0
		   
		   exp.start();
		   exp.report(); //creates the report
		   exp.finish();//terminates the experiment and all running modules
		   
	   }
}
