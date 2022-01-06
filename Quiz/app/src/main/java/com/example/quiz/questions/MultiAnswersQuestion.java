package com.example.quiz.questions;

import java.util.ArrayList;

public class MultiAnswersQuestion {

    private String questionContent;
    private String questionAnswerA;
    private String questionAnswerB;
    private String questionAnswerC;
    private String questionAnswerD;
    private ArrayList<Integer> correctAnswers = new ArrayList<Integer>();

    public MultiAnswersQuestion(String questionContent, String questionAnswerA, String questionAnswerB, String questionAnswerC, String questionAnswerD) {
        this.questionContent = questionContent;
        this.questionAnswerA = questionAnswerA;
        this.questionAnswerB = questionAnswerB;
        this.questionAnswerC = questionAnswerC;
        this.questionAnswerD = questionAnswerD;
    }

    public String getQuestionContent() {
        return questionContent;
    }

    public void setQuestionContent(String questionContent) {
        this.questionContent = questionContent;
    }

    public String getQuestionAnswerA() {
        return questionAnswerA;
    }

    public void setQuestionAnswerA(String questionAnswerA) {
        this.questionAnswerA = questionAnswerA;
    }

    public String getQuestionAnswerB() {
        return questionAnswerB;
    }

    public void setQuestionAnswerB(String questionAnswerB) {
        this.questionAnswerB = questionAnswerB;
    }

    public String getQuestionAnswerC() {
        return questionAnswerC;
    }

    public void setQuestionAnswerC(String questionAnswerC) {
        this.questionAnswerC = questionAnswerC;
    }

    public String getQuestionAnswerD() {
        return questionAnswerD;
    }

    public void setQuestionAnswerD(String questionAnswerD) {
        this.questionAnswerD = questionAnswerD;
    }

    public ArrayList<Integer> getCorrectAnswers() {
        return correctAnswers;
    }

    public void setCorrectAnswers(ArrayList<Integer> correctAnswers) {
        this.correctAnswers = correctAnswers;
    }
}
