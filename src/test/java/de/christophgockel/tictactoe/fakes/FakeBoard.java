package de.christophgockel.tictactoe.fakes;

import de.christophgockel.tictactoe.Board;
import de.christophgockel.tictactoe.Mark;

import java.util.ArrayList;
import java.util.List;

public class FakeBoard extends Board {
  public boolean isPlayableHasBeenCalled;
  public List<Boolean> isPlayableValues;
  public List<Boolean> hasWinnerValues;
  public boolean setMoveHasBeenCalled;
  public int lastMove;
  public Mark lastMarkPlayed;

  public FakeBoard() {
    isPlayableHasBeenCalled = false;
    isPlayableValues = new ArrayList<Boolean>();
    hasWinnerValues = new ArrayList<Boolean>();
    setMoveHasBeenCalled = false;
  }

  public void setHasWinnerReturnValues(boolean... values) {
    hasWinnerValues.clear();

    for (boolean value : values) {
      hasWinnerValues.add(value);
    }
  }

  public void setIsPlayableValues(boolean... values) {
    isPlayableValues.clear();

    for (boolean value : values) {
      isPlayableValues.add(value);
    }
  }

  @Override
  public boolean isPlayable() {
    try {
      isPlayableHasBeenCalled = true;
      return isPlayableValues.remove(0);
    } catch (IndexOutOfBoundsException e) {
      return true;
    }
  }

  @Override
  public boolean hasWinner() {
    try {
      return hasWinnerValues.remove(0);
    } catch (IndexOutOfBoundsException e) {
      return false;
    }
  }

  @Override
  public Mark getWinner() {
    return lastMarkPlayed;
  }

  @Override
  public Board setMove(int move, Mark mark) {
    setMoveHasBeenCalled = true;
    lastMove = move;
    lastMarkPlayed = mark;

    return this;
  }
}
