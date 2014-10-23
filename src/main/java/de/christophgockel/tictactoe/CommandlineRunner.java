package de.christophgockel.tictactoe;

import java.util.Map;

import static de.christophgockel.tictactoe.PlayerPairsFactory.Pair;

public class CommandlineRunner {
  private final CommandlineUI ui;

  public CommandlineRunner(CommandlineUI ui) {
    this.ui = ui;
  }

  public Game createGame() {
    Map<Integer, Pair> playerPairs = PlayerPairsFactory.getAvailablePairs();
    int choice = ui.requestPlayerPair(playerPairs);

    Player[] players = PlayerPairsFactory.createPair(playerPairs.get(choice), ui);

    Map<Integer, Board.Size> boardSizes = Board.getAvailableSizes();
    choice = ui.requestBoardSize(boardSizes);

    Board board = new Board(boardSizes.get(choice));

    return new Game(players[0], players[1], board, ui);
  }

  public void play(Game game) {
    while (game.isPlayable()) {
      game.nextRound();
    }
  }
}
