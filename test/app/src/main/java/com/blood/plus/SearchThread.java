package com.blood.plus;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

public class SearchThread{
	

	private String group,state,city,place,lat,lon;
	private HttpURLConnection connection;
	private DataOutputStream out;
	private String post;
	private URL url;
	private BufferedReader in;
	private String email;
	private String temp;
	private String search_what="all";
	private String privacy;
	private String available;
	private String phone="phone";
	public SearchThread(String group,String state,String city,String lat,String lon)
	{
		this.group=group;
		this.state=state;
		this.place=place;
		this.city=city;
		this.lat=lat;
		this.lon=lon;
		this.search_what="all";
		try {
			url=new URL("http://www.vsss.16mb.com//blood//select_donors.php");
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public SearchThread(String email)
	{
		this.email=email;
		search_what="one";
		this.privacy="no";
		this.available="no";
		try {
			url=new URL("http://www.vsss.16mb.com//blood//select_donor.php");
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void run()
	{
		
		try{
			
			
		connection=(HttpURLConnection) url.openConnection();
		
		connection.setRequestMethod("POST");
		connection.setDoInput(true);
		connection.setDoOutput(true);
		out=new DataOutputStream(connection.getOutputStream());
		//connection.setRequestProperty("Content-Type", )
		post();
		
		
		
		
		out.writeBytes(post);
		System.out.println(post);
		out.flush();
		out.close();
		System.out.println(connection.getResponseCode());
		//
	retrive();
		
		}catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		}
		finally{
			connection.disconnect();
			
		}
	}
	
	public void post()
	{
		try{
		if(search_what.equals("one"))
		{
			post="&email="+URLEncoder.encode(email, "UTF-8")+
					"&search="+URLEncoder.encode("one", "UTF-8");
		}else{
			post="&bloodgroup="+URLEncoder.encode(group, "UTF-8")+
					"&lattitude="+URLEncoder.encode(lat, "UTF-8")+
					"&longitude="+URLEncoder.encode(lon, "UTF-8")+
					"&city="+URLEncoder.encode(city, "UTF-8")+
					"&state="+URLEncoder.encode(state, "UTF-8")+
					"&search="+URLEncoder.encode("all", "UTF-8");;
		}
	} catch (UnsupportedEncodingException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	}
	
	public void retrive()
	{
		if(search_what.equals("all"))
		{
			try {
				JsonParser get=new JsonParser(connection.getInputStream());
				SearchFragment.populate.notifyDataSetChanged();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}else
		{
			try {
				String temp;
				in=new BufferedReader(new InputStreamReader(connection.getInputStream()));
				//while((temp=in.readLine())!=null)
			//	{
				//	System.out.println(temp);
			//	}
				if(in!=null)
				{
					if(!in.readLine().equals("0")){
						
					
					available=in.readLine();
					System.out.println(available);
					System.out.println("--------------------------------");
					privacy=in.readLine();
					System.out.println(privacy);
					System.out.println("--------------------------------");
					phone=in.readLine();
					System.out.println(phone);
					System.out.println("--------------------------------");
					}
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPrivacy() {
		return privacy;
	}

	public void setPrivacy(String privacy) {
		this.privacy = privacy;
	}

	public String getAvailable() {
		return available;
	}

	public void setAvailable(String available) {
		this.available = available;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
}