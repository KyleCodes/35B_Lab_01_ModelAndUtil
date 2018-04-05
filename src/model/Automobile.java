package model;
import java.io.Serializable;
import interfaces.Debuggable;

public class Automobile implements Debuggable, Serializable
{
	/////////////////////////////////////////
	// INSTANCE VARIABLES
	private String name;
	private int numOfProperties;
	private String basePrice;
	private OptionSet[] opset;
	
	/////////////////////////////////////////
	// CONSTRUCTORS
	public Automobile()
	{}
	
	public Automobile(String name, OptionSet[] opset, String basePrice)
	{
		if (DEBUG)
			System.out.println("Constructing Automobile");
		this.name = name;
		this.opset = opset;
		this.numOfProperties = this.opset.length;
		this.basePrice = basePrice;
	}
	
	/////////////////////////////////////////
	// GETTERS / SETTERS
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getNumOfProperties() {
		return numOfProperties;
	}

	public void setNumOfProperties(int numOfProperties) {
		this.numOfProperties = numOfProperties;
	}

	public OptionSet[] getOpset() {
		return opset;
	}

	public void setOpset(OptionSet[] opset) {
		this.opset = opset;
	}

	public String getBasePrice() {
		return basePrice;
	}

	public void setBasePrice(String basePrice) {
		this.basePrice = basePrice;
	}

	/////////////////////////////////////////
	// METHODS
	public void calculateTotalPrice()
	{
		// add baseprice
		// loop and add each Option's price
	}
	
	public void addOpset()
	{
		// not adding until Collections can be used
	}
	
	public void deleteOpset()
	{
		// not adding until Collections can be used
	}
	
	public void addOption()
	{
		// not adding until Collections can be used
	}
	
	public void deleteOption()
	{
		// not adding until Collections can be used
	}
	
	
	public void print()
	{
		System.out.println("=========================================\n");
		System.out.println("\t" + this.name);
		System.out.println("");
		System.out.println("Base Price: $" + this.basePrice);
		System.out.println("Options   :  " + this.numOfProperties);
		System.out.println("");
		for (int i = 0; i < numOfProperties; i++)
			this.opset[i].print();
	}
	
}
