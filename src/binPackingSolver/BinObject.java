package binPackingSolver;

public class BinObject {
	
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

}
