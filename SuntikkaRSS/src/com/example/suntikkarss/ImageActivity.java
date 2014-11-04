package com.example.suntikkarss;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

/**
 * @author
 * Luokka joka hoitaa Imageihin liittyvän toiminnallisuuden.
 * On jatke perus aktiviteetille.
 *
 */
public class ImageActivity extends BaseActivity {

	/* (non-Javadoc)
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_image);
	}

	
}
