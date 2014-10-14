package de.christophgockel.tictactoe.fakes;

import de.christophgockel.tictactoe.Board;
import de.christophgockel.tictactoe.Mark;
import de.christophgockel.tictactoe.Output;

public class FakeOutput implements Output {
  public boolean showBoardHasBeenCalled;
  public boolean showWinnerHasBeenCalled;
  public Mark announcedWinner;
  public boolean announcedDraw;

  public FakeOutput() {
    showBoardHasBeenCalled = false;
    showWinnerHasBeenCalled = false;
    announcedDraw = false;
  }

  @Override
  public void show(Board board) {
    showBoardHasBeenCalled = true;
  }

  @Override
  public void showWinner(Mark mark) {
    showWinnerHasBeenCalled = true;
    announcedWinner = mark;
  }

  @Override
  public void showDraw() {
    announcedDraw = true;
  }
}
