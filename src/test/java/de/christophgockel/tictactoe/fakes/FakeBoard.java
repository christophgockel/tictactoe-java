package de.christophgockel.tictactoe.fakes;

import de.christophgockel.tictactoe.Board;
import de.christophgockel.tictactoe.Mark;

import java.util.ArrayList;
import java.util.List;

public class FakeBoard extends Board {
  public boolean isPlayableHasBeenCalled;
  public boolean isPlayable;
  public List<Boolean> isWinnerValues;
  public boolean setMoveHasBeenCalled;
  public int lastMove;

  public FakeBoard() {
    isPlayableHasBeenCalled = false;
    isPlayable = true;
    isWinnerValues = new ArrayList<Boolean>();
    setMoveHasBeenCalled = false;
  }

  public void setIsWinnerReturnValues(boolean... values) {
    isWinnerValues.clear();

    for (boolean value : values) {
      isWinnerValues.add(value);
    }
  }

  @Override
  public boolean isPlayable() {
    isPlayableHasBeenCalled = true;
    return isPlayable;
  }

  @Override
  public boolean isWinner(Mark mark) {
    try {
      return isWinnerValues.remove(0);
    } catch (IndexOutOfBoundsException e) {
      return false;
    }
  }

  @Override
  public Board setMove(int move, Mark mark) {
    setMoveHasBeenCalled = true;
    lastMove = move;

    return this;
  }
}
