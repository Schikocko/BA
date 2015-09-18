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
		  
		  ArrayList<Bin> useableBins;
		  useableBins = new ArrayList<Bin>();
		  useableBins.add(new Bin ("bin0", 10));
		  useableBins.add(new Bin ("bin1", 10));
		  useableBins.add(new Bin ("bin2", 1));
		  useableBins.add(new Bin ("bin3", 1));
		  useableBins.add(new Bin ("bin4", 10));
		  useableBins.add(new Bin ("bin5", 10));
		  useableBins.add(new Bin ("bin6", 10));
		  useableBins.add(new Bin ("bin7", 10));
		  useableBins.add(new Bin ("bin8", 10));
		  useableBins.add(new Bin ("bin9", 10));
		  useableBins.add(new Bin ("bin10", 10));
		  useableBins.add(new Bin ("bin11", 10));
		  useableBins.add(new Bin ("bin12", 10));
		  

		
		

		  
//		  BinPacking pb = new FirstFit(binObjects, false);
//		  pb.solveBinPacking(10);		

		  
		  BinPacking bp = new NextFit(binObjects, false);
		  
		  if(!bp.solveBinPacking(useableBins)){
			  System.out.println("failed");
	
		  }else
//		  bp.solveBinPacking();
		  BinPackingHandler.printBin("Test Problem", bp);

		  for(Bin b : useableBins)
		  {
			  System.out.println(b.name);
			  System.out.println(b.getStatus());
		  }

	  } 
	


}
