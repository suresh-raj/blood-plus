package com.blood.plus;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.CommonStatusCodes;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.plus.People;
import com.google.android.gms.plus.People.LoadPeopleResult;
import com.google.android.gms.plus.Plus;
import com.google.android.gms.plus.model.people.Person;
import com.google.android.gms.plus.model.people.PersonBuffer;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentSender.SendIntentException;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class register_fragment extends Fragment implements ConnectionCallbacks, OnConnectionFailedListener,
ResultCallback<People.LoadPeopleResult>, View.OnClickListener{
	
	 

	private static final String TAG = "android-plus-quickstart";

	  private static final int STATE_DEFAULT = 0;
	  private static final int STATE_SIGN_IN = 1;
	  private static final int STATE_IN_PROGRESS = 2;
	  private static final int RC_SIGN_IN = 0;
	  private static final int DIALOG_PLAY_SERVICES_ERROR = 0;
	  private static final String SAVED_PROGRESS = "sign_in_progress";

	  // GoogleApiClient wraps our service connection to Google Play services and
	  // provides access to the users sign in state and Google's APIs.
	  public static GoogleApiClient mGoogleApiClient;

	  // We use mSignInProgress to track whether user has clicked sign in.
	  // mSignInProgress can be one of three values:
	  //
	  //       STATE_DEFAULT: The default state of the application before the user
	  //                      has clicked 'sign in', or after they have clicked
	  //                      'sign out'.  In this state we will not attempt to
	  //                      resolve sign in errors and so will display our
	  //                      Activity in a signed out state.
	  //       STATE_SIGN_IN: This state indicates that the user has clicked 'sign
	  //                      in', so resolve successive errors preventing sign in
	  //                      until the user has successfully authorized an account
	  //                      for our app.
	  //   STATE_IN_PROGRESS: This state indicates that we have started an intent to
	  //                      resolve an error, and so we should not start further
	  //                      intents until the current intent completes.
	  public static int mSignInProgress;

	  // Used to store the PendingIntent most recently returned by Google Play
	  // services until the user clicks 'sign in'.
	  private PendingIntent mSignInIntent;

	  // Used to store the error code most recently returned by Google Play services
	  // until the user clicks 'sign in'.
	  private int mSignInError;

	  private ImageButton mSignInButton;
	  private Button mSignOutButton;
	  private Button mRevokeButton;
	  private TextView mStatus;
	  private ListView mCirclesListView;
	  private ArrayAdapter<String> mCirclesAdapter;
	  private ArrayList<String> mCirclesList;
	  private String check="yes";

	private ProgressDialog mprogressDialog;

	private URL url;

	private HttpURLConnection connection;

	private DataOutputStream out;

	private BufferedReader in;

	private String terms;

	private Spinner blood;

	private TextView term;

	private Button submit;

	private DatePicker dob;

	private EditText name;

	private RadioButton checked;

	private EditText phone;

	private EditText email;

	private Spinner state;

	private Spinner city;

	private EditText place;

	private EditText password;

	private EditText rpassword;

	private CheckBox chkbx1;

	private int year;

	private SpinnerAdapter spinnerAdapter;

	private int Vcheck;

	private ProgressDialog mProgressDialog;

	public static boolean SHOW_SIGNEDIN_FRAGMENT;

	public register_fragment() {
		// TODO Auto-generated constructor stub
	}
	
	public register_fragment(String c) {
		// TODO Auto-generated constructor stub
		this.check=c;
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreateView(inflater, container, savedInstanceState);
		 View rootView = inflater.inflate(R.layout.register_layout, container, false);
         return rootView;
	}

	@Override
	public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onViewCreated(view, savedInstanceState);
		SHOW_SIGNEDIN_FRAGMENT=false;
	    mSignInButton = (ImageButton) view.findViewById(R.id.signup_btn);
	  //  mSignOutButton = (Button) view.findViewById(R.id.sign_out_button);
	  //  mRevokeButton = (Button) view.findViewById(R.id.revoke_access_button);
	    //mStatus = (TextView) findViewById(R.id.sign_in_status);
	  //  mCirclesListView = (ListView) findViewById(R.id.circles_list);

	   // mSignInButton.setOnClickListener(this);
	    //mSignOutButton.setOnClickListener(this);
	    //mRevokeButton.setOnClickListener(this);

	 //   mCirclesList = new ArrayList<String>();
	 //   mCirclesAdapter = new ArrayAdapter<String>(
	 //       this, R.layout.circle_member, mCirclesList);
//	    mCirclesListView.setAdapter(mCirclesAdapter);
	    mSignInButton.setOnClickListener(this);
	    terms="<u>terms and conditions</u>";
		spinnerAdapter =new SpinnerAdapter(getActivity()); 
		Calendar calendar= new GregorianCalendar();
         blood = (Spinner)view.findViewById(R.id.bloodSpinner);
         term=(TextView)view.findViewById(R.id.textView14);
		submit=(Button) view.findViewById(R.id.button1);
		dob=(DatePicker)view.findViewById(R.id.datePicker1);
		name=(EditText) view.findViewById(R.id.editText1);
		checked=(RadioButton) view.findViewById(R.id.radio1);
		phone=(EditText) view.findViewById(R.id.editText2);
		email=(EditText) view.findViewById(R.id.editText3);
		state=(Spinner) view.findViewById(R.id.zoneSpinner);
		city=(Spinner) view.findViewById(R.id.city);
		place=(EditText) view.findViewById(R.id.editText6);
		password=(EditText) view.findViewById(R.id.editText7);
		rpassword=(EditText) view.findViewById(R.id.editText8);
        chkbx1=(CheckBox) view.findViewById(R.id.checkBox1);
         year=dob.getYear();
        blood.setAdapter(spinnerAdapter.getAdapter(blood.getId()));
        state.setAdapter(spinnerAdapter.getAdapter(state.getId()));
        term.setText(Html.fromHtml(terms));
        dob.setMinDate(calendar.get(Calendar.YEAR)-40);
      //  dob.setMaxDate(calendar.get(Calendar.YEAR));
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
        
       
        if(chkbx1.isChecked())
        	Vcheck=1;
        else Vcheck=0;
		
		 mProgressDialog = new ProgressDialog(getActivity());
		   mProgressDialog.setIndeterminate(false);
		   mProgressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
		   
		   submit.setOnClickListener(new View.OnClickListener() {
				
				private boolean first=true;
				private String Sbloodgroup;
				private String Sname;
				private String Sdob;
				private String Sgender;
				private String Sphone;
				private String Semail;
				private String Sstate;
				private String Scity;
				private String Splace;
				private String Spassword;
				private String Srpassword;
				private int dyear;

				@Override
				public void onClick(View arg0) {
					// TODO Auto-generated method stub
					
					Sname=name.getText().toString();
					Sdob=String.valueOf(dob.getDayOfMonth())+"/"+(String.valueOf(dob.getMonth()))+"/"+(String.valueOf(dob.getYear()));
					
					if(checked.isChecked())
						Sgender="female";
					else
					{
						Sgender="male";
					}
					
					Sbloodgroup=spinnerAdapter.getValue(blood.getId(),blood.getSelectedItemPosition());
					
					Sphone=phone.getText().toString();
					Semail=email.getText().toString();
					
					Sstate=spinnerAdapter.getValue(state.getId(),state.getSelectedItemPosition());
				//	Scity=spinnerAdapter.getValue(city.getId(),city.getSelectedItemPosition());
					Scity=city.getSelectedItem().toString();
					Splace=place.getText().toString();
					Spassword=password.getText().toString();
					Srpassword=rpassword.getText().toString();
					dyear=dob.getYear();	
					validateclass validate=new validateclass(Sname,Sphone,Semail,Sstate,Scity,Splace,Sbloodgroup,Sdob,Sgender,Spassword,Srpassword,year,dyear);
					String errormsg=validate.valid();
					ConnectivityManager cm =
					        (ConnectivityManager)getActivity().getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
					 
					NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
					boolean isConnected = activeNetwork != null &&
					       activeNetwork.isConnectedOrConnecting();
					             if(errormsg.equals("valid")&&!chkbx1.isChecked())
					            	 errormsg="Accept to be a blood donar";
					
					            
					             
					if(errormsg.equals("valid")&&chkbx1.isChecked())
					{
						if(isConnected)
					{			
					try {
						
						
						new AsyncTask<String,String,String>(){
							String response;						@Override
							protected String doInBackground(String... arg0) {
								
						
								try
								{
									
								
								// TODO Auto-generated method stub
								Register register=new Register(Sname,Sphone,Semail,Sstate,Scity,Splace,Sbloodgroup,Sdob,Sgender,Spassword);
								 response=register.run();
								
														
								}catch(Exception e){
									e.printStackTrace();
								}
								return response;
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
								mProgressDialog.setMessage("updating");
								mProgressDialog.show();
							}
							
						}.execute("wow","wow","wow");
								  
						
						
						
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					}
					else
					{
						
						Toast.makeText(getActivity(), "No Internet Connection\n", Toast.LENGTH_SHORT).show();
					}
					} else
						Toast.makeText(getActivity(), errormsg, Toast.LENGTH_SHORT).show();

					
				}
			});

	}
	
	
	
	 @Override
	  public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	   // setContentView(R.layout.gplus_signin);

	    if (savedInstanceState != null) {
	      mSignInProgress = savedInstanceState
	          .getInt(SAVED_PROGRESS, STATE_DEFAULT);
	    }

	    mGoogleApiClient = buildGoogleApiClient();
	  }

	  private GoogleApiClient buildGoogleApiClient() {
	    // When we build the GoogleApiClient we specify where connected and
	    // connection failed callbacks should be returned, which Google APIs our
	    // app uses and which OAuth 2.0 scopes our app requests.
	    return new GoogleApiClient.Builder(getActivity().getApplicationContext())
	        .addConnectionCallbacks(this)
	        .addOnConnectionFailedListener(this)
	        .addApi(Plus.API, Plus.PlusOptions.builder().build())
	        .addScope(Plus.SCOPE_PLUS_LOGIN)
	        .build();
	  }

	  @Override
	public void onStart() {
	    super.onStart();
	   if(check.equals("no"))
	   {
		   
	   }else{
	    
	   }
	   
	   mGoogleApiClient.connect();
	  }

	  @Override
	public void onStop() {
	    super.onStop();

	    if (mGoogleApiClient.isConnected()) {
	   //   mGoogleApiClient.disconnect();
	    }
	  }

	  @Override
	public void onSaveInstanceState(Bundle outState) {
	    super.onSaveInstanceState(outState);
	    outState.putInt(SAVED_PROGRESS, mSignInProgress);
	  }

	  @Override
	  public void onClick(View v) {
	    if (!mGoogleApiClient.isConnecting()) {
	      // We only process button clicks when GoogleApiClient is not transitioning
	      // between connected and not connected.
	      switch (v.getId()) {
	          case R.id.signup_btn:
	         //   mStatus.setText(R.string.status_signing_in);
	        	  if(check.equals("no"))
	        	  {
	        		  onStart();
	        	  }
	            resolveSignInError();
	            break;
	          case R.id.button1:
	            // We clear the default account on sign out so that Google Play
	            // services will not return an onConnected callback without user
	            // interaction.
	          //  Plus.AccountApi.clearDefaultAccount(mGoogleApiClient);
	          //  mGoogleApiClient.disconnect();
	          //  Log.i("cause", "onclick of gplus prblm");
	          //  mGoogleApiClient.connect();
	        	  
	            break;
	          case R.id.revoke_access_button:
	            // After we revoke permissions for the user with a GoogleApiClient
	            // instance, we must discard it and create a new one.
	            Plus.AccountApi.clearDefaultAccount(mGoogleApiClient);
	            // Our sample has caches no user data from Google+, however we
	            // would normally register a callback on revokeAccessAndDisconnect
	            // to delete user data so that we comply with Google developer
	            // policies.
	            Plus.AccountApi.revokeAccessAndDisconnect(mGoogleApiClient);
	            mGoogleApiClient = buildGoogleApiClient();
	            mGoogleApiClient.connect();
	            break;
	      }
	    }
	  }

	

	/* onConnected is called when our Activity successfully connects to Google
	   * Play services.  onConnected indicates that an account was selected on the
	   * device, that the selected account has granted any requested permissions to
	   * our app and that we were able to establish a service connection to Google
	   * Play services.
	   */
	  @Override
	  public void onConnected(Bundle connectionHint) {
	    // Reaching onConnected means we consider the user signed in.
	    Log.i(TAG, "onConnected");

	    // Update the user interface to reflect that the user is signed in.
	  //  mSignInButton.setEnabled(false);
	    //mSignOutButton.setEnabled(true);
	    //mRevokeButton.setEnabled(true);

	    // Retrieve some profile information to personalize our app for the user.
	    Person currentUser = Plus.PeopleApi.getCurrentPerson(mGoogleApiClient);
	    final String email = Plus.AccountApi.getAccountName(mGoogleApiClient);
	   // mStatus.setText(String.format(
	  //      getResources().getString(R.string.signed_in_as),
	   //     currentUser.getDisplayName()));
	    Log.i("email", email);
	  //  Log.i("dob", currentUser.getBirthday());
	    Log.i("gender", String.valueOf(currentUser.getGender()));
	    Log.i("age", String.valueOf(currentUser.getAgeRange()));
	    //Log.i("aboutme", currentUser.getAboutMe());
	   // Plus.PeopleApi.loadVisible(mGoogleApiClient, null)
	   //     .setResultCallback(this);

	    // Indicate that the sign in process is complete.
	    mSignInProgress = STATE_DEFAULT;
	    
	    new AsyncTask<String,String,String>(){
	    	ProgressDialog progress;
	    	boolean ss=true;
			@Override
			protected void onPostExecute(String result) {
				// TODO Auto-generated method stub
				super.onPostExecute(result);
				if(progress.isShowing()){
					progress.dismiss();
					if(!ss)
					 {
	
						getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container, new RegFinal()).commit();
							
					    	
					 }else{
					    
							Toast.makeText(getActivity()," Already Registerd", Toast.LENGTH_LONG).show();
					
					    }
				}
			}

			@Override
			protected void onPreExecute() {
				// TODO Auto-generated method stub
				super.onPreExecute();
				progress=new ProgressDialog(getActivity());
				progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
				progress.show();
			}

			@Override
			protected String doInBackground(String... arg0) {
				// TODO Auto-generated method stub
				ss=checkRegister(email);
			   
				return "yes";
			}
	    	
	    }.execute("1","2","3");
	    
	    
	    
	    
	  }

	  /* onConnectionFailed is called when our Activity could not connect to Google
	   * Play services.  onConnectionFailed indicates that the user needs to select
	   * an account, grant permissions or resolve an error in order to sign in.
	   */
	  @Override
	  public void onConnectionFailed(ConnectionResult result) {
	    // Refer to the javadoc for ConnectionResult to see what error codes might
	    // be returned in onConnectionFailed.
	    Log.i(TAG, "onConnectionFailed: ConnectionResult.getErrorCode() = "
	        + result.getErrorCode());

	      if (result.getErrorCode() == ConnectionResult.API_UNAVAILABLE) {
	          // An API requested for GoogleApiClient is not available. The device's current
	          // configuration might not be supported with the requested API or a required component
	          // may not be installed, such as the Android Wear application. You may need to use a
	          // second GoogleApiClient to manage the application's optional APIs.
	    	  Log.i("google plus", "no api found");
	      } else if (mSignInProgress != STATE_IN_PROGRESS) {
	          // We do not have an intent in progress so we should store the latest
	          // error resolution intent for use when the sign in button is clicked.
	          mSignInIntent = result.getResolution();
	          mSignInError = result.getErrorCode();

	          if (mSignInProgress == STATE_SIGN_IN) {
	              // STATE_SIGN_IN indicates the user already clicked the sign in button
	              // so we should continue processing errors until the user is signed in
	              // or they click cancel.
	              resolveSignInError();
	          }
	      }

	    // In this sample we consider the user signed out whenever they do not have
	    // a connection to Google Play services.
	    onSignedOut();
	  }

	  /* Starts an appropriate intent or dialog for user interaction to resolve
	   * the current error preventing the user from being signed in.  This could
	   * be a dialog allowing the user to select an account, an activity allowing
	   * the user to consent to the permissions being requested by your app, a
	   * setting to enable device networking, etc.
	   */
	  private void resolveSignInError() {
	    if (mSignInIntent != null) {
	      // We have an intent which will allow our user to sign in or
	      // resolve an error.  For example if the user needs to
	      // select an account to sign in with, or if they need to consent
	      // to the permissions your app is requesting.

	      try {
	        // Send the pending intent that we stored on the most recent
	        // OnConnectionFailed callback.  This will allow the user to
	        // resolve the error currently preventing our connection to
	        // Google Play services.
	        mSignInProgress = STATE_IN_PROGRESS;
	        getActivity().startIntentSenderForResult(mSignInIntent.getIntentSender(),
	            RC_SIGN_IN, null, 0, 0, 0);
	      } catch (SendIntentException e) {
	        Log.i(TAG, "Sign in intent could not be sent: "
	            + e.getLocalizedMessage());
	        // The intent was canceled before it was sent.  Attempt to connect to
	        // get an updated ConnectionResult.
	        mSignInProgress = STATE_SIGN_IN;
	        mGoogleApiClient.connect();
	        
	      }
	    } else {
	      // Google Play services wasn't able to provide an intent for some
	      // error types, so we show the default Google Play services error
	      // dialog which may still start an intent on our behalf if the
	      // user can resolve the issue.
	      getActivity().showDialog(DIALOG_PLAY_SERVICES_ERROR);
	    }
	  }

	  @Override
	public void onActivityResult(int requestCode, int resultCode,
	      Intent data) {
	    switch (requestCode) {
	      case RC_SIGN_IN:
	        if (resultCode == Activity.RESULT_OK) {
	          // If the error resolution was successful we should continue
	          // processing errors.
	          mSignInProgress = STATE_SIGN_IN;
	        } else {
	          // If the error resolution was not successful or the user canceled,
	          // we should stop processing errors.
	          mSignInProgress = STATE_DEFAULT;
	        }

	        if (!mGoogleApiClient.isConnecting()) {
	          // If Google Play services resolved the issue with a dialog then
	          // onStart is not called so we need to re-attempt connection here.
	        	  Log.i("cause", "line 351");
	          mGoogleApiClient.connect();
	        }
	        break;
	    }
	  }

	  @Override
	  public void onResult(LoadPeopleResult peopleData) {
	    if (peopleData.getStatus().getStatusCode() == CommonStatusCodes.SUCCESS) {
	      mCirclesList.clear();
	      PersonBuffer personBuffer = peopleData.getPersonBuffer();
	      try {
	          int count = personBuffer.getCount();
	          for (int i = 0; i < count; i++) {
	              mCirclesList.add(personBuffer.get(i).getDisplayName());
	          }
	      } finally {
	          personBuffer.close();
	      }

	      mCirclesAdapter.notifyDataSetChanged();
	    } else {
	      Log.e(TAG, "Error requesting visible circles: " + peopleData.getStatus());
	    }
	  }

	  private void onSignedOut() {
	    // Update the UI to reflect that the user is signed out.
	   // mSignInButton.setEnabled(true);
	    //mSignOutButton.setEnabled(false);
	    //mRevokeButton.setEnabled(false);
	  //  getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container, new GplusSigninFragment("no")).commit();

	    //mStatus.setText(R.string.status_signed_out);

	    //mCirclesList.clear();
	   // mCirclesAdapter.notifyDataSetChanged();
	  }

	  @Override
	  public void onConnectionSuspended(int cause) {
	    // The connection to Google Play services was lost for some reason.
	    // We call connect() to attempt to re-establish the connection or get a
	    // ConnectionResult that we can attempt to resolve.
		  Log.i("cause", String.valueOf(cause));
	    mGoogleApiClient.connect();
	  }

	  public Dialog onCreateDialog(int id) {
	    switch(id) {
	      case DIALOG_PLAY_SERVICES_ERROR:
	        if (GooglePlayServicesUtil.isUserRecoverableError(mSignInError)) {
	          return GooglePlayServicesUtil.getErrorDialog(
	              mSignInError,
	              getActivity(),
	              RC_SIGN_IN,
	              new DialogInterface.OnCancelListener() {
	                @Override
	                public void onCancel(DialogInterface dialog) {
	                  Log.e(TAG, "Google Play services resolution cancelled");
	                  mSignInProgress = STATE_DEFAULT;
	                  mStatus.setText(R.string.status_signed_out);
	                }
	              });
	        } else {
	          return new AlertDialog.Builder(getActivity().getApplicationContext())
	              .setMessage(R.string.play_services_error)
	              .setPositiveButton(R.string.close,
	                  new DialogInterface.OnClickListener() {
	                    public void onClick(DialogInterface dialog, int which) {
	                      Log.e(TAG, "Google Play services error could not be "
	                          + "resolved: " + mSignInError);
	                      mSignInProgress = STATE_DEFAULT;
	                      mStatus.setText(R.string.status_signed_out);
	                    }
	                  }).create();
	        }
	      default:
	        return null;
	        
	    }
	  }
	  
	  public boolean checkRegister(String mail)
		{
			
			try{
				url=new URL("http://www.vsss.16mb.com//blood//registered.php");
				
			connection=(HttpURLConnection) url.openConnection();
			
			connection.setRequestMethod("POST");
			connection.setDoInput(true);
			connection.setDoOutput(true);
			out=new DataOutputStream(connection.getOutputStream());
			//connection.setRequestProperty("Content-Type", )
			String post = "&email="+URLEncoder.encode(mail, "UTF-8");
			
			
			
			
			out.writeBytes(post);
			System.out.println(post);
			out.flush();
			out.close();
			System.out.println(connection.getResponseCode());
			//
			in=new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String temp=in.readLine();
			System.out.println(temp);
			if(temp.equalsIgnoreCase("yes"))
			{
				return true;
			}
			else{
				return false;
			}
	}catch(Exception r)
	{
		r.printStackTrace();
	}
			return true;
		}
	  
	  public void registerManual()
	  {
		  new AsyncTask<String,String,String>(){
		    	ProgressDialog progress;
				@Override
				protected void onPostExecute(String result) {
					// TODO Auto-generated method stub
					super.onPostExecute(result);
					if(progress.isShowing()){
						progress.dismiss();
					}
				}

				@Override
				protected void onPreExecute() {
					// TODO Auto-generated method stub
					super.onPreExecute();
					progress=new ProgressDialog(getActivity());
					progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
					progress.show();
				}

				@Override
				protected String doInBackground(String... arg0) {
					
				   
					return null;
				}
		    	
		    }.execute("1","2","3");
		    
	  }
	 
}