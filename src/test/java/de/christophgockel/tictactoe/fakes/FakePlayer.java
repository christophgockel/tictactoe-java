package de.christophgockel.tictactoe.fakes;

import de.christophgockel.tictactoe.Board;
import de.christophgockel.tictactoe.Mark;
import de.christophgockel.tictactoe.Player;

import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;

public class FakePlayer implements Player {
  public boolean nextMoveHasBeenCalled;
  private Mark mark;
  private List<Integer> moves = new ArrayList<>();

  public FakePlayer(Mark mark) {
    this.mark = mark;
    nextMoveHasBeenCalled = false;
  }

  @Override
  public Board nextMove(Board board) {
    nextMoveHasBeenCalled = true;
    int move = moves.remove(0);
    return board.setMove(move, mark);
  }

  @Override
  public Mark getMark() {
    return mark;
  }

  public void setNextMovesToPlay(Integer ... moves) {
    this.moves = new ArrayList<>(asList(moves));
  }
}
