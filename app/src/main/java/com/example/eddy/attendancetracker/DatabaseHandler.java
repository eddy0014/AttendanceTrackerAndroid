package com.example.eddy.attendancetracker;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

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

        ContentValues values = new ContentValues();
        values.put("course_num", courseNum);
        values.put("course_name", courseName);

        //Inserting row
        db.insert("courses", null, values);
        db.close();
    }
}
