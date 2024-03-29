package com.example.assignment02_quizapp;
import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.example.assignment02_quizapp.Result;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DbHelper extends SQLiteOpenHelper {

    public static final String TABLE_NAME = "Result";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_QUESTION = "question";
    public static final String COLUMN_ANSWER = "answer";
    public static final String COLUMN_SUBMITTEDANSWER = "submittedAnswer";
    public static final String COLUMN_STATUS = "status";

    public DbHelper(@Nullable Context context) {
        super(context, "Quiz.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " ( "
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_QUESTION + " TEXT,"
                + COLUMN_ANSWER + " TEXT,"
                + COLUMN_SUBMITTEDANSWER + " TEXT,"
                + COLUMN_STATUS + " TEXT"
                + " ) ";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "DROP TABLE IF EXISTS " + TABLE_NAME;

        db.execSQL(sql);
        onCreate(db);
    }
    public void insertResult(Result result) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_QUESTION, result.getQuestion());
        values.put(COLUMN_ANSWER, result.getAnswer());
        values.put(COLUMN_SUBMITTEDANSWER, result.getSubmittedAnswer());
        values.put(COLUMN_STATUS, result.getStatus());
        db.insert(TABLE_NAME, null, values);
        db.close();
    }
    public int select() {
        String sql = "SELECT "+ COLUMN_STATUS +" FROM " + TABLE_NAME +" WHERE status = 'T';";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(sql, null);
        int right = cursor.getCount();
        cursor.close();
        db.close();
        return right;
    }
    public void truncate() {
        String sql = "DELETE FROM "+ TABLE_NAME + ";";

        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL(sql);
        db.close();
    }
    public List<Result> selectAllStudents() {
        List<Result> results = new ArrayList<>();

        String sql = "SELECT * FROM " + TABLE_NAME;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor.moveToFirst()) {
            do {
                @SuppressLint("Range") int id = cursor.getInt(cursor.getColumnIndex(COLUMN_ID));
                @SuppressLint("Range") String question = cursor.getString(cursor.getColumnIndex(COLUMN_QUESTION));
                @SuppressLint("Range") String answer = cursor.getString(cursor.getColumnIndex(COLUMN_ANSWER));
                @SuppressLint("Range") String submittedAnswer = cursor.getString(cursor.getColumnIndex(COLUMN_SUBMITTEDANSWER));
                @SuppressLint("Range") String status = cursor.getString(cursor.getColumnIndex(COLUMN_STATUS));
                results.add(new Result(question,answer,submittedAnswer,status));
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return results;
    }
}

