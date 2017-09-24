package com.blood.plus;


import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.widget.DrawerLayout;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import com.blood.plus.R;


public class MainActivity extends ActionBarActivity
        implements NavigationDrawerFragment.NavigationDrawerCallbacks {
	public GplusSigninFragment mSigninFragment;
	public ProgressDialog mprogressDialog;
	
    @Override
	protected void onActivityResult(int arg0, int arg1, Intent arg2) {
		// TODO Auto-generated method stub
		super.onActivityResult(arg0, arg1, arg2);
	//	mSigninFragment.onActivityResult(1, RESULT_OK, arg2);
		 switch (arg0) {
	      case 0:
	        if (arg1 == Activity.RESULT_OK) {
	          // If the error resolution was successful we should continue
	          // processing errors.
	          GplusSigninFragment.mSignInProgress = 1;
	          
	        } else {
	          // If the error resolution was not successful or the user canceled,
	          // we should stop processing errors.
	        	GplusSigninFragment.mSignInProgress = 0;
	        }

	        if (!GplusSigninFragment.mGoogleApiClient.isConnecting()) {
	          // If Google Play services resolved the issue with a dialog then
	          // onStart is not called so we need to re-attempt connection here.
	        	 if(register_fragment.SHOW_SIGNEDIN_FRAGMENT)
	        	GplusSigninFragment.mGoogleApiClient.connect();
	        	 else
	        		 register_fragment.mGoogleApiClient.connect();
	        }
	        break;
	    }
	}

	/**
     * Fragment managing the behaviors, interactions and presentation of the navigation drawer.
     */
    private NavigationDrawerFragment mNavigationDrawerFragment;

    /**
     * Used to store the last screen title. For use in {@link #restoreActionBar()}.
     */
    private CharSequence mTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getSupportFragmentManager().findFragmentById(R.id.navigation_drawer);
        mTitle = getTitle();

        // Set up the drawer.
        mNavigationDrawerFragment.setUp(
                R.id.navigation_drawer,
                (DrawerLayout) findViewById(R.id.drawer_layout));
        
       
    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {
        // update the main content by replacing fragments
    	 FragmentManager fragmentManager = getSupportFragmentManager();
    	switch(position)
    	{
    	case 0:
    		//Intent sign =new Intent();
    		//sign.setClass(MainActivity.this, Gplus_signin.class);
    		//startActivityForResult(sign, 1);
        fragmentManager.beginTransaction()
                .replace(R.id.container,new GplusSigninFragment())
                .commit();
        mTitle = "Login";
        
        break;
    	case 1:
    		 fragmentManager.beginTransaction()
             .replace(R.id.container, new register_fragment())
             .commit();
    		 getSupportActionBar().setTitle("register");
    		 mTitle = "Register";
    		 break;
    	case 2:
    		fragmentManager.beginTransaction()
            .replace(R.id.container, new SearchFragment())
            .commit();
    		getSupportActionBar().setTitle("search");
    		   mTitle = "Search";
    		break;
    		
    	}
    }

    public void onSectionAttached(int number) {
        switch (number) {
            case 1:
                mTitle = "profile";
                break;
            case 2:
                mTitle = "register";
                break;
            case 3:
                mTitle = "search";
                break;
        }
    }

    public void restoreActionBar() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle(mTitle);
    }

 

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!mNavigationDrawerFragment.isDrawerOpen()) {
            // Only show items in the action bar relevant to this screen
            // if the drawer is not showing. Otherwise, let the drawer
            // decide what to show in the action bar.
            getMenuInflater().inflate(R.menu.main, menu);
            restoreActionBar();
            return true;
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            return rootView;
        }

        @Override
        public void onAttach(Activity activity) {
            super.onAttach(activity);
            ((MainActivity) activity).onSectionAttached(
                    getArguments().getInt(ARG_SECTION_NUMBER));
        }
    }

    public void terms(View v){
		  Intent i=new Intent(Intent.ACTION_VIEW,Uri.parse("http://www.vsss.16mb.com/blood/terms.php"));
		  startActivity(i);
	  }
}
