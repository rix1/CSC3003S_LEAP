package org.rix1.phishlight;

import android.content.Context; 
import android.database.SQLException;
import android.database.sqlite.SQLiteOpenHelper; 
import android.database.sqlite.SQLiteDatabase; 
import android.database.sqlite.SQLiteQueryBuilder;

public class PhishingHelper {
	 private static final String DATABASE_NAME = "phishing.db";
	 private static final int SCHEMA_VERSION = 1;
	 
	  public PhishingHelper(Context context){    
		  super(context, DATABASE_NAME, null, SCHEMA_VERSION);  
	  }
	  
	  public void onCreate(SQLiteDatabase db) {
		  db.execSQL("CREATE TABLE ip_address (_id INTEGER PRIMARY KEY AUTOINCREMENT, ip TEXT);");  
	  }
	  
	  public Cursor getAll() { 
		  return(getReadableDatabase().rawQuery("SELECT _id, ip_address ORDER BY ip", null));  
	  }
	  
	  //Put IP address into a ContentValues and tell SQLiteDatabase to insert
	  //it into the database
	  public void insert(String ip) {
		  ContentValues contV = new ContentValues();
		  
		  contV.put("ip", ip);
		  
		  getWritableDatabase().insert(ip, contV);
	  }
	  
	  //Get IP address out of the cursor
	  public String getIP(Cursor c){
		  return(c.getIP());
	  }
	  
}
