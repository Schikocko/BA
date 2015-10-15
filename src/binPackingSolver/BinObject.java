package binPackingSolver;

public class BinObject implements BinPackingObject {
	
	String name;
	int weight;
	
	/**
	 * constructor 
	 *
	 *creates a new entity of an BinObject with given name and weight
	 *these BinObjects are needed as input for the binpacking solver 
	 */
	public BinObject (String name, int weight)
	{
		this.name = name;
		this.weight = weight;
	}
	
	/**
	 * @return returns the name of the object as a String
	 */
	public String getName(){
		return name;
	}
	
	/**
	 * @return return the weight of the object as an integer
	 */
	public int getWeight(){
		return weight;
	}

}
