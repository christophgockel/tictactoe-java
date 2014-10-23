package de.christophgockel.tictactoe.game;

import java.util.HashMap;
import java.util.Map;

import static de.christophgockel.tictactoe.game.PlayerPairsFactory.Pair.*;

public class PlayerPairsFactory {
  public static Player[] createPair(Pair requestedPair, Input input) {
    Player[] pair;

    if (requestedPair == HumanHuman) {
      pair = new Player[] { new HumanPlayer(Mark.X, input),
                            new HumanPlayer(Mark.O, input) };
    } else if (requestedPair == HumanComputer) {
      pair = new Player[] { new HumanPlayer(Mark.X, input),
                            new ComputerPlayer(Mark.O) };
    } else if (requestedPair == ComputerHuman) {
      pair = new Player[]{ new ComputerPlayer(Mark.X),
                           new HumanPlayer(Mark.O, input) };
    } else {
      pair = new Player[] { new ComputerPlayer(Mark.X),
                            new ComputerPlayer(Mark.O) };
    }

    return pair;
  }

  public static Map<Integer, Pair> getAvailablePairs() {
    Map<Integer, Pair> pairs = new HashMap<>();
    int index = 1;

    for (Pair pair : Pair.values()) {
      pairs.put(index++, pair);
    }

    return pairs;
  }

  public enum Pair {
    HumanHuman("Human vs. Human"),
    HumanComputer("Human vs. Computer"),
    ComputerHuman("Computer vs. Human"),
    ComputerComputer("Computer vs. Computer");

    private String description;

    private Pair(String description) {
      this.description = description;
    }

    public String getDescription() {
      return description;
    }
  }
}
