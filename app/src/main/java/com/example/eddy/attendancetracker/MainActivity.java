package com.example.eddy.attendancetracker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onResume() {
        super.onResume();

        //TODO tis is where you get the courses from the database and create buttons for them
    }

    public void addCourse(View view) {
        Intent myIntent = new Intent(MainActivity.this, AddCourse.class);
        startActivity(myIntent);
    }
}
