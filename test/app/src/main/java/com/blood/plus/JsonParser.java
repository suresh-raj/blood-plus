package com.blood.plus;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import android.annotation.SuppressLint;
import android.util.JsonReader;


public class JsonParser {
	
	
	public JsonReader reader;
	public static List<node> person;
	public node n;
	String name,group,place,city;
	public static int count1;
    int count;
	private String phone;
	private double lat,lon;
	private String email;
	private String privacy;
	@SuppressLint("NewApi")
	public JsonParser(InputStream in) throws Exception
	{
		
			reader=new JsonReader(new InputStreamReader(in,"UTF-8"));
			reader.setLenient(true);
			person=new ArrayList<node>();
			parse();
		
	}
	
	@SuppressLint("NewApi")
	public void parse() throws Exception
	{
		count=reader.nextInt();
		System.out.println(count);
		count1=count;
	while(count-->=0)
	{
		reader.beginObject();
		while(reader.hasNext())
		{
			String x=reader.nextName();
			if(x.equals("name"))
			{
				name=reader.nextString();
			System.out.println(name);
				
			}else if(x.equals("bloodgroup"))
			{
				group=reader.nextString();
			System.out.println(group);
			}else if(x.equals("city"))
			{
				city=reader.nextString();
				System.out.println(city);
			}else if(x.equals("phone"))
			{
				phone=reader.nextString();
			}else if(x.equals("lattitude"))
			{
				lat=reader.nextDouble();
			}else if(x.equals("longitude"))
			{
				lon=reader.nextDouble();
			}else if(x.equals("email"))
			{
				email=reader.nextString();
				System.out.println(email);
			}else if(x.equals("privacy"))
			{
				privacy=reader.nextString();
			}
			else if(x.equals("place"))
				place=reader.nextString();
			
			else{
				reader.skipValue();
			}
			
		}reader.endObject();
		store(name,group,city,phone,place,lat,lon,email,privacy);
		
			
	}
		
	}

	public void store(String Sname,String Sgroup,String Scity,String Sphone,String Splace,double Slat,double Slon,String Semail,String Sprivacy)
	{
		person.add(new node(Sname,Sgroup,Scity,Sphone,Splace,Slat,Slon,Semail,Sprivacy));
		System.out.println(person.size());
		
		
	}
	
}
