package binPackingSolver;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class BinPackingHandler {
	
	/**
 	* This method returns the bin which contains the searched BinObject
 	* 
    * @param BinObject o object that has to be found 
    * @param BinPacking bp the input bin packing problem in which the BinObject has to be found
    */
	public static Bin searchBin(BinObject o, BinPacking bp){
		
		ArrayList<Bin> Bins = new ArrayList<Bin>();
		Bins = bp.getBins();
				
		Bin result = null;
		
		for (Bin b : Bins)
		{
			if (b.containsObject(o))
				{
				return result = b;
				}
			else{
			result = null;
			}
			
		}
		System.out.println("searched" + result);
		return result;
		
	}
	
	public static int binsUsed(BinPacking bp){
		return bp.getBins().size();
	}

	
	/**
	 * prints each bin and all objects contained in the file created with "createBinReportFile"
	 * aswell as the current load and the space left
	 * @param String name name of the current bin packing problem
	 * @param Binpacking bp the input binpacking problem the needs to be printed
	 */
	public static void printBin(String name, BinPacking bp){//TODO reset file if restarted OR add to report

		ArrayList<Bin> Bins = new ArrayList<Bin>();
		Bins = bp.getBins();

    	
    	try{
    	PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("bin_report.txt", true)));
		out.println("");
		out.println(name);
		for (Bin b : Bins)
		{
			out.println (b.inputObjects());//TODO make this go into the report file
			out.println("load: " + b.load);
			out.println("space left: " + b.spaceLeft());
		}

    	out.close();
    	}catch (IOException e) {
  	      e.printStackTrace();
  	}
	}
	
	/**
 	* creates the output file for the printBin() method, needs to be called in the main() method
    */
	public static void createBinReportFile(){
    	try { 
   		 
  	      File file = new File("./bin_report.txt");
  	      
  	      if (file.createNewFile()){
  	      }else{
  	    	  file.delete();
  	    	  file.createNewFile();
  	    
  	      }
  	      
      	} catch (IOException e) {
  	      e.printStackTrace();
  	}
	}
	
	/**
	 * takes the given input and sorts in decreasing by objects weight, starting with the highest to the lowest 
	 * @param ArrayList<BinObject> l ArrayList of the given objects for the problem
	 * @return return a new sorted ArrayList<BinObject> that can be used as input for the solveBinPacking() method  
	 */
	public static ArrayList<BinObject> sortDecreasing(ArrayList<BinObject> l){
	
		Collections.sort(l, new Comparator<BinObject>() {
			public int compare (BinObject o1, BinObject o2){
				return o2.weight - o1.weight;
			}
		});
//		for (BinObject o : l) // outputs the new sorted objects 
//		{
//			System.out.println (o.weight); 
//		}
		return l;
	}
	
	/**
	 * this method can be used to set the status of all bins (only for global bin lists) used by a bin packing to unused again, after finish the job time 
	 * @param BinPacking bp the method will reset the bins for the given bin packing problem 
	 */	
	public static void setAllBinsFree(BinPacking bp){
		ArrayList<Bin> usedBins = new ArrayList<Bin>();
		usedBins = bp.getBins();
		
		for (Bin b : usedBins){
			b.setBinFree();
		}
	}
}
