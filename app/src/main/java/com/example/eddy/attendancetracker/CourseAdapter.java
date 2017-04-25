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
 * Created by e-sal on 4/19/2017.
 */

public class CourseAdapter extends BaseAdapter {

    //Variables
    private ArrayList<Course> courses;
    private LayoutInflater courseInflater;

    //Constructor used to instantiate variables
    public CourseAdapter(Context c, ArrayList<Course> theCourses) {
        courses = theCourses;
        courseInflater = LayoutInflater.from(c);
    }

    @Override
    public int getCount() {
        return courses.size();
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
        //map to course layout
        LinearLayout courseLayout = (LinearLayout) courseInflater.inflate(R.layout.course, parent, false);
        //get course number and name fields
        TextView cNumView = (TextView) courseLayout.findViewById(R.id.courseNumber);
        TextView cNameView = (TextView) courseLayout.findViewById(R.id.courseName);
        //get course using position
        Course currSong = courses.get(position);
        //get number and name strings
        cNumView.setText(currSong.getCourseNum());
        cNameView.setText(currSong.getCourseName());
        //set position as tag
        courseLayout.setTag(currSong.getCourseNum());
        return courseLayout;
    }
}
