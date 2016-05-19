package com.example.delia_grimes.charterconnect;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class StartScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_screen);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

    }

    public void switchToEvents(View view) {
        Intent intent = new Intent(this, EventsMain.class);
        startActivity(intent);

    }

    public void switchToResource(View view) {
        Intent intent = new Intent(this, ResourceMain.class);
        startActivity(intent);

    }

}
