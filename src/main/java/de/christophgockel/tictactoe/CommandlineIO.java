package de.christophgockel.tictactoe;

import java.io.*;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

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
    Map<Integer, Mark> marks = board.getMarks();
    Integer sideLength = (int) Math.sqrt(marks.size());
    String template = "";

    for (Object o : marks.entrySet()) {
      Map.Entry entry = (Map.Entry) o;
      Integer position = (Integer) entry.getKey();
      Mark mark = (Mark) entry.getValue();

      Object valueToPrint;

      if (mark == null) {
        valueToPrint = position;
      } else {
        valueToPrint = mark;
      }

      template += String.format(cellFormat(board), valueToPrint);

      if (position % sideLength == 0) {
        template += "\n";
      } else {
        template += " | ";
      }
    }

    output.print(template);
  }

  private String cellFormat(Board board) {
    Integer boardLength = board.getMarks().size();
    int numberOfDigits = boardLength.toString().length();

    return "%" + numberOfDigits + "s";
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
