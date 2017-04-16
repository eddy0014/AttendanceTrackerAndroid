package com.example.eddy.attendancetracker;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddCourse extends AppCompatActivity {

    EditText courseNumField;
    EditText courseNameField;

    DatabaseHandler db = new DatabaseHandler(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_course);

        courseNumField = (EditText) findViewById(R.id.courseNumField);
        courseNameField = (EditText) findViewById(R.id.courseNameField);
    }

    public void addCourse(View view) {
        String courseNum = courseNumField.getText().toString();
        String courseName = courseNameField.getText().toString();

        db.addCourse(courseNum, courseName);
        //TODO make database code to add attendance table for this course

        Toast.makeText(this, "Course added", Toast.LENGTH_SHORT).show();

        finish();
    }
}
