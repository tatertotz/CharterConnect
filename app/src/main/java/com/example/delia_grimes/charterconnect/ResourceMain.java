package com.example.delia_grimes.charterconnect;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

/*
//cannot resolve symbol android
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;
import android.content.ContentValues;
*/
//import <packagename>.app.R;



///!!!!!!! AM PLACING THE DATABASE INITAILIZATION HERE, IF YOU CHANGE THE START ACTIVITY, MAKE SURE
//TO PUT THE DATABASE INITIALIZATION SOMEWHERE ELSE!
public class ResourceMain extends AppCompatActivity {
    //private GoogleApiClient client;

    SQLiteDatabase db;



    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resource_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Getting the screen's width and height
        DisplayMetrics displaymetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        int screenHeight = displaymetrics.heightPixels;
        int screenWidth = displaymetrics.widthPixels;

        //Making a new screen configuration (part of the class I made) that takes the screen width and height as arguments.
        ScreenConfigurations resourceMainScreen = new ScreenConfigurations(screenWidth, screenHeight, 7);

//        int buttonHeight = (int)(screenHeight*0.25);    //Making a variable for the height of the buttons
//        int buttonWidth = (int)(screenWidth*0.25); //Making a variable for the width of the buttons

        float addResourceButtonY = (float)(screenWidth*0.5);
        float addResourceButtonX = (float)(screenHeight*0.5);

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
//        artButton.setHeight(Math.min(buttonHeight, buttonWidth));   //Sets the height of the artButton to 1/4 of the height of the screen
//        artButton.setWidth(Math.min(buttonHeight, buttonWidth)); //Sets the width of the artButton to 1/4 of the width of the screen
        resourceMainScreen.setButton0(artButton); //putting the artButton into the first slot of the array
        artButton.setMinHeight(resourceMainScreen.getButton(0).getMinHeight()); //Setting the height to the corrected height that the screen configurations class gave me. You have to use minHeight not Height.
        artButton.setMinWidth(resourceMainScreen.getButton(0).getMinWidth());



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
//        makerButton.setHeight(Math.min(buttonHeight, buttonWidth));   //Sets the height of the makerButton to 1/4 of the height of the screen
//        makerButton.setWidth(Math.min(buttonHeight, buttonWidth)); //Sets the width of the makerButton to 1/4 of the width of the screen
        resourceMainScreen.setButton1(makerButton);
        makerButton.setMinHeight(resourceMainScreen.getButton(1).getMinHeight());
        makerButton.setMinWidth(resourceMainScreen.getButton(1).getMinWidth());


        final Button booksButton = (Button) findViewById(R.id.booksButton); //Setting the IDs for the buttons
        final String booksButtonText = booksButton.getText().toString();
        booksButton.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                resourcemainSwitchScreen(v, booksButton);
                /*Intent intent = new Intent(v.getContext(), ResourceCategory.class);//secondactivity.class); //new Intent(this, secondactivity.class);
                intent.putExtra("resourcecategory", booksButtonText );
                startActivity(intent);*/
            }
        });
//        booksButton.setHeight(Math.min(buttonHeight, buttonWidth));   //Sets the height of the booksButton to 1/4 of the height of the screen
//        booksButton.setWidth(Math.min(buttonHeight, buttonWidth)); //Sets the width of the booksButton to 1/4 of the width of the screen
        resourceMainScreen.setButton2(booksButton);
        booksButton.setMinHeight(resourceMainScreen.getButton(2).getMinHeight());
        booksButton.setMinWidth(resourceMainScreen.getButton(2).getMinWidth());


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
//        scienceButton.setHeight(Math.min(buttonHeight, buttonWidth));   //Sets the height of the scienceButton to 1/4 of the height of the screen
//        scienceButton.setWidth(Math.min(buttonHeight, buttonWidth)); //Sets the width of the scienceButton to 1/4 of the width of the screen
        resourceMainScreen.setButton3(scienceButton);
        scienceButton.setMinHeight(resourceMainScreen.getButton(3).getMinHeight());
        scienceButton.setMinWidth(resourceMainScreen.getButton(3).getMinWidth());

        //client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();

        final Button addResourceButton = (Button) findViewById(R.id.button);
        addResourceButton.setX(addResourceButtonX);
        addResourceButton.setY(addResourceButtonY);


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


