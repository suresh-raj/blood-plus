package com.blood.plus;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

import android.widget.Toast;


public class LocationUpdateThread {

	private String lat,lon;
	private HttpURLConnection connection;
	private URL url;
	private DataOutputStream out;
	private String post;
	private String response;
	private BufferedReader in;
	private String email;
	
	public LocationUpdateThread() {
		// TODO Auto-generated constructor stub
	}

	public  LocationUpdateThread(CharSequence charSequence,CharSequence charSequence2,CharSequence charSequence3)
	{
		this.lat=(String) charSequence;
		this.lon=(String) charSequence2;
		this.email=(String) charSequence3;
		
	}

	public void update() {
		// TODO Auto-generated method stub
		try{
			url=new URL("http://www.vsss.16mb.com//blood//location_update.php");
		connection=(HttpURLConnection) url.openConnection();
		
		connection.setRequestMethod("POST");
		connection.setDoInput(true);
		connection.setDoOutput(true);
		out=new DataOutputStream(connection.getOutputStream());
		post="&lattitude="+URLEncoder.encode(lat, "UTF-8")+
		"&longitude="+URLEncoder.encode(lon, "UTF-8")+
		"&email="+URLEncoder.encode(email,"UTF-8");
		
		
		
		out.writeBytes(post);
		out.flush();
		out.close();
		System.out.println(connection.getResponseCode());
		in=new BufferedReader(new InputStreamReader(connection.getInputStream()));
		while((response=in.readLine())!=null)
		{
			System.out.println(response);
			
		}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			connection.disconnect();
			
		}
		
	}
}



