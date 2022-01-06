package com.example.quiz.questions;

public class SingleAnswerQuestion {

    private String questionContent;
    private String questionAnswerA;
    private String questionAnswerB;
    private String questionAnswerC;
    private String questionAnswerD;
    private int correctAnswer;

    public SingleAnswerQuestion(String questionContent, String questionAnswerA, String questionAnswerB, String questionAnswerC, String questionAnswerD, int correctAnswer) {
        this.questionContent = questionContent;
        this.questionAnswerA = questionAnswerA;
        this.questionAnswerB = questionAnswerB;
        this.questionAnswerC = questionAnswerC;
        this.questionAnswerD = questionAnswerD;
        this.correctAnswer = correctAnswer;
    }

    public String getQuestionContent() {
        return questionContent;
    }

    public String getQuestionAnswerA() {
        return questionAnswerA;
    }

    public String getQuestionAnswerB() {
        return questionAnswerB;
    }

    public String getQuestionAnswerC() {
        return questionAnswerC;
    }

    public String getQuestionAnswerD() {
        return questionAnswerD;
    }

    public int getCorrectAnswer() {
        return correctAnswer;
    }
}
