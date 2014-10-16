package de.christophgockel.tictactoe.fakes;

import de.christophgockel.tictactoe.Board;
import de.christophgockel.tictactoe.Mark;
import de.christophgockel.tictactoe.Player;

public class FakePlayer implements Player {
  public boolean nextMoveHasBeenCalled;
  private Mark mark;

  public FakePlayer(Mark mark) {
    this.mark = mark;
    nextMoveHasBeenCalled = false;
  }

  @Override
  public Board nextMove(Board board) {
    nextMoveHasBeenCalled = true;
    return board.setMove(0, mark);
  }

  @Override
  public Mark getMark() {
    return mark;
  }
}
