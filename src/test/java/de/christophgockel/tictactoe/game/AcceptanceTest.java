package de.christophgockel.tictactoe.game;

import de.christophgockel.tictactoe.doubles.SpyOutput;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class AcceptanceTest {
  @Test
  public void playingComputerVsComputerDraws() {
    SpyOutput output = new SpyOutput();
    Player[] players = PlayerPairsFactory.createPair(PlayerPairsFactory.Pair.ComputerComputer, null);

    Game game = new Game(players[0], players[1], new Board(), output);

    for (int i = 0; i < 9; i++) {
      game.nextRound();
    }

    assertTrue(output.announcedDraw);
  }
}
