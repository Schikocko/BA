package binPackingSolver;

import java.util.ArrayList;


public class TestProblem {
	
	

	
	public static void main(java.lang.String[] args) {
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
		
		

		  
//		  BinPacking pb = new FirstFit(binObjects, 10);
//		  pb.solveBinPacking();		

		  
		  BinPacking pb = new BestFit(binObjects, 10);
		  pb.solveBinPacking();
		  pb.printBin("Test Problem");

	  } 
	


}
