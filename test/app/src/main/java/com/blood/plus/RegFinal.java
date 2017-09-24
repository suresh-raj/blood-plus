package com.blood.plus;

import java.net.MalformedURLException;

import com.google.android.gms.plus.Plus;
import com.google.android.gms.plus.model.people.Person;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Spinner;
import android.widget.Toast;

public class RegFinal extends Fragment {
	public node donor;
	


private EditText phoneno;
	private Spinner blood_spinner;
	ArrayAdapter<CharSequence> adapter;


	private Button register;



	private String phone;



	private String blood;



	private String name;



	private String age;



	private String email;



	private String gender;
	
	
	public RegFinal() {
		// TODO Auto-generated constructor stub
	}
	
	
	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreateView(inflater, container, savedInstanceState);
		return inflater.inflate(R.layout.register_final, container, false);
	}

	@Override
	public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onViewCreated(view, savedInstanceState);
		phoneno=(EditText)view.findViewById(R.id.editText1);
		blood_spinner=(Spinner)view.findViewById(R.id.spinner1);
		register=(Button)view.findViewById(R.id.button1);
		
		adapter=ArrayAdapter.createFromResource(getActivity(), R.array.bloodgroup, android.R.layout.simple_spinner_item);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		blood_spinner.setAdapter(adapter);
		register.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				validateclass phn=new validateclass(phoneno.getText().toString());
				if(phn.phoneValid())
				{
				getInfo();
				register();
				}
				else{
					Toast.makeText(getActivity(), "Invalid phone numbr", Toast.LENGTH_LONG).show();
				}
			}
		});
		
	}
	
	protected void getInfo() {
		// TODO Auto-generated method stub
		phone=phoneno.getText().toString();
		blood=blood_spinner.getSelectedItem().toString();
		
		//name=bundle.getString("name");
		//age=bundle.getString("age");
	//	email=bundle.getString("email");
	//	gender=bundle.getString("gender");
		 final Person currentUser = Plus.PeopleApi.getCurrentPerson(register_fragment.mGoogleApiClient);
		    email = Plus.AccountApi.getAccountName(register_fragment.mGoogleApiClient);
		    name=String.valueOf(currentUser.getDisplayName());
		    String temp_age=currentUser.getAgeRange().toString();
		    age=(String) temp_age.subSequence(7, 9);
		    if(currentUser.getGender()==0)
		    {
		    	gender="male";
		    }else{
		    	gender="female";
		    }
		    
		System.out.println(name);
		System.out.println(phone);
		System.out.println(email);
		System.out.println(age);
		System.out.println(blood);
		System.out.println(gender);
	
	}
	protected void register() {
		// TODO Auto-generated method stub
		
		new AsyncTask<String,String,String>(){
			String response;
			@Override
			protected void onPostExecute(String result) {
				// TODO Auto-generated method stub
				super.onPostExecute(result);
				Toast.makeText(getActivity().getApplicationContext(), response, Toast.LENGTH_LONG).show();
				 getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container,new GplusSigninFragment()).commit();
			}

			@Override
			protected void onPreExecute() {
				// TODO Auto-generated method stub
				super.onPreExecute();
			}

			@Override
			protected String doInBackground(String... arg0) {
				// TODO Auto-generated method stub
				
				try {
					Register reg = new Register(name, phone, email, blood, age, gender);
					response=reg.run();
					
				} catch (MalformedURLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
				return null;
			}
			
		}.execute("1","2","3");
	}
	
	
}
