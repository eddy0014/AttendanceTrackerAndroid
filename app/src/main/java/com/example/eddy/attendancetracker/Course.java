package com.example.eddy.attendancetracker;

import static android.R.attr.id;

/**
 * Created by e-sal on 4/19/2017.
 */

public class Course {

    //Variables for the data of each course
    private String courseNum;
    private String courseName;

    //Empty constructor
    public Course() {
    }

    //Constructor used to instantiate the variables
    public Course(String courseNumVar, String courseNameVar) {
        courseNum = courseNumVar;
        courseName = courseNameVar;
    }

    //Getters for the variables
    public String getCourseNum() {
        return courseNum;
    }
    public String getCourseName() {
        return courseName;
    }

    //Setters for the variables
    public void setCourseNum(String courseNumVar) {this.courseNum = courseNumVar;}
    public void setCourseName(String courseNameVar) {this.courseName = courseNameVar;}
}
