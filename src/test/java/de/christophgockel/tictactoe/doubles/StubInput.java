package de.christophgockel.tictactoe.doubles;

import de.christophgockel.tictactoe.Input;

public class StubInput implements Input {
  public int move;

  public StubInput() {
    move = 0;
  }

  @Override
  public int getMove() {
    return move;
  }
}
