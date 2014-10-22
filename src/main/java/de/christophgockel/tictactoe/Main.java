package de.christophgockel.tictactoe;

public class Main {
  public static void main(String[] args) {
    CommandlineIO io = new CommandlineIO(System.in, System.out);
    CommandlineUI ui = new CommandlineUI(System.in, System.out);
    CommandlineRunner runner = new CommandlineRunner(ui);

    runner.play(runner.createGame(io));
  }
}
