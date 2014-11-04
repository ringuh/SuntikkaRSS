package com.example.suntikkarss;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

/**
 * 
 *
 * Feedin lis‰‰misen ja tallentamisen hoitava luokka. 
 * @author Veli-V
 */
public class AddFeed extends BaseActivity {

	private static final int LOAD_IMAGE_RESULTS = 1;
	private ImageView thumbnail;
	private String thumbpath = "";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.add_feed);
		thumbnail = (ImageView)findViewById(R.id.editimage);
	}

	
	/**
	 * M‰‰ritt‰‰ viewille mik‰ kuva n‰ytet‰‰n.
	 * @param view Mihin viewiin imagen valinta liittyy.
	 */
	public void chooseImage(View view)
	{
		Toast.makeText(getBaseContext(), "text", Toast.LENGTH_LONG).show();
		
		// Create the Intent for Image Gallery.
        Intent i = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
         
        // Start new activity with the LOAD_IMAGE_RESULTS to handle back the results when image is picked from the Image Gallery.
        startActivityForResult(i, LOAD_IMAGE_RESULTS);

	}
	
	/**
	 * Funktio joka suorittaa feedin tallentamisen kantaan.
	 * @param view Mihin viewiin tallennus liitty.
	 */
	public void saveFeed(View view)
	{
		//Toast.makeText(getBaseContext(), "nyt pit‰isi tallentaa kantaan tiedot", Toast.LENGTH_LONG).show();
		
		SQL mysli = new SQL(getApplicationContext());
		//mysli.ClearDB();
		
		EditText url = (EditText)findViewById(R.id.editFeedUrl);
		String ret = mysli.InsertFeed(null, url.getText().toString(), thumbpath);
		Toast.makeText(getBaseContext(), ret, Toast.LENGTH_LONG).show();
		
		
		Intent intent = new Intent(this, MainActivity.class);
			
		startActivity(intent);
		
		
	}
	
	@Override
	    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
	        super.onActivityResult(requestCode, resultCode, data);
	         
	        // Here we need to check if the activity that was triggers was the Image Gallery.
	        // If it is the requestCode will match the LOAD_IMAGE_RESULTS value.
	        // If the resultCode is RESULT_OK and there is some data we know that an image was picked.
	        if (requestCode == LOAD_IMAGE_RESULTS && resultCode == RESULT_OK && data != null) {
	            // Let's read picked image data - its URI
	            Uri pickedImage = data.getData();
	            // Let's read picked image path using content resolver
	            String[] filePath = { MediaStore.Images.Media.DATA };
	            Cursor cursor = getContentResolver().query(pickedImage, filePath, null, null, null);
	            cursor.moveToFirst();
	            String imagePath = cursor.getString(cursor.getColumnIndex(filePath[0]));
	            //Toast.makeText(getBaseContext(), imagePath, Toast.LENGTH_LONG).show();
	            // Now we need to set the GUI ImageView data with data read from the picked file.
	            thumbnail.setImageBitmap(BitmapFactory.decodeFile(imagePath));
	           
	            thumbpath = imagePath;
	            // At the end remember to close the cursor or you will end with the RuntimeException!
	            cursor.close();
	        }
	    }

}
