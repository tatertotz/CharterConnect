package com.example.delia_grimes.charterconnect;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethod;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.content.ContentValues;
import android.widget.EditText;
import android.widget.TextView;

public class AddResource extends AppCompatActivity {

    SQLiteDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_resource);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        final Button saveButton = (Button) findViewById(R.id.saveButton);
        //final String scienceButtonText = scienceButton.getText().toString();
        saveButton.setOnClickListener(new View.OnClickListener() {
            //onClick(View v, Button scienceButton);
            public void onClick(View v) {
                saveResource(v, saveButton);
                /*Intent intent = new Intent(v.getContext(), ResourceCategory.class);//secondactivity.class); //new Intent(this, secondactivity.class);
                intent.putExtra("resourcecategory", scienceButtonText );
                startActivity(intent);*/
            }
        });


//        EditText edittext;

//        edittext = (EditText) findViewById(R.id.editCategory);
//        InputMethodManager mgr = (InputMethodManager) getSystemService(v.getContext().INPUT_METHOD_SERVICE);
//        mgr.hideSoftInputFromWindow(edittext.getWindowToken(), 0);
//
//        edittext = (EditText) findViewById(R.id.editResourceName);
//        InputMethodManager mgr2 = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
//        mgr2.hideSoftInputFromWindow(edittext.getWindowToken(), 0);
//
//        edittext = (EditText) findViewById(R.id.editGradeLevels);
//        InputMethodManager mgr3 = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
//        mgr3.hideSoftInputFromWindow(edittext.getWindowToken(), 0);
//
//        edittext = (EditText) findViewById(R.id.editUnits);
//        InputMethodManager mgr4 = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
//        mgr4.hideSoftInputFromWindow(edittext.getWindowToken(), 0);
//
//        edittext = (EditText) findViewById(R.id.editDateAvailable);
//        InputMethodManager mgr5 = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
//        mgr5.hideSoftInputFromWindow(edittext.getWindowToken(), 0);
//
//        edittext = (EditText) findViewById(R.id.editContactEmail);
//        InputMethodManager mgr6 = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
//        mgr6.hideSoftInputFromWindow(edittext.getWindowToken(), 0);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        return true;
    }

    private void saveResource(View v, Button thisbutton) {


        CCDataSQLhelper mDbHelper = new CCDataSQLhelper(this);
        db=mDbHelper.getWritableDatabase();

        ContentValues values = new ContentValues(); //make an entry

        EditText gettext;
        gettext = (EditText) findViewById(R.id.editCategory);
        String new_entry_category=gettext.getText().toString();
        values.put(CharterConnectDataSQL.Resources.CATEGORY, new_entry_category); //this shoudl be inserting

        gettext = (EditText) findViewById(R.id.editResourceName);
        values.put(CharterConnectDataSQL.Resources.NAME, gettext.getText().toString());

        gettext = (EditText) findViewById(R.id.editGradeLevels);
        values.put(CharterConnectDataSQL.Resources.GRADELEVEL, gettext.getText().toString());

        gettext = (EditText) findViewById(R.id.editUnits);
        values.put(CharterConnectDataSQL.Resources.NUM_UNITS, gettext.getText().toString());

        gettext = (EditText) findViewById(R.id.editDateAvailable);
        values.put(CharterConnectDataSQL.Resources.DATE_NEXT_AVAILABLE, gettext.getText().toString());

        gettext = (EditText) findViewById(R.id.editContactEmail);
        values.put(CharterConnectDataSQL.Resources.CONTACT_INFO, gettext.getText().toString());


        //query the full db and find out what the largest idx is,and set this idx as +1 of that
        Integer thisidx = getNewDBIx();
        values.put(CharterConnectDataSQL.Resources.COLUMN_NAME_ENTRY_ID, thisidx.toString());


        long newRowId;
        newRowId = db.insert(
                CharterConnectDataSQL.Resources.TABLE_NAME,
                null,
                values);


        //final String buttontext = thisbutton.getText().toString();

        //GUYS WHEN YOU SWITCH TO RESOURCECATEGORY THE INTENT HAS TO CONTAIN WHICH RESOURCE YOU ARE
        //VIEWING OR ELSE HUGE BUGZZZZ
        Intent intent = new Intent(v.getContext(), ResourceCategory.class);//secondactivity.class); //new Intent(this, secondactivity.class);
        intent.putExtra("resourcecategory", new_entry_category);//you need to grab whatever category the person just wrote
        startActivity(intent);
    }

    //find the max index in database, then add 1 to it and return value to be entered in DB
    private Integer getNewDBIx(){//CCDataSQLhelper dbhelper) {

        //what if its null? what will it return?
        //WILL THIS EVER BE NULL? or since its an int it doesnt matter?
        String[] selectionArgs = {CharterConnectDataSQL.Resources.COLUMN_NAME_ENTRY_ID,
                CharterConnectDataSQL.Resources.TABLE_NAME};
        final SQLiteStatement stmt = db.compileStatement("SELECT MAX(" +
                CharterConnectDataSQL.Resources.COLUMN_NAME_ENTRY_ID + ") FROM " +
                CharterConnectDataSQL.Resources.TABLE_NAME);
        int ix= (int) stmt.simpleQueryForLong();

        ix=ix+1;
        Integer thisidx=new Integer(ix);
        //remember to add 1 to whatever value I get before outputting
        return thisidx;

    }


}





///////HOW TO INSERT VALUES INTO DATABASE

/*
*         CCDataSQLhelper mDbHelper = new CCDataSQLhelper(this);
        db=mDbHelper.getWritableDatabase();


            ContentValues values = new ContentValues(); //make an entry
            values.put(CharterConnectDataSQL.Resources.CATEGORY, "Art"); //this shoudl be inserting
            values.put(CharterConnectDataSQL.Resources.NAME, "PAINT");
            values.put(CharterConnectDataSQL.Resources.GRADELEVEL, 10);
            values.put(CharterConnectDataSQL.Resources.NUM_UNITS, "1");
            values.put(CharterConnectDataSQL.Resources.DATE_NEXT_AVAILABLE, "Jan 1");
            values.put(CharterConnectDataSQL.Resources.CONTACT_INFO, "AA");

            long newRowId;
            newRowId = db.insert(
                    CharterConnectDataSQL.Resources.TABLE_NAME,
                    null,
                    values);

* */