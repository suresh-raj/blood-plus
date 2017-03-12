package com.blood.plus;



import java.net.URI;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.telephony.SmsManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemLongClickListener;

@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class search_result extends ActionBarActivity {
	
	
	ListView list;
	private Context context;
	private DonarAdapter populate;
	private double lat=0,lon=0;
	private String phone=null;
	private String email=null;
	private String privacy="no";
	private boolean item_selected=false;
	private Uri data;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.search_result);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		context=getApplicationContext();
		list=(ListView) findViewById(R.id.list);
		populate=new DonarAdapter(context,JsonParser.person);
		list.setAdapter(populate);
		list.setOnItemClickListener(new OnItemClickListener() {
			
			

			public void onItemClick(AdapterView<?> arg0,
					View arg1, int arg2, long arg3) {
				// TODO Auto-generated method stub
				//TextView phone_view=(TextView) arg1.findViewById(R.id.textView6);
			//Intent intent=new Intent(Intent.ACTION_CALL,Uri.parse("tel:"+phone_view.getText().toString()));
			
			if(!item_selected)
			{
			item_selected=true;
			invalidateOptionsMenu();
			}
			lat=JsonParser.person.get(arg2).getLattitude();
			lon=JsonParser.person.get(arg2).getLongitude();
			phone=JsonParser.person.get(arg2).getPhone();
			email=JsonParser.person.get(arg2).getEmail();
			privacy=JsonParser.person.get(arg2).getPrivacy();
			//arg1.setBackgroundColor(110022);
			//Intent dial=new Intent(search_result.this,FakeScreen.class);
		//	dial.setFlags(Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT);	
			//startActivity(intent);
			//sstartActivity(dial);
				
			
				
			}
		});
		
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		 super.onCreateOptionsMenu(menu);
		 if(item_selected)
		 getMenuInflater().inflate(R.menu.result_action, menu);
		 
		 return true;
		 
	}
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		 //super.onOptionsItemSelected(item);
		 switch(item.getItemId())
		 {
		 case R.id.call:
			 if(privacy.equals("no"))
			 {
			 Intent call_intent=new Intent(Intent.ACTION_CALL, Uri.parse("tel:"+phone));
			 startActivity(call_intent);
			 }else
			 {
				 Toast.makeText(getApplicationContext(), "Donor opted not to call facility\n\n you can send sms/email", Toast.LENGTH_LONG).show();
			 }
			 break;
		 case R.id.locate:
			// data=Uri.parse("geo:"+lat+","+lon+"?q="+lat+","+lon+"(Donor)");
			 data=Uri.parse("http://maps.google.com/maps?daddr="+lat+","+lon);
			 Intent map_intent=new Intent(Intent.ACTION_VIEW);
			 map_intent.setData(data);
			startActivity(map_intent);
			break;
		 case R.id.sms:
			 SmsManager smsManager=SmsManager.getDefault();
			 smsManager.sendTextMessage(phone, null, "Blood Required.kindly contact back since you hav opted not to distrub facility", null, null);
			 Toast.makeText(getApplicationContext(), "SMS sent succesfully", Toast.LENGTH_LONG).show();
			 break;
		 case R.id.email:
			 Intent email_intent=new Intent(Intent.ACTION_SENDTO);
			 email_intent.setData(Uri.parse("mailto:"+email)); // only email apps should handle this
			   // email_intent.putExtra(Intent.EXTRA_EMAIL,email);
			    email_intent.putExtra(Intent.EXTRA_SUBJECT, "Blood required ");
			    if (email_intent.resolveActivity(getPackageManager()) != null) {
			        startActivity(email_intent);
			    }
			 break;
			 
		 }
		 return true;
	}

}
