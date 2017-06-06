package com.example.delia_grimes.charterconnect;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;
import android.widget.Toast;

public class AddEvent extends AppCompatActivity {
    SQLiteDatabase db;
    Cursor schoolsCursor;
    SimpleCursorAdapter schoolsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_event);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //Get the save button and then when it is clicked, it calls saveEvent which saves the event
        final Button saveButton = (Button) findViewById(R.id.eventsSaveButton);
        saveButton.setOnClickListener(new View.OnClickListener() {
            //onClick(View v, Button scienceButton);
            public void onClick(View v) {
                saveEvent(v, saveButton);
            }
        });

        //Creates a cursor in order to populate the spinner
        try {
            SQLiteOpenHelper schoolsDatabaseHelper = new CCDataSQLhelper(this);
            db = schoolsDatabaseHelper.getReadableDatabase();
//            schoolsCursor = db.query("Resources", new String[] {"_id", "category"},
//                    null, null, null, null, null);
            schoolsCursor = db.rawQuery("SELECT * FROM Schools", null);

            /*I tried to do this all with a SimpleCursorAdapter, but it wasn't working, because when it put the value of the
            spinner into the database, it would put the Cursor (ex. android:database.sqlite.SQLiteCursor@3988439). So, the
            array and array adapter worked better*/

            final String[] schoolsArray = new String[schoolsCursor.getCount()];
            int ii = 0;
            while(schoolsCursor.moveToNext()) {
                String schoolName = schoolsCursor.getString(schoolsCursor.getColumnIndex("schoolName"));
                schoolsArray[ii] = schoolName;
                ii++;
            }

            ArrayAdapter schoolsAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, schoolsArray);

            schoolsAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

            //Get the spinner and put the adapter into the spinner
            Spinner schoolsSpinner = (Spinner) findViewById(R.id.hostSchoolsSpinner);
            schoolsSpinner.setAdapter(schoolsAdapter);

        } catch (SQLiteException e) {
            Toast toast = Toast.makeText(this, "Database unavailable", Toast.LENGTH_SHORT);
            toast.show();
        }

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        return true;
    }

    private void saveEvent(View v, Button thisbutton) {


        CCDataSQLhelper mDbHelper = new CCDataSQLhelper(this);
        db=mDbHelper.getWritableDatabase();

        Spinner hostSchoolsSpinner = (Spinner) findViewById(R.id.hostSchoolsSpinner);
        String spinnerText = String.valueOf(hostSchoolsSpinner.getSelectedItem());
        Log.d("Spinner Text", spinnerText);

        ContentValues values = new ContentValues(); //make an entry

        values.put(CharterConnectDataSQL.Events.HOST_SCHOOL, spinnerText);

        EditText gettext;
        gettext = (EditText) findViewById(R.id.eventName);
        String new_entry_category=gettext.getText().toString();
        values.put(CharterConnectDataSQL.Events.NAME, new_entry_category); //this shoudl be inserting

//        gettext = (EditText) findViewById(R.id.hostSchool);
//        values.put(CharterConnectDataSQL.Events.HOST_SCHOOL, gettext.getText().toString());

        gettext = (EditText) findViewById(R.id.location);
        values.put(CharterConnectDataSQL.Events.LOCATION, gettext.getText().toString());

        gettext = (EditText) findViewById(R.id.eventDate);
        values.put(CharterConnectDataSQL.Events.DATE, gettext.getText().toString());

        gettext = (EditText) findViewById(R.id.eventTime);
        values.put(CharterConnectDataSQL.Events.TIME, gettext.getText().toString());

        gettext = (EditText) findViewById(R.id.eventCost);
        values.put(CharterConnectDataSQL.Events.COST, gettext.getText().toString());

        gettext = (EditText) findViewById(R.id.eventGradeLevels);
        values.put(CharterConnectDataSQL.Events.GRADELEVEL, gettext.getText().toString());

        gettext = (EditText) findViewById(R.id.spotsAvailable);
        values.put(CharterConnectDataSQL.Events.SPACES_AVAILABLE, gettext.getText().toString());

        gettext = (EditText) findViewById(R.id.rsvpEmail);
        values.put(CharterConnectDataSQL.Events.EMAIL_ADDRESS, gettext.getText().toString());

        gettext = (EditText) findViewById(R.id.rsvpPhone);
        values.put(CharterConnectDataSQL.Events.PHONE_NUMBER, gettext.getText().toString());


        //query the full db and find out what the largest idx is,and set this idx as +1 of that
        Integer thisidx = getNewDBIx();
        values.put(CharterConnectDataSQL.Events.COLUMN_NAME_ENTRY_ID, thisidx.toString());


        long newRowId;
        newRowId = db.insert(
                CharterConnectDataSQL.Events.TABLE_NAME,
                null,
                values);


        //final String buttontext = thisbutton.getText().toString();

        //GUYS WHEN YOU SWITCH TO RESOURCECATEGORY THE INTENT HAS TO CONTAIN WHICH RESOURCE YOU ARE
        //VIEWING OR ELSE HUGE BUGZZZZ
        Intent intent = new Intent(v.getContext(), EventsMain.class);//secondactivity.class); //new Intent(this, secondactivity.class);
        startActivity(intent);
    }

    //find the max index in database, then add 1 to it and return value to be entered in DB
    private Integer getNewDBIx(){//CCDataSQLhelper dbhelper) {

        //what if its null? what will it return?
        //WILL THIS EVER BE NULL? or since its an int it doesnt matter?
        String[] selectionArgs = {CharterConnectDataSQL.Events.COLUMN_NAME_ENTRY_ID,
                CharterConnectDataSQL.Events.TABLE_NAME};
        final SQLiteStatement stmt = db.compileStatement("SELECT MAX(" +
                CharterConnectDataSQL.Events.COLUMN_NAME_ENTRY_ID + ") FROM " +
                CharterConnectDataSQL.Events.TABLE_NAME);
        int ix= (int) stmt.simpleQueryForLong();

        ix=ix+1;
        Integer thisidx=new Integer(ix);
        //remember to add 1 to whatever value I get before outputting
        return thisidx;

    }

//    @Override
//    public void onDestroy() {
//        super.onDestroy();
//        schoolsCursor.close();
//        db.close();
//    }

}
