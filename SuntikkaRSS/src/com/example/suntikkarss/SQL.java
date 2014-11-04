package com.example.suntikkarss;

import java.util.ArrayList;
import java.util.Random;

import android.R.bool;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

/**
 *
 * Luokka joka vastaa SQL kannan ja ohjelman välisestä toiminnasta.
 * @author Veli-V
 */
public class SQL extends SQLiteOpenHelper {
	
	private Context mC;
	private static final String DATABASE_NAME = "suntikkadb";
	private static final String FEEDS = "RSSfeedit";
	private static final String FOLLOW = "RSSteksti";
	private static final int DATABASE_VERSION = 1;
	
	
	private static final String MKFEEDS = "CREATE TABLE IF NOT EXISTS "
			+ FEEDS + "( id INTEGER PRIMARY KEY, "
			+ "url TEXT, "
			+ "path TEXT )";
	
	private static final String MKRSS = "CREATE TABLE IF NOT EXISTS "
			+ FOLLOW + "( id INTEGER PRIMARY KEY, "
			+ "url TEXT, "
			+ "content TEXT )";
	
	public SQL(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        //Toast.makeText(context, "sql", Toast.LENGTH_LONG).show();
        mC=context;
        
	}


	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		db.execSQL("DROP TABLE IF EXISTS " + FEEDS );
		db.execSQL("DROP TABLE IF EXISTS " + FOLLOW );
		db.execSQL(MKFEEDS);
		db.execSQL(MKRSS);
		//db.execSQL("insert into "+FEEDS+" (path, url) VALUES('/storage/emulated/0/eBooks/abercombie/Blade Itself, The - Abercrombie, Joe.jpg', 'www.radiorock.fi/rss/podcasts/1' )");
		//db.execSQL("insert into "+FEEDS+" (path, url) VALUES('/storage/emulated/0/comics/Deadpool Alternate Universes/X-Calibre (3085)/cover.jpg', 'www.radiosuomipop.fi/rss/podcasts/1' )");
		
		
	}


	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		db.execSQL("DROP TABLE IF EXISTS " + MKFEEDS );
		db.execSQL("DROP TABLE IF EXISTS " + FOLLOW );
		onCreate(db);
	}
	
	/**
	 * @param nimi Syötteelle annettu nimi
	 * @param url Syötteen url osoite
	 * @param path Syötteen polku
	 * @return Palauttaa tiedon onnistuiko lisäys ja jos onnistui lisätyt tiedot
	 * 
	 * Funktio joka lisää annetun syötteen tiedot kantaan.
	 */
	public String InsertFeed(String nimi, String url, String path)
	{
		SQLiteDatabase db = this.getWritableDatabase();
		//Random r = new Random();
		
		db.execSQL("INSERT INTO "+FEEDS+" ( url, path ) VALUES( '"+url+"', '"+path+"' )");
		String ret = "";
		String count = "SELECT * FROM "+FEEDS;
		
		Cursor c = db.rawQuery(count, null);
		
		
		if (c .moveToFirst()) {

            while (c.isAfterLast() == false) {
               
                String urli = c.getString(1);
                String pathi = c.getString(2);

                ret += urli+","+pathi+" | ";
                c.moveToNext();
            }
        }
		
		c.close();
		db.close();
		
		return ret;
	}

	/**
	 * Funktio joka tyhjentää kannan tarvittaessa. 
	 */
	public void ClearDB()
	{
		SQLiteDatabase db = this.getWritableDatabase();
		db.execSQL("DROP TABLE IF EXISTS " + FEEDS );
		onCreate(db);
	}


	/**
	 * @return Palauttaa listan feedeistä.
	 * 
	 * Funktio joka palauttaa tallennetut feedit listana.
	 */
	public ArrayList<String> listFeeds() {
		
		ArrayList<String> ret = new ArrayList<String>();
		
		SQLiteDatabase db = this.getWritableDatabase();
		String count = "SELECT * FROM "+FEEDS;
		
		Cursor c = db.rawQuery(count, null);
		
		
		if (c .moveToFirst()) {

            while (c.isAfterLast() == false) {
                String pathi = c.getString(2);
                
                ret.add(pathi);                
               
                c.moveToNext();
            }
        }
		c.close();
		db.close();
		
		return ret;
	}
	
	/**
	 * @return Lista urleista
	 * 
	 * Funktio joka palauttaa tallennetut urlit.
	 */
	public ArrayList<String> listUrls() {
		
		ArrayList<String> ret = new ArrayList<String>();
		
		SQLiteDatabase db = this.getWritableDatabase();
		String count = "SELECT * FROM "+FEEDS;
		
		Cursor c = db.rawQuery(count, null);
		
		if (c .moveToFirst()) {

            while (c.isAfterLast() == false) {
                ret.add(c.getString(1));      
                
               
                
               
                c.moveToNext();
            }
        }
		
		return ret;
	}
	
	/**
	 * @param index Syötteen sijainti joka poistetaan
	 * 
	 * Funktio joka poistaa syötteen annetussa indeksissä.
	 */
	public void deleteFeed(int index)
	{
		ArrayList<String> feedit = listFeeds();
		SQLiteDatabase db = this.getWritableDatabase();
		String count = "DELETE FROM "+FEEDS + " WHERE path='"+feedit.get(index)+"'";
		db.execSQL(count);
		db.close();
	}
	
	/**
	 * @param syote Poistettavan syötteen nimi.
	 * 
	 * Funktio joka poistaa annetun nimisen syötteen kannasta.
	 */
	public void deleteRSS(String syote)
	{
		SQLiteDatabase db = this.getWritableDatabase();
		String sql = "DELETE FROM "+FOLLOW + " WHERE content='"+syote+"'";
		db.execSQL(sql);
		db.close();
		
	}
	
	/**
	 * @param url Syötteen url.
	 * @param syote Syötteen nimi.
	 * @return Boolean arvo onnistuiko lisäys.
	 * 
	 * Lisää syötteen annetuilla tiedoilla kantaan.
	 */
	public boolean addRSS(String url, String syote)
	{
		
		boolean exists = false;
		
		SQLiteDatabase db = this.getWritableDatabase();
		Cursor c = db.rawQuery("SELECT * FROM "+FOLLOW+" WHERE url='"+url+"' AND content='"+syote+"'", null);
		if( c.moveToFirst())
			exists = true;
		
		else
		{
			String count = "INSERT INTO "+FOLLOW + " ( url, content ) VALUES ( '"+url+"', '"+syote+"')";
			db.execSQL(count);
		}
		
		db.close();
		return exists;
	
	}
	
	/**
	 * @param url Syötteen url.
	 * @return Lista vastaavista feedeistä.
	 * 
	 * Palauttaa urlia vastaavat syötteet.
	 */
	public ArrayList<String> getRSS(String url)
	{
		ArrayList<String> ret = new ArrayList<String>();
		SQLiteDatabase db = this.getWritableDatabase();
		Cursor c = db.rawQuery("SELECT * FROM "+FOLLOW+" WHERE url='"+url+"'", null);
		if (c .moveToFirst()) {
            while (c.isAfterLast() == false) {
                ret.add(c.getString(2));                
               
                c.moveToNext();
            }
        }
		c.close();
		db.close();
		return ret;
	}
}

