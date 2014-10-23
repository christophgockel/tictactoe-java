package de.christophgockel.tictactoe.cli;

import de.christophgockel.tictactoe.game.Board;
import de.christophgockel.tictactoe.game.Game;
import de.christophgockel.tictactoe.game.Player;
import de.christophgockel.tictactoe.game.PlayerPairsFactory;

import java.util.Map;

import static de.christophgockel.tictactoe.game.PlayerPairsFactory.Pair;

public class CommandLineRunner {
  private final CommandLineUI ui;

  public CommandLineRunner(CommandLineUI ui) {
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
