package com.example.eddy.attendancetracker;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;

public class ViewAttendance extends AppCompatActivity {

    // Variables
    private String courseNumber;
    private TextView date;
    private String dateText;
    ListView theList;
    ArrayList<String> list = new ArrayList<String>();
    ArrayAdapter<String> adapter;
    private DatePickerDialog datePickerDialog;

    DatabaseHandler db = new DatabaseHandler(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_attendance);

        // Get the course number after activity created
        Intent i = getIntent();
        courseNumber = i.getStringExtra("courseNumber");

        theList = (ListView) findViewById(R.id.listView);

        // Reference the date variable and set listener to it
        date = (TextView) findViewById(R.id.dateTextView);
        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // calender class's instance and get current date , month and year from calender
                final Calendar c = Calendar.getInstance();
                int mYear = c.get(Calendar.YEAR); // current year
                int mMonth = c.get(Calendar.MONTH); // current month
                int mDay = c.get(Calendar.DAY_OF_MONTH); // current day
                // date picker dialog
                datePickerDialog = new DatePickerDialog(ViewAttendance.this,
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
    }

    public void viewAttendance(View view) {
        // Set the date as a string variable
        dateText = date.getText().toString();

        getAttendance();

        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list);
        theList.setAdapter(adapter);
    }

    public void getAttendance() {
        list = db.getAttendanceForDay(courseNumber, dateText);
    }
}
