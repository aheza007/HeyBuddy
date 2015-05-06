package com.joinpeoplearound.heybuddy;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class SignUpActivity extends Activity {

	EditText username_edit_text;
	EditText password_edit_text ;
	EditText verify_password_edit_text ;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.signup_activity);
		username_edit_text = (EditText) findViewById(R.id.username_edit_text);
		password_edit_text = (EditText) findViewById(R.id.password_edit_text);
		verify_password_edit_text = (EditText) findViewById(R.id.password_again_edit_text);
		Button signUpButton = (Button) findViewById(R.id.sign_action_button);
		
		signUpButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				String username=username_edit_text.getText().toString().trim();
				String password=password_edit_text.getText().toString().trim();
				String verify_password=verify_password_edit_text.getText().toString().trim();
				Toast.makeText(SignUpActivity.this,username+password, Toast.LENGTH_LONG).show();
				boolean validationError = false;
				StringBuilder validationErrorMessage = new StringBuilder(
						getString(R.string.error_intro));
				// check if username is empty
				if (username.length() == 0) {
					validationError = true;
					validationErrorMessage
							.append(getString(R.string.error_blank_username));
				}

				// check if the password is empty

				// check if password and verify password are Equal
				if (password.length() == 0) {
					if (validationError) {
						validationErrorMessage
								.append(getString(R.string.error_join));
					}
					validationError = true;
					validationErrorMessage
							.append(getString(R.string.error_blank_password));
				}
				if (!password.equals(verify_password)) {
					if (validationError) {
						validationErrorMessage
								.append(getString(R.string.error_join));
					}
					validationError = true;
					validationErrorMessage
							.append(getString(R.string.error_mismatched_passwords));
				}
				validationErrorMessage.append(getString(R.string.error_end));
				
				if(validationError){
					 Toast.makeText(SignUpActivity.this, validationErrorMessage.toString(), Toast.LENGTH_LONG)
				        .show();
				    return;
					
				}
				
				// Set up a progress dialog
			    final ProgressDialog dialog = new ProgressDialog(SignUpActivity.this);
			    dialog.setMessage(getString(R.string.progress_signup));
			    dialog.show();
			    
			    //create heyBuddy user
				ParseUser user = new ParseUser();
				user.setUsername(username);
				user.setPassword(password);
				user.signUpInBackground(new SignUpCallback() {
					
					@Override
					public void done(ParseException e) {
						dialog.dismiss();
						if (e == null) {
						      //sign up succeed
							
						    } else {
						      // Sign up didn't succeed. Look at the ParseException
						      // to figure out what went wrong
						    	Toast.makeText(SignUpActivity.this,e.getMessage().toString(), Toast.LENGTH_LONG).show();
						    }						
					}
				});
			}
		});
	}
}
