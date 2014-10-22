package de.christophgockel.tictactoe;

import java.util.Map;

import static de.christophgockel.tictactoe.PlayerPairsFactory.Pair;

public class CommandlineRunner {
  private final CommandlineUI ui;

  public CommandlineRunner(CommandlineUI ui) {
    this.ui = ui;
  }

  public Game createGame(CommandlineIO io) {
    Map<Integer, Pair> playerPairs = PlayerPairsFactory.getAvailablePairs();
    int choice = ui.requestPlayerPair(playerPairs);

    Player[] players = PlayerPairsFactory.createPair(playerPairs.get(choice), io);

    return new Game(players[0], players[1], new Board(), io);
  }

  public void play(Game game) {
    while (game.isPlayable()) {
      game.nextRound();
    }
  }
}
