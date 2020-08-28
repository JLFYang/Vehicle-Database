package database;

import java.util.*;

public class VehicleList
{
	int size = 0;
	private ArrayList<Vehicle> vehicles;
	public VehicleList()
	{
		vehicles = new ArrayList<Vehicle>();
		size = 0;
	}
	public int getSize() {return size;}
	public void addVehicle(Vehicle v)
	{
		vehicles.add(v);
		size++;
	}
	public void removeVehicle(String n)
	{
		Iterator<Vehicle> iter = vehicles.iterator();
		while(iter.hasNext())
		{
			Vehicle v = (Vehicle)iter.next();
			if(v.getOwner().equals(n))
			{
				iter.remove();
				size--;
			}
		}
	}
	public void listVehicles()
	{
		for(Vehicle v : vehicles)
		{
			System.out.println(v);
		}
	}
	public void listVehicleOwners()
	{
		for(Vehicle v : vehicles)
		{
			System.out.print(v.getOwner()+"\n");
		}
	}
	
	public Vehicle findCar(String n)
	{
		Vehicle found = new Vehicle();
		for(Vehicle v: vehicles)
		{
			if(v.getOwner().equals(n))
			{
				System.out.println(v);
				found = v;
			}
		}
		return found;
	}
	
	// GUI Methods
	public String[] listOwners()
	{
		String[] owners = new String[size];
		for(int i = 0;i < size;i++)
		{
				owners[i] = vehicles.get(i).getOwner();
		}
		return owners;
	}
	
	public void remove(int index)
	{
        if ((index >= 0) && (index < size))
        {
           for(int i = 0;i < listOwners().length;i++)
           {
        	   if(listOwners()[i].equals(vehicles.get(index).getOwner()))
        	   {
        		   removeVehicle(listOwners()[i]);
        	   }
           }
        }
    }
	
	public Vehicle find(int index)
	{
		Vehicle found = new Vehicle();
		if ((index >= 0) && (index < size))
	    {
			for(int i = 0;i < listOwners().length;i++)
	        {
	        	if(listOwners()[i].equals(vehicles.get(index).getOwner()))
	        	{
	        		found = vehicles.get(index);
	        	}
	        }
	    }
		return found;
	}
	
	public void saveTo(java.io.PrintWriter aFile) throws java.io.IOException
    {
    	for(Vehicle v: vehicles)
    	{
    		v.saveTo(aFile);
    	}
    }
	
	public static VehicleList loadFrom(java.io.BufferedReader aFile) throws java.io.IOException
	{
		VehicleList vl = new VehicleList();
		aFile.readLine();
    	while(aFile.ready())
    	{
    		vl.addVehicle(Vehicle.loadFrom(aFile));
    	}
    	return vl;
    }
}

