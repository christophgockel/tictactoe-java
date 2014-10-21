package de.christophgockel.tictactoe;

public class CommandlineRunner {
  private final Game game;

  public CommandlineRunner(Game game) {
    this.game = game;
  }

  public void play() {
    while (game.isPlayable()) {
      game.nextRound();
    }
  }
}
