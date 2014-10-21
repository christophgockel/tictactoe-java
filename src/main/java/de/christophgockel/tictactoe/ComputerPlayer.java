package de.christophgockel.tictactoe;

public class ComputerPlayer implements Player {
  private final Mark mark;

  public ComputerPlayer(Mark mark) {
    this.mark = mark;
  }

  @Override
  public Board nextMove(Board board) {
    return board.setMove(1, mark);
  }

  @Override
  public Mark getMark() {
    return mark;
  }
}
