package de.christophgockel.tictactoe.game;

public class HumanPlayer implements Player {
  private final Mark mark;
  private final Input input;

  public HumanPlayer(Mark mark, Input input) {
    this.mark = mark;
    this.input = input;
  }

  @Override
  public boolean isReady() {
    return input.canProvideMove();
  }

  @Override
  public Board nextMove(Board board) {
    int move = input.getMove();
    return board.setMove(move, mark);
  }

  @Override
  public Mark getMark() {
    return mark;
  }
}
