package com.example.suntikkarss;

import java.util.ArrayList;
import java.util.Random;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class SQL extends SQLiteOpenHelper {
	
	private Context mC;
	private static final String DATABASE_NAME = "suntikkadb";
	private static final String FEEDS = "RSSfeedit";
	private static final String FOLLOW = "seuratut_feedit";
	private static final int DATABASE_VERSION = 1;
	
	private static final String MKFEEDS = "CREATE TABLE IF NOT EXISTS "
			+ FEEDS + "( id INTEGER PRIMARY KEY, "
			+ "url TEXT, "
			+ "path TEXT )";
	
	public SQL(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        //Toast.makeText(context, "sql", Toast.LENGTH_LONG).show();
        mC=context;
        
	}


	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		db.execSQL("DROP TABLE IF EXISTS " + FEEDS );
		db.execSQL(MKFEEDS);
		//Toast.makeText(, "sql", Toast.LENGTH_LONG).show();
		
	}


	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		db.execSQL("DROP TABLE IF EXISTS " + MKFEEDS );
		
		onCreate(db);
	}
	
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
		
		
		
		return ret;
	}

	public void ClearDB()
	{
		SQLiteDatabase db = this.getWritableDatabase();
		db.execSQL("DROP TABLE IF EXISTS " + FEEDS );
		onCreate(db);
	}


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
		
		return ret;
	}
	
}
