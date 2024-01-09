package com.b4bank.tennis.services;

import com.b4bank.tennis.models.Player;

import java.util.List;

public interface ScoreService {

    List<Player> updatePlayerScore(String game);
    String getWinner(List<Player> players);
}
