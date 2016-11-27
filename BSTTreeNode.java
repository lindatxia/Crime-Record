package bstproject;

public class BSTTreeNode 
{
	
	private BSTTreeNode lcNew;
    private BSTTreeNode rcNew;
    private CrimeRecordList dataNew;  // Linked list 
    
    
    // Initializes three fields to null.
    // done with this.
    
	public BSTTreeNode()
	{
		lcNew = null;
		rcNew = null;
		dataNew = null;
	}
	
	public BSTTreeNode(CrimeRecordList data, BSTTreeNode lc, BSTTreeNode rc)
	{
		this.dataNew = data;
		this.lcNew = lc;
		this.rcNew = rc;
	}
	
	public BSTTreeNode getLc()
	{
		
		return lcNew;
	}
	
	public BSTTreeNode getRc()
	{
		return rcNew;
	}
	
	public void setLc(BSTTreeNode lc)
	{
		lcNew = lc;
	}
	
	public void setRc(BSTTreeNode rc)
	{
		rcNew = rc;
	}
	
	public CrimeRecordList getData()
	{
		return dataNew;
	}
	
	public void setData(CrimeRecordList data)
	{
		dataNew = data;
		
	}
	
}
