package com.example.eddy.attendancetracker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class ManageStudents extends AppCompatActivity {

    final String TAG = "MANAGESTUDENTS_MESSAGE";

    // Variables
    String courseNumber;
    private ArrayList<Student> studentList;
    private ListView studentListView;

    DatabaseHandler db = new DatabaseHandler(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_students);

        // Get the course number after activity created
        Intent i = getIntent();
        courseNumber = i.getStringExtra("courseNumber");

        // Initiation for the student list
        studentListView = (ListView) findViewById(R.id.studentListView);
        studentList = new ArrayList<Student>();
    }

    @Override
    protected void onResume() {
        super.onResume();

        // Get the student list and use it for the adapter
        getStudentList();
        StudentAdapter studentAdapter = new StudentAdapter(this, studentList);
        studentListView.setAdapter(studentAdapter);

        View v;
        ArrayList<String> names = new ArrayList<String>();
        TextView tv;
        for(int i = 0; i < studentListView.getCount(); i++) {
            v = studentListView.getAdapter().getView(i, null, null);
            tv = (TextView) v.findViewById(R.id.studentNameView);
            names.add(tv.getText().toString());
            Log.v(TAG, tv.getText().toString() + " added");
        }
    }

    public void addStudent(View view) {
        Intent myIntent = new Intent(ManageStudents.this, AddStudent.class);
        myIntent.putExtra("courseNumber", courseNumber);
        startActivity(myIntent);
    }

    public void getStudentList() {
        studentList = db.getStudentsFromCourse(courseNumber);
    }

    public void studentPicked(View view) {
        Intent thisIntent = new Intent(ManageStudents.this, ManageStudentPicked.class);
        int value = Integer.parseInt(view.getTag().toString());
        thisIntent.putExtra("studentID", value);
        thisIntent.putExtra("courseNum", courseNumber);
        startActivity(thisIntent);
    }
}
