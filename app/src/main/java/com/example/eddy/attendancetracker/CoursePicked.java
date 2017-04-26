package com.example.eddy.attendancetracker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class CoursePicked extends AppCompatActivity {

    // Variables
    String courseNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_picked);

        // Get the course number after activity created
        Intent i = getIntent();
        courseNumber = i.getStringExtra("courseNumber");
    }

    public void takeAttendance(View view) {
        // Start the take attendance activity
        Intent myIntent = new Intent(CoursePicked.this, TakeAttendance.class);
        myIntent.putExtra("courseNumber", courseNumber);
        startActivity(myIntent);
    }

    public void viewAttendance(View view) {
        // Start the take attendance activity
        Intent myIntent = new Intent(CoursePicked.this, ViewAttendance.class);
        myIntent.putExtra("courseNumber", courseNumber);
        startActivity(myIntent);
    }

    public void manageStudents(View view) {
        // Start the manage students activity
        Intent myIntent = new Intent(CoursePicked.this, ManageStudents.class);
        myIntent.putExtra("courseNumber", courseNumber);
        startActivity(myIntent);
    }
}
