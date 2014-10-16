package de.christophgockel.tictactoe;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.List;

public class CommandlineIO implements Output, Input {
  private InputStream input;
  private PrintStream output;

  public CommandlineIO(InputStream input, PrintStream output) {
    this.input = input;
    this.output = output;
  }

  @Override
  public int getMove() {
    output.print("Next move: ");
    try {
      return input.read();
    } catch (IOException e) {
      return 0;
    }
  }

  @Override
  public void show(Board board) {
    List<Mark> cells = board.getCells();
    String template = " 1 | 2 | 3 " + "\n" +
                      " 4 | 5 | 6 " + "\n" +
                      " 7 | 8 | 9 ";

    for (int i = 0; i < cells.size(); i++) {
      Mark cell = cells.get(i);

      if (cell != null) {
        template = template.replace(Integer.toString(i + 1), cell.toString());
      }
    }

    output.print(template);
  }

  @Override
  public void showWinner(Mark mark) {
    output.println("Winner: " + mark.toString());
  }

  @Override
  public void showDraw() {
    output.println("Game ended in a draw.");
  }
}
