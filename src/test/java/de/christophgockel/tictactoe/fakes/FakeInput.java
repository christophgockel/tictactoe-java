package de.christophgockel.tictactoe.fakes;

import de.christophgockel.tictactoe.Input;

public class FakeInput implements Input {
  public boolean getMoveHasBeenCalled;
  public int move;

  public FakeInput() {
    move = 0;
    getMoveHasBeenCalled = false;
  }

  @Override
  public int getMove() {
    getMoveHasBeenCalled = true;
    return move;
  }
}
