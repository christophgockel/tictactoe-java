package de.christophgockel.tictactoe;

public class Main {
  public static void main(String[] args) {
    CommandlineUI ui = new CommandlineUI(System.in, System.out);
    CommandlineRunner runner = new CommandlineRunner(ui);

    runner.play(runner.createGame());
  }
}
