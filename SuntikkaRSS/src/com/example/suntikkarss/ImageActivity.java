package com.example.suntikkarss;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

/**
 * Luokka joka hoitaa Imageihin liittyvän toiminnallisuuden.
 * Käytössä etusivun kuvagridissä.
 * @author Veli-V
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
