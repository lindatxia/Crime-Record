package bstproject;

public class CrimeRecordListNode extends java.lang.Object {
	
	// DONE WITH THIS CLASS. 
	
	// Initialize some variables.
	CrimeRecord dataField;
    CrimeRecordListNode nextField;
	
	// Constructor 
	public CrimeRecordListNode(CrimeRecord data,
            CrimeRecordListNode next)
	{
		dataField = data;
		nextField = next;
	}
	
	public CrimeRecordListNode getNext()
	{
		return nextField;
	}

	public void setNext(CrimeRecordListNode next)
	{
		nextField = next;
	}
	
	public CrimeRecord getData()
	{
		return dataField;
	}
	
	public void setData(CrimeRecord data)
	{
		dataField = data;
	}
	
}
