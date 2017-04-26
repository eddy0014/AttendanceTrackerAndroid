package com.example.eddy.attendancetracker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class ManageStudentPicked extends AppCompatActivity {

    // Variables
    TextView studentIDView, nameView;
    private int studentID;
    private String courseNum;
    private String firstName;
    private String lastName;
    ArrayList<Student> student = new ArrayList<Student>();
    DatabaseHandler db = new DatabaseHandler(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_student_picked);

        // Get the intent extras
        Intent i = getIntent();
        studentID = i.getIntExtra("studentID", 4);
        courseNum = i.getStringExtra("courseNum");

        // Reference the variables
        studentIDView = (TextView) findViewById(R.id.studentIDView);
        nameView = (TextView) findViewById(R.id.nameView);

        getStudent();

        // Get the details for the student
        Student currStu = student.get(0);
        String studentIDFormatted = Integer.toString(studentID);
        firstName = currStu.getFirstName();
        lastName = currStu.getLastName();
        // Set the text in the TextViews
        studentIDView.setText(studentIDFormatted);
        nameView.setText(firstName + " " + lastName);

    }

    public void getStudent() {
        student = db.getStudent(studentID, courseNum);
    }

    public void deleteStudent(View view) {
        db.deleteStudent(studentID, courseNum);

        Toast.makeText(this, "Student deleted", Toast.LENGTH_SHORT).show();
        // End activity
        finish();
    }
}
