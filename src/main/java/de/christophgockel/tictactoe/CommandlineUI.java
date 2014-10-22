package de.christophgockel.tictactoe;

import java.io.*;
import java.util.Map;
import java.util.Set;

public class CommandlineUI {
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
}
