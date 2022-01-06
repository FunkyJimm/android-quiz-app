package com.example.quiz.database;

import android.provider.BaseColumns;

public final class QuestionsContract {

    private QuestionsContract() {}

    public static class QuestionsEntry implements BaseColumns {
        public static final String TABLE_NAME = "questions";
        public static final String COLUMNN_NAME_CONTENT = "content";
        public static final String COLUMNN_NAME_ANSWER_A = "answer_a";
        public static final String COLUMNN_NAME_ANSWER_B = "answer_b";
        public static final String COLUMNN_NAME_ANSWER_C = "answer_c";
        public static final String COLUMNN_NAME_ANSWER_D = "answer_d";
        public static final String COLUMNN_NAME_CORRECT = "correct";
    }

    public static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + QuestionsEntry.TABLE_NAME + " (" +
                    QuestionsEntry._ID + " INTEGER PRIMARY KEY," +
                    QuestionsEntry.COLUMNN_NAME_CONTENT + " TEXT," +
                    QuestionsEntry.COLUMNN_NAME_ANSWER_A + " TEXT," +
                    QuestionsEntry.COLUMNN_NAME_ANSWER_B + " TEXT," +
                    QuestionsEntry.COLUMNN_NAME_ANSWER_C + " TEXT," +
                    QuestionsEntry.COLUMNN_NAME_ANSWER_D + " TEXT," +
                    QuestionsEntry.COLUMNN_NAME_CORRECT + " TEXT)";

    public static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + QuestionsEntry.TABLE_NAME;
}
