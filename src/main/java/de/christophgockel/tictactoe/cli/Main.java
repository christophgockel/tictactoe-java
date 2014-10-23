package de.christophgockel.tictactoe.cli;

public class Main {
  public static void main(String[] args) {
    CommandLineUI ui = new CommandLineUI(System.in, System.out);
    CommandLineRunner runner = new CommandLineRunner(ui);

    runner.play(runner.createGame());
  }
}
