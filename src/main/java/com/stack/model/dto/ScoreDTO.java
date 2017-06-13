package com.stack.model.dto;

public class ScoreDTO {
    private int score;

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public ScoreDTO(int score){
        setScore(score);
    }
}
