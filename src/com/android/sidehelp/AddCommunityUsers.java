package com.android.sidehelp;

import java.util.Calendar;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;


public class AddCommunityUsers extends Activity {
	static StringBuffer dateOfBirth;
    static TextView textView;
    public static final String EXTRA_NAME = "com.android.sidehelp.AddCommunityUsers.FIRST_NAME";
 
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_community_users);
		
		
		
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return super.onCreateOptionsMenu(menu);
	}
	
	
	public static class DatePickerFragment extends DialogFragment
    implements DatePickerDialog.OnDateSetListener {
		//TextView textView;
		
		@Override
		public Dialog onCreateDialog(Bundle savedInstanceState) {
		// Use the current date as the default date in the picker
		final Calendar c = Calendar.getInstance();
		int year = c.get(Calendar.YEAR);
		int month = c.get(Calendar.MONTH);
		int day = c.get(Calendar.DAY_OF_MONTH);
		
		// Create a new instance of DatePickerDialog and return it
		return new DatePickerDialog(getActivity(), this, year, month, day);
		}
		
		public void onDateSet(DatePicker view, int year, int month, int day) {
		// Do something with the date chosen by the user
			//TextView dateOfBirth = (TextView) findViewById(R.id.dateOfBirthText);
			StringBuffer str = new StringBuffer();
			str.append(year+"/");
			str.append(month+"/");
			str.append(day);
			textView.setText(str);
		}
	}
	
	public void displayDatePicker(View view){
		this.textView = (TextView) view;
		DialogFragment newFragment = new DatePickerFragment();
	    newFragment.show(getFragmentManager(), "datePicker");
	}
	
	public void onSubmit(View view){
		Intent intent = new Intent(this, CommunityHomePage.class);
		TextView firstNameText = (TextView) findViewById(R.id.firstNameText);
		TextView lastNameText = (TextView) findViewById(R.id.lastNameText);
		StringBuffer str = new StringBuffer();
		str.append(firstNameText.toString() + lastNameText.toString());
		intent.putExtra(EXTRA_NAME, str.toString());
	}
}

