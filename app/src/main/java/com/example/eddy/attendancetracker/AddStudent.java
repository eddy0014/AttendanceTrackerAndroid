package com.example.eddy.attendancetracker;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class AddStudent extends AppCompatActivity {

    EditText studentIDField;
    EditText firstNameField;
    EditText lastNameField;

    DatabaseHandler db = new DatabaseHandler(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);

        studentIDField = (EditText) findViewById(R.id.studentIDField);
        firstNameField = (EditText) findViewById(R.id.firstNameField);
        lastNameField = (EditText) findViewById(R.id.lastNameField);
    }

    public void addStudent(View view) {
        int studentID = Integer.parseInt(studentIDField.getText().toString());
        String firstName = firstNameField.getText().toString();
        String lastName = lastNameField.getText().toString();

        db.addStudent(studentID, firstName, lastName);

        Toast.makeText(this, "Student added", Toast.LENGTH_SHORT).show();

        finish();
    }
}
