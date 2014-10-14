package de.christophgockel.tictactoe;

public interface Output {
  void show(Board board);
  void showWinner(Mark mark);
  void showDraw();
}
