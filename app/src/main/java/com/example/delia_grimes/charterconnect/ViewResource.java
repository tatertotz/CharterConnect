package com.example.delia_grimes.charterconnect;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ViewResource extends AppCompatActivity {
    SQLiteDatabase db;
    String categoryName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_resource);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ListView resourceInfoList;

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

            categoryName = c.getString(c.getColumnIndex("category"));

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
            resourceInfoList = (ListView) findViewById(R.id.resourceInfoList);
            resourceInfoList.setAdapter(thisArrayAdapter);

        }

        resourceInfoList = (ListView) findViewById(R.id.resourceInfoList);
        resourceInfoList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    Intent intent = new Intent(ViewResource.this, ResourceCategory.class);
                    intent.putExtra("resourcecategory", categoryName);
                    startActivity(intent);
                }
            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public Intent getParentActivityIntent() {
        Intent parent = super.getParentActivityIntent();
        parent.putExtra("resourcecategory", categoryName);
        return parent;
    }

}

//Log.d("STUFF!!=: ", c.getString(c.getColumnIndex("name")));