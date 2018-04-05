package model;
import java.io.Serializable;
import interfaces.Debuggable;

public class OptionSet implements Debuggable, Serializable 
{
	/////////////////////////////////////////
	// INSTANCE VARIABLES
	private int numOfOptions;
	private String OptionName;
	private Option[] options;
	private Option opt = new Option();
	
	/////////////////////////////////////////
	// CONSTRUCTORS
	public OptionSet()
	{if (DEBUG)
		System.out.println("Constructing default OptionSet");}
	
	public OptionSet(String choiceType)
	{
		if (DEBUG)
			System.out.println("Constructing OptionSet - name only");
		this.OptionName = choiceType;
		for (int i = 0; i < options.length; i++)
		{
			options[i] = new Option();
		}
	}
	
	public OptionSet(String choiceType, Option[] options)
	{
		if (DEBUG)
			System.out.println("Constructing OptionSet");
		this.OptionName = choiceType;
		this.options = options;
		this.numOfOptions = this.options.length;
	}
	
	/////////////////////////////////////////
	// GETTERS / SETTERS
	public int getNumOfOptions() {
		return numOfOptions;
	}

	public void setNumOfOptions(int numOfOptions) {
		this.numOfOptions = numOfOptions;
	}

	public Option[] getChoices() {
		return options;
	}

	public void setChoices(Option[] options) {
		this.options = options;
	}
	
	public String getChoiceType() {
		return OptionName;
	}

	public void setChoiceType(String choiceType) {
		this.OptionName = choiceType;
	}

	public Option getOpt() {
		return opt;
	}

	public void setOpt(Option opt) {
		this.opt = opt;
	}

	/////////////////////////////////////////
	// METHODS
	public void buildInner()
	{
		if (DEBUG)
			System.out.println("Creating instance of Option in " + OptionName);
		this.opt = new Option();
	}
	
	public void buildChoiceArray(String[] choiceTitles, String[] prices)
	{  	
		if (DEBUG)
			System.out.println("Copying array of Options to " + OptionName);
	    	this.options = opt.returnChoiceArray(choiceTitles, prices);
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
		System.out.println("\t____________________________");
		System.out.println("\t" + this.OptionName);
		for (int i = 0; i < options.length; i++)
			options[i].print();
	}
	
	/////////////////////////////////////////
	// INNER CLASS
	private class Option implements Debuggable, Serializable
	{
		private String choiceTitle;
		private String price;
		
		public Option ()
		{if (DEBUG)
			System.out.println("Constructing default option");}
		
		public Option (String choiceTitle, String price)
		{
			if (DEBUG)
				System.out.println("Constructing Option");
			this.choiceTitle = choiceTitle;
			this.price = price;
		}

		
		///////
		public Option[] returnChoiceArray(String[] choiceTitles, String[] prices)
		{
			if (DEBUG)
				System.out.println("Creating array of Options");
			int numOfChoices = choiceTitles.length;
			Option[] arr = new Option[numOfChoices];
			
			for(int i = 0; i < numOfChoices; i++)
				arr[i] = new Option(choiceTitles[i], prices[i]);
			
			return arr;
		}
		
		///////
		
		public String getChoiceTitle() {
			return choiceTitle;
		}

		public void setChoiceTitle(String choiceTitle) {
			this.choiceTitle = choiceTitle;
		}

		public String getPrice() {
			return price;
		}

		public void setPrice(String price) {
			this.price = price;
		}
		
		public void print()
		{
			System.out.printf("\t   - $%s \t%s		\n", this.price, this.choiceTitle);
		}
	}
}
