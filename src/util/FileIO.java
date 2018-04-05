package util;

import model.*;
import java.io.*;
import interfaces.Debuggable;

public class FileIO implements Debuggable, Serializable {

	/////////////////////////////////////////
	// BUILDERS

	public Automobile buildAutomobile(String fileName) {
		if (DEBUG)
			System.out.println("Initializing build of Automobile from " + fileName);
		return this.readCarConfig(fileName);
	}

	public String[] metaDataExtract(String line) {
		if (DEBUG)
			System.out.println("Extracting Metadata from file");
		String[] metaData = line.split(", ");
		return metaData;
	}

	/////////////////////////////////////////
	// FILE INTEREACTION
	public Automobile readCarConfig(String fileName) {
		if (DEBUG)
			System.out.println("Parsing car configuration");
		Automobile auto;
		int numOfProperties = 0;
		String[] metaData;
		String name = ""; // 1
		String basePrice = ""; // 2
		OptionSet[] opset = null; // 3
		OptionSet choice = new OptionSet();
		choice.buildInner();
		String[] optionTitles;

		try {
			int counter = 0;
			FileReader file = new FileReader(fileName);
			BufferedReader buff = new BufferedReader(file);
			boolean eof = false;

			if (DEBUG)
				System.out.println("Opening file");

			while (!eof) {

				String line = buff.readLine();
				if (DEBUG)
					System.out.println("Reading: " + line);

				if (line == null) // end of file
					eof = true;

				else if (counter == 0) // metadata
				{
					metaData = metaDataExtract(line);
					name = metaData[0];
					basePrice = metaData[1];
					numOfProperties = Integer.parseInt(metaData[2]);
					opset = new OptionSet[numOfProperties];

					if (DEBUG)
						System.out.println("Metadata successfully configured");

					counter++;
				}

				else if (counter == 1) // OptionSetNames
				{
					if (DEBUG)
						System.out.println("Assigning OptionSet titles");

					optionTitles = line.split(", ");
					for (int i = 0; i < numOfProperties; i++) {
						opset[i] = new OptionSet();
						opset[i].setChoiceType(optionTitles[i]); // <------ SOMETHING BREAKS HERE (NPE)
					}
					counter++;
				}

				else if (counter > 1) // optionSet choices
				{
					if (DEBUG)
						System.out.println("Beginning creation of Options");

					String[] temp = line.split(","); // splits string into choice / price info
					int numOfChoices = temp.length;
					String[] choiceName = new String[(numOfChoices / 2)]; // initializes arrays where i'th element
																			// corresponds to a choice object
					String[] price = new String[(numOfChoices / 2)];

					if (DEBUG)
						System.out.println("Extracting titles and prices");

					for (int i = 0; i < numOfChoices; i++) {
						if (i % 2 == 0)
							choiceName[(i / 2)] = temp[i]; // even indexes correspond to names
						else
							price[(i / 2)] = temp[i]; // odd indexes correspond to prices
					}

					opset[(counter - 2)].buildChoiceArray(choiceName, price); // fills optionset corresponding to line
																				// with an array of choices
					if (DEBUG)
						System.out.println("Option array successfully assigned to OptionSet");

					counter++;
				}

			}
			buff.close();
		} catch (IOException e) {
			System.out.println("Error -- " + e.toString());
		}

		auto = new Automobile(name, opset, basePrice);
		if (DEBUG)
			System.out.println("Autombile successfully created!");

		return auto;
	}

	public String pathCreate(String path) {
		String newPath = new String();

		for (int i = 0; i < path.length(); i++) {
			if (path.charAt(i) != ' ')
				newPath += path.charAt(i);
			else if (path.charAt(i) == ' ')
				newPath += "_";

			newPath += path.charAt(i);
		}

		return path;
	}

	public void serialOutput(String path, Automobile auto) {
		// path = pathCreate(path);
		try {
			File file = new File(path);

			if (DEBUG)
			{
				System.out.println("=========================================");
				System.out.println("\nCreating Serial File");
				System.out.println("Path: " + path + auto.getName() + ".dat");
			}

			FileOutputStream outStream = new FileOutputStream(path + auto.getName() + ".dat");
			ObjectOutputStream objectOutputFile = new ObjectOutputStream(outStream);

			objectOutputFile.writeObject(auto);
			objectOutputFile.close();
			outStream.close();

		} catch (IOException e) {
			System.out.println("Error -- " + e.toString());
		}

		if (DEBUG)
			System.out.println("Object successfully serialized!\n\n");
	}

	public Automobile serialInput(String path) {
		Automobile auto = new Automobile();
		// path = pathCreate(path);

		if (DEBUG)
		{
			System.out.println("Reading in serial file");
			System.out.println("Path: " + path);
		}
		
		
		try {
			ObjectInputStream in = new ObjectInputStream(new FileInputStream(path));
			auto = (Automobile) in.readObject();

		} 
		catch (ClassNotFoundException e) 
		{
			e.printStackTrace();
		} 
		catch (FileNotFoundException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		catch (IOException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (DEBUG)
			System.out.println("Serial file successfully read!");
		
		return auto;
	}
}
