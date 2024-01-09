package com.b4bank.tennis.services.impl;

import com.b4bank.tennis.models.Score;
import com.b4bank.tennis.services.ScoreService;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertThrows;

public class ScoreServiceImplTest extends TestCase {

    private ScoreService scoreService;

    @Before
    public void setUp() {
        scoreService = new ScoreServiceImpl();
    }

    @Test
    public void testPlayerOneWins() {
        var players = scoreService.updatePlayerScore("AAAA");
        assertEquals("Player One Wins", scoreService.getWinner(players));
    }

    @Test
    public void testPlayerTwoWins() {
        var players = scoreService.updatePlayerScore("BBBB");
        assertEquals("Player Two Wins", scoreService.getWinner(players));
    }

    @Test
    public void testPlayerOneAdvantage() {
        var players = scoreService.updatePlayerScore("BAABBAA");
        assertEquals("Player One Advantage", scoreService.getWinner(players));
    }

    @Test
    public void testPlayerTwoAdvantage() {
        var players = scoreService.updatePlayerScore("ABABABB");
        assertEquals("Player Two Advantage", scoreService.getWinner(players));
    }

    @Test
    public void testNoWinnerYet() {
        var players = scoreService.updatePlayerScore("ABABAB");
        assertEquals("No winner yet", scoreService.getWinner(players));
    }

    @Test
    public void testInvalidCharacterException() {
        assertThrows(IllegalArgumentException.class, () -> {
            scoreService.updatePlayerScore("ACAB");
        });
    }

    @Test
    public void testDeuceToAdvantage() {
        assertEquals(Score.ADVANTAGE, scoreService.updatePlayerScore("ABABABABA").get(0).getScore());
    }

    @Test
    public void testAdvantageToGame() {
        assertEquals(Score.GAME, scoreService.updatePlayerScore("ABABABBB").get(1).getScore());
    }

    @Test
    public void testAdvantageToDeuce() {
        assertEquals(Score.DEUCE, scoreService.updatePlayerScore("ABABABBA").get(0).getScore());
    }

}

