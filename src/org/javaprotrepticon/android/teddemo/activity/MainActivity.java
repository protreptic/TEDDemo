package org.javaprotrepticon.android.teddemo.activity;

import org.javaprotrepticon.android.teddemo.R;
import org.javaprotrepticon.android.teddemo.fragment.FeedFragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;

public class MainActivity extends ActionBarActivity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		 
		setContentView(R.layout.main_activity);
		
		initToolbar();
		
		Fragment fragment = new FeedFragment();
		
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.content_frame, fragment);
        fragmentTransaction.commit();	
	}
	
	private Toolbar mToolBar;
	
	private void initToolbar() {
		mToolBar = (Toolbar) findViewById(R.id.toolbar);
		mToolBar.setTitle("");
		mToolBar.setSubtitle("");
		
	    setSupportActionBar(mToolBar);
	}
	
}
