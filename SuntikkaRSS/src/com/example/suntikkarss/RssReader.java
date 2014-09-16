package com.example.suntikkarss;

import java.util.ArrayList;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemLongClickListener;

public class RssReader extends BaseActivity {
	private Bundle bundle;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_rss_reader);
		Bundle input = getIntent().getExtras();
        bundle = savedInstanceState;
        String url = input.getString("url");
        TextView testi = (TextView)findViewById(R.id.testi);
        testi.setText(url);
        Toast.makeText(this, "rssReader: "+url, Toast.LENGTH_LONG).show();
        
        fetch("http://"+url);
        
	}
	
	private void fetch(String urli){
	      HandleXML obj = new HandleXML(urli, this);
	      String teksti = "null";
	      String prev = "null";
	      obj.fetchXML();
	      ArrayList<String> itemit = new ArrayList<String>();
	      while(obj.parsingComplete)
	      {
	            
	      }
	      
	      Toast.makeText(this, teksti, Toast.LENGTH_LONG).show();
	      itemit = obj.getItems();
	      ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
	              android.R.layout.simple_list_item_1, android.R.id.text1, itemit);
	      
	      ListView lista = (ListView)findViewById(R.id.rss_tekstit);
	      lista.setAdapter(adapter);
	      
	      
	      OnItemLongClickListener longClickListener = new OnItemLongClickListener()
	        {

				@Override
				public boolean onItemLongClick(AdapterView<?> parent, View view,
						int position, long id) {
					
					TextView c = (TextView) view.findViewById(android.R.id.text1);
				    String teksti = c.getText().toString();
					SQL mysli = new SQL(getApplicationContext());
					mysli.deleteRSS(teksti);
					Toast.makeText(getApplicationContext(), "poistetaan "+teksti , Toast.LENGTH_LONG).show();
					onCreate(bundle);
					return true;
				}
	        	
	        };
	      
	      lista.setOnItemLongClickListener(longClickListener);
	      
	      
	   }
	
	
	
}
