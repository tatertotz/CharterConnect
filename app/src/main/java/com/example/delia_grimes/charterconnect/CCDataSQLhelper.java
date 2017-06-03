package com.example.delia_grimes.charterconnect;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 */




public class CCDataSQLhelper extends SQLiteOpenHelper {
    // If you change the database schema, you must increment the database version.
    public static final int DATABASE_VERSION = 1;
    //public int LAST_IDX= 0;// add 1 to this to get the next id number for your sql database
    //public int LAST_IDX= 0;// add 1 to this to get the next id number for your sql database
    //NO dont use this, find the largest number int he DB instead.. query the db



    //how the heck is it supposed to know what "resources" is??

    //if table already exists don't create it though!
    //shouldn't the _ID thing be a number??
    //_ID enters in a random ID wtf is _ID though ahh???
 //I THINK IT NEEDS THE COLON..
//wait but this is already deleting the table if it exists wtf..
    //oh maybe i should get rid of this
    private static final String SQL_DELETE_ENTRIES_RESOURCES =
            "DROP TABLE IF EXISTS " + CharterConnectDataSQL.Resources.TABLE_NAME;
    //"DROP TABLE IF EXISTS " + CharterConnectDataSQL.Resources.TABLE_NAME;

    private static final String SQL_DELETE_ENTRIES_EVENTS =
            "DROP TABLE IF EXISTS " + CharterConnectDataSQL.Events.TABLE_NAME;

    private static final String SQL_DELETE_ENTRIES_SCHOOLS =
            "DROP TABLE IF EXISTS " + CharterConnectDataSQL.Schools.TABLE_NAME;


    public CCDataSQLhelper(Context context) {
        //new SQLiteOpenHelper(context, CharterConnectDataSQL.Resources.DATABASE_NAME, null, DATABASE_VERSION);
        super(context, CharterConnectDataSQL.DATABASE_NAME, null, DATABASE_VERSION);
        //super(context, CharterConnectDataSQL.Resources.DATABASE_NAME, null, DATABASE_VERSION);
    }
    public void onCreate(SQLiteDatabase db) {

        //db.execSQL(SQL_DELETE_ENTRIES); //GET RID OF THIS!! JUST USE IT FOR NOW SINCE I CANT ACCESS DB
        db.execSQL(CharterConnectDataSQL.Events.SQL_CREATE_ENTRIES);
        db.execSQL(CharterConnectDataSQL.Resources.SQL_CREATE_ENTRIES);
        db.execSQL(CharterConnectDataSQL.Schools.SQL_CREATE_ENTRIES);

        //For now, I'm just adding in the data from one school right here. Later, it will probably get added in somewhere else.
        ContentValues avalonValues = new ContentValues();
        avalonValues.put("schoolName", "Avalon");
        avalonValues.put("url", "avalonschool.org");
        avalonValues.put("address", "700 Glendale Street, St. Paul, MN 55114");
        avalonValues.put("grades", "6-12");
        avalonValues.put("missionStatement", "Avalon School prepares students for college " +
                "and life in a strong, nurturing community that inspires active learning, " +
                "engaged citizenship, and hope for the future.");
        avalonValues.put("contactInfo", "phone number: 651.649.5495, email: info@avalonschool.org");
        db.insert("Schools", null, avalonValues);
    }
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        //oh so this only happens if i upgrade?? when is onupgrade called?

        Log.w("myApp","HAI");
        db.execSQL(SQL_DELETE_ENTRIES_RESOURCES);
        db.execSQL(SQL_DELETE_ENTRIES_EVENTS);
        db.execSQL(SQL_DELETE_ENTRIES_SCHOOLS);
        onCreate(db);
    }
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }
}
