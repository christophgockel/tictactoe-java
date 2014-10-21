package de.christophgockel.tictactoe.fakes;

import de.christophgockel.tictactoe.*;

import java.util.ArrayList;
import java.util.List;

public class FakeGame extends Game {
  private static Player playerOne;
  private static FakePlayer playerTwo;
  private static FakeBoard board;

  private List<Boolean> isPlayableValues;
  public boolean isPlayableHasBeenCalled;
  public boolean nextRoundHasBeenCalled;
  public int nextRoundCallTimes;

  {
    playerOne = new FakePlayer(Mark.X);
    playerTwo = new FakePlayer(Mark.O);
    board     = new FakeBoard();
  }

  public FakeGame(Player playerOne, Player playerTwo, Board board, Output output) {
    super(playerOne, playerTwo, board, output);
    isPlayableValues = new ArrayList<>();
    isPlayableHasBeenCalled = false;
    nextRoundHasBeenCalled = false;
    nextRoundCallTimes = 0;
  }

  public FakeGame(Output output) {
    this(playerOne, playerTwo, board, output);
  }

  @Override
  public boolean isPlayable() {
    try {
      isPlayableHasBeenCalled = true;
      return isPlayableValues.remove(0);
    } catch (IndexOutOfBoundsException e) {
      return false;
    }
  }

  @Override
  public void nextRound() {
    nextRoundHasBeenCalled = true;
    nextRoundCallTimes++;
  }

  public void setIsPlayableReturnValues(boolean... values) {
    isPlayableValues.clear();

    for (boolean value : values) {
      isPlayableValues.add(value);
    }
  }
}
