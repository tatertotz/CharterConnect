package com.example.delia_grimes.charterconnect;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import static com.example.delia_grimes.charterconnect.R.id.listView;

public class ResourceCategory extends AppCompatActivity {


    SQLiteDatabase db;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {



        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resource_category);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        //receive the intent
        Bundle extras = getIntent().getExtras();
        String resourcecategory = null;
        if (extras != null) {
            resourcecategory = extras.getString("resourcecategory");

        }

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();



        //////////////////////////////////////
        //////////////////////////////////////
        //Grab database, read in all of the values under the particular category, display them on scrolling list

        CCDataSQLhelper mDbHelper = new CCDataSQLhelper(this);
        db = mDbHelper.getWritableDatabase();

        //wait its saying resourcecategory is null?
        String[] selectionArgs = {resourcecategory};//"Books"}; //I actually want this to be from the button anyway, so make this an entry from the button.. instead of writing in book or wahteer here
        Cursor c = db.rawQuery("SELECT * FROM Resources WHERE category = ?", selectionArgs);

        String[] namearray = new String[c.getCount()];
        final String[] idarray = new String[c.getCount()];
        int i=0;
        while(c.moveToNext())
        {
            String uname = c.getString(c.getColumnIndex("name"));
            namearray[i] = uname;

            String uid = c.getString(c.getColumnIndex("id"));
            idarray[i] = uid;

            i++;
            //System.out.println(uname);
        }

        /*ArrayAdapter thisArrayAdapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,namearray);
        ListView thislist = (ListView) findViewById(R.id.listView2);
        thislist.setAdapter(thisArrayAdapter);

        thislist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(EventsMain.this, ViewEvent.class);
                intent.putExtra("sql_id", idarray[position]);
                startActivity(intent);
            }
        });
        */


        //addRandomItem(mDbHelper);
        ArrayAdapter thisArrayAdapter=new ArrayAdapter(this,android.R.layout.simple_list_item_1,namearray);
        ListView thislist = (ListView) findViewById(R.id.listView);
        thislist.setAdapter(thisArrayAdapter);

