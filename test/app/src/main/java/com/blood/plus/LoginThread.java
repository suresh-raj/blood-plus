package com.blood.plus;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import android.annotation.SuppressLint;
import android.util.JsonReader;

@SuppressLint("NewApi")
public class LoginThread{
	String email,password;
	private HttpURLConnection connection;
	private DataOutputStream out;
	private String post;
	private URL url;
	private BufferedReader in;
	private String response;
	public LoginThread(String Semail,String Spassword)
	{
		email=Semail;
		password=Spassword;
		
	}
	
	public String login()
	{
		
		try{
			url=new URL("http://www.vsss.16mb.com//blood//login.php");
		connection=(HttpURLConnection) url.openConnection();
		
		connection.setRequestMethod("POST");
		connection.setDoInput(true);
		connection.setDoOutput(true);
		out=new DataOutputStream(connection.getOutputStream());
		post="&email="+URLEncoder.encode(email, "UTF-8")+
		"&password="+URLEncoder.encode(password, "UTF-8");
		
		
		
		out.writeBytes(post);
		out.flush();
		out.close();
		System.out.println(connection.getResponseCode());
		
		
		JsonReader reader=new JsonReader(new InputStreamReader(connection.getInputStream(),"UTF-8"));
		reader.setLenient(true);		
		response=reader.nextString();
		
		Update.Scity="";
		Update.Sstate="";
		Update.Splace="";
		Update.Sphone="";
		Update.Savailable="";
		
		
		
		reader.beginObject();
			if(reader.nextName().equals("phone"));
			Update.Sphone=reader.nextString();
      

		 String x = reader.nextName();
		  if(x.equals("state"))
			Update.Sstate=reader.nextString();

		 x=reader.nextName();
		if(x.equals("city"))
			Update.Scity=reader.nextString();

		 x=reader.nextName();
		if(x.equals("place"))
			Update.Splace=reader.nextString();
		
		 x=reader.nextName();
			if(x.equals("available"))
				Update.Savailable=reader.nextString();
		
		}catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		}
		finally{
			connection.disconnect();
			Update.Semail=email;
			Update.Spassword=password;
		}
		return response;
	}


}
