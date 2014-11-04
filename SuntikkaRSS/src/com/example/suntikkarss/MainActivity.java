package com.example.suntikkarss;

import java.util.ArrayList;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.GridView;
import android.widget.Toast;


/**
 * @author Veli-V
 * BaseActivityn p‰‰lle rakennettu main luokka joka alustaa toiminnan ja toimii
 * sen kautta agrekaattina muulle toiminnalle.
 */
public class MainActivity extends BaseActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        GridView gridview = (GridView) findViewById(R.id.gridView1);
        gridview.setAdapter(new ImageAdapter(this));
        
        
        OnItemClickListener clickListener = new OnItemClickListener(){

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position,
					long id) {
				// TODO Auto-generated method stub
				 
				 SQL mysli = new SQL(getApplicationContext());
			     ArrayList<String> arrlist = mysli.listUrls();
				 
				 Toast.makeText(getApplicationContext(), "pos:" + position + " url:"+arrlist.get(position), Toast.LENGTH_SHORT).show();
				 Intent intent = new Intent(getApplicationContext(), RssReader.class);
				 intent.putExtra("url", arrlist.get(position));
				 startActivity(intent);
			}
        	
        };
        
        OnItemLongClickListener longClickListener = new OnItemLongClickListener()
        {

			@Override
			public boolean onItemLongClick(AdapterView<?> parent, View view,
					int position, long id) {
				SQL mysli = new SQL(getApplicationContext());
				mysli.deleteFeed(position);
				Toast.makeText(getApplicationContext(), "poistetaan", Toast.LENGTH_LONG).show();
				onCreate(null);
				return true;
			}
        	
        };
        
		gridview.setOnItemClickListener(clickListener);
        
		gridview.setOnItemLongClickListener(longClickListener);
        
    
    }

    
}
