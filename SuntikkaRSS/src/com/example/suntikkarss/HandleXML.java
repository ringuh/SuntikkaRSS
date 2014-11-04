package com.example.suntikkarss;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import android.content.Context;

/**
 * @author 
 *
 * Luokka joka hoitaa XML k�sittelyn
 */
public class HandleXML {

	   private ArrayList<String> summaries = new ArrayList<String>();
	   private Context mContext;
	   private String urlString = null;
	   private XmlPullParserFactory xmlFactoryObject;
	   public volatile boolean parsingComplete = true;
	   public HandleXML(String url, Context mC){
	      this.urlString = url;
	      mContext = mC;
	      SQL mysli = new SQL(mC);
	      //summaries = mysli.getRSS(url); 
	      /* ei k�ytet�, koska ohjelma ei kuitenkaan toimi ilman nettiyhteytt�, 
	       * ja t�m� kusee sy�tteiden j�rjestysken */
	   }
	 /**
	 * @return ArrayList<String> sy�te itemit sis�lt�v� lista.
	 * 
	 * Palauttaa sy�tteiden tiedot listana.
	 */
	public ArrayList<String> getItems()
	   {
		   return summaries;
	   }
	   
	   /**
	 * @param myParser Kertoo mit� parseria t�m� funktio t�ss� instansissa k�ytt��.
	 * 
	 * Funktion tarkoitus on parsia XML:st� irti tarpeellinen tieto ja tallentaa
	 * se helpommin k�sitelt�v��n muotoon.
	 */
	public void parseXMLAndStoreIt(XmlPullParser myParser) {
	      int event;
	      String text=null;
	      try {
	         event = myParser.getEventType();
	         while (event != XmlPullParser.END_DOCUMENT) {
	         String name=myParser.getName();
	         
	         switch (event){
	            case XmlPullParser.START_TAG:
		        break;
	            case XmlPullParser.TEXT:
			       text = myParser.getText();
	            break;
	            case XmlPullParser.END_TAG:
		           if(name.equals("itunes:summary"))
	               {
		        	   boolean haettuKannasta = summaries.contains(text);
		        	   if(!haettuKannasta)
		        	   {
		        		   //summaries.add("NEW! "+text);
			        	   SQL mysli = new SQL(mContext);
			        	   boolean in = mysli.addRSS(this.urlString, text);
			        	   if( !in )
			        		   summaries.add("NEW! "+text);
			        	   else
			        		   summaries.add(text);
		        	   }
	               }
	               else{
	            	   
	               }
	               break;
	         }		 
	         event = myParser.next(); 
	       }
	       parsingComplete = false;
	      } catch (Exception e) {
	         e.printStackTrace();
	      }
	   }
	   public void fetchXML(){
	   Thread thread = new Thread(new Runnable(){
		   
	@Override
	   public void run() {
	      try {
	         URL url = new URL(urlString);
	         HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	         conn.setReadTimeout(10000 /* milliseconds */);
	         conn.setConnectTimeout(15000 /* milliseconds */);
	         conn.setRequestMethod("GET");
	         conn.setDoInput(true);
	         // Starts the query
	         conn.connect();
	         InputStream stream = conn.getInputStream();
	         xmlFactoryObject = XmlPullParserFactory.newInstance();
	         XmlPullParser myparser = xmlFactoryObject.newPullParser();
	         myparser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
	         myparser.setInput(stream, null);
	         parseXMLAndStoreIt(myparser);
	         stream.close();
	      } catch (Exception e) {
	    	  //parsingComplete = true;
	      }
	      }
	      });
	      thread.start(); 
	   }
	}