package com.blood.plus;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.net.URI;





import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SearchFragment extends Fragment {
	static DonarAdapter populate;
	LocationManager manager;
	LocationListener listener;
	Location location=null;
	TextView lon,lat;
	Intent map_intent;
	Uri data;
	Button search;
	AlertDialog.Builder alertDialog;
	public ProgressDialog mProgressDialog;
	private EditText mail;
	private Spinner stateSpinner;
	private Spinner citySpinner;
	private SpinnerAdapter spinnerAdapter;
	@TargetApi(Build.VERSION_CODES.GINGERBREAD)

	public SearchFragment()
	{
		
	}

	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreateView(inflater, container, savedInstanceState);
		View rootView = inflater.inflate(R.layout.search, container, false);
        return rootView;
	}

	@TargetApi(Build.VERSION_CODES.GINGERBREAD)
	@Override
	public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onViewCreated(view, savedInstanceState);
		alertDialog = new AlertDialog.Builder(getActivity());
		mProgressDialog = new ProgressDialog(getActivity());
		   mProgressDialog.setIndeterminate(false);
		   mProgressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
		final Spinner bloodgroup=(Spinner) view.findViewById(R.id.bloodSpinner);
		ArrayAdapter<CharSequence> group_adapter= ArrayAdapter.createFromResource(getActivity(),R.array.bloodgroup, android.R.layout.simple_spinner_item);
		group_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		bloodgroup.setAdapter(group_adapter);
		lat=(TextView) view.findViewById(R.id.textView2);
		lon=(TextView) view.findViewById(R.id.textView3);
		stateSpinner=(Spinner)view.findViewById(R.id.zoneSpinner);
		citySpinner=(Spinner)view.findViewById(R.id.city);
		spinnerAdapter=new SpinnerAdapter(getActivity());
		stateSpinner.setAdapter(spinnerAdapter.getAdapter(R.id.zoneSpinner));
		citySpinner.setAdapter(spinnerAdapter.getAdapter(R.id.citySpinner));
		manager= (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
		search=(Button) view.findViewById(R.id.button1);
		stateSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				// TODO Auto-generated method stub
                citySpinner.setAdapter(spinnerAdapter.getAdapterFor(arg2));
				
                spinnerAdapter.adapter_city.notifyDataSetChanged();
				
				
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				
			}
		});
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
		
		
		
		map_intent=new Intent(Intent.ACTION_VIEW);

	
	
	
	/*show.setOnClickListener(new OnClickListener(){

		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub

			
			map_intent.setData(data);
			startActivity(map_intent);
		}
		
	}); */
	
	
	search.setOnClickListener(new View.OnClickListener() {

		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub

						
			
			
			
			

			   
			
			   try{
				  
			ConnectivityManager cm =
			        (ConnectivityManager)getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
			 
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
						Log.i("bloodgroup spinner", bloodgroup.getSelectedItem().toString());
						SearchThread search=new SearchThread(bloodgroup.getSelectedItem().toString(),"","",lat.getText().toString(),lon.getText().toString());
					search.run();
						
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
					findDistances();
					
						Intent temp=new Intent(getActivity(),search_result.class);
						startActivity(temp);
						//SearchFragment.populate.notifyDataSetChanged();
						
					
				}

				@Override
				protected void onPreExecute() {
					// TODO Auto-generated method stub
					super.onPreExecute();
				//	( mProgressDialog).setMessage("Loading..");
					mProgressDialog.show();
				}
				
			}.execute("1","2","3");
					  

			}
			else
			{
				Toast.makeText(getActivity().getApplicationContext(), "No Internet Connection", Toast.LENGTH_SHORT).show();
			}
			
			
			
			
			   }
		        catch(Exception e)
		        {
					e.printStackTrace();

		        }
			
			
			
			
			
		}
	}); 
    
//	setLocationIfGPS_ON();
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
	
public void findDistances()
{
	int list_size = JsonParser.person.size();
	float[] result = new float[1];
	for(int i=0;i<list_size;i++)
	{
		Location.distanceBetween(location.getLatitude(), location.getLongitude(), JsonParser.person.get(i).getLattitude(),JsonParser.person.get(i).getLongitude() ,result );
		JsonParser.person.get(i).setDistance(result[0]/1000);
	}
	
}
}
