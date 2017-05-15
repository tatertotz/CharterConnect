/*package com.example.delia_grimes.charterconnect;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class EventsMain extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

        //receive the intent
        Bundle extras = getIntent().getExtras();
        String resourcecategory = null;
        if (extras != null) {
            resourcecategory = extras.getString("resourcecategory");

        }

    }

    public void switchToResource(View view) {
        Intent intent = new Intent(this, ResourceMain.class);
        startActivity(intent);

    }
}*/

package com.example.delia_grimes.charterconnect;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

public class EventsMain extends AppCompatActivity {


    SQLiteDatabase db;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {



        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //Getting the screen's width and height
        DisplayMetrics displaymetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        int screenHeight = displaymetrics.heightPixels;
        int screenWidth = displaymetrics.widthPixels;

        ScreenConfigurations eventsMainScreen = new ScreenConfigurations(screenWidth, screenHeight, 3);


        //receive the intent
        /*Bundle extras = getIntent().getExtras();
        String resourcecategory = null;
        if (extras != null) {
            resourcecategory = extras.getString("resourcecategory");

        }*/

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();



        //////////////////////////////////////
        //////////////////////////////////////
        //Grab database, read in all of the values under the particular category, display them on scrolling list

        setTitle("");

        CCDataSQLhelper mDbHelper = new CCDataSQLhelper(this);
        db = mDbHelper.getWritableDatabase();

        //wait its saying resourcecategory is null?
        //String[] selectionArgs = {resourcecategory};//"Books"}; //I actually want this to be from the button anyway, so make this an entry from the button.. instead of writing in book or wahteer here
        String[] selectionArgs =null;
        Cursor c = db.rawQuery("SELECT * FROM Events",selectionArgs );// WHERE category = ?", selectionArgs);

        //rawquery("SELECT ? FROM ? WHERE ? = ?",mtx);
        String[] namearray = new String[c.getCount()];
        final String[] idarray = new String[c.getCount()];
        int i=0;
        while(c.moveToNext())
        {
            String uname = c.getString(c.getColumnIndex("name"));
            namearray[i] = uname;

            String uid = c.getString(c.getColumnIndex("id"));
            idarray[i] = uid;
            Log.d("WOOHOO!!=: ", uid);

            i++;
            //System.out.println(uname);
        }

        //addRandomItem(mDbHelper); //add a random item

        ArrayAdapter thisArrayAdapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,namearray);
        ListView thislist = (ListView) findViewById(R.id.listView2);
        thislist.setAdapter(thisArrayAdapter);

        thislist.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(EventsMain.this, ViewEvent.class);
                intent.putExtra("sql_id", idarray[position]);
                startActivity(intent);
            }
        });

        Button resourcesButton = (Button) findViewById(R.id.resourcesButton);
        eventsMainScreen.setButton(0, resourcesButton, ""); //putting the artButton into the first slot of the array
        resourcesButton.setMinHeight(eventsMainScreen.getButton(0).getMinHeight()); //Setting the height to the corrected height that the screen configurations class gave me. You have to use minHeight not Height.
        resourcesButton.setMinWidth(eventsMainScreen.getButton(0).getMinWidth());

        Button eventsButton = (Button) findViewById(R.id.eventsButton);
        eventsMainScreen.setButton(1, eventsButton, ""); //putting the artButton into the first slot of the array
        eventsButton.setMinHeight(eventsMainScreen.getButton(1).getMinHeight()); //Setting the height to the corrected height that the screen configurations class gave me. You have to use minHeight not Height.
        eventsButton.setMinWidth(eventsMainScreen.getButton(1).getMinWidth());

        Button addEventButton = (Button) findViewById(R.id.addEvent);
        eventsMainScreen.setButton(2, addEventButton, ""); //putting the artButton into the first slot of the array
        addEventButton.setMinHeight(eventsMainScreen.getButton(2).getMinHeight()); //Setting the height to the corrected height that the screen configurations class gave me. You have to use minHeight not Height.
        addEventButton.setMinWidth(eventsMainScreen.getButton(2).getMinWidth());
        //////////////////////////////////////
        //////////////////////////////////////
        //////////////////////////////////////

