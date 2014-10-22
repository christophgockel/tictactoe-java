package de.christophgockel.tictactoe;

import de.christophgockel.tictactoe.fakes.FakeInput;
import org.junit.Test;

import static de.christophgockel.tictactoe.PlayerPairsFactory.Pair.*;
import static org.junit.Assert.assertEquals;

public class PlayerPairsFactoryTest {
  @Test
  public void providesListOfAvailablePairs() {
    assertEquals(4, PlayerPairsFactory.getAvailablePairs().size());
  }

  @Test
  public void canCreatePairWithHumanAndHuman() {
    Player[] players = PlayerPairsFactory.createPair(HumanHuman, new FakeInput());

    assertEquals(HumanPlayer.class, players[0].getClass());
    assertEquals(HumanPlayer.class, players[1].getClass());
  }

  @Test
  public void canCreatePairWithHumanAndComputer() {
    Player[] players = PlayerPairsFactory.createPair(HumanComputer, new FakeInput());

    assertEquals(HumanPlayer.class, players[0].getClass());
    assertEquals(ComputerPlayer.class, players[1].getClass());
  }

  @Test
  public void canCreatePairWithComputerAndHuman() {
    Player[] players = PlayerPairsFactory.createPair(ComputerHuman, new FakeInput());

    assertEquals(ComputerPlayer.class, players[0].getClass());
    assertEquals(HumanPlayer.class, players[1].getClass());
  }

  @Test
  public void canCreatePairWithComputerAndComputer() {
    Player[] players = PlayerPairsFactory.createPair(ComputerComputer, new FakeInput());

    assertEquals(ComputerPlayer.class, players[0].getClass());
    assertEquals(ComputerPlayer.class, players[1].getClass());
  }
}
