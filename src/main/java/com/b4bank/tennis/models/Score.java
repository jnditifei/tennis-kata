package com.b4bank.tennis.models;

public enum Score {
    LOVE("0"), FIFTEEN("15"), THIRTY("30"), FORTY("40"), ADVANTAGE("Advantage"), DEUCE("Deuce"), GAME("Game");

    private final String scoreValue;

    Score(String scoreValue) {
        this.scoreValue = scoreValue;
    }

    public String getScoreValue() {
        return scoreValue;
    }
}



