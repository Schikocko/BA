package binPackingSolver;

import java.util.ArrayList;

public class Bin {
	
	ArrayList<BinObject> contains;
	int maxCapacity; 
	int load; //current fill state
	String name;
	
	
	public Bin (String name, int capacity)
	{
		contains = new ArrayList<BinObject>();
		this.maxCapacity = capacity;
		this.load = 0;
		this.name = name;
		
	}
	
	public boolean addObject (BinObject i)
	{
		if (load + i.weight <= maxCapacity)
			{	contains.add(i);
				load = load + i.weight;
				return true;
			}
		return false;
		
	}
	
	
	//Printing a List of all Objects in this current bin
	public void inputObjects()
	{	
		System.out.println(this.name +": ");
		for (BinObject o : contains)
			
		
		System.out.println(o.name + " " + o.weight);
	}
	
//	//sends trace notes of all bins and its contains TODO
//		public void traceBin(boolean a)
//		{	
//			if(a){
//			sendTraceNote(this.name +": ");
//			for (BinObject o : contains)
//				
//			
//			sendTraceNote(o.name + " " + o.weight);
///		}
//		}
	
	public boolean containsObject(BinObject o)
	{
		return contains.contains(o); //searching through all Objects in this bin to find o, returning TRUE if found, FALSE if not
	}
	
	//returns the actual Weight of all objects in this bin
	public int currentWeight()
	{
		return this.load;
	}
	
	
	//returns an integer of the free Space in the bin
	public int spaceLeft()
	{
		return maxCapacity - currentWeight();
	}
//TODO Output
	
}
