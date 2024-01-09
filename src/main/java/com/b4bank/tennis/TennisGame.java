package com.b4bank.tennis;

import com.b4bank.tennis.services.ScoreService;
import com.b4bank.tennis.services.impl.ScoreServiceImpl;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class TennisGame {
    private final ScoreService scoreService;

    public TennisGame() {
        this.scoreService = new ScoreServiceImpl();
    }


    public void simulateGame(String game) {
        try {
            scoreService.updatePlayerScore(game);
            System.out.println(scoreService.getWinner());
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }


    public static void main(String[] args) {
        TennisGame game = new TennisGame();

        // Simulating a match
        game.simulateGame("ABABABABABBAABAA");
    }
}