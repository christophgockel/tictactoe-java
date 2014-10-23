package de.christophgockel.tictactoe.cli;

public class Main {
  public static void main(String[] args) {
    CommandlineUI ui = new CommandlineUI(System.in, System.out);
    CommandlineRunner runner = new CommandlineRunner(ui);

    runner.play(runner.createGame());
  }
}
