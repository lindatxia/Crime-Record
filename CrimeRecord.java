package bstproject;

public class CrimeRecord extends java.lang.Object {

	
	double newX;
	double newY;
	String newStreet;
	int newTime;
	String newOffense;
	String newDate;
	String newTract;
	double newLat;
	double newLon;

	// Constructor
	public CrimeRecord()
	{
		
	}
	
	public CrimeRecord(double x, double y, int time, java.lang.String street, java.lang.String offense, java.lang.String date,
			java.lang.String tract, double lat, double lon)
	{ 
		// Sets all members of the CrimeRecord object
		this.newX = x; 
		this.newY = y; 
		this.newTime = time;
		this.newStreet = street;
		this.newOffense = offense;
		this.newDate = date;
		this.newTract = tract; 
		this.newLat = lat;
		this.newLon = lon;
	}
	
	public java.lang.String getDate()
	{
		return newDate;
	}
	
	public double getLat()
	{
		return newLat;
	}
	
	public double getLon()
	{
		return newLon;
	}
	
	public java.lang.String getOffense()
	{
		return newOffense;
	}
	
	public java.lang.String getStreet()
	{
		return newStreet;
	}
	
	public int getTime()
	{
		return newTime;
	}
	
	public java.lang.String getTract()
	{
		return newTract;
	}
	
	public double getX()
	{
		return newX;
	}
	
	public double getY()
	{
		return newY;
	}
	
	public void setDate(java.lang.String date)
	{
		newDate = date;
	}
	
	public void setLat(double lat)
	{
		newLat = lat;
	}
	
	public void setLon(double lon)
	{
		newLon = lon;
	}
	
	public void setOffense(java.lang.String offense)
	{
		newOffense = offense;
	}
	
	public void setStreet(java.lang.String street)
	{
		newStreet = street;
	}
	
	public void setTime(int time)
	{
		newTime = time;
	}
	
	public void setTract(java.lang.String tract)
	{
		newTract = tract;
	}
	
	public void setX(double x)
	{
		newX = x;
	}
	
	public void setY(double y)
	{
		newY = y;
	}
	
	public java.lang.String toString()
	{
		return null;
	}
	
	// Line contains comma-separated values of crime data (single line of input from crime file).
	// Use split method from String class.
	// Returns: new CrimeRecord object holding the data from String line.
	
	public static CrimeRecord parseLine(java.lang.String line)
	{
		// Splits the line by commas and saves into an array
		String[]temp = line.split(",");
		CrimeRecord newInfo = new CrimeRecord();
		
		for (int i = 0; i < temp.length; i++)
		{
			if (i == 0) // X
			{
				double tempVar = Double.parseDouble(temp[i]);
				newInfo.setX(tempVar);
			}
			
			else if (i == 1) // Y 
			{
				double tempVar = Double.parseDouble(temp[i]);
				newInfo.setY(tempVar);
			}
			
			else if (i == 2) // Time 
			{
				int tempVar = Integer.parseInt(temp[i]);
				newInfo.setTime(tempVar);
			}
			
			else if (i == 3) // Street 
			{
				java.lang.String tempVar = (java.lang.String) temp[i]; 
				newInfo.setStreet(tempVar);
			}
			
			else if (i == 4) // Robbery type 
			{
				java.lang.String tempVar = (java.lang.String) temp[i];
				newInfo.setOffense(tempVar);
			}
			
			else if (i == 5) // Date 
			{
				java.lang.String tempVar = (java.lang.String) temp[i];
				newInfo.setDate(tempVar);
			}
			
			else if (i == 6) // Tract
			{
				java.lang.String tempVar = (java.lang.String) temp[i];
				newInfo.setTract(tempVar);
			}
			
			else if (i == 7) // Latitude 
			{
				double tempVar = Double.parseDouble(temp[i]);
				newInfo.setLat(tempVar);
			}
			
			else if (i == 8) // Longitude
			{
				double tempVar = Double.parseDouble(temp[i]);
				newInfo.setLon(tempVar);
			}
		}
		
		return newInfo;
		
	}
	
	
	public static double distanceBetween(CrimeRecord a, CrimeRecord b)
	{
		double xA = a.getX(); 
		double yA = a.getY();
		
		double xB = b.getX();
		double yB = b.getY();
		
		double distance = Math.sqrt(Math.pow((xA - xB),2) + Math.pow((yA - yB),2));
		return distance;
		
	}
	
	
	
}

