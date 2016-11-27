package bstproject;

import java.io.*;
import java.util.*;


public class BSTTree extends java.lang.Object {
	
	// BSTTree defines a binary search tree, where each node contains a linked list of crime records.
	// Tree is organized on the street address. 
	// Initialize values. 
	
	BSTTreeNode root;
	BSTTreeNode ptrPopular; 

	int largestSize; // Size of largest list for #2: most popular crime
	int crimeCount = 0;
	
	CrimeRecordList ptrChamp; // Points to the most popular node
	
	Boolean first = true;
	ArrayList<CrimeRecordList> nodeList = new ArrayList<CrimeRecordList>();
	
	// Constructor to set root to null and the two counters to zero
	public BSTTree()
	{
		root = null;
		largestSize = 0;
		crimeCount = 0;
		ptrChamp = null;
		ptrPopular = null;
	}
	
	// Constructor to initialize the tree by loading the tree with all data from the crime report file. 
	// pre: filePathname points to the crime file. post: the tree is constructed with the crime data
	public BSTTree(java.lang.String filePathAndName) throws java.lang.Exception
	{
		readTree(filePathAndName);
	}

	// Handles the actual reading of the file. Called by constructor. CrimeDataLocation is the location of the crime file.
	public void readTree(java.lang.String crimeDataLocation) throws java.lang.Exception
	{
		Scanner scanner = new Scanner(new File(crimeDataLocation));

		// RUNTIME COMPLEXITY of inserting n records into a binary tree: 
		// O(n * log(n))
		
		// SPLIT BY FILE NAME
		while (scanner.hasNextLine()) 
		{
			
			if (first) // The first line is all strings... not data, so skip it. 
			{
				first = false;
				String throwaway = scanner.nextLine();
				continue;
			}
			
			else 
			{
				// Parse the line, save the attributes into newRecord.
				CrimeRecord newRecord = CrimeRecord.parseLine(scanner.nextLine());
				crimeCount++;
				
				if (root == null) // First node inside the tree
				{
					root = new BSTTreeNode(); // Create a new first node.
					CrimeRecordList newLinkedList = new CrimeRecordList(); 
					newLinkedList.add(newRecord); // Compare to ONE linked list, their setStreet value.
					root.setData(newLinkedList);
				}
				
				else // There's already stuff inside the tree
				{
					BSTTreeNode node = root; // Current node
					BSTTreeNode parent = null;
					
					// Parse the line, save the attributes into newRecord.
					CrimeRecord currentNode = null;
					while(node!=null)
					{
						parent = node;
						currentNode = node.getData().getHead();
						// If node is alphabetically LESS 
						if (((currentNode.getStreet()).compareTo(newRecord.getStreet())) < 0)
						{
							node = node.getRc();
						}
						else if (((currentNode.getStreet()).compareTo(newRecord.getStreet())) > 0)
						{
							node = node.getLc();
						}
						else
						{
							break;
						}
					}
			
					CrimeRecord data = currentNode; // Returns CrimeRecordList
					
					// If child is alphabetically greater than parent
					if (data.getStreet().compareTo(newRecord.getStreet()) < 0)
					{
						CrimeRecordList newList = new CrimeRecordList();
						newList.add(newRecord);
						parent.setRc(new BSTTreeNode(newList, null, null));
					}
					
					else if (data.getStreet().compareTo(newRecord.getStreet()) == 0) 
					{
						// You don't need to make another linked list because it already exists.
						parent.getData().add(newRecord);	
					}
					 
					else
					{
						CrimeRecordList newList = new CrimeRecordList();
						newList.add(newRecord);
						parent.setLc(new BSTTreeNode(newList, null, null));
					}
					
				}
				
			}
			
		}
		scanner.close();

	}
	
	// Runtime complexity of traversal: O(n) 
	// Goes from left child --> parent --> right child
	public void inOrderPrint(BSTTreeNode t)
	{
		if (t.getLc() != null)
		{
			inOrderPrint(t.getLc());
		}
		
		nodeList.add(t.getData());
		
		if (t.getRc() != null)
		{
			inOrderPrint(t.getRc());
		}
	}
	