//        setTitle("Events");// + i);// + String.valueOf(numrows));
        //setTitle(resourcecategory + idarray[0]);// + i);// + String.valueOf(numrows));

        //HERE, MAKE A CLICK EVENT FOR THE LISTVIEW, AND USE IDARRAY TO GET THE ID OF THE ITEM YOU'RE CLICKING ON
        //The listview should give you  an index of the item you clicked, and if you look that index
        // up in idarray you'll get the sql index. Send THAT information in your intent to viewresource
        //use the sql index in the viewresource event to grab the data based on COLUMN_NAME_ENTRY_ID
    }

    /*@Override
    public void onItemClick(AdapterView<?>adapter,View v, int position) {
        ItemClicked item = adapter.getItemAtPosition(position);
        Intent intent = new Intent(EventsMain.this,ViewEvent.class);
        startActivity(intent);
    }*/

    public void addEvent(View view) {
        Intent intent = new Intent(this, AddEvent.class);
        startActivity(intent);

    }

    private void addRandomItem(CCDataSQLhelper mDbHelper) {



        /*
        *         public static final String TABLE_NAME = "Resources";
        public static final String COLUMN_NAME_ENTRY_ID = "id";
        public static final String CATEGORY= "category"; //books, movies, art, science
        public static final String NAME= "name"; //ex book title and author, "microscope" etc
        public static final String GRADELEVEL= "gradelevel";
        public static final String NUM_UNITS= "quantity"; //10 microscopes, 10 copies of blah book
        public static final String DATE_NEXT_AVAILABLE= "dateAvailable";
        public static final String CONTACT_INFO= "contactInfo";// for who you reserve it with
        public static final String COLUMN_NAME_NULLABLE="canbenull";
        * */
        //CCDataSQLhelper mDbHelper = new CCDataSQLhelper(this);
        //db=mDbHelper.getWritableDatabase();

        ContentValues values = new ContentValues(); //make an entry
//
//        EditText gettext;
//        gettext = (EditText) findViewById(R.id.eventName);
//        String new_entry_category=gettext.getText().toString();
        values.put(CharterConnectDataSQL.Events.NAME, "COOL DANCE"); //this shoudl be inserting

        //gettext = (EditText) findViewById(R.id.hostSchool);
        values.put(CharterConnectDataSQL.Events.GRADELEVEL, "Avalon");

        //gettext = (EditText) findViewById(R.id.location);
        values.put(CharterConnectDataSQL.Events.LOCATION, "Avalon");

        //gettext = (EditText) findViewById(R.id.eventDate);
        values.put(CharterConnectDataSQL.Events.DATE, "1/1");

        //gettext = (EditText) findViewById(R.id.eventTime);
        values.put(CharterConnectDataSQL.Events.TIME, "3");

        //gettext = (EditText) findViewById(R.id.eventCost);
        values.put(CharterConnectDataSQL.Events.COST, "$100");

        //gettext = (EditText) findViewById(R.id.eventGradeLevels);
        values.put(CharterConnectDataSQL.Events.GRADELEVEL, "1-10");

        //gettext = (EditText) findViewById(R.id.spotsAvailable);
        values.put(CharterConnectDataSQL.Events.SPACES_AVAILABLE, "0");

        //gettext = (EditText) findViewById(R.id.rsvpEmail);
        values.put(CharterConnectDataSQL.Events.EMAIL_ADDRESS, "catsmcgee@avalon.biz");

        //gettext = (EditText) findViewById(R.id.rsvpPhone);
        values.put(CharterConnectDataSQL.Events.PHONE_NUMBER, "555-555-5555");


        //query the full db and find out what the largest idx is,and set this idx as +1 of that
        //Integer thisidx = getNewDBIx();
        values.put(CharterConnectDataSQL.Events.COLUMN_NAME_ENTRY_ID, "1");


        long newRowId;
        newRowId = db.insert(
                CharterConnectDataSQL.Events.TABLE_NAME,
                null,
                values);

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

    public void switchToResource(View view) {
        Intent intent = new Intent(this, ResourceMain.class);
        startActivity(intent);

    }

}

