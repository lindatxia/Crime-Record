package bstproject;

import java.io.*;
import java.util.Scanner;

public class BSTTreeDriver extends java.lang.Object {
	
	// Initialize variables
	
	static String csvFile = "/Users/lindaxia/Documents/workspace2/Homework/src/bstproject/CrimeLatLonXY.csv";
	static BSTTree tree;
	static Scanner sc;
	static String kmlFirst = "<?xml version= \"1.0\" encoding=\"UTF-8\" ?><kml xmlns=\"http://earth.google.com/kml/2.2\"><Document><Style id=\"style1\"><IconStyle><Icon><href>http://maps.gstatic.com/intl/en_ALL/mapfiles/ms/micons/blue-dot.png</href></Icon></IconStyle></Style>";
	static String kmlLast = "</Document></kml>";
	static CrimeRecordList kmlAnswer;
	
	// MAIN METHOD
	public static void main(java.lang.String[] args) throws java.lang.Exception
	{
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in)); 
		System.out.println("Reading input data");
		
		// CREATE THE TREE. This will read the tree and put parsed data into tree.
		// Please look at BSTTree file for runtime complexity comments.
		tree = new BSTTree(csvFile);  
		
		System.out.println("File read with " + tree.crimeCount + " records");
		System.out.println("Total number of crimes reported on file: "+ tree.crimeCount);
		System.out.println("Number of distinct address locations: " + tree.getDistinctCrimeLocations());
		
		System.out.print("\n");
		
		System.out.print("\n");
		
		while (true)
		{
			System.out.println("1) Enter a Street address for a crime report at that street address.");
			System.out.println("2) Find all crimes at the most popular address for crimes.");
			System.out.println("3) Find all crimes within a user specified distance from a crime address.");
			System.out.println("4) Quit");
			System.out.print("\n");
			
			System.out.print("Enter: ");
			String line = input.readLine();
			
			if(line.equals("4")) 
			{
				System.out.print("Quitting... Be safe out there!");
				break;
			}
			
			if(line.equals("1"))
			{
				handleLookUp(tree,sc);
			}
			
			else if (line.equals("2"))
			{
				handleLookForMostCrimes(tree);
			}
			
			else if (line.equals("3"))
			{
				handleNearbyCrimes(tree, sc);
			}
		}
	}
	
	// My self-written method.
	// Pre-condition: kmlAnswer (which is a CrimeRecordList) is NOT null. 
	// Post-condition: Returns a CrimeRecordList kmlAnswer that has the resulting linked list nodes for the KML file. 
	public static CrimeRecordList updateKML()
	{
		return kmlAnswer;
	}

	// Handles menu selection 1 
	public static void handleLookUp(BSTTree tree, java.util.Scanner sc) throws Exception
	{
		sc = new Scanner(System.in);	
		System.out.print("Enter street address: ");
		String strAddress = sc.nextLine(); // Obtains the street address you're looking at.
	
		// If there's a matching street name, then answer will contain the linked list w/all the crime information.
		CrimeRecordList answer = tree.lookUp(strAddress);
		
		if (answer != null)
		{
			System.out.print("Found " + answer.getSize() + " crimes at " + answer.getHead().getStreet());
			System.out.print("\nReport written to PGHCrimes.kml on the desktop");
			
			kmlAnswer = answer;
			
			// KML CONVERSION
			kmlAnswer.toKml();
			kmlAnswer.toGoogleEarth("/Users/lindaxia/Documents/workspace2/Homework/src/bstproject/PGHCrimes.kml");
		}
		
		else // Otherwise, this is not a valid address in the CSV file.
		{
			System.out.print("Found 0 crimes at " + strAddress);
		}
		
		System.out.print("\n\n");
	}
	
	// Handles menu selection 2 
	// Find address with most popular crimes. 
	public static void handleLookForMostCrimes(BSTTree tree) throws java.io.IOException
	{
		System.out.print("\n");
		System.out.print("Performing search. Finding address with most crimes.");
		System.out.print("Looking for address with most crime reports.");
		
		tree.inOrderFindChampion(); // This traverses the tree. See inside BSTTree for runtime comments.
		System.out.print("\n");
		System.out.print("Found " + tree.getChampionVal() + " crimes at the following address: " + tree.getChampionPtr().getData().getHead().getStreet());
		System.out.print("\n\n");
		
		kmlAnswer = tree.getChampionPtr().getData();
		
		kmlAnswer.toKml(); // Turn everything inside CrimeRecordList into KML
		kmlAnswer.toGoogleEarth("/Users/lindaxia/Documents/workspace2/Homework/src/bstproject/PGHCrimes.kml");
	}
	
	// Handles menu selection 3 
	public static void handleNearbyCrimes(BSTTree tree, java.util.Scanner sc) throws java.io.IOException
	{
		sc = new Scanner(System.in);	
		System.out.print("Enter street address of a known crime location to center search: ");
		String strAddress = sc.nextLine(); // Obtains the street address you're looking at.

		CrimeRecordList answer = tree.lookUp(strAddress); // Finds the BSTTreeNode containing this street address with crime data.
		if (answer != null)
		{
			System.out.print("Found " + answer.getSize() + " crimes at " + answer.getHead().getStreet());
			System.out.print("\n\n");
		
			System.out.print("\n");
			System.out.print("Enter distance (IN FEET) from this spot to find additional crimes: ");
			double distance = sc.nextDouble();
			
			CrimeRecord answerHead = answer.getHead(); // Gets one CrimeRecord file that matches the inputed address.
			kmlAnswer = tree.bruteForceSearch(answerHead, distance);
			
			System.out.println("Found " + kmlAnswer.getSize() + " crimes in that range");
			System.out.print("Report written to PGHCrimes.kml on the desktop");
			System.out.print("\n\n");
			
			// KML CONVERSION
			kmlAnswer.toKml();
			kmlAnswer.toGoogleEarth("/Users/lindaxia/Documents/workspace2/Homework/src/bstproject/PGHCrimes.kml");
		}
		
		else 
		{
			System.out.print("Found no known crimes at " + strAddress);
			System.out.print("\n\n");
		}
		
	}

}
