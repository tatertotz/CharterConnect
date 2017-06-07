package com.example.delia_grimes.charterconnect;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 */




public class CCDataSQLhelper extends SQLiteOpenHelper {

    // If you change the database schema, you must increment the database version.
    public static final int DATABASE_VERSION = 1;

    private static final String SQL_DELETE_ENTRIES_RESOURCES =
            "DROP TABLE IF EXISTS " + CharterConnectDataSQL.Resources.TABLE_NAME;

    private static final String SQL_DELETE_ENTRIES_EVENTS =
            "DROP TABLE IF EXISTS " + CharterConnectDataSQL.Events.TABLE_NAME;

    private static final String SQL_DELETE_ENTRIES_SCHOOLS =
            "DROP TABLE IF EXISTS " + CharterConnectDataSQL.Schools.TABLE_NAME;

    public CCDataSQLhelper(Context context) {
        super(context, CharterConnectDataSQL.DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CharterConnectDataSQL.Events.SQL_CREATE_ENTRIES);
        db.execSQL(CharterConnectDataSQL.Resources.SQL_CREATE_ENTRIES);
        db.execSQL(CharterConnectDataSQL.Schools.SQL_CREATE_ENTRIES);

        //For now, I'm just adding in the data from one school right here. Later, it will probably get added in somewhere else.
        ContentValues avalonValues = new ContentValues();
        avalonValues.put("schoolName", "Avalon School");
        avalonValues.put("url", "www.avalonschool.org");
        avalonValues.put("address", "700 Glendale Street, St. Paul, MN 55114");
        avalonValues.put("grades", "6-12");
        avalonValues.put("missionStatement", "Avalon School prepares students for college " +
                "and life in a strong, nurturing community that inspires active learning, " +
                "engaged citizenship, and hope for the future.");
        avalonValues.put("contactInfo", "phone number: 651.649.5495, email: info@avalonschool.org");
        db.insert("Schools", null, avalonValues);

        //Here's a second school
        ContentValues greatRiverValues = new ContentValues();
        greatRiverValues.put("schoolName", "Great River School");
        greatRiverValues.put("url", "www.greatriverschool.org");
        greatRiverValues.put("address", "1326 Energy Park Drive, St. Paul, MN");
        greatRiverValues.put("grades", "1-12");
        greatRiverValues.put("missionStatement", "Great River School, an urban Montessori learning environment," +
                "prepares students for their unique roles as responsible and engaged citizens of the world.");
        greatRiverValues.put("contactInfo", "phone number: (651) 305-2780, email: info@greatriverschool.org");

        db.insert("Schools", null, greatRiverValues);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_ENTRIES_RESOURCES);
        db.execSQL(SQL_DELETE_ENTRIES_EVENTS);
        db.execSQL(SQL_DELETE_ENTRIES_SCHOOLS);
        onCreate(db);
    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }

}
