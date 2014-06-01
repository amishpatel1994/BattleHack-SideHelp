package com.android.sidehelp;

import java.util.List;
import java.util.Locale;
import java.util.Vector;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class CommunityHomePage extends FragmentActivity {

/**
 * The {@link android.support.v4.view.PagerAdapter} that will provide
 * fragments for each of the sections. We use a
 * {@link android.support.v4.app.FragmentPagerAdapter} derivative, which
 * will keep every loaded fragment in memory. If this becomes too memory
 * intensive, it may be best to switch to a
 * {@link android.support.v4.app.FragmentStatePagerAdapter}.
 */
SectionsPagerAdapter mSectionsPagerAdapter;
public List<String> fragments = new Vector<String>();

/**
 * The {@link ViewPager} that will host the section contents.
 */
ViewPager mViewPager;
static int positionClicked = -1;
static String name;
public static final String EXTRA_POSITION = "com.android.sidehelp.CommunityHomePage.POSITION";

@Override
protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_community_home_page);
    
    
    if (savedInstanceState == null) {
        Bundle extras = getIntent().getExtras();
        if(extras == null) {
            name= null;
        } else {
            name= extras.getString(AddCommunityUsers.EXTRA_NAME);
            positionClicked = Integer.parseInt(extras.getString(AddCommunityUsers.EXTRA_POSITION));
        }
    } else {
        name = (String) savedInstanceState.getSerializable("name");
    }
    

    // Create the adapter that will return a fragment for each of the three
    // primary sections of the app.
    mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
    MyPagerAdapter adapter;
    fragments.add( ConnectionFragment.class.getName());
    fragments.add( DataFragment.class.getName());
    fragments.add( GraphFragment.class.getName());

    // Set up the ViewPager with the sections adapter.
    mViewPager = (ViewPager) findViewById(R.id.pager); //pager
//    mViewPager.setAdapter(mSectionsPagerAdapter);
    adapter = new MyPagerAdapter(getSupportFragmentManager());
    mViewPager.setAdapter( adapter );
    Button addUserButton = (Button) findViewById(R.id.add_user);
    
    OnClickListener addUserListener = new OnClickListener() {
	    public void onClick(View v) {
	      // do something when the button is clicked
	    	Intent addUserIntent = new Intent(CommunityHomePage.this, AddCommunityUsers.class);
	    	addUserIntent.putExtra("", positionClicked);
	    	startActivity(addUserIntent);
	    }
	};
	addUserButton.setOnClickListener(addUserListener);
	
    

    Button button = (Button)findViewById(R.id.goto_available_funds);
    button.setOnClickListener(new OnClickListener() {
        public void onClick(View v) {
            mViewPager.setCurrentItem(1);
        }
    });
    button = (Button)findViewById(R.id.goto_feed);
    button.setOnClickListener(new OnClickListener() {
        public void onClick(View v) {
            mViewPager.setCurrentItem(2);
        }
    });
    
    Button createButton = (Button) findViewById(R.id.goto_users);
    OnClickListener createListener = new OnClickListener() {
	    public void onClick(View v) {
	      // do something when the button is clicked
	    	mViewPager.setCurrentItem(0);
	    }
	};
	createButton.setOnClickListener(createListener);
    

}

@Override
public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.main, menu);
    return true;
}

/**
 * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */


public class SectionsPagerAdapter extends FragmentPagerAdapter {


    public SectionsPagerAdapter(android.support.v4.app.FragmentManager fm) {
        super(fm);
        fragments.add(ConnectionFragment.class.getName());
        fragments.add(DataFragment.class.getName());
        fragments.add(GraphFragment.class.getName());
        //fragmentsA = "fragments";

    }

