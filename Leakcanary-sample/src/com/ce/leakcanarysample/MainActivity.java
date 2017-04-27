package com.ce.leakcanarysample;


import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		View button = findViewById(R.id.async_task);
	    button.setOnClickListener(new View.OnClickListener() {
	      @Override public void onClick(View v) {
	        startAsyncTask();
	      }
	    });
	}
	
	void startAsyncTask() {
	    // This async task is an anonymous class and therefore has a hidden reference to the outer
	    // class MainActivity. If the activity gets destroyed before the task finishes (e.g. rotation),
	    // the activity instance will leak.
	    new AsyncTask<Void, Void, Void>() {
	      @Override protected Void doInBackground(Void... params) {
	        // Do some slow work in background
	        SystemClock.sleep(20000);
	        return null;
	      }
	    }.execute();
	  }

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	@Override
	protected void onDestroy() {
		Log.v("MainActivity", "onDestroy");
		super.onDestroy();
	}
}
