package com.blood.plus;

public class node {

	private String name;
	private String dob;
	private String group;
	private String gender;
	private String phone;
	private String email;
	private String state;
	private String city;
	private String place;
	private double lat;
	private double lon;
	private double distance;
	private String privacy;
	
	
	//Constructor
	public node(String name ,String group,String city,String phone,String place,double lat,double lon,String email,String privacy){
		// TODO Auto-generated constructor stub
		this.name=name;
		this.group=group;
		this.city=city;
		this.phone=phone;
		this.place=place;
		this.lat=lat;
		this.lon=lon;
		this.email=email;
		this.privacy=privacy;
	}
	
	public node(){
		// TODO Auto-generated constructor stub
		
	}
	
	//getter methods
	public String getName()
	{
		return this.name;
	}
	

	public String getPrivacy()
	{
		return this.privacy;
	}
	public String getDob()
	{
		return this.dob;
	}
	
	
	public String getGroup()
	{
		return this.group;
	}
	
	
	public String getGender()
	{
		return this.gender;
	}
	
	
	public String getPhone()
	{
		return this.phone;
	}
	
	
	public String getEmail()
	{
		return this.email;
	}
	
	
	public String getState()
	{
		return this.state;
	}
	
	
	public String getCity()
	{
		return this.city;
	}
	
	
	public String getPlace()
	{
		return this.place;
	}
	
	public double getLattitude()
	{
		return this.lat;
	}
	
	public double getLongitude()
	{
		return this.lon;
	}
	public double getDistance()
	{
		return this.distance;
	}
//setter methods
	
	public void setName(String x)
	{
		 this.name=x;
	}
	public void setPrivacy(String x)
	{
		 this.privacy=x;
	}
	public void setDob(String x)
	{
		 this.dob=x;
	}
	
	
	public void setGroup(String x)
	{
		 this.group=x;
	}
	
	
	public void setGender(String x)
	{
		 this.gender=x;
	}
	
	
	public void setPhone(String x)
	{
		 this.phone=x;
	}
	
	
	public void setEmail(String x)
	{
		 this.email=x;
	}
	
	
	public void setState(String x)
	{
		 this.state=x;
	}
	
	
	public void setCity(String x)
	{
		 this.city=x;
	}
	
	
	public void setPlace(String x)
	{
		 this.place=x;
	}
	public void setLattitude(double x)
	{
		this.lat=x;
	}
	public void setLongitude(double x)
	{
		this.lon=x;
	}
	public void setDistance(double x)
	{
		this.distance=x;
	}
}
