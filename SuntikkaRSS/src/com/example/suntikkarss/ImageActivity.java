package com.example.suntikkarss;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

/**
 * Luokka joka hoitaa Imageihin liittyv�n toiminnallisuuden.
 * K�yt�ss� etusivun kuvagridiss�.
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
