package com.example.quiz.scores;

public class Score {

    private String nick;
    private String score;

    public Score(String nick, String score) {
        this.nick = nick;
        this.score = score;
    }

    public String getNick() {
        return nick;
    }

    public String getScore() {
        return score;
    }
}
