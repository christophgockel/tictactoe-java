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
  public void blocksWinningMoveInRow() {
    assertThat(boardWithOpponentMovesAt(2, 3), receivesMove(1));
  }

  private Board boardWithOpponentMovesAt(int... moves) {
    Board board = new Board();

    for (int move : moves) {
      board = board.setMove(move, mark.getOpponent());
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
