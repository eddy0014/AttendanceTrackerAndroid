package com.example.eddy.attendancetracker;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by e-sal on 4/25/2017.
 */

public class StudentAdapter extends BaseAdapter {
    //Variables
    private ArrayList<Student> students;
    private LayoutInflater studentInflater;

    //Constructor used to instantiate variables
    public StudentAdapter(Context c, ArrayList<Student> theStudents) {
        students = theStudents;
        studentInflater = LayoutInflater.from(c);
    }

    @Override
    public int getCount() {
        return students.size();
    }

    @Override
    public Object getItem(int arg0) {
        return null;
    }

    @Override
    public long getItemId(int arg0){
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //map to student layout
        LinearLayout studentLayout = (LinearLayout) studentInflater.inflate(R.layout.student, parent, false);
        //get student details
        TextView nameView = (TextView) studentLayout.findViewById(R.id.studentNameView);
        //get student using position
        Student currSong = students.get(position);
        //get number and name strings
        String firstN = currSong.getFirstName();
        String lastN = currSong.getLastName();
        nameView.setText(firstN + " " + lastN);
        //set position as tag
        studentLayout.setTag(currSong.getStudentID());
        return studentLayout;
    }
}
