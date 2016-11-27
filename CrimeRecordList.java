package bstproject;

import java.io.*;

public class CrimeRecordList extends java.lang.Object {
	
	// LINKED LIST IMPLEMENTATION
	
	private int size; // Size of the array 
	private CrimeRecordListNode head; 
	private CrimeRecordListNode ptr; // This is the pointer
	private CrimeRecordListNode ptrLast; // Points to the first element
	CrimeRecordList kmlAnswer;
	java.lang.String finalKML;
	
	String kmlFirst = "<?xml version= \"1.0\" encoding=\"UTF-8\" ?><kml xmlns=\"http://earth.google.com/kml/2.2\"><Document><Style id=\"style1\"><IconStyle><Icon><href>http://maps.gstatic.com/intl/en_ALL/mapfiles/ms/micons/blue-dot.png</href></Icon></IconStyle></Style>";
	String kmlLast = "</Document></kml>";
    
	public CrimeRecordList()
	{
		size = 0; 
		head = null;
	}
	
	public int getSize()
	{
		return size;
	}
	
	// Add a crime record to the end of the list.
	public void add(CrimeRecord cr)
	{
		
		if (head == null) // This is the first item
		{ 
			head = new CrimeRecordListNode(cr,null);
			ptrLast = head;
			size++;
			start();
		}
		
		else
		{
			CrimeRecordListNode oldHead = head; // Save the reference first!
			head = new CrimeRecordListNode(cr,null);
			head.nextField = oldHead;
			start(); // Set ptr to latest node added
			size++;
		}
	}

	// Sets the internal pointer to the top of the list. 
	public void start()
	{
		ptr = head;
	}

	// Return true if the internal pointer is not null.
	public boolean hasNext()
	{
		if (ptr!=null)
		{
			return true;
		}
		return false;
		
	}
	
	// When next is called, the CrimeRecord pointed to by ptr (the most recent node) is returned; 
	// ptr is set to point to the next item on the list. 
	public CrimeRecord next()
	{
		if (ptr!=null) // Ptr is NOT null :) 
		{
			CrimeRecord saveThis = ptr.dataField;
			ptr = ptr.nextField; // Set reference to next linked node on list 
			return (saveThis); 
		}
		return null; // return null if this reaches the end of the list
	}
	
	// My own method I wrote, returns the data field on the very top of the list
	public CrimeRecord getHead()
	{
		start();
		if (ptr != null)
		{
			return ptr.dataField;
		}
		return null;
	}
	
	// Returns a KML representation of this list of crime records for a single NODE.
	public java.lang.String toKml()
	{
		String kmlMiddle = "";
		CrimeRecord current; 
		kmlAnswer = BSTTreeDriver.updateKML();
		
		while (true)
		{
			current = kmlAnswer.next();
			if (current == null) break;
			kmlMiddle = kmlMiddle + "<Placemark><name>" + current.newOffense + "</name><description>" + current.newStreet + "</description><styleUrl>#style1</styleUrl><Point><coordinates>" + current.newLon + "," + current.newLat + ",0.000000</coordinates></Point></Placemark>";	
		}
		
		finalKML = "";
		finalKML = kmlFirst + kmlMiddle + kmlLast;
		return finalKML;
	}
	
	// Uses the PrintWriter package from java.io to read in the KML string and write it to the KML file.
	public void toGoogleEarth(java.lang.String pathToFile) throws java.io.IOException
	{
		PrintWriter out = new PrintWriter(pathToFile);
		out.println(finalKML);
		out.close();
	}
            
	public CrimeRecordList concat(CrimeRecordList a, CrimeRecordList b)
	{
		CrimeRecordList newList = new CrimeRecordList(); // Create new object 
		newList = a; // Copy a so you don't lose original list 
		newList.ptr.nextField = b.ptrLast;
		size++;
		return newList;	
	}
	
	
	
}
