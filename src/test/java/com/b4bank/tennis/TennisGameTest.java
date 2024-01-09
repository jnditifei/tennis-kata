package com.b4bank.tennis;

import com.b4bank.tennis.models.Player;
import com.b4bank.tennis.models.Score;
import com.b4bank.tennis.services.ScoreService;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;


public class TennisGameTest {
    private TennisGame game;

    @Before
    public void setUp() {
        game = new TennisGame();
    }

    @Test
    public void testSimulateGameWithValidInput() {
        String validGame = "ABABAA";
        List<Player> players = Arrays.asList(new Player(Score.GAME), new Player(Score.THIRTY));

        ScoreService scoreServiceMock = mock(ScoreService.class);
        TennisGame game = new TennisGame(scoreServiceMock);

        when(scoreServiceMock.updatePlayerScore(validGame)).thenReturn(players);
        when(scoreServiceMock.getWinner(players)).thenReturn("Player One Wins");

        game.simulateGame(validGame);

        verify(scoreServiceMock).updatePlayerScore(validGame);
        verify(scoreServiceMock).getWinner(players);
    }

    @Test
    public void testSimulateGameWithInvalidInput() {
        String invalidGame = "ABABXAB";

        ScoreService scoreServiceMock = mock(ScoreService.class);
        TennisGame game = new TennisGame(scoreServiceMock);

        doThrow(new IllegalArgumentException("Invalid character found: X")).when(scoreServiceMock).updatePlayerScore(invalidGame);

        game.simulateGame(invalidGame);

        verify(scoreServiceMock).updatePlayerScore(invalidGame);
        verify(scoreServiceMock, never()).getWinner(anyList());
    }
}

