package com.example.eddy.attendancetracker;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;

public class TakeAttendance extends AppCompatActivity {

    // Variables
    private String courseNumber;
    private ArrayList<StudentAttendance> attendanceList;
    private ListView attendanceListView;
    private TextView date;
    private DatePickerDialog datePickerDialog;

    DatabaseHandler db = new DatabaseHandler(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_take_attendance);

        // Get the course number after activity created
        Intent i = getIntent();
        courseNumber = i.getStringExtra("courseNumber");

        // Reference the date variable and set listener to it
        date = (TextView) findViewById(R.id.dateTv);
        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // calender class's instance and get current date , month and year from calender
                final Calendar c = Calendar.getInstance();
                int mYear = c.get(Calendar.YEAR); // current year
                int mMonth = c.get(Calendar.MONTH); // current month
                int mDay = c.get(Calendar.DAY_OF_MONTH); // current day
                // date picker dialog
                datePickerDialog = new DatePickerDialog(TakeAttendance.this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                // set day of month , month and year value in the edit text
                                date.setText((monthOfYear + 1) + "/"
                                        + dayOfMonth + "/" + year);

                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();
            }
        });

        // Initiation for the student list
        attendanceListView = (ListView) findViewById(R.id.attendanceListView);
        attendanceList = new ArrayList<StudentAttendance>();

        // Get the student list and use it for the adapter
        getStudentList();
        StudentAttendanceAdapter studentAdapter = new StudentAttendanceAdapter(this, attendanceList);
        attendanceListView.setAdapter(studentAdapter);
    }

    public void getStudentList() {
        attendanceList = db.getStudentsAtdFromCourse(courseNumber);
    }

    public void saveAttendance(View view) {
        View v;
        ArrayList<String> names = new ArrayList<String>();
        TextView name;
        CheckBox boxedChecked;
        for(int i = 0; i < attendanceListView.getCount(); i++) {
            v = attendanceListView.getAdapter().getView(i, null, null);
            name = (TextView) v.findViewById(R.id.attendanceNameView);

            // Get the name
            String fullName = name.getText().toString();
            String[] nameParts = fullName.split(" ");
            String firstN = nameParts[0];
            String lastN = nameParts[1];
            // Get box status
            boxedChecked = (CheckBox) v.findViewById(R.id.checkBox);
            boolean boxStatus = boxedChecked.isChecked();
            Log.v("TAKE_ATTENDANCE", "status " + boxStatus);
            // Get the date
            String statusDate = date.getText().toString();

            // Add to the database
            db.addStudentAttendance(courseNumber, firstN, lastN, boxStatus, statusDate);
        }

        Toast.makeText(this, "Attendance saved", Toast.LENGTH_SHORT).show();
        finish();
    }
}
