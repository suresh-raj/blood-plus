package com.blood.plus;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
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
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class GplusSigninFragment extends Fragment implements ConnectionCallbacks, OnConnectionFailedListener,
ResultCallback<People.LoadPeopleResult>, View.OnClickListener{
	
	validateclass isvalid;	
	AlertDialog.Builder builder1;
	 AlertDialog alert11;
	 Intent i,j;
	EditText email,password;
   String response;
   ImageButton register;
	Button button;

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

	  private SignInButton mSignInButton;
	  private Button mSignOutButton;
	  private Button mRevokeButton;
	  private TextView mStatus;
	  private ListView mCirclesListView;
	  private ArrayAdapter<String> mCirclesAdapter;
	  private ArrayList<String> mCirclesList;
	  private String check="yes";

	private ProgressDialog mprogressDialog;

	private Button login_btn;
	private ProgressDialog mProgressDialog;

	public GplusSigninFragment() {
		// TODO Auto-generated constructor stub
	}
	
	public GplusSigninFragment(String c) {
		// TODO Auto-generated constructor stub
		this.check=c;
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreateView(inflater, container, savedInstanceState);
		 View rootView = inflater.inflate(R.layout.gplus_signin, container, false);
         return rootView;
	}

	@Override
	public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onViewCreated(view, savedInstanceState);
		register_fragment.SHOW_SIGNEDIN_FRAGMENT=true;
	    mSignInButton = (SignInButton) view.findViewById(R.id.sign_in_button);
	    mSignOutButton = (Button) view.findViewById(R.id.sign_out_button);
	    mRevokeButton = (Button) view.findViewById(R.id.revoke_access_button);
	    login_btn=(Button) view.findViewById(R.id.button1);
	    //mStatus = (TextView) findViewById(R.id.sign_in_status);
	  //  mCirclesListView = (ListView) findViewById(R.id.circles_list);
	    
	    mSignInButton.setOnClickListener(this);
	   // mSignOutButton.setOnClickListener(this);
	   // mRevokeButton.setOnClickListener(this);

	 //   mCirclesList = new ArrayList<String>();
	 //   mCirclesAdapter = new ArrayAdapter<String>(
	 //       this, R.layout.circle_member, mCirclesList);
//	    mCirclesListView.setAdapter(mCirclesAdapter);
	    isvalid=new validateclass();
		i=new Intent(getActivity(),Update.class);
		email=(EditText)view.findViewById(R.id.editText1);
		password=(EditText)view.findViewById(R.id.editText2);
  
        try{
			    mProgressDialog=new ProgressDialog(getActivity());
			   mProgressDialog.setIndeterminate(false);
			   mProgressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        }
       catch(Exception e) 
       {
    	   e.printStackTrace();
       }
	    login_btn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub

				   if(isvalid.validemail(email.getText().toString())==0)
					   Toast.makeText(getActivity().getApplicationContext(), "enter a valid email", Toast.LENGTH_SHORT).show();
				   else  if(password.getText().length()<6)
					   Toast.makeText(getActivity().getApplicationContext(), "enter a valid password", Toast.LENGTH_SHORT).show();
				   else
				
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
							
							LoginThread login=new LoginThread(email.getText().toString(),password.getText().toString());
							response=login.login();
							System.out.println(response);
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
						if(response.equals("yes"))
						{
						Intent i=new Intent(getActivity(),Update.class);
						startActivity(i);
						}
						else if(response.equals("no"))
						{
							Toast.makeText(getActivity().getApplicationContext(), "Invalid email or password", Toast.LENGTH_SHORT).show();

						}
							
							
						
					}

					@Override
					protected void onPreExecute() {
						// TODO Auto-generated method stub
						super.onPreExecute();
						mProgressDialog.setMessage("Loading..");
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
	          case R.id.sign_in_button:
	         //   mStatus.setText(R.string.status_signing_in);
	        	  if(check.equals("no"))
	        	  {
	        		  onStart();
	        	  }
	            resolveSignInError();
	            break;
	          case R.id.sign_out_button:
	            // We clear the default account on sign out so that Google Play
	            // services will not return an onConnected callback without user
	            // interaction.
	            Plus.AccountApi.clearDefaultAccount(mGoogleApiClient);
	            mGoogleApiClient.disconnect();
	            Log.i("cause", "onclick of gplus prblm");
	            mGoogleApiClient.connect();
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
	    register_fragment.SHOW_SIGNEDIN_FRAGMENT=true;
	    // Update the user interface to reflect that the user is signed in.
	    mSignInButton.setEnabled(false);
	   // mSignOutButton.setEnabled(true);
	 //   mRevokeButton.setEnabled(true);

	    // Retrieve some profile information to personalize our app for the user.
	    Person currentUser = Plus.PeopleApi.getCurrentPerson(mGoogleApiClient);
	    String email = Plus.AccountApi.getAccountName(mGoogleApiClient);
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
	    
	    getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container, new SignedinFragment()).commit();
	    
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
	    mSignInButton.setEnabled(true);
	    mSignOutButton.setEnabled(false);
	    mRevokeButton.setEnabled(false);
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


	
	
	
	
}