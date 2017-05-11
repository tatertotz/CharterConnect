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
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

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


        CCDataSQLhelper mDbHelper = new CCDataSQLhelper(this);
        db = mDbHelper.getWritableDatabase();

        //wait its saying resourcecategory is null?
        String[] selectionArgs = {sql_idstr};//"Books"}; //I actually want this to be from the button anyway, so make this an entry from the button.. instead of writing in book or wahteer here
        //String[] selectionArgs = null;
        Cursor c = db.rawQuery("SELECT * FROM Resources WHERE id = ?", selectionArgs);



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



//Log.d("STUFF!!=: ", c.getString(c.getColumnIndex("name")));
        //String uname = c.getString(c.getColumnIndex("name"));
        if (c.moveToFirst()) {


            String info = c.getString(c.getColumnIndex("category"));
            resourceInfoArray[0] = info;
            info = c.getString(c.getColumnIndex("gradelevel"));
            resourceInfoArray[1] = info;
            info = c.getString(c.getColumnIndex("quantity"));
            resourceInfoArray[2] = info;
            info = c.getString(c.getColumnIndex("dateAvailable"));
            resourceInfoArray[3] = info;
            info = c.getString(c.getColumnIndex("contactInfo"));
            resourceInfoArray[4] = info;


            ArrayAdapter thisArrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, resourceInfoArray);
            ListView resourceInfoList = (ListView) findViewById(R.id.resourceInfoList);
            resourceInfoList.setAdapter(thisArrayAdapter);

        }

    }

}
