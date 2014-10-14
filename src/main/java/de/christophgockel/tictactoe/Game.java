package de.christophgockel.tictactoe;

public class Game {
  private Player playerOne;
  private Player playerTwo;
  private Board board;
  private Output output;

  public Game(Player playerOne, Player playerTwo, Board board, Output output) {
    this.playerOne = playerOne;
    this.playerTwo = playerTwo;
    this.board = board;
    this.output = output;

    this.output.show(board);
  }

  public void nextRound() {
    if (board.isPlayable()) {
      board = playerOne.nextMove(board);

      updateOutput();

      switchPlayers();
    } else {
      throw new Over();
    }
  }

  private void updateOutput() {
    output.show(board);

    if (board.isWinner(playerOne.getMark())) {
      output.showWinner(playerOne.getMark());
    } else if (board.isWinner(playerTwo.getMark())){
      output.showWinner(playerTwo.getMark());
    } else {
      output.showDraw();
    }
  }

  private void switchPlayers() {
    Player temp = playerOne;
    playerOne = playerTwo;
    playerTwo = temp;
  }

  public class Over extends RuntimeException {
  }
}