	public void inOrderPrint()
	{
		inOrderPrint(root);
	}
	
	// #2: To help find the most POPULAR address for crimes. 
	// Loop through all the nodes inside the tree, and then 
	// find which has the biggest linked list. 
	
	// Runtime complexity of traversal: O(n) 
	
	public void inOrderFindChampion(BSTTreeNode t)
	{
		if (t!=null)
		{
			inOrderFindChampion(t.getLc());
			
			if (t.getData().getSize() > largestSize)
			{
				largestSize = t.getData().getSize();
				ptrPopular = t; // Set the pointer! 	
			}
			
			inOrderFindChampion(t.getRc());
		}
	}
	
	public int getChampionVal()
	{
		return largestSize;
	}
	
	public BSTTreeNode getChampionPtr()
	{
		ptrPopular.getData().start(); // FIXED IT!!!!! :) :) You need this to reset the pointer
		return ptrPopular;
	}

	public int inOrderFindChampion()
	{
		inOrderFindChampion(root);
		return largestSize; 
	}
	
	// For #1: Find all crimes at this address.
	// Input a string address, return a list of crimes at that address.
	// Uses pre-order BST search.
	
	public int getCrimeCount()
	{
		return crimeCount;
	}
	
	// Finds the number of distinct address locations. This also means count how many nodes there are. 
	public int getDistinctCrimeLocations()
	{
		preOrderPrint();
		return nodeList.size();
	}
	
	// Finds all the crime reports at this entered address, so this wholeeee linked list.
	// Input: simple string with an address. 
	// Output: list of crimes at that address. 
	
	// Runtime complexity of looking up: 
	// O(log(n)). 
	public CrimeRecordList lookUp(java.lang.String address)
	{
		for (int i = 0; i < nodeList.size(); i++)
		{
			CrimeRecordList current = nodeList.get(i);
			if (i == 0) current.start();
			if (current.getHead().getStreet().toUpperCase().equals(address.toUpperCase()))
			{
				return current;
			}
		}
		return null;
	}
	
	// Pre-order: parent is processed before children.
	// Pre-condition: t can't be null.
	// Could add everything to a list and then search through it. 
	
	// Runtime complexity: O(n) this is traversal. 
	public void preOrderPrint(BSTTreeNode t)
	{
		if (t!=null)
		{
			nodeList.add(t.getData());
			preOrderPrint(t.getLc());
			preOrderPrint(t.getRc());
		}
	}
	
	public void preOrderPrint()
	{
		preOrderPrint(root);
	}
	
	
	// For #3: Find all crimes within a user-specified distance.	
	// CrimeRecord n is the entered in address by the user. 
	
	// Runtime complexity (since this is a traversal).. O(n).
	public CrimeRecordList bruteForceSearch(CrimeRecord n,
            double distance)
	{
		CrimeRecordList resultList = new CrimeRecordList();
		inOrderSearch(root, n, resultList, distance);
		
		return resultList;
	}
	
	
	// For #3: Find all crimes within a user-specified distance from a crime address.
	// Loop through whole tree, use distanceBetween function in Crime Record. 
	
	// Runtime complexity (since this is a traversal)... O(n). 
	public void inOrderSearch(BSTTreeNode t, CrimeRecord n, CrimeRecordList resultList, double distance)
	{
		if (t!=null)
		{
		
			inOrderSearch(t.getLc(), n, resultList, distance);
		
			Double thisDistance = n.distanceBetween(n, t.getData().getHead());
			
			if (thisDistance <= distance)
			{
				// Find the current node t, and add stuff from that node's linked list into resultList (which is a CrimeRecordList)
				// This runtime complexity is O(n), where n is the size of the node (or size of linked list inside that node) that is being searched for. 
				for (int i = 0; i < t.getData().getSize(); i++)
				{
					resultList.add(t.getData().next());
				}
			}
			inOrderSearch(t.getRc(), n, resultList, distance);
		}
	}

}


