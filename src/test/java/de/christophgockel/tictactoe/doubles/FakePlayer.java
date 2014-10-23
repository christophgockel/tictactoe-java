package de.christophgockel.tictactoe.doubles;

import de.christophgockel.tictactoe.game.Board;
import de.christophgockel.tictactoe.game.Mark;
import de.christophgockel.tictactoe.game.Player;

import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;

public class FakePlayer implements Player {
  private final Mark mark;
  private List<Integer> moves = new ArrayList<>();

  public FakePlayer(Mark mark) {
    this.mark = mark;
  }

  @Override
  public Board nextMove(Board board) {
    return board.setMove(moves.remove(0), mark);
  }

  @Override
  public Mark getMark() {
    return mark;
  }

  public void setNextMovesToPlay(Integer ... moves) {
    this.moves = new ArrayList<>(asList(moves));
  }
}
