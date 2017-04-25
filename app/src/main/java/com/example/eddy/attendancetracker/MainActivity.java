package com.example.eddy.attendancetracker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    // Variables
    private ArrayList<Course> courseList;
    private ListView courseListView;

    DatabaseHandler db = new DatabaseHandler(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initiation for the course list
        courseListView = (ListView) findViewById(R.id.courseListView);
        courseList = new ArrayList<Course>();

    }

    @Override
    protected void onResume() {
        super.onResume();

        // Get the course list and use it for the adapter
        getCourseList();
        CourseAdapter courseAdapter = new CourseAdapter(this, courseList);
        courseListView.setAdapter(courseAdapter);
    }

    public void addCourse(View view) {
        Intent myIntent = new Intent(MainActivity.this, AddCourse.class);
        startActivity(myIntent);
    }

    public void getCourseList() {
        courseList = db.getCourses();
    }

    public void coursePicked(View view) {
        Intent thisIntent = new Intent(MainActivity.this, CoursePicked.class);
        String value = view.getTag().toString();
        thisIntent.putExtra("courseNumber", value);
        startActivity(thisIntent);
    }
}