        thislist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(ResourceCategory.this, ViewResource.class);
                intent.putExtra("sql_id", idarray[position]);
                startActivity(intent);
            }
        });

        setTitle(resourcecategory);// + i);// + String.valueOf(numrows));
        //setTitle(resourcecategory + idarray[0]);// + i);// + String.valueOf(numrows));

        //HERE, MAKE A CLICK EVENT FOR THE LISTVIEW, AND USE IDARRAY TO GET THE ID OF THE ITEM YOU'RE CLICKING ON
        //The listview should give you  an index of the item you clicked, and if you look that index
        // up in idarray you'll get the sql index. Send THAT information in your intent to viewresource
        //use the sql index in the viewresource event to grab the data based on COLUMN_NAME_ENTRY_ID
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "ResourceCategory Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://com.example.delia_grimes.charterconnect/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "ResourceCategory Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://com.example.delia_grimes.charterconnect/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }
    private void addRandomItem(CCDataSQLhelper mDbHelper) {

        //CCDataSQLhelper mDbHelper = new CCDataSQLhelper(this);
        //db=mDbHelper.getWritableDatabase();

        ContentValues values = new ContentValues(); //make an entry

//
//        EditText gettext;
//        gettext = (EditText) findViewById(R.id.eventName);
//        String new_entry_category=gettext.getText().toString();
        values.put(CharterConnectDataSQL.Resources.NAME, "COOL DANCE"); //this shoudl be inserting

        //gettext = (EditText) findViewById(R.id.hostSchool);
        values.put(CharterConnectDataSQL.Resources.GRADELEVEL, "Avalon");

        //gettext = (EditText) findViewById(R.id.location);
        values.put(CharterConnectDataSQL.Resources.NUM_UNITS, "Avalon");

        //gettext = (EditText) findViewById(R.id.eventDate);
        values.put(CharterConnectDataSQL.Resources.CONTACT_INFO, "1/1");

        //gettext = (EditText) findViewById(R.id.eventTime);
        values.put(CharterConnectDataSQL.Resources.DATE_NEXT_AVAILABLE, "3");

        //gettext = (EditText) findViewById(R.id.eventCost);
        values.put(CharterConnectDataSQL.Resources.CATEGORY, "Science");

        values.put(CharterConnectDataSQL.Resources.COLUMN_NAME_ENTRY_ID, "1");


        long newRowId;
        newRowId = db.insert(
                CharterConnectDataSQL.Resources.TABLE_NAME,
                null,
                values);

/*
        //ContentValues values = new ContentValues(); //make an entry
        values = new ContentValues();
//
//        EditText gettext;
//        gettext = (EditText) findViewById(R.id.eventName);
//        String new_entry_category=gettext.getText().toString();
        values.put(CharterConnectDataSQL.Resources.NAME, "COOL DANCE"); //this shoudl be inserting

        //gettext = (EditText) findViewById(R.id.hostSchool);
        values.put(CharterConnectDataSQL.Resources.GRADELEVEL, "Avalon");

        //gettext = (EditText) findViewById(R.id.location);
        values.put(CharterConnectDataSQL.Resources.NUM_UNITS, "Avalon");

        //gettext = (EditText) findViewById(R.id.eventDate);
        values.put(CharterConnectDataSQL.Resources.CONTACT_INFO, "1/1");

        //gettext = (EditText) findViewById(R.id.eventTime);
        values.put(CharterConnectDataSQL.Resources.DATE_NEXT_AVAILABLE, "3");

        //gettext = (EditText) findViewById(R.id.eventCost);
        values.put(CharterConnectDataSQL.Resources.CATEGORY, "Maker");

        values.put(CharterConnectDataSQL.Resources.COLUMN_NAME_ENTRY_ID, "1");


        //long newRowId;
        newRowId = db.insert(
                CharterConnectDataSQL.Resources.TABLE_NAME,
                null,
                values);


        values = new ContentValues(); //make an entry

//
//        EditText gettext;
//        gettext = (EditText) findViewById(R.id.eventName);
//        String new_entry_category=gettext.getText().toString();
        values.put(CharterConnectDataSQL.Resources.NAME, "COOL DANCE"); //this shoudl be inserting

        //gettext = (EditText) findViewById(R.id.hostSchool);
        values.put(CharterConnectDataSQL.Resources.GRADELEVEL, "Avalon");

        //gettext = (EditText) findViewById(R.id.location);
        values.put(CharterConnectDataSQL.Resources.NUM_UNITS, "Avalon");

        //gettext = (EditText) findViewById(R.id.eventDate);
        values.put(CharterConnectDataSQL.Resources.CONTACT_INFO, "1/1");

        //gettext = (EditText) findViewById(R.id.eventTime);
        values.put(CharterConnectDataSQL.Resources.DATE_NEXT_AVAILABLE, "3");

        //gettext = (EditText) findViewById(R.id.eventCost);
        values.put(CharterConnectDataSQL.Resources.CATEGORY, "Books");

        values.put(CharterConnectDataSQL.Resources.COLUMN_NAME_ENTRY_ID, "1");


        //long newRowId;
        newRowId = db.insert(
                CharterConnectDataSQL.Resources.TABLE_NAME,
                null,
                values);


       values = new ContentValues(); //make an entry

//
//        EditText gettext;
//        gettext = (EditText) findViewById(R.id.eventName);
//        String new_entry_category=gettext.getText().toString();
        values.put(CharterConnectDataSQL.Resources.NAME, "COOL DANCE"); //this shoudl be inserting

        //gettext = (EditText) findViewById(R.id.hostSchool);
        values.put(CharterConnectDataSQL.Resources.GRADELEVEL, "Avalon");

        //gettext = (EditText) findViewById(R.id.location);
        values.put(CharterConnectDataSQL.Resources.NUM_UNITS, "Avalon");

        //gettext = (EditText) findViewById(R.id.eventDate);
        values.put(CharterConnectDataSQL.Resources.CONTACT_INFO, "1/1");

        //gettext = (EditText) findViewById(R.id.eventTime);
        values.put(CharterConnectDataSQL.Resources.DATE_NEXT_AVAILABLE, "3");

        //gettext = (EditText) findViewById(R.id.eventCost);
        values.put(CharterConnectDataSQL.Resources.CATEGORY, "Art");

        values.put(CharterConnectDataSQL.Resources.COLUMN_NAME_ENTRY_ID, "1");


        //long newRowId;
        newRowId = db.insert(
                CharterConnectDataSQL.Resources.TABLE_NAME,
                null,
                values);*/

    }







}




//int numrows=i;

        /*String bookname = null;
        if (c.getCount() > 0) {
            c.moveToFirst();
                    //long itemId = c.getLong(c.getColumnIndexOrThrow(CharterConnectDataSQL.Resources._ID)
                    // //this is a number...
            bookname = c.getString(c.getColumnIndex("name"));

            //how do i grab something based on item id then?
        }*/


       /*String[] projection = {
CharterConnectDataSQL.Resources._ID,
        CharterConnectDataSQL.Resources.NAME,
        CharterConnectDataSQL.Resources.NUM_UNITS
        };*/

//String sortOrder =
//      CharterConnectDataSQL.Resources.COLUMN_NAME_ENTRY_ID + " DESC";
//String selection = CharterConnectDataSQL.Resources.COLUMN_NAME_ENTRY_ID + " LIKE ?"; //why does it say like??
//String[] selectionArgs = { String.valueOf(1) }; //using selectionargs


//have't i already selected the column though eugh

        /*= db.query(
                CharterConnectDataSQL.Resources.TABLE_NAME,  // The table to query
                projection,                               // The columns to return
                selection,                                // The columns for the WHERE clause
                selectionArgs,                            // The values for the WHERE clause
                null,                                     // don't group the rows
                null,                                     // don't filter by row groups
                sortOrder                                 // The sort order
        );*/
//do this query instead

//Cursor c = db.rawQuery("SELECT name FROM Resources WHERE gradelevel = ? AND quantity = ?", selectionArgs);


