package com.blood.plus;

import java.io.InputStream;
import java.net.URL;

import com.google.android.gms.plus.Plus;
import com.google.android.gms.plus.model.people.Person;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class SignedinFragment extends Fragment implements View.OnClickListener {
	@Override
	public void onPrepareOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		super.onPrepareOptionsMenu(menu);
	//	MenuItem item=menu.findItem(R.id.update);
	//	MenuItem item1=menu.findItem(R.id.signout);
	//	item.setVisible(true);
	///.setVisible(true);
	//	getActivity().invalidateOptionsMenu();
	}



	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		// TODO Auto-generated method stub
		super.onCreateOptionsMenu(menu, inflater);
		inflater.inflate(R.menu.update, menu);
		//menu.add(arg0)
	}



	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stu
		
		
		switch(item.getItemId())
		{
		case R.id.signout:
			signout();
			return true;
		case R.id.update:
			validateclass phn=new validateclass(phone.getText().toString());
			if(phn.phoneValid())
			{
			update(); 
			}else
			{
				Toast.makeText(getActivity(), "invalid Phone number", Toast.LENGTH_LONG).show();
			}
			return true;
		}
		return  super.onOptionsItemSelected(item);
	}



	public SignedinFragment() {
		
	}
	
	LocationManager manager;
	LocationListener listener;
	Location location=null;
	TextView lat,lon;
	  private static boolean REGISTER=true;
	  AlertDialog alert_blood;
	Button signout;
	public GplusSigninFragment mGplusSigninFragment;
	private Bitmap dp_image;
	private Bitmap cover_image;
	protected ProgressDialog mProgressDialog;
	protected ImageView dp;
	protected ImageView cover;
	protected TextView name;
	protected TextView age;
	protected TextView email;
	protected Switch available;
	protected Switch privacy;
	protected TextView gender;
	protected EditText phone;
	String mail;
	ImageView gps;
	protected SearchThread donor_details;
	private Button update;
	private AlertDialog.Builder builder1;
	private AlertDialog alert11;
	private AlertDialog.Builder alertDialog;
	public ProgressBar progess;
	@Override
	public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onViewCreated(view, savedInstanceState);
		//checkRegister();
		manager= (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
		alertDialog = new AlertDialog.Builder(getActivity());
		signout=(Button) view.findViewById(R.id.sign_out_button);
		dp=(ImageView) view.findViewById(R.id.dp);
		cover=(ImageView) view.findViewById(R.id.cover);
		name=(TextView) view.findViewById(R.id.name);
		age=(TextView) view.findViewById(R.id.age);
		gender=(TextView) view.findViewById(R.id.gender);
		email=(TextView) view.findViewById(R.id.email);
		available=(Switch) view.findViewById(R.id.available);
		privacy=(Switch) view.findViewById(R.id.privacy);
		phone=(EditText) view.findViewById(R.id.phone);
		update=(Button) view.findViewById(R.id.button_update);
		lat=(TextView) view.findViewById(R.id.textView2);
		lon=(TextView) view.findViewById(R.id.textView3);
		gps=(ImageView)view.findViewById(R.id.gps);
		progess=(ProgressBar)view.findViewById(R.id.progressbar);
		mProgressDialog=new ProgressDialog(getActivity());
		mProgressDialog.setMessage("signing in..");
		builder1 = new AlertDialog.Builder(getActivity().getApplicationContext());
		signout.setOnClickListener(this);
		update.setOnClickListener(this);
		signout.setEnabled(true);
		lat.setText("Lattitude");
		lon.setText("Longitude");
		 final Person currentUser = Plus.PeopleApi.getCurrentPerson(GplusSigninFragment.mGoogleApiClient);
		    mail = Plus.AccountApi.getAccountName(GplusSigninFragment.mGoogleApiClient);
		    name.setText("Name    : "+String.valueOf(currentUser.getDisplayName()));
		    String temp_age=currentUser.getAgeRange().toString();
		    age.setText("Age    : "+temp_age.subSequence(7, 9));
		    if(currentUser.getGender()==0)
		    {
		    	gender.setText("Gender    : male");
		    }else{
		    	gender.setText("Gender    : female");
		    }
		    
		    email.setText(mail);
		    listener=new LocationListener(){

				@Override
				public void onLocationChanged(Location arg0) {
					// TODO Auto-generated method stub
				//	lat.setText(String.valueOf(arg0.getLatitude()));
					//lon.setText(String.valueOf(arg0.getLongitude()));
					//data=Uri.parse("geo:"+arg0.getLatitude()+","+arg0.getLongitude()+"?q="+arg0.getLatitude()+","+arg0.getLongitude());
				}

				@Override
				public void onProviderDisabled(String arg0) {
					// TODO Auto-generated method stub
					
					//Toast.makeText(getActivity().getApplicationContext(), "GPS turned off", Toast.LENGTH_LONG).show();
				}

				@Override
				public void onProviderEnabled(String arg0) {
					// TODO Auto-generated method stub
					Toast.makeText(getActivity().getApplicationContext(), "GPS turned on", Toast.LENGTH_LONG).show();
					while(manager==null){;}
					location=manager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
					if(location==null)
					{
						Toast.makeText(getActivity().getApplicationContext(), "wait for location to be set by GPS", Toast.LENGTH_LONG);
					}else{
						lat.setText(String.valueOf(location.getLatitude()));
						lon.setText(String.valueOf(location.getLongitude()));
						
					}
								
				}

				@Override
				public void onStatusChanged(String arg0, int arg1, Bundle arg2) {
					// TODO Auto-generated method stub
					//Toast.makeText(getApplicationContext(), "status changed", Toast.LENGTH_LONG).show();
				}
				
			};
			
			//manager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, listener);
			manager.requestSingleUpdate(LocationManager.GPS_PROVIDER, listener,null);
				showSettingsAlert();
			while(manager==null){System.out.println("manager null");}
			location=manager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
			if(location==null)
			{
				Toast.makeText(getActivity().getApplicationContext(), "wait for location to be set by GPS", Toast.LENGTH_LONG);
			}else{
				lat.setText(String.valueOf(location.getLatitude()));
				lon.setText(String.valueOf(location.getLongitude()));	
			} 
			
			updateLocation();
		    
		    ConnectivityManager cm =
			        (ConnectivityManager)getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
			 
			NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
			boolean isConnected = activeNetwork != null &&
			       activeNetwork.isConnectedOrConnecting();
			boolean GPSON=manager.isProviderEnabled(LocationManager.GPS_PROVIDER);
			if(isConnected&GPSON)
			{
		    new AsyncTask<String,String,String>(){


				

				

				@Override
				protected String doInBackground(String... arg0) {
					try
					{
						 try{
							 donor_details=new SearchThread(mail);
					    	 donor_details.run();
							 String url=currentUser.getImage().getUrl().toString()+"0";
							 System.out.println(url);
							  dp_image=BitmapFactory.decodeStream((InputStream) new URL(url).getContent());
						    	 cover_image=BitmapFactory.decodeStream((InputStream) new URL(currentUser.getCover().getCoverPhoto().getUrl()).getContent());
						    	 
						    	  
						    
						    }catch(Exception e)
						    {
						    	e.printStackTrace();
						    }
						
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
					
			   	 if(donor_details.getPrivacy().equalsIgnoreCase("yes"))
		    	 {
		    		 privacy.setChecked(true);
		    	 }else
		    	 {
		    		 privacy.setChecked(false);
		    	 }
		    	 if(donor_details.getAvailable().equalsIgnoreCase("yes"))
		    	 {
		    		 available.setChecked(true);
		    	 }else
		    	 {
		    		 available.setChecked(false);
		    	 }	
						phone.setText(donor_details.getPhone());
		    	 if(mProgressDialog.isShowing())
						mProgressDialog.dismiss();
		    	// checkRegister();
		    	 dp.setImageBitmap(dp_image);
			    	cover.setImageBitmap(cover_image);
			    	 progess.setVisibility(View.GONE);
				}

				@Override
				protected void onPreExecute() {
					// TODO Auto-generated method stub
					super.onPreExecute();
					//mProgressDialog.setMessage("Loading..");
					mProgressDialog.show();
				}
				
			}.execute("1","2","3");
		    
			}else{
				if(isConnected)
				Toast.makeText(getActivity(),"Internet disconnected",Toast.LENGTH_SHORT).show();
				if(GPSON)
					showSettingsAlert();
				
			}
		
			gps.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View arg0) {
					// TODO Auto-generated method stub
					updateLocation();
				}
			});
	}

	
	
	protected void checkRegister() {
		// TODO Auto-generated method stub
		if(SignedinFragment.REGISTER)
		{
			SignedinFragment.REGISTER=false;
			register();
		}
	}



	private void register() {
		// TODO Auto-generated method stub
		Intent i=new Intent(getActivity(),RegFinal.class);
		startActivity(i);
	}



	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreateView(inflater, container, savedInstanceState);
		 View rootView = inflater.inflate(R.layout.fragment_main, container, false);
		 setHasOptionsMenu(true);
         return rootView;
}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		switch(arg0.getId())
		{
		case R.id.sign_out_button:
			signout();
			break;
		case R.id.button_update:
			update();
			break;
		}
	}

	private void signout() {
		// TODO Auto-generated method stub
		mGplusSigninFragment =new GplusSigninFragment("no");
		 if (!GplusSigninFragment.mGoogleApiClient.isConnecting()) {
			 
			 		
			
		 if (mGplusSigninFragment.mGoogleApiClient.isConnected()) {
			 Plus.AccountApi.clearDefaultAccount(mGplusSigninFragment.mGoogleApiClient);
			 GplusSigninFragment.mGoogleApiClient.disconnect();
			
		    }
		 }
		 
		 getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container,mGplusSigninFragment).commit();
	}



	public void update( )
	{
		 ConnectivityManager cm =
			        (ConnectivityManager)getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
			 
			NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
			boolean isConnected = activeNetwork != null &&
			       activeNetwork.isConnectedOrConnecting();
			if(isConnected)
			{
		    new AsyncTask<String,String,String>(){


				private String priv;
				private String avail;
				private String response;

				public void getPrivacy()
				{
					if(privacy.isChecked()){
						priv="yes";
					}else
					{
						priv="no";
					}
				}
				public void getAvailablity()
				{
					if(available.isChecked()){
						avail="yes";
						
					}else{
						avail="no";
					}
				}
				

				@Override
				protected String doInBackground(String... arg0) {
					try
					{
						 try{
							 getPrivacy();
							 getAvailablity();
						    UpdateThread update_donor=new UpdateThread(avail,priv,phone.getText().toString(),email.getText().toString());
						   response= update_donor.update();
						    }catch(Exception e)
						    {
						    	e.printStackTrace();
						    }
						
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
		    	 Toast.makeText(getActivity(), response, Toast.LENGTH_LONG).show();
				}

				@Override
				protected void onPreExecute() {
					// TODO Auto-generated method stub
					super.onPreExecute();
					mProgressDialog.setMessage("updating...");
					mProgressDialog.show();
				}
				
			}.execute("1","2","3");
		    
			}else{
				Toast.makeText(getActivity(),"Internet disconnected",Toast.LENGTH_SHORT).show();
			}
	}
	public void showSettingsAlert() {
		
		ConnectivityManager cm =
		        (ConnectivityManager)getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
		 
		NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
		boolean isConnected = activeNetwork != null &&
		       activeNetwork.isConnectedOrConnecting();
		boolean isGPSEnabled = manager
	            .isProviderEnabled(LocationManager.GPS_PROVIDER);

		if(!isGPSEnabled)
		{
	    

	    // Setting DialogHelp Title
	    alertDialog.setTitle("GPS settings");

	    // Setting DialogHelp Message
	    alertDialog
	            .setMessage("GPS is not enabled. Do you want to go to settings menu?");

	    // On pressing Settings button
	    alertDialog.setPositiveButton("Settings",
	            new DialogInterface.OnClickListener() {
	                public void onClick(DialogInterface dialog, int which) {
	                    Intent intent = new Intent(
	                            Settings.ACTION_LOCATION_SOURCE_SETTINGS);
	                    startActivity(intent);
	                }
	            });

	    // on pressing cancel button
	    alertDialog.setNegativeButton("Cancel",
	            new DialogInterface.OnClickListener() {
	                public void onClick(DialogInterface dialog, int which) {
	                    dialog.cancel();
	                }
	            });

	    // Showing Alert Message
	    alertDialog.show();
		}
		
		if(!isConnected){
			Toast.makeText(getActivity().getApplicationContext(), "Mobile data is off",Toast.LENGTH_LONG).show();
		}
	}

	public void setLocationIfGPS_ON()
	{
		
			if(manager.isProviderEnabled(LocationManager.GPS_PROVIDER)){
		location=manager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
		if(location!=null){
			lat.setText(String.valueOf(location.getLatitude()));
			lon.setText(String.valueOf(location.getLongitude()));
		}

		}
	}

	public void updateLocation()
	{

		   try{
			  
		ConnectivityManager cm =
		        (ConnectivityManager)getActivity().getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
		 
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
					
					LocationUpdateThread location_update=new LocationUpdateThread(lat.getText().toString(),lon.getText().toString(),mail);
					location_update.update();
					
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
				
					
					
				
			}

			@Override
			protected void onPreExecute() {
				// TODO Auto-generated method stub
				super.onPreExecute();
				( mProgressDialog).setMessage("updating location..");
				mProgressDialog.show();
			}
			
		}.execute("1","2","3");
				  

		}
		else
		{
			Toast.makeText(getActivity(), "No Internet Connection", Toast.LENGTH_SHORT).show();
		}
		
		
		
		
		   }
	        catch(Exception e)
	        {
				e.printStackTrace();

	        }
		
	}
}