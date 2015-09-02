package example;

import java.util.ArrayList;
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
	    * Model parameter: number of moving cases
	    */
	   protected static int NUM_COMP = 1;	  
	   
	   /**
	    * Model parameter: number of moving cases
	    */
	   protected static int NUM_CREATE = 1;
	   
	   
	   protected ArrayList<BinPacking> solvedCases;
	
	   
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
		      company.activate(new TimeSpan(9.5));
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
		   
	   }
	   
	   public static void main(java.lang.String[] args) 
	   {
		// erstellt Modell and Experiment
		   Moving model = new Moving(null, "Modell eines Umzugs", true, true);
		  
		   //Erstellt das Experiment
		   Experiment exp = new Experiment("Umzug", TimeUnit.SECONDS, TimeUnit.MINUTES, null);
		   
		   //verbindet das Modell und das Experiment
		   model.connectToExperiment(exp);
		 //  exp.setSeedGenerator(6857521);
		   
		// Setzt die Experiment Paramenter
		   exp.setShowProgressBar(true);  
		   exp.stop(new TimeInstant(10, TimeUnit.MINUTES));   // 
		   exp.tracePeriod(new TimeInstant(0), new TimeInstant(10, TimeUnit.MINUTES));
		                                              
		   exp.debugPeriod(new TimeInstant(0), new TimeInstant(10, TimeUnit.MINUTES));  
		   
		   //startet das Experiment bei 0.0
		   
		   exp.start();
		   exp.report(); //erstellt den Report
		   exp.finish();//beenedet das Experiment und alle noch laufenden Teile
		   
	   }
}
