package com.example.eddy.attendancetracker;

/**
 * Created by e-sal on 4/25/2017.
 */

public class StudentAttendance {

    //Variables for the data of each student
    private String studentID;
    private String firstName;
    private String lastName;
    private boolean present = true;

    // Empty constructor
    public StudentAttendance() {

    }

    //Constructor used to instantiate the variables
    public StudentAttendance(String studentIDVar, String firstNameVar, String lastNameVar) {
        studentID = studentIDVar;
        firstName = firstNameVar;
        lastName = lastNameVar;
    }

    //Getters for the variables
    public String getStudentID() {
        return studentID;
    }
    public String getFirstName() {
        return firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public boolean getPresentStatus() { return present; }

    //Setters for the variables
    public void setStudentID(String studentIDVar) {this.studentID = studentIDVar;}
    public void setFirstName(String firstNameVar) {this.firstName = firstNameVar;}
    public void setLastName(String lastNameVar) {this.lastName = lastNameVar;}
    public void setPresentStatus(boolean presentVar) {this.present = presentVar;}
}
