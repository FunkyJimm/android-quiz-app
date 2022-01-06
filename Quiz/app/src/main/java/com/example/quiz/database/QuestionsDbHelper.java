package com.example.quiz.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.quiz.questions.SingleAnswerQuestion;

import java.util.LinkedList;

import static com.example.quiz.database.QuestionsContract.SQL_CREATE_ENTRIES;
import static com.example.quiz.database.QuestionsContract.SQL_DELETE_ENTRIES;

public class QuestionsDbHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 2;
    public static final String DATABASE_NAME = "Questions.db";

    public QuestionsDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }

    public void addQuestionToDatabase(QuestionsDbHelper questionsDbHelper, SingleAnswerQuestion singleAnswerQuestion) {
        SQLiteDatabase db = questionsDbHelper.getWritableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM questions WHERE content='"+singleAnswerQuestion.getQuestionContent()+"'", null);

        if (cursor.moveToFirst()) {
            System.out.println("Podany rekord już istnieje w bazie danych!");
        } else {
            ContentValues values = new ContentValues();
            values.put(QuestionsContract.QuestionsEntry.COLUMNN_NAME_CONTENT, singleAnswerQuestion.getQuestionContent());
            values.put(QuestionsContract.QuestionsEntry.COLUMNN_NAME_ANSWER_A, singleAnswerQuestion.getQuestionAnswerA());
            values.put(QuestionsContract.QuestionsEntry.COLUMNN_NAME_ANSWER_B, singleAnswerQuestion.getQuestionAnswerB());
            values.put(QuestionsContract.QuestionsEntry.COLUMNN_NAME_ANSWER_C, singleAnswerQuestion.getQuestionAnswerC());
            values.put(QuestionsContract.QuestionsEntry.COLUMNN_NAME_ANSWER_D, singleAnswerQuestion.getQuestionAnswerD());
            values.put(QuestionsContract.QuestionsEntry.COLUMNN_NAME_CORRECT, singleAnswerQuestion.getCorrectAnswer());

            long newRowId = db.insert(QuestionsContract.QuestionsEntry.TABLE_NAME, null, values);
        }
    }

    public LinkedList<SingleAnswerQuestion> readQuestionsFromDatabase(QuestionsDbHelper questionsDbHelper) {
        SQLiteDatabase db = questionsDbHelper.getReadableDatabase();

        LinkedList<SingleAnswerQuestion> singleAnswerQuestions = new LinkedList<>();

        String[] projection = {
                QuestionsContract.QuestionsEntry.COLUMNN_NAME_CONTENT,
                QuestionsContract.QuestionsEntry.COLUMNN_NAME_ANSWER_A,
                QuestionsContract.QuestionsEntry.COLUMNN_NAME_ANSWER_B,
                QuestionsContract.QuestionsEntry.COLUMNN_NAME_ANSWER_C,
                QuestionsContract.QuestionsEntry.COLUMNN_NAME_ANSWER_D,
                QuestionsContract.QuestionsEntry.COLUMNN_NAME_CORRECT
        };

        Cursor cursor = db.query(
                QuestionsContract.QuestionsEntry.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                null
        );

        while(cursor.moveToNext()) {
            String content = cursor.getString(
                    cursor.getColumnIndexOrThrow(QuestionsContract.QuestionsEntry.COLUMNN_NAME_CONTENT));
            String answerA = cursor.getString(
                    cursor.getColumnIndexOrThrow(QuestionsContract.QuestionsEntry.COLUMNN_NAME_ANSWER_A));
            String answerB = cursor.getString(
                    cursor.getColumnIndexOrThrow(QuestionsContract.QuestionsEntry.COLUMNN_NAME_ANSWER_B));
            String answerC = cursor.getString(
                    cursor.getColumnIndexOrThrow(QuestionsContract.QuestionsEntry.COLUMNN_NAME_ANSWER_C));
            String answerD = cursor.getString(
                    cursor.getColumnIndexOrThrow(QuestionsContract.QuestionsEntry.COLUMNN_NAME_ANSWER_D));
            String correct = cursor.getString(
                    cursor.getColumnIndexOrThrow(QuestionsContract.QuestionsEntry.COLUMNN_NAME_CORRECT));
            singleAnswerQuestions.add(new SingleAnswerQuestion(content, answerA, answerB, answerC, answerD, Integer.parseInt(correct)));
        }
        cursor.close();

        return singleAnswerQuestions;
    }
}
