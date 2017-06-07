package com.example.delia_grimes.charterconnect;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.Button;
import android.widget.TextView;

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
        Cursor schoolsCursor = schoolsDatabase.rawQuery("SELECT * FROM Schools WHERE schoolName = ?", schoolName);

        if (schoolsCursor.moveToFirst()) {
            TextView schoolTextView = (TextView) findViewById(R.id.schoolTextView);
            schoolTextView.setText(schoolsCursor.getString(schoolsCursor.getColumnIndex("schoolName")));

            Button urlButton = (Button) findViewById(R.id.urlButton);
            urlButton.setText("URL: " + schoolsCursor.getString(schoolsCursor.getColumnIndex("url")));
//            schoolInfoArray[1] = "URL: " + schoolsCursor.getString(schoolsCursor.getColumnIndex("url"));

            Button addressButton = (Button) findViewById(R.id.addressButton);
            addressButton.setText("Address: " + schoolsCursor.getString(schoolsCursor.getColumnIndex("address")));
            //            schoolInfoArray[2] = "Address: " + schoolsCursor.getString(schoolsCursor.getColumnIndex("address"));

            TextView gradesTextView = (TextView) findViewById(R.id.gradesTextView);
            gradesTextView.setText("Grades: " + schoolsCursor.getString(schoolsCursor.getColumnIndex("grades")));
//            schoolInfoArray[3] = "Grades: " + schoolsCursor.getString(schoolsCursor.getColumnIndex("grades"));

            TextView missionStatementTextView = (TextView) findViewById(R.id.missionStatementTextView);
            missionStatementTextView.setText("Mission Statement: " + schoolsCursor.getString(schoolsCursor.getColumnIndex("missionStatement")));
//            schoolInfoArray[4] = "Mission Statement: " + schoolsCursor.getString(schoolsCursor.getColumnIndex("missionStatement"));

            Button contactInfoButton = (Button) findViewById(R.id.contactInfoButton);
            contactInfoButton.setText("Contact Info: " + schoolsCursor.getString(schoolsCursor.getColumnIndex("contactInfo")));
//            schoolInfoArray[5] = "Contact Info: " + schoolsCursor.getString(schoolsCursor.getColumnIndex("contactInfo"));

            setTitle(schoolsCursor.getString(schoolsCursor.getColumnIndex("schoolName")));

        }
    }

}
