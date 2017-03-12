package com.blood.plus;

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
public class Register {

	String username;
	HttpURLConnection connection;
	URL url;
	InputStream in;
	DataOutputStream out;
	StringBuilder param;
	String response;
	String Spassword,Sname,Sphone,Semail,Sstate,Scity,Splace,Sbloodgroup,Sdob,Sgender,Sage;
	String post;
	boolean post_what;
	
	public Register(String name,String phone,String email,String state,String city,String place,String bloodgroup,String dob,String gender,String password) throws MalformedURLException {
		// TODO Auto-generated constructor stub
		this.Sname=name;
		this.Sphone=phone;
		this.Semail=email;
		this.Sstate=state;
		this.Scity=city;
		this.Splace=place;
		this.Sbloodgroup=bloodgroup;
		this.Sdob=dob;
		this.Sgender=gender;
		this.Spassword=password;
		this.post_what=true;
		this.url=new URL("http://www.vsss.16mb.com//blood//insert.php");
	}
	
	public Register(String name,String phone,String email,String bloodgroup,String age,String gender) throws MalformedURLException {
		// TODO Auto-generated constructor stub
		this.Sname=name;
		this.Sphone=phone;
		this.Semail=email;
		this.Sbloodgroup=bloodgroup;
		this.Sage=age;
		this.Sgender=gender;
		this.post_what=false;
		this.url=new URL("http://www.vsss.16mb.com//blood//insert.php");
	}

	public String run()
	{
		try {
			connection=(HttpURLConnection) url.openConnection();
			
			connection.setRequestMethod("POST");
			connection.setDoInput(true);
			connection.setDoOutput(true);
			out=new DataOutputStream(connection.getOutputStream());
			//connection.setRequestProperty("Content-Type", )
			
			post();
			
			
			
			out.writeBytes(post);
			out.flush();
			out.close();
			System.out.println(connection.getResponseCode());
			System.out.println(post);
			in=connection.getInputStream();
			JsonReader reader=new JsonReader(new InputStreamReader(in,"UTF-8"));
			reader.setLenient(true);
		 response= reader.nextString();

			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
			connection.disconnect();
		}
		//System.out.println(response);
		return response;
	}

	public void post() throws UnsupportedEncodingException
	{
		if(post_what)
		post="name="+URLEncoder.encode(Sname, "UTF-8")+
			    "&phone="+URLEncoder.encode(Sphone, "UTF-8")+
				"&email="+URLEncoder.encode(Semail, "UTF-8")+
				"&state="+URLEncoder.encode(Sstate, "UTF-8")+
				"&city="+URLEncoder.encode(Scity, "UTF-8")+
				"&password="+URLEncoder.encode(Spassword, "UTF-8")+
				"&place="+URLEncoder.encode(Splace, "UTF-8")+
				"&bloodgroup="+URLEncoder.encode(Sbloodgroup, "UTF-8")+
				"&dob="+URLEncoder.encode(Sdob, "UTF-8")+
				"&gender="+URLEncoder.encode(Sgender, "UTF-8");
		else
			post="name="+URLEncoder.encode(Sname, "UTF-8")+
					"&email="+URLEncoder.encode(Semail, "UTF-8")+
		    "&phone="+URLEncoder.encode(Sphone, "UTF-8")+
			"&bloodgroup="+URLEncoder.encode(Sbloodgroup, "UTF-8")+
			"&age="+URLEncoder.encode(Sage, "UTF-8")+
			"&gender="+URLEncoder.encode(Sgender, "UTF-8");
	}

	
}
