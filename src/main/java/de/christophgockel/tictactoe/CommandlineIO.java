package de.christophgockel.tictactoe;

import java.io.PrintStream;

public class CommandlineIO implements Output, Input {
  private PrintStream output;

  public CommandlineIO(PrintStream output) {
    this.output = output;
  }

  @Override
  public int getMove() {
    return 0;
  }

  @Override
  public void show(Board board) {

  }

  @Override
  public void showWinner(Mark mark) {

  }

  @Override
  public void showDraw() {
    output.println("Game ended in a draw.");
  }
}
