package driver;
import util.*;
import model.*;

public class Driver
{
	public static void main(String[] args) 
	{
		String fileName = "ford_focus_wgn_ztw_2010.txt";
		FileIO FIO = new FileIO();
		Automobile auto;
		
		System.out.println("Testing " + fileName);
		
		auto = FIO.buildAutomobile(fileName);
		
		auto.print();
		
		String path = "binaries/";
		
		FIO.serialOutput(path, auto);
		Automobile newAuto = FIO.serialInput("binaries/ford_focus_wgn_ztw_2010.dat");
		
		newAuto.print();
	}
}
