package de.christophgockel.tictactoe;

import java.io.*;
import java.util.Map;

public class CommandlineIO implements Output, Input {
  private final PrintStream output;
  private final BufferedReader reader;

  public CommandlineIO(InputStream input, PrintStream output) {
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
    int sideLength = (int) Math.sqrt(marks.size());
    String content = "";
    String format = cellFormat(board);

    for (Map.Entry<Integer, Mark> entry : marks.entrySet()) {
      int position = entry.getKey();
      Mark mark = entry.getValue();

      content += addMarker(format, position, mark);
      content += addSeparator(sideLength, position);
    }

    output.print(content);
  }

  private String addSeparator(int sideLength, int position) {
    if (isEndOfRow(sideLength, position)) {
      return "\n";
    } else {
      return " | ";
    }
  }

  private String addMarker(String format, int position, Mark mark) {
    if (mark == null) {
      return String.format(format, position);
    } else {
      return String.format(format, mark);
    }
  }

  private boolean isEndOfRow(int sideLength, int position) {
    return position % sideLength == 0;
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
