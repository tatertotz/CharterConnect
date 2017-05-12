package com.example.delia_grimes.charterconnect;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class ViewResource extends AppCompatActivity {
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_resource);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //receive the intent
        Bundle extras = getIntent().getExtras();
        //int sql_id;
        String sql_idstr = null;
        if (extras != null) {
            sql_idstr = extras.getString("sql_id");
            //Integer intObj = new Integer(sql_id);
            //sql_idstr=intObj.toString();
        }
        //Log.d("STUFFF!!!!!!!!!=: ", sql_idstr);

        //Makes a version of the SQL database helper thing or something
        CCDataSQLhelper mDbHelper = new CCDataSQLhelper(this);
        db = mDbHelper.getWritableDatabase();

        //Makes an array called selectionArgs and puts the id of what we clicked on that we got from the ResourceCategory page
        String[] selectionArgs = {sql_idstr};
        Cursor c = db.rawQuery("SELECT * FROM Resources WHERE id = ?", selectionArgs);


        //TODO: WE SHOULD CHANGE THE NUMBER OF ENTRIES IN THE ARRAY SO THAT IT ACTUALLY GETS THE RIGHT NUMBER OR SOMETHING
        //Makes an array of strings with 5 entries for resource info that will be later be given to the array adapter to populate the listview
        String[] resourceInfoArray = new String[5];

        /**
        int i=0;
        while(c.moveToNext())
        {
            String uname = c.getString(c.getColumnIndex("name"));
            resourceInfoArray[i] = uname;

            i++;
            //System.out.println(uname);
        }
         **/


        //We don't know what the if loop is for but it doesn't work without it.
        //Putting the information about the selected resource into an array and then putting that into our listview.
        // Also, setting the title of the page as the name of the selected resource.
        if (c.moveToFirst()) {


            String info = "Category: " + c.getString(c.getColumnIndex("category"));
            resourceInfoArray[0] = info;
            info = "Grade Levels: " + c.getString(c.getColumnIndex("gradelevel"));
            resourceInfoArray[1] = info;
            info = "Number of Units: " + c.getString(c.getColumnIndex("quantity"));
            resourceInfoArray[2] = info;
            info = "Date Available: " + c.getString(c.getColumnIndex("dateAvailable"));
            resourceInfoArray[3] = info;
            info = "Contact Info: " + c.getString(c.getColumnIndex("contactInfo"));
            resourceInfoArray[4] = info;

            setTitle(c.getString(c.getColumnIndex("name")));

            ArrayAdapter thisArrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, resourceInfoArray);
            ListView resourceInfoList = (ListView) findViewById(R.id.resourceInfoList);
            resourceInfoList.setAdapter(thisArrayAdapter);

        }

    }

}

//Log.d("STUFF!!=: ", c.getString(c.getColumnIndex("name")));