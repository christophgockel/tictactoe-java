package de.christophgockel.tictactoe.doubles;

import de.christophgockel.tictactoe.game.Board;
import de.christophgockel.tictactoe.game.Mark;
import de.christophgockel.tictactoe.game.Player;

import java.util.LinkedList;

import static java.util.Arrays.asList;

public class FakePlayer implements Player {
  private final Mark mark;
  private LinkedList<Integer> moves = new LinkedList<>();

  public FakePlayer(Mark mark) {
    this.mark = mark;
  }

  @Override
  public Board nextMove(Board board) {
    return board.setMove(moves.pop(), mark);
  }

  @Override
  public Mark getMark() {
    return mark;
  }

  public void setNextMovesToPlay(Integer... moves) {
    this.moves = new LinkedList<>(asList(moves));
  }
}
