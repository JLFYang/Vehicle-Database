package database;

import java.util.*;
public class Vehicle
{
	private String owner;
	private String make;
	private String model;
	private int year;
	private String color;
	private String plate;
	public static StringTokenizer tok;
	
	public Vehicle()
	{
		this("","","",0,"","");
	}
	public Vehicle(String anOwner)
	{
		this(anOwner,"","",0,"","");
	}
	public Vehicle(String anOwner, String aMake, String aModel, int aYear, String aColor, String aPlate)
	{
		owner = anOwner;
		make = aMake;
		model = aModel;
		year = aYear;
		color = aColor;
		plate = aPlate;
	}
	public String getOwner(){return owner;}
	public String getMake(){return make;}
	public String getModel(){return model;}
	public int getYear(){return year;}
	public String getColor(){return color;}
	public String getPlate(){return plate;}
	
	public void setOwner(String anOwner) {owner = anOwner;}
	public void setMake(String aMake) {make = aMake;}
	public void setModel(String aModel) {model = aModel;}
	public void setYear(int aYear) {year = aYear;}
	public void setColor(String aColor) {color = aColor;}
	public void setPlate(String aPlate) {plate = aPlate;}
	
	public void saveTo(java.io.PrintWriter aFile) throws java.io.IOException
	{
		aFile.println(owner+"   "+make+"   "+model+"   "+year+"   "+color+"   "+plate);
	}
	
	public static Vehicle loadFrom(java.io.BufferedReader aFile) throws java.io.IOException
	{
		Vehicle v = new Vehicle();
		String line = aFile.readLine();
		tok = new StringTokenizer(line);
		v.setOwner(tok.nextToken());
		v.setMake(tok.nextToken());
		v.setModel(tok.nextToken());
		v.setYear(Integer.parseInt(tok.nextToken()));
		v.setColor(tok.nextToken());
		v.setPlate(tok.nextToken());
		return v;
	}
	public String toString()
	{
		return(getOwner()+"   "+getMake()+"   "+getModel()+"   "+getYear()+"   "+getColor()+"   "+getPlate());
	}
	}
