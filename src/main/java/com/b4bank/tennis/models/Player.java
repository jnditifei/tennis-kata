package com.b4bank.tennis.models;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Player {
    private Score score;

    public Player () {
        this.score = Score.LOVE;
    }
}
