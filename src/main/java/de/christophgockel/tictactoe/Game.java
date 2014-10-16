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

    this.output.show(board);
  }

  public void nextRound() {
    if (board.isPlayable()) {
      board = currentPlayer.nextMove(board);

      updateOutput();

      switchPlayers();
    } else {
      throw new Over();
    }
  }

  public boolean isPlayable() {
    return board.isPlayable();
  }

  private void updateOutput() {
    output.show(board);

    if (board.hasWinner()) {
      output.showWinner(board.getWinner());
    } else {
      if (board.isPlayable()) {
        output.showNextPlayer(otherPlayer.getMark());
      } else {
        output.showDraw();
      }
    }
  }

  private void switchPlayers() {
    Player previousPlayer = currentPlayer;
    currentPlayer = otherPlayer;
    otherPlayer = previousPlayer;
  }

  public class Over extends RuntimeException {
  }
}
