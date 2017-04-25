package com.example.eddy.attendancetracker;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

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
        String CREATE_NEW_COURSE_ATTENDANCE_TABLE = "CREATE TABLE attendance_" + courseNum + " (student_id int, first_name TEXT, last_name TEXT, status TEXT, status_date TEXT)";
        db.execSQL(CREATE_NEW_COURSE_ATTENDANCE_TABLE);

        db.close();
    }

    public void addStudent(int studentID, String firstName, String lastName) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("student_id", studentID);
        values.put("first_name", firstName);
        values.put("last_name", lastName);

        // Inserting row
        //db.insert("")
        //db.close();
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
}
