package com.blood.plus;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

import android.annotation.SuppressLint;
import android.util.JsonReader;

@SuppressLint("NewApi")
public class UpdateThread {

	String username;
	HttpURLConnection connection;
	URL url;
	BufferedReader in;
	DataOutputStream out;
	StringBuilder param;
	String response;
	String Spassword,Sname,Sphone,Semail,Sstate,Scity,Splace,Savailable;
	String post;
	String lat,lon;
	private String email;
	private String available;
	private String privacy;
	private String phone; 
	
	public UpdateThread(String mail,String password,String phone,String state,String city,String avail) throws MalformedURLException, UnsupportedEncodingException {
		// TODO Auto-generated constructor stub
		
		
		Sphone=phone;
		Semail=mail;
		Sstate=state;
		Scity=city;
		//Splace=place;
		Spassword=password;
		Savailable=avail;
		url=new URL("http://www.vsss.16mb.com//blood//update.php");
		post= "&phone="+URLEncoder.encode(Sphone, "UTF-8")+
				"&email="+URLEncoder.encode(Semail, "UTF-8")+
				"&state="+URLEncoder.encode(Sstate, "UTF-8")+
				"&city="+URLEncoder.encode(Scity, "UTF-8")+
				"&available="+URLEncoder.encode(Savailable, "UTF-8")+
				"&password="+URLEncoder.encode(Spassword, "UTF-8");
				
	}
	
	public UpdateThread(String lat,String lon,String email){
		
		this.lat=lat;
		this.lon=lon;
		this.email=email;
		try {
			url=new URL("http://www.vsss.16mb.com//blood//location_update.php");
		
		
		post= "&lattitude="+URLEncoder.encode(lat, "UTF-8")+
				"&longitude="+URLEncoder.encode(lon, "UTF-8")+
				"&email="+URLEncoder.encode(email, "UTF-8");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

public UpdateThread(String avail,String privacy,String phone,String email){
		
		this.available=avail;
		this.privacy=privacy;
		this.email=email;
		this.phone=phone;
		
		try {
			url=new URL("http://www.vsss.16mb.com//blood//update.php");
		
		
			post= "&phone="+URLEncoder.encode(this.phone, "UTF-8")+
					"&email="+URLEncoder.encode(this.email, "UTF-8")+
					"&privacy="+URLEncoder.encode(this.privacy, "UTF-8")+
					"&available="+URLEncoder.encode(this.available, "UTF-8");
				
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public String update()
	{
		try {
			connection=(HttpURLConnection) url.openConnection();
			
			connection.setRequestMethod("POST");
			connection.setDoInput(true);
			connection.setDoOutput(true);
			out=new DataOutputStream(connection.getOutputStream());
			out.writeBytes(post);
			out.flush();
			out.close();
			System.out.println(connection.getResponseCode());
			in=new BufferedReader(new InputStreamReader(connection.getInputStream()));
			//in=connection.getInputStream();
			//JsonReader reader=new JsonReader(new InputStreamReader(in,"UTF-8"));
		//	reader.setLenient(true);
		 response= in.readLine();

			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
			connection.disconnect();
		}
		System.out.println(response);
		return response;
	}

	

	
}
