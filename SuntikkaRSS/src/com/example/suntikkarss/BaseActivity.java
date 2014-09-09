package com.example.suntikkarss;

import android.app.Activity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

public class BaseActivity extends Activity {

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
	    MenuInflater inflater = getMenuInflater();
	    inflater.inflate(R.menu.main, menu);
	    return true;
	}
	
	public boolean onOptionsItemSelected(MenuItem item) {
		  switch (item.getItemId()) {
		    case R.id.background:
		      Toast.makeText(getApplicationContext(), "bgbutton", Toast.LENGTH_LONG).show();
		      return true;
		    case R.id.toast:
		      Toast.makeText(getBaseContext(), R.string.toast_title, 
		                     Toast.LENGTH_LONG).show();
		      return true;
		    default:
		      return super.onOptionsItemSelected(item);
		  }
		}
}
