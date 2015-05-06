package com.joinpeoplearound.heybuddy;

import android.app.Application;

import com.parse.Parse;

public class HeyBuddyApplication extends Application {
	/*
	 * (non-Javadoc)
	 * @see android.app.Application#onCreate()
	 * Authenticates this client as belonging to HeyBuddyApplication. 
	 */
	  @Override
	  public void onCreate() {
	    super.onCreate();
	   

		// Enable Local Datastore.
		Parse.enableLocalDatastore(this); 
		 //the application ID and the Client ID that we need in order to access the backend
	  	Parse.initialize(this, "BmSBPuH6vkvqYVQtEGyf9VgezqcFYaF9luKPMxcr", "5U2PWIt1jgywZfpgqLJ37u5MvhgxPMBcKd3oryED");
	}
}
