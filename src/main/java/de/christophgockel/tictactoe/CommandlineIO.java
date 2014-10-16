package de.christophgockel.tictactoe;

import java.io.*;
import java.util.List;

public class CommandlineIO implements Output, Input {
  private InputStream input;
  private PrintStream output;

  private BufferedReader reader;

  public CommandlineIO(InputStream input, PrintStream output) {
    this.input = input;
    this.output = output;

    this.reader = new BufferedReader(new InputStreamReader(input));
  }

  @Override
  public int getMove() {
    try {
      output.print("Next move: ");
      return Integer.parseInt(reader.readLine());
    } catch (NumberFormatException | IOException e) {
      return 0;
    }
  }

  @Override
  public void show(Board board) {
    List<Mark> cells = board.getCells();
    String template = " 1 | 2 | 3 " + "\n" +
                      " 4 | 5 | 6 " + "\n" +
                      " 7 | 8 | 9 " + "\n";

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

  @Override
  public void showNextPlayer(Mark mark) {
    output.println("Next Player: " + mark);
  }

  @Override
  public void showInvalidMoveMessage() {
    output.println("Invalid move.");
  }
}
