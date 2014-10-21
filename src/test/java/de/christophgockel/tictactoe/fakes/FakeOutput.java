package de.christophgockel.tictactoe.fakes;

import de.christophgockel.tictactoe.Board;
import de.christophgockel.tictactoe.Mark;
import de.christophgockel.tictactoe.Output;

public class FakeOutput implements Output {
  public Mark announcedWinner;
  public Mark announcedPlayer;
  public boolean announcedDraw;
  public String invalidMoveMessage;
  public Board showedBoard;

  public FakeOutput() {
    announcedDraw = false;
    invalidMoveMessage = "";
  }

  @Override
  public void show(Board board) {
    showedBoard = board;
  }

  @Override
  public void showWinner(Mark mark) {
    announcedWinner = mark;
  }

  @Override
  public void showDraw() {
    announcedDraw = true;
  }

  @Override
  public void showNextPlayer(Mark mark) {
    announcedPlayer = mark;
  }

  @Override
  public void showInvalidMoveMessage() {
    invalidMoveMessage = "invalid move";
  }
}
