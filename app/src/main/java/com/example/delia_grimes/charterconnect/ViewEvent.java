package com.example.delia_grimes.charterconnect;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ViewEvent extends AppCompatActivity {

    SQLiteDatabase db;
    String hostSchool;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_event);
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


        CCDataSQLhelper mDbHelper = new CCDataSQLhelper(this);
        db = mDbHelper.getWritableDatabase();

        //wait its saying resourcecategory is null?
        String[] selectionArgs = {sql_idstr};//"Books"}; //I actually want this to be from the button anyway, so make this an entry from the button.. instead of writing in book or wahteer here
        //String[] selectionArgs = null;
        Cursor c = db.rawQuery("SELECT * FROM Events WHERE id = ?", selectionArgs);

        final String[] eventInfoArray = new String[9];


        //String[] thisrow = new String[10];


//Log.d("STUFF!!=: ", c.getString(c.getColumnIndex("name")));
        //String uname = c.getString(c.getColumnIndex("name"));
        if (c.moveToFirst()) {

            eventInfoArray[0] = "Host School: " + c.getString(c.getColumnIndex("hostSchool"));
            hostSchool = c.getString(c.getColumnIndex("hostSchool"));
            eventInfoArray[1] = "Location: " + c.getString(c.getColumnIndex("location"));
            eventInfoArray[2] = "Date: " + c.getString(c.getColumnIndex("date"));
            eventInfoArray[3] = "Time: " + c.getString(c.getColumnIndex("time"));
            eventInfoArray[4] = "Cost: " + c.getString(c.getColumnIndex("cost"));
            eventInfoArray[5] = "Grade Levels: " + c.getString(c.getColumnIndex("gradelevel"));
            eventInfoArray[6] = "Spaces Available" + c.getString(c.getColumnIndex("spacesAvailable"));
            eventInfoArray[7] = "Contact Email Address: " + c.getString(c.getColumnIndex("emailAddress"));
            eventInfoArray[8] = "Contact Host School: " + c.getString(c.getColumnIndex("phoneNumber"));

            setTitle(c.getString(c.getColumnIndex("name")));

            ArrayAdapter thisArrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, eventInfoArray);
            ListView eventInfoList = (ListView) findViewById(R.id.eventInfoList);
            eventInfoList.setAdapter(thisArrayAdapter);
        }

        ListView eventInfoList = (ListView) findViewById(R.id.eventInfoList);

        eventInfoList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    Intent intent = new Intent(ViewEvent.this, SchoolProfile.class);
                    intent.putExtra("whichSchool", hostSchool);
                    startActivity(intent);
                }
            }
        });

    }

}

//        values.put(CharterConnectDataSQL.Resources.NAME, gettext.getText().toString());

        /*String[] namearray = new String[c.getCount()];
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
        }*/

//            TextView textviewid;
//
//            //thisrow[0] = c.getString(c.getColumnIndex("name"));//c.getString(2);
//            textviewid = (TextView) findViewById(R.id.textView2);
//            textviewid.setText(c.getString(c.getColumnIndex("name")));
//
//            textviewid = (TextView) findViewById(R.id.textView3);
//            textviewid.setText(c.getString(c.getColumnIndex(CharterConnectDataSQL.Events.HOST_SCHOOL)));
//            textviewid = (TextView) findViewById(R.id.textView4);
//            textviewid.setText(c.getString(c.getColumnIndex(CharterConnectDataSQL.Events.LOCATION)));
//            textviewid = (TextView) findViewById(R.id.textView5);
//            textviewid.setText(c.getString(c.getColumnIndex(CharterConnectDataSQL.Events.DATE)));
//            textviewid = (TextView) findViewById(R.id.textView6);
//            textviewid.setText(c.getString(c.getColumnIndex(CharterConnectDataSQL.Events.TIME)));
//            textviewid = (TextView) findViewById(R.id.textView7);
//            textviewid.setText(c.getString(c.getColumnIndex(CharterConnectDataSQL.Events.COST)));
//            textviewid = (TextView) findViewById(R.id.textView8);
//            textviewid.setText(c.getString(c.getColumnIndex(CharterConnectDataSQL.Events.GRADELEVEL)));
//            textviewid = (TextView) findViewById(R.id.textView9);
//            textviewid.setText(c.getString(c.getColumnIndex(CharterConnectDataSQL.Events.SPACES_AVAILABLE)));
//            textviewid = (TextView) findViewById(R.id.textView10);
//            textviewid.setText(c.getString(c.getColumnIndex(CharterConnectDataSQL.Events.EMAIL_ADDRESS)));
//            textviewid = (TextView) findViewById(R.id.textView11);
//            textviewid.setText(c.getString(c.getColumnIndex(CharterConnectDataSQL.Events.PHONE_NUMBER)));
