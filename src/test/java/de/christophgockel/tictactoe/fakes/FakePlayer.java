package de.christophgockel.tictactoe.fakes;

import de.christophgockel.tictactoe.Board;
import de.christophgockel.tictactoe.Mark;
import de.christophgockel.tictactoe.Player;

public class FakePlayer implements Player {
  public boolean nextMoveHasBeenCalled;
  public int nextMoveToPlay;
  private Mark mark;

  public FakePlayer(Mark mark) {
    this.mark = mark;
    nextMoveHasBeenCalled = false;
    nextMoveToPlay = 0;
  }

  @Override
  public Board nextMove(Board board) {
    nextMoveHasBeenCalled = true;
    return board.setMove(nextMoveToPlay, mark);
  }

  @Override
  public Mark getMark() {
    return mark;
  }
}
