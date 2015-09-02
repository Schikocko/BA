package example;

import desmoj.core.simulator.*;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import binPackingSolver.BinObject;





public class CaseCreater extends SimProcess{
	
	public CaseCreater (Model owner, String name, boolean showInTrace){
		 super(owner, name, showInTrace);
	}
	
	public void lifeCycle(){
		
		ArrayList<BinObject> binObjects;
		  binObjects = new ArrayList<BinObject>();
		  binObjects.add(new BinObject ("object0", 6));
		  binObjects.add(new BinObject ("object1", 3));
		  binObjects.add(new BinObject ("object2", 4));
		  binObjects.add(new BinObject ("object3", 7));
		  binObjects.add(new BinObject ("object4", 1));
		  binObjects.add(new BinObject ("object5", 4));
		  binObjects.add(new BinObject ("object6", 3));
		  binObjects.add(new BinObject ("object7", 2));
		  binObjects.add(new BinObject ("object8", 6));
		  binObjects.add(new BinObject ("object9", 3));
		
		Moving model = (Moving)getModel();
		while (true){
        // erstelle einen neuen Patienten
        // Parameters:
        // model       = Ist ein teil dieses Modell
        // "Patient"   = Name der Entität
        // true        = der Patient wird getraced
		MovingCase newCase = new MovingCase (model, "Umzug", true, binObjects , 10);
		
		newCase.activateAfter(this);
		hold(new TimeSpan(1, TimeUnit.MINUTES));
		}
		
	}

}
