package com.example.delia_grimes.charterconnect;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

public class SchoolProfile extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_school_profile);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Bundle extras = getIntent().getExtras();
        String whichSchool = null;
        if (extras != null) {
            whichSchool = extras.getString("whichSchool");
        }

        SQLiteOpenHelper schoolsDatabaseHelper = new CCDataSQLhelper(this);
        SQLiteDatabase schoolsDatabase = schoolsDatabaseHelper.getReadableDatabase();

        String[] schoolName = {whichSchool};
        Cursor schoolsCursor = schoolsDatabase.rawQuery("SELECT * FROM Events WHERE schoolName = ?", schoolName);

        final String[] schoolInfoArray = new String[5];

        if (schoolsCursor.moveToFirst()) {

//            schoolInfoArray[0] = "Host School: " + c.getString(c.getColumnIndex("hostSchool"));
//            schoolInfoArray[1] = "Location: " + c.getString(c.getColumnIndex("location"));
//            schoolInfoArray[2] = "Date: " + c.getString(c.getColumnIndex("date"));
//            schoolInfoArray[3] = "Time: " + c.getString(c.getColumnIndex("time"));
//            schoolInfoArray[4] = "Cost: " + c.getString(c.getColumnIndex("cost"));

            setTitle(schoolsCursor.getString(schoolsCursor.getColumnIndex("schoolName")));

        }
    }

}
