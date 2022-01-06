package com.example.quiz;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.provider.BaseColumns;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.quiz.database.QuestionsContract;
import com.example.quiz.database.QuestionsDbHelper;
import com.example.quiz.questions.SingleAnswerQuestion;

import java.util.LinkedList;
import java.util.Random;

public class SingleAnswerActivity extends AppCompatActivity {

    private final int QUESTIONS_LIMIT = 5;

    private LinkedList<SingleAnswerQuestion> singleAnswerQuestions = new LinkedList<>();

    private TextView tvPlayerPointsView;
    private TextView tvQuestionNumber;
    private TextView tvQuestionView;
    private RadioButton radioButtonA;
    private RadioButton radioButtonB;
    private RadioButton radioButtonC;
    private RadioButton radioButtonD;
    private Button btnClear;
    private Button btnCheck;

    private int drawQuestionNumber;
    private boolean correctAnswer = false;

    private int playerScore;
    private int questionsCount = 0;
    private int correctQuestionsCount = 1;

    private boolean multipleAnswersOn = false;
    private boolean playerScoreOn = false;
    private boolean correctAnswersShowOn = false;

    QuestionsDbHelper questionsDbHelper = new QuestionsDbHelper(this);

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_answer);

        tvPlayerPointsView = (TextView) findViewById(R.id.tvPlayerPoints);
        tvQuestionNumber = (TextView) findViewById(R.id.questionNumber);
        tvQuestionView = (TextView) findViewById(R.id.questionView);
        radioButtonA = (RadioButton) findViewById(R.id.radioButtonA);
        radioButtonB = (RadioButton) findViewById(R.id.radioButtonB);
        radioButtonC = (RadioButton) findViewById(R.id.radioButtonC);
        radioButtonD = (RadioButton) findViewById(R.id.radioButtonD);
        btnClear = (Button) findViewById(R.id.btnClear);
        btnCheck = (Button) findViewById(R.id.btnCheck);

        Bundle options = getIntent().getExtras();
        multipleAnswersOn = options.getBoolean("multipleAnswersOn");
        playerScoreOn = options.getBoolean("playerScoreOn");
        correctAnswersShowOn = options.getBoolean("correctAnswersShowOn");

        singleAnswerQuestions = questionsDbHelper.readQuestionsFromDatabase(questionsDbHelper);

        addQuestions();
        drawQuestionNumber = drawQuestion();

        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                radioButtonA.setChecked(false);
                radioButtonB.setChecked(false);
                radioButtonC.setChecked(false);
                radioButtonD.setChecked(false);
            }
        });

        btnCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                correctAnswer = checkAnswer(drawQuestionNumber);
                singleAnswerQuestions.remove(drawQuestionNumber);

                if (singleAnswerQuestions.isEmpty() || questionsCount == QUESTIONS_LIMIT) {
                    if (correctAnswer) {
                        correctQuestionsCount++;
                    }

                    if (multipleAnswersOn) {
                        Intent options = new Intent(getApplicationContext(), MultiAnswersActivity.class);
                        options.putExtra("playerScoreOn", playerScoreOn);
                        options.putExtra("correctAnswersShowOn", correctAnswersShowOn);
                        options.putExtra("playerScore", playerScore);
                        options.putExtra("questionsCount", questionsCount);
                        startActivity(options);
                    } else {
                        Intent intent = new Intent(getApplicationContext(), ResultActivity.class);
                        intent.putExtra("playerScore", playerScore);
                        startActivity(intent);
                    }
                }

                if (correctAnswer) {
                    correctQuestionsCount++;
                    if (questionsCount < QUESTIONS_LIMIT)
                        drawQuestionNumber = drawQuestion();
                } else {
                    if (questionsCount < QUESTIONS_LIMIT)
                        drawQuestionNumber = drawQuestion();
                }
            }
        });
    }

    private void addQuestions() {
        SingleAnswerQuestion question1 = new SingleAnswerQuestion(
                "Z ilu stopni zbudowana była amerykańska rakieta Saturn V?",
                "Jednego",
                "Dwóch",
                "Trzech",
                "Czterech",
                3
        );
        SingleAnswerQuestion question2 = new SingleAnswerQuestion(
                "W jakim gatunku muzycznym wydał swoje dwa albumy tytułowy Dr House?",
                "Blues",
                "Rock",
                "Pop",
                "Jazz",
                1
        );
        SingleAnswerQuestion question3 = new SingleAnswerQuestion(
                "BFR to pierwotna nazwa, której rakiety firmy SpaceX?",
                "Falcon Heavy",
                "Dragon",
                "Falcon 9",
                "Starship",
                4
        );
        SingleAnswerQuestion question4 = new SingleAnswerQuestion(
                "W którym roku zadebiutował bardzo wydajny czip M1 firmy Apple?",
                "2018",
                "2020",
                "2019",
                "2021",
                2
        );
        SingleAnswerQuestion question5 = new SingleAnswerQuestion(
                "Ile wynosi 1 cal?",
                "2,54 cm",
                "1 cm",
                "3 cm",
                "5,7 cm",
                1
        );

        questionsDbHelper.addQuestionToDatabase(questionsDbHelper, question1);
        questionsDbHelper.addQuestionToDatabase(questionsDbHelper, question2);
        questionsDbHelper.addQuestionToDatabase(questionsDbHelper, question3);
        questionsDbHelper.addQuestionToDatabase(questionsDbHelper, question4);
        questionsDbHelper.addQuestionToDatabase(questionsDbHelper, question5);
    }

    private int drawQuestion() {
        Random random = new Random();
        int questionNumber = random.nextInt(singleAnswerQuestions.size());

        tvQuestionView.setText(singleAnswerQuestions.get(questionNumber).getQuestionContent());
        radioButtonA.setText(singleAnswerQuestions.get(questionNumber).getQuestionAnswerA());
        radioButtonB.setText(singleAnswerQuestions.get(questionNumber).getQuestionAnswerB());
        radioButtonC.setText(singleAnswerQuestions.get(questionNumber).getQuestionAnswerC());
        radioButtonD.setText(singleAnswerQuestions.get(questionNumber).getQuestionAnswerD());

        tvQuestionNumber.setText("Pytanie nr " + (questionsCount + 1));
        questionsCount++;
        togglePlayerPoints();
        return questionNumber;
    }

    private boolean checkAnswer(int questionNumber) {
        if (radioButtonA.isChecked() && singleAnswerQuestions.get(questionNumber).getCorrectAnswer() == 1) {
            Toast.makeText(this, "Poprawna odpowiedź!", Toast.LENGTH_SHORT).show();
            playerScore += correctQuestionsCount * 100;
            return true;
        } else if (radioButtonB.isChecked() && singleAnswerQuestions.get(questionNumber).getCorrectAnswer() == 2) {
            Toast.makeText(this, "Poprawna odpowiedź!", Toast.LENGTH_SHORT).show();
            playerScore += correctQuestionsCount * 100;
            return true;
        } else if (radioButtonC.isChecked() && singleAnswerQuestions.get(questionNumber).getCorrectAnswer() == 3) {
            Toast.makeText(this, "Poprawna odpowiedź!", Toast.LENGTH_SHORT).show();
            playerScore += correctQuestionsCount * 100;
            return true;
        } else if (radioButtonD.isChecked() && singleAnswerQuestions.get(questionNumber).getCorrectAnswer() == 4) {
            Toast.makeText(this, "Poprawna odpowiedź!", Toast.LENGTH_SHORT).show();
            playerScore += correctQuestionsCount * 100;
            return true;
        } else {
            if (correctAnswersShowOn) {
                int correctAnswerNumber = singleAnswerQuestions.get(questionNumber).getCorrectAnswer();
                char correctAnswerChar = ' ';

                if (correctAnswerNumber == 1)
                    correctAnswerChar = 'A';
                else if (correctAnswerNumber == 2)
                    correctAnswerChar = 'B';
                else if (correctAnswerNumber == 3)
                    correctAnswerChar = 'C';
                else if (correctAnswerNumber == 4)
                    correctAnswerChar = 'D';

                Toast.makeText(this, "Źle! Prawidłową odpowiedzią jest odpowiedź " + correctAnswerChar + "!", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Odpowiedź nieprawidłowa!", Toast.LENGTH_SHORT).show();
            }
            return false;
        }
    }

    @SuppressLint("SetTextI18n")
    private void togglePlayerPoints() {
        if (playerScoreOn) {
            tvPlayerPointsView.setText("Punkty gracza: " + playerScore);
        } else {
            tvPlayerPointsView.setText("");
        }
    }
}
