package com.example.delia_grimes.charterconnect;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.view.View.OnClickListener;
import android.widget.SimpleCursorAdapter;

import android.content.Intent;
import android.widget.EditText;


/*
//cannot resolve symbol android
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;
import android.content.ContentValues;
*/
//import <packagename>.app.R;
import com.example.delia_grimes.charterconnect.R; //says that sometimes this has trouble..



///!!!!!!! AM PLACING THE DATABASE INITAILIZATION HERE, IF YOU CHANGE THE START ACTIVITY, MAKE SURE
//TO PUT THE DATABASE INITIALIZATION SOMEWHERE ELSE!
public class ResourceMain extends AppCompatActivity {
    //private GoogleApiClient client;

    SQLiteDatabase db;




    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resource_main);
                //R.layout.activity_resource_main);//this is messed up
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                /*Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();*/
//                Intent intent = new Intent(ResourceMain.this, AddResource.class);
//                startActivity(intent);
//            }
//        });




        //Set the title so it isn't saying "resource main" etc
        setTitle("");


        final Button booksButton = (Button) findViewById(R.id.booksButton);
        final String booksButtonText = booksButton.getText().toString();
        booksButton.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                resourcemainSwitchScreen(v, booksButton);
                /*Intent intent = new Intent(v.getContext(), ResourceCategory.class);//secondactivity.class); //new Intent(this, secondactivity.class);
                intent.putExtra("resourcecategory", booksButtonText );
                startActivity(intent);*/
            }
        });

        final Button artButton = (Button) findViewById(R.id.artButton);
        final String artButtonText = artButton.getText().toString();
        artButton.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                resourcemainSwitchScreen(v, artButton);
                /*Intent intent = new Intent(v.getContext(), ResourceCategory.class);//secondactivity.class); //new Intent(this, secondactivity.class);
                intent.putExtra("resourcecategory", artButtonText );
                startActivity(intent);*/
            }
        });

        final Button makerButton = (Button) findViewById(R.id.makerButton);
        final String makerButtonText = makerButton.getText().toString();
        makerButton.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                resourcemainSwitchScreen(v, makerButton);
                /*Intent intent = new Intent(v.getContext(), ResourceCategory.class);//secondactivity.class); //new Intent(this, secondactivity.class);
                intent.putExtra("resourcecategory", makerButtonText );
                startActivity(intent);*/
            }
        });

        final Button scienceButton = (Button) findViewById(R.id.scienceButton);
        //final String scienceButtonText = scienceButton.getText().toString();
        scienceButton.setOnClickListener(new OnClickListener() {
            //onClick(View v, Button scienceButton);
            public void onClick(View v) {
                resourcemainSwitchScreen(v, scienceButton);
                /*Intent intent = new Intent(v.getContext(), ResourceCategory.class);//secondactivity.class); //new Intent(this, secondactivity.class);
                intent.putExtra("resourcecategory", scienceButtonText );
                startActivity(intent);*/
            }
        });

        //client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();



    }

    public void switchToEvents(View view) {
        Intent intent = new Intent(this, EventsMain.class);
        startActivity(intent);

    }

    public void addResource(View view) {
        Intent intent = new Intent(this, AddResource.class);
        startActivity(intent);

    }

    //created so that its only one function to switch apps when you switch screens

    private void resourcemainSwitchScreen(View v, Button thisbutton) {




        final String buttontext = thisbutton.getText().toString();
        Intent intent = new Intent(v.getContext(), ResourceCategory.class);//secondactivity.class); //new Intent(this, secondactivity.class);




        intent.putExtra("resourcecategory", buttontext );
        startActivity(intent);
    }


}


