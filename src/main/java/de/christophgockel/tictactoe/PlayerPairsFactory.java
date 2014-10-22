package de.christophgockel.tictactoe;

import java.util.HashMap;
import java.util.Map;

import static de.christophgockel.tictactoe.PlayerPairsFactory.Pair.*;

public class PlayerPairsFactory {
  public enum Pair {
    HumanHuman,
    HumanComputer,
    ComputerHuman,
    ComputerComputer;
  }

  public static Player[] createPair(Pair requestedPair, Input input) {
    Player[] pair;

    if (requestedPair == HumanHuman) {
      pair = new Player[]{ new HumanPlayer(Mark.X, input),
                           new HumanPlayer(Mark.O, input)};
    } else if (requestedPair == HumanComputer) {
      pair = new Player[] { new HumanPlayer(Mark.X, input),
                            new ComputerPlayer(Mark.O)};
    } else if (requestedPair == ComputerHuman) {
      pair = new Player[] { new ComputerPlayer(Mark.X),
                            new HumanPlayer(Mark.O, input)};
    } else {
      pair = new Player[] { new ComputerPlayer(Mark.X),
                            new ComputerPlayer(Mark.O)};
    }

    return pair;
  }

  public static Map<Integer, Pair> getAvailablePairs() {
    Map<Integer, Pair> pairs = new HashMap<>();
    int index = 1;

    for(Pair pair : Pair.values()) {
      pairs.put(index++, pair);
    }

    return pairs;
  }
}
