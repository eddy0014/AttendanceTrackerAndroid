package com.example.eddy.attendancetracker;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by e-sal on 4/16/2017.
 */

public class DatabaseHandler extends SQLiteOpenHelper{
    //Used for logging
    private static final String TAG = "DATABASE_MESSAGE";

    //Static variables
    //Database version
    private static final int DATABASE_VERSION = 1;

    //Database name
    private static final String DATABASE_NAME = "attendanceTracker";

    //Table names
    private static final String TABLE_SONGS = "songs";
    private static final String TABLE_ARTISTS = "artists";

    //Table columns' names
    private static final String KEY_ID = "id";
    private static final String KEY_TITLE = "title";
    private static final String KEY_ARTIST = "artist";
    private static final String KEY_ALBUM = "album";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    //Creating tables
    //Tutorial had ID as the primary key. Took that off since each song will originally
    //have its own ID when added to the database
    @Override
    public void onCreate(SQLiteDatabase db) {
        //The strings used for the queries
        String CREATE_COURSES_TABLE = "CREATE TABLE courses (course_num TEXT, course_name TEXT)";
        String CREATE_STUDENTS_TABLE = "CREATE TABLE students (student_id INT, first_name TEXT, last_name TEXT)";

        //Execute the strings
        db.execSQL(CREATE_COURSES_TABLE);
        db.execSQL(CREATE_STUDENTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //Drop older tables if existed
        db.execSQL("DROP TABLE IF EXISTS " + "courses");
        db.execSQL("DROP TABLE IF EXISTS " + "students");

        //Create tables again
        onCreate(db);
    }

    public void addCourse(String courseNum, String courseName) {
        SQLiteDatabase db = this.getWritableDatabase();

        // Values to put into the "courses" table
        ContentValues values = new ContentValues();
        values.put("course_num", courseNum);
        values.put("course_name", courseName);

        // Inserting row into "courses" table
        db.insert("courses", null, values);

        // Create the table that will hold the students enrolled in the class
        String CREATE_NEW_COURSE_TABLE = "CREATE TABLE " + courseNum + " (student_id int, first_name TEXT, last_name TEXT)";
        db.execSQL(CREATE_NEW_COURSE_TABLE);

        // Create the table that will hold the attendance for the students in the course
        String CREATE_NEW_COURSE_ATTENDANCE_TABLE = "CREATE TABLE attendance_" + courseNum + " (first_name TEXT, last_name TEXT, status TEXT, status_date TEXT)";
        db.execSQL(CREATE_NEW_COURSE_ATTENDANCE_TABLE);

        db.close();
    }

    public void addStudent(String courseNumber, int studentID, String firstName, String lastName) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("student_id", studentID);
        values.put("first_name", firstName);
        values.put("last_name", lastName);

        // Inserting row
        db.insert(courseNumber, null, values);
        db.close();
    }

    public void addStudentAttendance(String courseNumber, String firstName, String lastName, boolean statusValue, String date) {
        SQLiteDatabase db = this.getWritableDatabase();

        String status = String.valueOf(statusValue);

        ContentValues values = new ContentValues();
        values.put("first_name", firstName);
        values.put("last_name", lastName);
        values.put("status", status);
        values.put("status_date", date);

        // Inserting row
        db.insert("attendance_" + courseNumber, null, values);
        Log.v(TAG, firstName + " " + status + " " + date);
        db.close();
    }

    public ArrayList<Course> getCourses() {
        ArrayList<Course> courseList = new ArrayList<Course>();

        String selectQuery = "SELECT * FROM courses";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // Loop through all the selected rows
        if(cursor.moveToFirst()) {
            do {
                Course course = new Course();
                course.setCourseNum(cursor.getString(0));
                course.setCourseName(cursor.getString(1));
                courseList.add(course);
            } while(cursor.moveToNext());
        }

        return courseList;
    }

    public ArrayList<Student> getStudent(int studentID, String courseNum) {
        ArrayList<Student> student = new ArrayList<Student>();

        String selectQuery = "SELECT * FROM " + courseNum + " WHERE student_id = " + studentID;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // Loop through all the selected rows
        if(cursor.moveToFirst()) {
            do {
                Student theStudent = new Student();
                theStudent.setStudentID(cursor.getString(0));
                theStudent.setFirstName(cursor.getString(1));
                theStudent.setLastName(cursor.getString(2));
                student.add(theStudent);
            } while(cursor.moveToNext());
        }

        return student;
    }

    public ArrayList<Student> getStudentsFromCourse(String courseNum) {
        ArrayList<Student> studentList = new ArrayList<Student>();

        String selectQuery = "SELECT * FROM " + courseNum;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // Loop through all the selected rows
        if(cursor.moveToFirst()) {
            do {
                Student student = new Student();
                student.setStudentID(cursor.getString(0));
                student.setFirstName(cursor.getString(1));
                student.setLastName(cursor.getString(2));
                studentList.add(student);
            } while(cursor.moveToNext());
        }

        return studentList;
    }

    public ArrayList<StudentAttendance> getStudentsAtdFromCourse(String courseNum) {
        ArrayList<StudentAttendance> studentList = new ArrayList<StudentAttendance>();

        String selectQuery = "SELECT * FROM " + courseNum;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // Loop through all the selected rows
        if(cursor.moveToFirst()) {
            do {
                StudentAttendance student = new StudentAttendance();
                student.setStudentID(cursor.getString(0));
                student.setFirstName(cursor.getString(1));
                student.setLastName(cursor.getString(2));
                studentList.add(student);
            } while(cursor.moveToNext());
        }

        return studentList;
    }

    public ArrayList<String> getAttendanceForDay(String courseNum, String date) {
        ArrayList<String> attendanceList = new ArrayList<String>();

        String selectQuery = "SELECT * FROM attendance_" + courseNum + " WHERE status_date = '" + date + "'";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // Loop through all the selected rows
        if(cursor.moveToFirst()) {
            do {
                String presentOrNot;
                if(cursor.getString(2).equals("true")) {
                    presentOrNot = "Present";
                }
                else {
                    presentOrNot = "Not Present";
                }
                String student = cursor.getString(0) + " " + cursor.getString(1) + " " + presentOrNot + " " + cursor.getString(3);
                attendanceList.add(student);
            } while(cursor.moveToNext());
        }

        return attendanceList;
    }


    public void deleteStudent(int studentId, String courseNum) {
        SQLiteDatabase db = this.getWritableDatabase();

        String deleteQuery = "DELETE FROM " + courseNum + " WHERE student_id = " + studentId;

        db.execSQL(deleteQuery);
    }
}
