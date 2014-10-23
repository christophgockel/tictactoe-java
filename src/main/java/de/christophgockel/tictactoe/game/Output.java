package de.christophgockel.tictactoe.game;

public interface Output {
  void show(Board board);
  void showWinner(Mark mark);
  void showDraw();
  void showNextPlayer(Mark mark);
  void showInvalidMoveMessage();
}
