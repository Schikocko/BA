package example;

import desmoj.core.dist.ContDistUniform;
import desmoj.core.simulator.*;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import binPackingSolver.BinObject;





public class CaseCreater extends SimProcess{
	
	private Moving myModel;
	private desmoj.core.dist.ContDistUniform numberObjects;
	private desmoj.core.dist.ContDistUniform objectWeight;
	
	public CaseCreater (Model owner, String name, boolean showInTrace){
		 super(owner, name, showInTrace);
		 myModel = (Moving)owner;
		 numberObjects = new ContDistUniform(myModel, "NumberOfObjects", 1, 100, false, true);
		 objectWeight = new ContDistUniform(myModel, "WeightOfAnObject", 1, 50, false, true);
	}
	
	public void lifeCycle(){
		
	
		ArrayList<BinObject> binObjects;
		binObjects = new ArrayList<BinObject>();
		
		
//		  binObjects.add(new BinObject ("object0", 6)); //example
//		  binObjects.add(new BinObject ("object1", 3));
//		  binObjects.add(new BinObject ("object2", 4));
//		  binObjects.add(new BinObject ("object3", 7));
//		  binObjects.add(new BinObject ("object4", 1));
//		  binObjects.add(new BinObject ("object5", 4));
//		  binObjects.add(new BinObject ("object6", 3));
//		  binObjects.add(new BinObject ("object7", 2));
//		  binObjects.add(new BinObject ("object8", 6));
//		  binObjects.add(new BinObject ("object9", 3));


		

		
		Moving model = (Moving)getModel();
		while (true){

			
			//new random ArrayList for each MovingCase
			for (int i = 0; i <= numberObjects.sample(); i++){
				int weight = objectWeight.sample().intValue();
				 System.out.println ("Object" + i + " "+  "Gewicht" +":" + weight);
				binObjects.add(new BinObject("object"+i, weight));
				
			}
			

        // creates a new moving case, that needs to be solved with the bin packing algorithm
        // Parameters:
        // model       = is part of the current model
        // "Umzug"     = name of the entity 
        // true        = get traced
		// binObjects  = an Arraylist of the to be solved objects with size and name
		// model.getTruckSize()		   = max bin capacity 
		// myModel.getOrderCompletionTime() = time this order takes to be completed 
		MovingCase newCase = new MovingCase (model, "Umzug", true, binObjects , model.getTruckSize(), myModel.getOrderCompletionTime());
		
		newCase.activateAfter(this);
		hold(new TimeSpan(myModel.getNewOrderTime(), TimeUnit.MINUTES));
		}
		
	}

}
