package de.christophgockel.tictactoe;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Before;
import org.junit.Test;

import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;

public class ComputerPlayerTest {
  private ComputerPlayer computer;
  private Mark mark;

  @Before
  public void setup() {
    mark = Mark.X;
    computer = new ComputerPlayer(mark);
  }

  @Test
  public void hasAMark() {
    assertEquals(mark, computer.getMark());
  }

  @Test
  public void blocksWinningMovesInRows() {
    assertThat(boardWithOpponentMovesAt(2, 3), receivesMove(1));
    assertThat(boardWithOpponentMovesAt(1, 3), receivesMove(2));
    assertThat(boardWithOpponentMovesAt(1, 2), receivesMove(3));

    assertThat(boardWithOpponentMovesAt(5, 6), receivesMove(4));
    assertThat(boardWithOpponentMovesAt(4, 6), receivesMove(5));
    assertThat(boardWithOpponentMovesAt(4, 5), receivesMove(6));

    assertThat(boardWithOpponentMovesAt(8, 9), receivesMove(7));
    assertThat(boardWithOpponentMovesAt(7, 9), receivesMove(8));
    assertThat(boardWithOpponentMovesAt(7, 8), receivesMove(9));
  }

  @Test
  public void blocksWinningMovesInColumns() {
    assertThat(boardWithOpponentMovesAt(4, 7), receivesMove(1));
    assertThat(boardWithOpponentMovesAt(1, 7), receivesMove(4));
    assertThat(boardWithOpponentMovesAt(1, 4), receivesMove(7));

    assertThat(boardWithOpponentMovesAt(5, 8), receivesMove(2));
    assertThat(boardWithOpponentMovesAt(2, 8), receivesMove(5));
    assertThat(boardWithOpponentMovesAt(2, 5), receivesMove(8));

    assertThat(boardWithOpponentMovesAt(6, 9), receivesMove(3));
    assertThat(boardWithOpponentMovesAt(3, 9), receivesMove(6));
    assertThat(boardWithOpponentMovesAt(3, 6), receivesMove(9));
  }

  @Test
  public void blocksWinningMovesInDiagonals() {
    assertThat(boardWithOpponentMovesAt(5, 9), receivesMove(1));
    assertThat(boardWithOpponentMovesAt(1, 9), receivesMove(5));
    assertThat(boardWithOpponentMovesAt(1, 5), receivesMove(9));

    assertThat(boardWithOpponentMovesAt(5, 7), receivesMove(3));
    assertThat(boardWithOpponentMovesAt(3, 7), receivesMove(5));
    assertThat(boardWithOpponentMovesAt(3, 5), receivesMove(7));
  }

  @Test
  public void finishesWinningRows() {
    assertThat(boardWithComputerMovesAt(2, 3), receivesMove(1));
    assertThat(boardWithComputerMovesAt(1, 3), receivesMove(2));
    assertThat(boardWithComputerMovesAt(1, 2), receivesMove(3));

    assertThat(boardWithComputerMovesAt(5, 6), receivesMove(4));
    assertThat(boardWithComputerMovesAt(4, 6), receivesMove(5));
    assertThat(boardWithComputerMovesAt(4, 5), receivesMove(6));

    assertThat(boardWithComputerMovesAt(8, 9), receivesMove(7));
    assertThat(boardWithComputerMovesAt(7, 9), receivesMove(8));
    assertThat(boardWithComputerMovesAt(7, 8), receivesMove(9));
  }

  @Test
  public void finishesWinningColumns() {
    assertThat(boardWithComputerMovesAt(4, 7), receivesMove(1));
    assertThat(boardWithComputerMovesAt(1, 7), receivesMove(4));
    assertThat(boardWithComputerMovesAt(1, 4), receivesMove(7));

    assertThat(boardWithComputerMovesAt(5, 8), receivesMove(2));
    assertThat(boardWithComputerMovesAt(2, 8), receivesMove(5));
    assertThat(boardWithComputerMovesAt(2, 5), receivesMove(8));

    assertThat(boardWithComputerMovesAt(6, 9), receivesMove(3));
    assertThat(boardWithComputerMovesAt(3, 9), receivesMove(6));
    assertThat(boardWithComputerMovesAt(3, 6), receivesMove(9));
  }

  @Test
  public void finishesWinningDiagonals() {
    assertThat(boardWithComputerMovesAt(5, 9), receivesMove(1));
    assertThat(boardWithComputerMovesAt(1, 9), receivesMove(5));
    assertThat(boardWithComputerMovesAt(1, 5), receivesMove(9));

    assertThat(boardWithComputerMovesAt(5, 7), receivesMove(3));
    assertThat(boardWithComputerMovesAt(3, 7), receivesMove(5));
    assertThat(boardWithComputerMovesAt(3, 5), receivesMove(7));
  }

  private Board boardWithOpponentMovesAt(int... moves) {
    return boardWithMoves(mark.getOpponent(), moves);
  }

  private Board boardWithComputerMovesAt(int... moves) {
    return boardWithMoves(mark, moves);
  }

  private Board boardWithMoves(Mark mark, int[] moves) {
    Board board = new Board();

    for (int move : moves) {
      board = board.setMove(move, mark);
    }

    return computer.nextMove(board);
  }

  private Matcher<Board> receivesMove(final int move) {
    return new TypeSafeMatcher<Board>() {
      @Override
      protected boolean matchesSafely(Board board) {
        Map<Integer, Mark> marks = board.getMarks();

        return marks.get(move) == mark;
      }

      @Override
      public void describeTo(Description description) {
        description.appendText("Board did not get move " + move);
      }
    };
  }
}
