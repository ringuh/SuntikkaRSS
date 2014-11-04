package com.example.suntikkarss;

import java.util.ArrayList;

import android.content.Context;
import android.net.Uri;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

/**
 * @author 
 *
 * Kuvien käsittelyyn liittyvä adapteri luokka.
 */
public class ImageAdapter extends BaseAdapter {
    private Context mContext;

    /**
     * @param c Määrittää kontekstin jossa työskennellään.
     * 
     * Tallentaan parametrina saadun kontekstin käyttöön ja hakee kuvat toisen funktion avulla.
     */
    public ImageAdapter(Context c) {
    	
        mContext = c;
        
        haeKuvat();
    }

    public int getCount() {
    	
        return kuvalista.size();
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }

    // create a new ImageView for each item referenced by the Adapter
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;
        if (convertView == null) {  // if it's not recycled, initialize some attributes
            imageView = new ImageView(mContext);
            imageView.setLayoutParams(new GridView.LayoutParams(85, 85));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setPadding(8, 8, 8, 8);
        } else {
            imageView = (ImageView) convertView;
        }

        
        //Uri uri = Uri.parse("file:///storage/emulated/0/DCIM/Camera/short.jpg");
        //Toast.makeText(mContext, kuvalista.get(position), Toast.LENGTH_LONG).show();
        Uri uri = Uri.parse(kuvalista.get(position));
        imageView.setImageURI(uri);
       
        return imageView;
    }

    // references to our images
    
    
    private ArrayList<String> kuvalista = new ArrayList<String>();
    
    /**
     * Funktio joka suorittaa kuvien tietojen hakemisen SQL kannasta.
     */
    private void haeKuvat()
    {
    	SQL mysli = new SQL(mContext);
    	kuvalista = mysli.listFeeds();
    	//Toast.makeText(mContext, "haettiin kuvalista"+kuvalista.size(), Toast.LENGTH_LONG).show();
    }
    

}
