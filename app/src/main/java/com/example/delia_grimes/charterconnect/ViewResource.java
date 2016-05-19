package com.example.delia_grimes.charterconnect;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
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


        //String[] thisrow = new String[10];


//Log.d("STUFF!!=: ", c.getString(c.getColumnIndex("name")));
        //String uname = c.getString(c.getColumnIndex("name"));
        if (c.moveToFirst()) {
            TextView textviewid;


            /*
            *         public static final String CATEGORY= "category"; //books, movies, art, science
        public static final String NAME= "name"; //ex book title and author, "microscope" etc
        public static final String GRADELEVEL= "gradelevel";
        public static final String NUM_UNITS= "quantity"; //10 microscopes, 10 copies of blah book
        public static final String DATE_NEXT_AVAILABLE= "dateAvailable";
        public static final String CONTACT_INFO= "contactInfo";// for who you reserve it with
            * */
            //thisrow[0] = c.getString(c.getColumnIndex("name"));//c.getString(2);
            textviewid = (TextView) findViewById(R.id.textView12);
            textviewid.setText(c.getString(c.getColumnIndex("name")));

            textviewid = (TextView) findViewById(R.id.textView13);
            textviewid.setText(c.getString(c.getColumnIndex(CharterConnectDataSQL.Resources.CATEGORY)));
            textviewid = (TextView) findViewById(R.id.textView14);
            textviewid.setText(c.getString(c.getColumnIndex(CharterConnectDataSQL.Resources.GRADELEVEL)));
            textviewid = (TextView) findViewById(R.id.textView15);
            textviewid.setText(c.getString(c.getColumnIndex(CharterConnectDataSQL.Resources.NUM_UNITS)));
            textviewid = (TextView) findViewById(R.id.textView16);
            textviewid.setText(c.getString(c.getColumnIndex(CharterConnectDataSQL.Resources.DATE_NEXT_AVAILABLE)));
            textviewid = (TextView) findViewById(R.id.textView17);
            textviewid.setText(c.getString(c.getColumnIndex(CharterConnectDataSQL.Resources.CONTACT_INFO)));
            /*textviewid = (TextView) findViewById(R.id.textView8);
            textviewid.setText(c.getString(c.getColumnIndex(CharterConnectDataSQL.Events.GRADELEVEL)));
            textviewid = (TextView) findViewById(R.id.textView9);
            textviewid.setText(c.getString(c.getColumnIndex(CharterConnectDataSQL.Events.SPACES_AVAILABLE)));
            textviewid = (TextView) findViewById(R.id.textView10);
            textviewid.setText(c.getString(c.getColumnIndex(CharterConnectDataSQL.Events.EMAIL_ADDRESS)));
            textviewid = (TextView) findViewById(R.id.textView11);
            textviewid.setText(c.getString(c.getColumnIndex(CharterConnectDataSQL.Events.PHONE_NUMBER)));*/


        }

    }

}
