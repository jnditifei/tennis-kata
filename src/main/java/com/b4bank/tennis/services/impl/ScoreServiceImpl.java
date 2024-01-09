package com.b4bank.tennis.services.impl;

import com.b4bank.tennis.models.Player;
import com.b4bank.tennis.models.Score;
import com.b4bank.tennis.services.ScoreService;

import java.util.Arrays;
import java.util.List;

public class ScoreServiceImpl implements ScoreService {

    private Player playerOne;
    private Player playerTwo;
    private static final String PLAYER_ONE_WIN = "Player One Wins";
    private static final String PLAYER_TWO_WIN = "Player Two Wins";
    private static final String PLAYER_ONE_ADVANTAGE = "Player One Advantage";
    private static final String PLAYER_TWO_ADVANTAGE = "Player Two Advantage";
    private static final String NO_WINNER_YET = "No winner yet";

    public ScoreServiceImpl(){
        this.playerOne = new Player();
        this.playerTwo = new Player();
    }

    public List<Player> updatePlayerScore(String game) {
        for (char ch : game.toCharArray()) {
            if (ch == 'A' || ch == 'B') {
                Player scorer = (ch == 'A') ? playerOne : playerTwo;
                Player opponent = (ch == 'A') ? playerTwo : playerOne;
                calculateScore(scorer, opponent);
            } else {
                throw new IllegalArgumentException("Invalid character found: " + ch);
            }
            System.out.println("Player A: " + playerOne.getScore().getScoreValue() + " - Player B: " + playerTwo.getScore().getScoreValue());
        }
        List<Player> players = Arrays.asList(playerOne, playerTwo);
        return players;
    }

    public String getWinner(List<Player> players) {
        Score playerOneScore = players.get(0).getScore();
        Score playerTwoScore = players.get(1).getScore();

        if (playerOneScore == Score.GAME) {
            return PLAYER_ONE_WIN;
        } else if (playerTwoScore == Score.GAME) {
            return PLAYER_TWO_WIN;
        } else if (playerOneScore == Score.ADVANTAGE) {
            return PLAYER_ONE_ADVANTAGE;
        } else if (playerTwoScore == Score.ADVANTAGE) {
            return PLAYER_TWO_ADVANTAGE;
        } else {
            return NO_WINNER_YET;
        }
    }

    private void calculateScore(Player scorer, Player opponent) {
        Score scorerScore = scorer.getScore();
        Score opponentScore = opponent.getScore();

        if (scorerScore == Score.ADVANTAGE) {
            scorer.setScore(Score.GAME);
        } else if (opponentScore == Score.ADVANTAGE) {
            setScores(scorer, Score.DEUCE, opponent, Score.DEUCE);
        } else if (scorerScore == Score.DEUCE && opponentScore == Score.DEUCE) {
            setScores(scorer, Score.ADVANTAGE, opponent, Score.FORTY);
        } else if (scorerScore == Score.FORTY && opponentScore == Score.FORTY) {
            setScores(scorer, Score.ADVANTAGE, opponent, Score.FORTY);
        } else if (scorerScore == Score.FORTY && opponentScore != Score.DEUCE) {
            scorer.setScore(Score.GAME);
        } else if (scorerScore.ordinal() < Score.GAME.ordinal()) {
            scorer.setScore(Score.values()[scorerScore.ordinal() + 1]);
        }
    }

    private void setScores(Player scorer, Score scorerScore, Player opponent, Score opponentScore) {
        scorer.setScore(scorerScore);
        opponent.setScore(opponentScore);
    }

}
