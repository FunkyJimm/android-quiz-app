package com.example.quiz;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.quiz.questions.MultiAnswersQuestion;

import java.util.LinkedList;
import java.util.Random;

public class MultiAnswersActivity extends AppCompatActivity {

    private final int QUESTIONS_LIMIT = 5;

    private LinkedList<MultiAnswersQuestion> multiAnswersQuestionList = new LinkedList<MultiAnswersQuestion>();

    private TextView tvPlayerPointsView;
    private TextView tvQuestionView;
    private TextView tvQuestionNumber;
    private CheckBox checkBoxA;
    private CheckBox checkBoxB;
    private CheckBox checkBoxC;
    private CheckBox checkBoxD;
    private Button btnClear;
    private Button btnCheck;

    private int drawQuestionNumber;
    private boolean correctAnswer = false;

    private int playerScore;
    private int questionsCount = 0;
    private int correctQuestionsCount = 1;
    private int previousQuestionsCount = 0;

    private boolean playerScoreOn = false;
    private boolean correctAnswersShowOn = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multi_answers);

        tvPlayerPointsView = (TextView) findViewById(R.id.tvPlayerPoints);
        tvQuestionNumber = (TextView) findViewById(R.id.questionNumber);
        tvQuestionView = (TextView) findViewById(R.id.questionView);
        checkBoxA = (CheckBox) findViewById(R.id.checkBoxA);
        checkBoxB = (CheckBox) findViewById(R.id.checkBoxB);
        checkBoxC = (CheckBox) findViewById(R.id.checkBoxC);
        checkBoxD = (CheckBox) findViewById(R.id.checkBoxD);
        btnClear = (Button) findViewById(R.id.btnClear);
        btnCheck = (Button) findViewById(R.id.btnCheck);

        Bundle options = getIntent().getExtras();
        playerScoreOn = options.getBoolean("playerScoreOn");
        correctAnswersShowOn = options.getBoolean("correctAnswersShowOn");
        playerScore = options.getInt("playerScore", playerScore);
        previousQuestionsCount = options.getInt("questionsCount", questionsCount) + 1;

        addQuestions();
        drawQuestionNumber = drawQuestion();

        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkBoxA.setChecked(false);
                checkBoxB.setChecked(false);
                checkBoxC.setChecked(false);
                checkBoxD.setChecked(false);
            }
        });

        btnCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                correctAnswer = checkAnswer(drawQuestionNumber);
                multiAnswersQuestionList.remove(drawQuestionNumber);

                if (multiAnswersQuestionList.isEmpty() || questionsCount == QUESTIONS_LIMIT) {
                    if (correctAnswer) {
                        correctQuestionsCount++;
                    }

                    Intent intent = new Intent(getApplicationContext(), ResultActivity.class);
                    intent.putExtra("playerScore", playerScore);
                    startActivity(intent);
                }

                if (correctAnswer) {
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
        MultiAnswersQuestion question1 = new MultiAnswersQuestion(
                "Które z planet nie są gazowymi olbrzymami?",
                "Merkury",
                "Jowisz",
                "Uran",
                "Wenus"
        );
        question1.getCorrectAnswers().add(1);
        question1.getCorrectAnswers().add(4);
        multiAnswersQuestionList.add(question1);
        MultiAnswersQuestion question2 = new MultiAnswersQuestion(
                "Z jakich członów składać będzie się rakieta Starship?",
                "Pathfinder",
                "Starship",
                "BFR",
                "Super Heavy"
        );
        question2.getCorrectAnswers().add(2);
        question2.getCorrectAnswers().add(4);
        multiAnswersQuestionList.add(question2);
        MultiAnswersQuestion question3 = new MultiAnswersQuestion(
                "Które z języków programowania to języki typowane statycznie?",
                "Java",
                "Pascal",
                "Python",
                "Swift"
        );
        question3.getCorrectAnswers().add(1);
        question3.getCorrectAnswers().add(2);
        question3.getCorrectAnswers().add(4);
        multiAnswersQuestionList.add(question3);
        MultiAnswersQuestion question4 = new MultiAnswersQuestion(
                "Które z gier zostały wyprodukowane w Polsce?",
                "Dying Light",
                "Europa 1400",
                "The Last of Us",
                "This War of Mine"
        );
        question4.getCorrectAnswers().add(1);
        question4.getCorrectAnswers().add(4);
        multiAnswersQuestionList.add(question4);
        MultiAnswersQuestion question5 = new MultiAnswersQuestion(
                "Którzy z wymienionych założyli firmę Apple?",
                "Ronald Wayne",
                "Steve Wozniak",
                "Steve Jobs",
                "Tim Cook"
        );
        question5.getCorrectAnswers().add(1);
        question5.getCorrectAnswers().add(2);
        question5.getCorrectAnswers().add(3);
        multiAnswersQuestionList.add(question5);
    }

    private int drawQuestion() {
        Random random = new Random();
        int questionNumber = random.nextInt(multiAnswersQuestionList.size());

        tvQuestionView.setText(multiAnswersQuestionList.get(questionNumber).getQuestionContent());
        checkBoxA.setText(multiAnswersQuestionList.get(questionNumber).getQuestionAnswerA());
        checkBoxB.setText(multiAnswersQuestionList.get(questionNumber).getQuestionAnswerB());
        checkBoxC.setText(multiAnswersQuestionList.get(questionNumber).getQuestionAnswerC());
        checkBoxD.setText(multiAnswersQuestionList.get(questionNumber).getQuestionAnswerD());

        tvQuestionNumber.setText("Pytanie nr " + (previousQuestionsCount + questionsCount));
        questionsCount++;
        togglePlayerPoints();
        return questionNumber;
    }

    private boolean checkAnswer(int questionNumber) {
        int numberOfCorrectAnswers = 0;
        int numberOfSelectedCorrectAnswers = 0;

        for (int i = 0; i < multiAnswersQuestionList.get(questionNumber).getCorrectAnswers().size(); i++) {
            numberOfCorrectAnswers++;
        }

        if (checkBoxA.isChecked() && multiAnswersQuestionList.get(questionNumber).getCorrectAnswers().contains(1)) {
            numberOfSelectedCorrectAnswers++;
        } else if (checkBoxA.isChecked() && !multiAnswersQuestionList.get(questionNumber).getCorrectAnswers().contains(1)){
            numberOfSelectedCorrectAnswers--;
        }

        if (checkBoxB.isChecked() && multiAnswersQuestionList.get(questionNumber).getCorrectAnswers().contains(2)) {
            numberOfSelectedCorrectAnswers++;
        } else if (checkBoxB.isChecked() && !multiAnswersQuestionList.get(questionNumber).getCorrectAnswers().contains(2)){
            numberOfSelectedCorrectAnswers--;
        }

        if (checkBoxC.isChecked() && multiAnswersQuestionList.get(questionNumber).getCorrectAnswers().contains(3)) {
            numberOfSelectedCorrectAnswers++;
        } else if (checkBoxC.isChecked() && !multiAnswersQuestionList.get(questionNumber).getCorrectAnswers().contains(3)){
            numberOfSelectedCorrectAnswers--;
        }

        if (checkBoxD.isChecked() && multiAnswersQuestionList.get(questionNumber).getCorrectAnswers().contains(4)) {
            numberOfSelectedCorrectAnswers++;
        } else if (checkBoxD.isChecked() && !multiAnswersQuestionList.get(questionNumber).getCorrectAnswers().contains(4)){
            numberOfSelectedCorrectAnswers--;
        }

        if (numberOfCorrectAnswers == numberOfSelectedCorrectAnswers) {
            Toast.makeText(this, "Poprawna odpowiedź!", Toast.LENGTH_SHORT).show();
            playerScore += correctQuestionsCount * 100;
            correctQuestionsCount++;
            return true;
        } else {
            if (correctAnswersShowOn) {
                String correctAnswersChars = "";

                if (multiAnswersQuestionList.get(questionNumber).getCorrectAnswers().contains(1))
                    correctAnswersChars += "A";
                if (multiAnswersQuestionList.get(questionNumber).getCorrectAnswers().contains(2))
                    correctAnswersChars += "B";
                if (multiAnswersQuestionList.get(questionNumber).getCorrectAnswers().contains(3))
                    correctAnswersChars += "C";
                if (multiAnswersQuestionList.get(questionNumber).getCorrectAnswers().contains(4))
                    correctAnswersChars += "D";

                Toast.makeText(this, "Źle! Prawidłowymi odpowiedziami są: " + correctAnswersChars + "!", Toast.LENGTH_SHORT).show();
                return false;
            } else {
                Toast.makeText(this, "Odpowiedź nieprawidłowa!", Toast.LENGTH_SHORT).show();
                return false;
            }
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