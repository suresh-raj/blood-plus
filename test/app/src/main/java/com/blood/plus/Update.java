package com.blood.plus;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.View;
import android.view.ContextMenu.ContextMenuInfo;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;
public class Update  extends Activity {
	EditText phone;
	Spinner state;
	boolean first;
	 Spinner city;
	EditText place;
	ArrayAdapter<String> adapt;
	private ProgressDialog mProgressDialog;
    RadioGroup available;
    RadioButton available1;
    String response="error";
	 SpinnerAdapter spinnerAdapter;
	 public static String Sstate,Scity,Splace,Sphone,Savailable,Semail,Spassword;
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
			super.onCreate(savedInstanceState);
		setContentView(R.layout.update);
     phone=(EditText)findViewById(R.id.editText1);
     state=(Spinner)findViewById(R.id.zoneSpinner);
     city=(Spinner)findViewById(R.id.citySpinner);
     place=(EditText)findViewById(R.id.editText4);
     available=(RadioGroup)findViewById(R.id.radioGroup1);
     available1=(RadioButton)findViewById(R.id.radio0);
     first=true;
     phone.setText(Sphone);
     place.setText(Splace);
     spinnerAdapter =new SpinnerAdapter(this); 
     state.setAdapter(spinnerAdapter.getAdapter(R.id.zoneSpinner));
     int state_pos=((ArrayAdapter<String>)spinnerAdapter.getAdapter(R.id.zoneSpinner)).getPosition(Sstate);
     state.setSelection(state_pos);
     adapt=(ArrayAdapter<String>)spinnerAdapter.getAdapterFor(state_pos);
    
     city.setAdapter(adapt);
     
    
     //city.setSelection(spinnerAdapter.getAdapterFor(spinnerAdapter.getAdapter(R.id.zoneSpinner).getPosition(Sstate)).getPosition(Scity));
     //city.setSelection(4);
  //  Log.i("info", city.getSelectedItem().toString());
   
    
     
    
       if(Savailable.equals("yes"))
    	   available.check(R.id.radio0);
       else
    	   available.check(R.id.radio1);
      // city.setSelection(((ArrayAdapter<String>)spinnerAdapter.getAdapterFor(spinnerAdapter.getAdapter(R.id.zoneSpinner).getPosition(Sstate))).getPosition(Scity));
       state.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				// TODO Auto-generated method stub
				city.setAdapter(spinnerAdapter.getAdapterFor(arg2));
				
				spinnerAdapter.adapter_city.notifyDataSetChanged();
				
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
			}
		});
       city.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				// TODO Auto-generated method stub
			if(first)
			{
				city.setSelection(spinnerAdapter.getAdapterFor(spinnerAdapter.getAdapter(R.id.zoneSpinner).getPosition(Sstate)).getPosition(Scity));
				first=false;
			}
			
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				city.setSelection(spinnerAdapter.getAdapterFor(spinnerAdapter.getAdapter(R.id.zoneSpinner).getPosition(Sstate)).getPosition(Scity));
			}
		});

       Button update=(Button)findViewById(R.id.button1);
       
     	try{
		   mProgressDialog = new ProgressDialog(this);
		   mProgressDialog.setIndeterminate(false);
		   mProgressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
          }
        catch(Exception e) 
              {
	         e.printStackTrace();
             }
		
       
       update.setOnClickListener(new View.OnClickListener() {
		
		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			
			
			
			Sstate=state.getSelectedItem().toString();
			Scity=city.getSelectedItem().toString();
			Sphone=phone.getText().toString();
		//	Splace=place.getText().toString();
			if(available1.isChecked())
				Savailable="yes";
			else
				Savailable="no";

			validateclass valid=new validateclass(Sphone,Sstate,Scity,Splace);
			String errormsg= valid.updatevalid();
			if(errormsg.equals("valid"))
			{
				try{
				  
			ConnectivityManager cm =
			        (ConnectivityManager)getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
			 
			NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
			boolean isConnected = activeNetwork != null &&
			       activeNetwork.isConnectedOrConnecting();
			if(isConnected)
			{
			new AsyncTask<String,String,String>(){

				@Override
				protected String doInBackground(String... arg0) {
					try
					{
						
						UpdateThread update=new UpdateThread(Semail,Spassword,Sphone,Sstate,Scity,Savailable);
						response=update.update();
					// TODO Auto-generated method stub
				
					}catch(Exception e){
						e.printStackTrace();
					}
					return "1";
				}

				@Override
				protected void onCancelled() {
					// TODO Auto-generated method stub
					super.onCancelled();
				}

				@Override
				protected void onPostExecute(String result) {
					// TODO Auto-generated method stub
					super.onPostExecute(result);
					if(mProgressDialog.isShowing())
						mProgressDialog.dismiss();

					
						Toast.makeText(getApplicationContext(), "Your details are Updated Successfully!.", Toast.LENGTH_SHORT).show();
						finish();
					
				
						
						
					
				}

				@Override
				protected void onPreExecute() {
					// TODO Auto-generated method stub
					super.onPreExecute();
					mProgressDialog.setMessage("Updating..");
					mProgressDialog.show();
				}
				
			}.execute("1","2","3");
					  

			}
			else
			{
				Toast.makeText(getApplicationContext(), "No Internet Connection", Toast.LENGTH_SHORT).show();
			}
			
			
			
			
			   }
		        catch(Exception e)
		        {
					e.printStackTrace();

		        }
			
			}
			else
				
				Toast.makeText(getApplicationContext(), errormsg, Toast.LENGTH_SHORT).show();

				
			
			
			
		}
	});
       
       
       

}
	
	
	public void onRadioButtonClicked(View view) {
	    // Is the button now checked?
	    boolean checked = ((RadioButton) view).isChecked();
	    
	    // Check which radio button was clicked
	    switch(view.getId()) {
	        case R.id.radio0:
	            if (checked)
	               Savailable="yes";
	            break;
	        case R.id.radio1:
	            if (checked)
	                Savailable="no";
	            break;
	    }
	}
	
@Override
public void onCreateContextMenu(ContextMenu menu, View v,
		ContextMenuInfo menuInfo) {
	// TODO Auto-generated method stub
	super.onCreateContextMenu(menu, v, menuInfo);
}

@Override
public void onBackPressed() {
	// TODO Auto-generated method stub

	Intent i=new Intent(Update.this,MainActivity.class);
	startActivity(i);
	android.os.Process.killProcess(android.os.Process.myPid());
	finish();
	
	
}


@Override
protected void onPostCreate(Bundle savedInstanceState) {
	// TODO Auto-generated method stub
	super.onPostCreate(savedInstanceState);
	//city.setSelection(spinnerAdapter.getAdapterFor(spinnerAdapter.getAdapter(R.id.zoneSpinner).getPosition(Sstate)).getPosition(Scity));
	 // Log.i("info", city.getSelectedItem().toString());
	 // city.setVisibility(View.INVISIBLE);
	
}




}
