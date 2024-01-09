package com.b4bank.tennis.models;

import lombok.Data;

@Data
public class Player {
    private Score score;

    public Player () {
        this.score = Score.LOVE;
    }
}
