package com.example.quiz.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static com.example.quiz.database.QuestionsContract.SQL_CREATE_ENTRIES;
import static com.example.quiz.database.QuestionsContract.SQL_DELETE_ENTRIES;
import static com.example.quiz.database.ScoresContract.SQL_CREATE_SCORES;
import static com.example.quiz.database.ScoresContract.SQL_DELETE_SCORES;

public class ScoresDbHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 2;
    public static final String DATABASE_NAME = "Scores.db";

    public ScoresDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_SCORES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_SCORES);
        onCreate(db);
    }
}
