package com.example.suntikkarss;

import java.util.ArrayList;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class RssReader extends BaseActivity {
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_rss_reader);
		Bundle input = getIntent().getExtras();
        
        String url = input.getString("url");
        TextView testi = (TextView)findViewById(R.id.testi);
        testi.setText(url);
        Toast.makeText(this, "rssReader: "+url, Toast.LENGTH_LONG).show();
        
        fetch("http://"+url);
        
	}
	
	private void fetch(String urli){
	      HandleXML obj = new HandleXML(urli);
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
	   }
	
	
	
}
