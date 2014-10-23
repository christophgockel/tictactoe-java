package de.christophgockel.tictactoe.doubles;

import de.christophgockel.tictactoe.game.Input;

public class StubInput implements Input {
  private int move;

  public StubInput() {
    move = 0;
  }

  @Override
  public int getMove() {
    return move;
  }

  public void setNextMove(int move) {
    this.move = move;
  }
}
