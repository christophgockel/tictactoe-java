package de.christophgockel.tictactoe.cli;

import de.christophgockel.tictactoe.game.*;

import java.io.*;
import java.util.Map;
import java.util.Set;

public class CommandlineUI implements Output, Input {
  private final PrintStream output;
  private final BufferedReader reader;

  public CommandlineUI(InputStream input, PrintStream output) {
    this.output = output;

    this.reader = new BufferedReader(new InputStreamReader(input));
  }

  public int requestPlayerPair(Map<Integer, PlayerPairsFactory.Pair> pairs) {
    output.println("Game Mode:");

    for (Map.Entry<Integer, PlayerPairsFactory.Pair> entry : pairs.entrySet()) {
      output.println(String.format("%d. %s", entry.getKey(), getPairDescription(entry.getValue())));
    }

    return getValidInput(pairs.keySet());
  }

  public int requestBoardSize(Map<Integer, Board.Size> sizes) {
    output.println("Board Size:");

    for (Map.Entry<Integer, Board.Size> entry : sizes.entrySet()) {
      output.println(String.format("%d. %s", entry.getKey(), getSizeDescription(entry.getValue())));
    }

    return getValidInput(sizes.keySet());
  }

  private String getSizeDescription(Board.Size size) {
    String stringSize = size.toString();
    return stringSize.replace("Three", "3")
                     .replace("Four", "4")
                     .replace("By", "x");
  }

  private int getValidInput(Set<Integer> choices) {
    Integer input = null;
    while(input == null || isInvalidChoice(input, choices)){
      input = getInputChoice();
    }

    return input;
  }

  private boolean isInvalidChoice(Integer choice, Set<Integer> choices) {
    return !choices.contains(choice);
  }

  private String getPairDescription(PlayerPairsFactory.Pair pair) {
    String[] separatedWords = getCamelCaseSeparatedStrings(pair.toString());

    return separatedWords[0] + " vs. " + separatedWords[1];
  }

  private String[] getCamelCaseSeparatedStrings(String string) {
    return string.split("(?<!^)(?=[A-Z])");
  }

  public int getInputChoice() {
    try {
      return Integer.parseInt(reader.readLine());
    } catch (NumberFormatException | IOException e) {
      return 0;
    }
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
    int sideLength = board.getSideLength();
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
