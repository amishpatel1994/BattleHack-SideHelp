package com.android.sidehelp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;


public class MainActivity extends Activity {
	
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	
		OnClickListener signInListener = new OnClickListener() {
		    public void onClick(View v) {
		      // do something when the button is clicked
		    	Intent secondIntent = new Intent(MainActivity.this, CommunityHomePage.class);
		    	startActivity(secondIntent);
		    }
		};
		Button signIn = (Button)findViewById(R.id.for_shelter);
		signIn.setOnClickListener(signInListener);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return super.onCreateOptionsMenu(menu);
	}
	
	public void donateToShelter (View view) {
		
	}
	
	
}
