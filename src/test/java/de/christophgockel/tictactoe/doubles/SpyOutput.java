package de.christophgockel.tictactoe.doubles;

import de.christophgockel.tictactoe.game.Board;
import de.christophgockel.tictactoe.game.Mark;
import de.christophgockel.tictactoe.game.Output;

public class SpyOutput implements Output {
  public Mark announcedWinner;
  public Mark announcedPlayer;
  public boolean announcedDraw;
  public String invalidMoveMessage;
  public Board showedBoard;

  public SpyOutput() {
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
