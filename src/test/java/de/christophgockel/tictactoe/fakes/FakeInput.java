package de.christophgockel.tictactoe.fakes;

import de.christophgockel.tictactoe.Input;

public class FakeInput implements Input {
  public int move;

  public FakeInput() {
    move = 0;
  }

  @Override
  public int getMove() {
    return move;
  }
}
