package com.example.delia_grimes.charterconnect;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class AddResource extends AppCompatActivity {

    ContentValues values; //Initialize a content value so that later, you can put data into it to eventually put it in the database.
    EditText gettext; //Initialize an EditText variable so that later you can use it to get the values of the EditText fields.

    SQLiteDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_resource);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //Get the save button and then when it is clicked, it calls saveResource which saves the resource
        final Button saveButton = (Button) findViewById(R.id.saveButton);
        saveButton.setOnClickListener(new View.OnClickListener() {
            //onClick(View v, Button scienceButton);
            public void onClick(View v) {
                saveResource(v, saveButton);
            }
        });
    }

    //I honestly don't know what this does...
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        return true;
    }

    //This is what the function saveResource does
    private void saveResource(View v, Button thisbutton) {

        //Get a SQL helper for the database so that you can add the values to the database.
        CCDataSQLhelper mDbHelper = new CCDataSQLhelper(this);
        db=mDbHelper.getWritableDatabase();

        //Make a content values so that you can put data into it later in order to put it into the database.
        values = new ContentValues();

        //Get the spinner so that you can get the value of it.
        Spinner categorySpinner = (Spinner) findViewById(R.id.categorySpinner);

        //Get the value of it, and if it is still at "Choose a category", alert the user, and don't do anything else.
        //Otherwise, put all the values into the resources table of the database in their respective columns.
        String spinnerText = String.valueOf(categorySpinner.getSelectedItem());
        if (spinnerText.equals("Choose a Category")) {
            Toast toast = Toast.makeText(this, "Choose a Category", Toast.LENGTH_SHORT);
            toast.show();
        }
        else {
            values.put(CharterConnectDataSQL.Resources.CATEGORY, spinnerText); //putting category spinner selection into database
            //Put all the values from the editTexts into the database.
            editTextToDatabase(R.id.editResourceName, CharterConnectDataSQL.Resources.NAME);
            editTextToDatabase(R.id.editGradeLevels, CharterConnectDataSQL.Resources.GRADELEVEL);
            editTextToDatabase(R.id.editUnits, CharterConnectDataSQL.Resources.NUM_UNITS);
            editTextToDatabase(R.id.editDateAvailable, CharterConnectDataSQL.Resources.DATE_NEXT_AVAILABLE);
            editTextToDatabase(R.id.editContactEmail, CharterConnectDataSQL.Resources.CONTACT_INFO);

            //This queries the full database and find out what the largest idx (index number?( is, and sets this idx as +1 of that
            Integer thisidx = getNewDBIx();
            values.put(CharterConnectDataSQL.Resources.COLUMN_NAME_ENTRY_ID, thisidx.toString());

            //Inserts the values into the database
            db.insert(CharterConnectDataSQL.Resources.TABLE_NAME, null, values);

            //Switches to the resource category screen and grabs whatevery category the user chose
            Intent intent = new Intent(v.getContext(), ResourceCategory.class);
            intent.putExtra("resourcecategory", spinnerText); //This grabs whatever category the user chose
            startActivity(intent);
        }
    }

    //Find the max index in database, then add 1 to it and return value to be entered in DB
    private Integer getNewDBIx(){

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

    private void editTextToDatabase(int editTextId, String databaseSlot) {
        gettext = (EditText) findViewById(editTextId);
        values.put(databaseSlot, gettext.getText().toString());
    }

}