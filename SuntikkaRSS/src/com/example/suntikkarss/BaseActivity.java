package com.example.suntikkarss;

import android.app.Activity;
import android.content.Intent;
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
		    
		    
		      
		    case R.id.action_settings:
		    	Intent intent = new Intent(this, AddFeed.class);
		    	startActivity(intent);
		    	return true;
		    default:
		      return super.onOptionsItemSelected(item);
		  }
		}
}
