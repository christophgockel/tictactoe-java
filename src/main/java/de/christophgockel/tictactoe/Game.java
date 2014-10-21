package de.christophgockel.tictactoe;

public class Game {
  private Player currentPlayer;
  private Player otherPlayer;
  private Board board;
  private Output output;

  public Game(Player playerOne, Player playerTwo, Board board, Output output) {
    this.currentPlayer = playerOne;
    this.otherPlayer = playerTwo;
    this.board = board;
    this.output = output;
  }

  public void nextRound() {
    try {
      showBoard();

      if (isPlayable()) {
        board = currentPlayer.nextMove(board);

        switchPlayers();

        if (isFinished()) {
          showEndResult();
        }
      } else {
        throw new Over();
      }
    } catch (Board.InvalidMove invalidMove) {
      output.showInvalidMoveMessage();
    }
  }

  private boolean isFinished() {
    return !board.isPlayable();
  }

  private void showBoard() {
    output.show(board);
    output.showNextPlayer(currentPlayer.getMark());
  }

  private void showEndResult() {
    if (board.hasWinner()) {
      output.show(board);
      output.showWinner(board.getWinner());
    } else {
      output.showDraw();
    }
  }

  public boolean isPlayable() {
    return board.isPlayable();
  }

  public Board getBoard() {
    return board;
  }

  private void switchPlayers() {
    Player previousPlayer = currentPlayer;
    currentPlayer = otherPlayer;
    otherPlayer = previousPlayer;
  }

  public class Over extends RuntimeException {
  }
}
