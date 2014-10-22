package de.christophgockel.tictactoe;

import java.io.*;
import java.util.Map;

public class CommandlineUI {
  private final PrintStream output;
  private final BufferedReader reader;

  public CommandlineUI(InputStream input, PrintStream output) {
    this.output = output;

    this.reader = new BufferedReader(new InputStreamReader(input));
  }

  public int requestPlayerPair(Map<Integer, PlayerPairsFactory.Pair> pairs) {
    for (Map.Entry<Integer, PlayerPairsFactory.Pair> entry : pairs.entrySet()) {
      output.println(String.format("%d. %s", entry.getKey(), getPairDescription(entry.getValue())));
    }

    Integer input = null;
    while(input == null || isInvalidChoice(input, pairs)){
      input = getInputChoice();
    }

    return input;
  }

  private boolean isInvalidChoice(Integer choice, Map<Integer, PlayerPairsFactory.Pair> pairs) {
    return !pairs.containsKey(choice);
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
