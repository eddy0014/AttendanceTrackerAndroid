package com.example.eddy.attendancetracker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class ManageStudents extends AppCompatActivity {

    // Variables
    String courseNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_students);

        // Get the course number after activity created
        Intent i = getIntent();
        courseNumber = i.getStringExtra("courseNumber");
    }
}
