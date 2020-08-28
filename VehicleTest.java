package database;

import java.io.*;
import java.util.*;
public class VehicleTest
{
	public static void main(String[] args)
	{
		VehicleList data = new VehicleList();
		char input;
		try {
	  		 BufferedReader in = new BufferedReader(new FileReader("Vehicle.txt"));
	  		 data = VehicleList.loadFrom(in);
	  		 in.close();
	  		}
		catch (FileNotFoundException e){}
		catch (IOException e){}
		do
		{
		System.out.println("ECE 25100 Vehicle Database:");
		System.out.println("Please make a selection:");
		System.out.println("(L) List All Vehicles ");
		System.out.println("(S) Search For a Vehicle");
		System.out.println("(A) Add a Vehicle");
		System.out.println("(R) Remove a Vehicle");
		System.out.println("(Q) Quit");
		Scanner n = new Scanner(System.in);
		input = n.nextLine().charAt(0);
		if(input == 'L' || input == 'l')
			{
				System.out.println("All Information? Y or N");
				String value = n.nextLine();
				while(value.equals("N") || value.equals("Y"))
				{
					if (value.equals("Y"))
					{
						data.listVehicles();
						break;
					}
					else if(value.equals("N"))
					{
						data.listVehicleOwners();
						break;
					}
				}
			}
			if(input == 'S' || input == 's')
			{
				System.out.println("Please Enter Name of Vehicle Owner: ");
				String name = n.nextLine();
				data.findCar(name);
			}
			if(input == 'A' || input == 'a')
			{
				Vehicle veh = new Vehicle();
				System.out.println("Please Enter Owner Name: ");
				String name = n.nextLine();
				veh.setOwner(name);
				System.out.println("Please Enter Make of Car: ");
				String make = n.nextLine();
				veh.setMake(make);
				System.out.println("Please Enter Model of Car: ");
				String model = n.nextLine();
				veh.setModel(model);
				System.out.println("Please Enter Year of Car: ");
				int year = Integer.parseInt(n.nextLine());
				veh.setYear(year);
				System.out.println("Please Enter Color of Car: ");
				String color = n.nextLine();
				veh.setColor(color);
				System.out.println("Please Enter Plate of Car: ");
				String plate = n.nextLine();
				veh.setPlate(plate);
				data.addVehicle(veh);
				try {
			   		PrintWriter out = new PrintWriter(new FileWriter("Vehicle.txt"));
			   		data.saveTo(out);
			   		out.close();
			   		}
				catch (FileNotFoundException e){}
				catch (IOException e){}
			}
			if(input == 'R' || input == 'r')
			{
				System.out.println("Please Enter Name of Vehicle Owner: ");
				String name = n.nextLine();
				data.removeVehicle(name);
				try {
			   		PrintWriter out = new PrintWriter(new FileWriter("Vehicle.txt"));
			   		data.saveTo(out);
			   		out.close();
			   		}
				catch (FileNotFoundException e){}
				catch (IOException e){}
			}
		}while(input != 'Q');
		System.out.println("Thank you for using Database.");
	}
		
}

