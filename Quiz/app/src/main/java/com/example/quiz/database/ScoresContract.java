package com.example.quiz.database;

import android.provider.BaseColumns;

public final class ScoresContract {

    private ScoresContract() {}

    public static class ScoreEntry implements BaseColumns {
        public static final String TABLE_NAME = "scores";
        public static final String COLUMN_NAME_NICK = "nick";
        public static final String COLUMN_NAME_SCORE = "score";
    }

    public static final String SQL_CREATE_SCORES =
            "CREATE TABLE " + ScoreEntry.TABLE_NAME + " (" +
                    ScoresContract.ScoreEntry._ID + " INTEGER PRIMARY KEY," +
                    ScoresContract.ScoreEntry.COLUMN_NAME_NICK + " TEXT," +
                    ScoresContract.ScoreEntry.COLUMN_NAME_SCORE + " TEXT)";

    public static final String SQL_DELETE_SCORES =
            "DROP TABLE IF EXISTS " + ScoresContract.ScoreEntry.TABLE_NAME;
}