    @Override
    public android.support.v4.app.Fragment getItem(int position) {
        // getItem is called to instantiate the fragment for the given page.
        // Return a DummySectionFragment (defined as a static inner class
        // below) with the page number as its lone argument.
        /*Fragment fragment =null;
        switch (position) {
            case 0:
                fragment = new ConnectionFragment();
                break;
            case 1:
                fragment = new DataFragment();
                break;              
            case 2:
                fragment = new GraphFragment();
                break;          }
        return fragment;*/
        return android.support.v4.app.Fragment.instantiate(getBaseContext(), fragments.get(position));

    }
    @Override
    public int getCount() {
        // Show 3 total pages.
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        Locale l = Locale.getDefault();
        switch (position) {
        case 0:
            return "Page 1";
        case 1:
            return "Page 2";
        case 2:
            return "Page 3";
        }
        return null;
    }
}




/**
 * A dummy fragment representing a section of the app, but that simply
 * displays dummy text.
 */
public static class ConnectionFragment extends Fragment {
    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    public static final String ARG_SECTION_NUMBER = "section_number";
    public String stringConnectionStatus = "Offline";
    public String stringWiflyIp = "0.0.0.0";

    public ConnectionFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
            Bundle savedInstanceState) {
        View connectionView = inflater.inflate(R.layout.fragment_pager_list,container, false);
        TextView statusView = (TextView) connectionView.findViewById(R.id.text);
        statusView.setText("Registered Users: ");
        ListView lv = (ListView) connectionView.findViewById(R.id.list_view);
        ArrayAdapter arrayAdapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_list_item_1, new String[]{"User A ","User B "});

        if(positionClicked > 0)
        	arrayAdapter.insert(name, positionClicked);
        lv.setAdapter(arrayAdapter);
        
        lv.setOnItemClickListener(new OnItemClickListener() {
        	  @Override
        	  public void onItemClick(AdapterView<?> parent, View view,
        	    int position, long id) {
        	    Toast.makeText(container.getContext(),
        	      "Click ListItem Number " + position, Toast.LENGTH_LONG)
        	      .show();
        	    positionClicked = position;
        	  }
        	}); 
  
        /**TextView sectionBarView = (TextView) connectionView
                .findViewById(R.id.section_label);
        sectionBarView.setText("pups");"*/

        return connectionView;
    }
}
public static class DataFragment extends Fragment {

    public DataFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View dataView = inflater.inflate(R.layout.available_funds,
                container, false);
        //TextView sectionBarView = (TextView) dataView.findViewById(R.id.section_label);
        //sectionBarView.setText("pups2");

        TextView statusView = (TextView) dataView.findViewById(R.id.available_funds_text);
        statusView.setText("Total funds raised: ");
        TextView fundsThisWeekView = (TextView) dataView.findViewById(R.id.funds_this_week);
        fundsThisWeekView.setText("Funds raised this week: ");
        
        return dataView;
    }
}
public static class GraphFragment extends Fragment {

    public GraphFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_feed,
                container, false);
        //TextView sectionBarView = (TextView) rootView.findViewById(R.id.section_label);
        //sectionBarView.setText("pups3");
        ListView lv = (ListView) rootView.findViewById(R.id.feed_list_view);
        ArrayAdapter arrayAdapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_list_item_1, new String[]{"We have raised enough funds for people" +
                		" in this area with medical problems and have successfully provided medical facilities for" +
                		" 27 hobos :D","We have raised enough funds to provide money for children in need of " +
                				" education, and have successfully paid tuition fees for 21 children."});
        lv.setAdapter(arrayAdapter);
        
        return rootView;
    }
}



class MyPagerAdapter extends FragmentPagerAdapter {
    public List<String> fragmentsA; 

    public MyPagerAdapter(FragmentManager fm) {
        super(fm);
        fragmentsA = fragments;
    }

    @Override
    public android.support.v4.app.Fragment getItem(int position) {
        //return MyFragment.newInstance();
        return Fragment.instantiate(getApplicationContext(), fragmentsA.get(position));

    }

    @Override
    public CharSequence getPageTitle(int position) {
        //return CONTENT[position % CONTENT.length].toUpperCase();
//        return mEntries.get(position % CONTENT.length).toUpperCase();
        return "";
    }

    @Override
    public int getCount() {
       // return CONTENT.length;
//        return mEntries.size();
        return 3;
    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }
}
}