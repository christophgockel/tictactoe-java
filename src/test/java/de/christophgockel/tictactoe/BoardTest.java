package de.christophgockel.tictactoe;

import org.junit.Before;
import org.junit.Test;

import java.util.Map;

import static de.christophgockel.tictactoe.Board.Size;
import static org.hamcrest.core.StringContains.containsString;
import static org.junit.Assert.*;

public class BoardTest {
  private Board board;

  @Before
  public void setup() {
    board = new Board();
  }

  @Test
  public void newBoardIsEmpty() {
    for (Map.Entry entry : board.getMarks().entrySet()) {
      assertEquals(null, entry.getValue());
    }
  }

  @Test
  public void moveIsPlacedOnBoard() {
    board.setMove(1, Mark.X);

    assertEquals(Mark.X, board.getMarks().get(1));
  }

  @Test
  public void hasNineLocations() {
    Map<Integer, Mark> marks = board.getMarks();
    assertEquals(9, marks.size());
  }

  @Test
  public void newBoardsArePlayable() {
    assertTrue(board.isPlayable());
  }

  @Test
  public void fullBoardsAreNotPlayable() {
    prepareFullBoard();

    assertFalse(board.isPlayable());
  }

  @Test
  public void knowsWhenThereIsAWinner() {
    board.setMove(1, Mark.X);
    board.setMove(2, Mark.X);
    board.setMove(3, Mark.X);

    assertTrue(board.hasWinner());
  }

  @Test
  public void knowsWinningConditionForFirstRow() {
    board.setMove(1, Mark.X);
    board.setMove(2, Mark.X);
    board.setMove(3, Mark.X);

    assertEquals(Mark.X, board.getWinner());
  }

  @Test
  public void knowsWinningConditionForSecondRow() {
    board.setMove(4, Mark.X);
    board.setMove(5, Mark.X);
    board.setMove(6, Mark.X);

    assertEquals(Mark.X, board.getWinner());
  }

  @Test
  public void knowsWinningConditionForThirdRow() {
    board.setMove(7, Mark.X);
    board.setMove(8, Mark.X);
    board.setMove(9, Mark.X);

    assertEquals(Mark.X, board.getWinner());
  }

  @Test
  public void knowsWinningConditionForFirstColumn() {
    board.setMove(1, Mark.O);
    board.setMove(4, Mark.O);
    board.setMove(7, Mark.O);

    assertEquals(Mark.O, board.getWinner());
  }

  @Test
  public void knowsWinningConditionForSecondColumn() {
    board.setMove(2, Mark.O);
    board.setMove(5, Mark.O);
    board.setMove(8, Mark.O);

    assertEquals(Mark.O, board.getWinner());
  }

  @Test
  public void knowsWinningConditionForThirdColumn() {
    board.setMove(3, Mark.O);
    board.setMove(6, Mark.O);
    board.setMove(9, Mark.O);

    assertEquals(Mark.O, board.getWinner());
  }

  @Test
  public void knowsWinningConditionForFirstDiagonal() {
    board.setMove(1, Mark.X);
    board.setMove(5, Mark.X);
    board.setMove(9, Mark.X);

    assertEquals(Mark.X, board.getWinner());
  }

  @Test
  public void knowsWinningConditionForSecondDiagonal() {
    board.setMove(3, Mark.O);
    board.setMove(5, Mark.O);
    board.setMove(7, Mark.O);

    assertEquals(Mark.O, board.getWinner());
  }

  @Test (expected = Board.InvalidMove.class)
  public void throwsExceptionWhenPlacingInvalidMove_lowerBound() {
    board.setMove(0, Mark.O);
  }

  @Test
  public void exceptionThrownOnInvalidMoveContainsTheMove() {
    try {
      board.setMove(0, Mark.O);
      fail("expected exception was not thrown");
    } catch (Board.InvalidMove invalidMove) {
      assertThat(invalidMove.getMessage(), containsString("Invalid"));
      assertThat(invalidMove.getMessage(), containsString("0"));
    }
  }

  @Test (expected = Board.InvalidMove.class)
  public void throwsExceptionWhenPlacingInvalidMove_upperBound() {
    board.setMove(10, Mark.O);
  }

  @Test (expected = Board.InvalidMove.class)
  public void throwsExceptionWhenPlacingInvalidMove_alreadyOccupiedSpot() {
    board.setMove(2, Mark.O);
    board.setMove(2, Mark.X);
  }

  @Test
  public void isNotPlayableWhenWinnerIsAvailable() {
    board.setMove(1, Mark.O);
    board.setMove(4, Mark.O);
    board.setMove(7, Mark.O);

    assertFalse(board.isPlayable());
  }

  @Test
  public void canBeOfSize4x4() {
    Board board = new Board(Size.FourByFour);
    assertEquals(16, board.getMarks().size());
  }

  @Test (expected = Board.InvalidMove.class)
  public void size4x4_lowerBoundary() {
    Board board = new Board(Size.FourByFour);
    board.setMove(0, Mark.O);
  }

  @Test (expected = Board.InvalidMove.class)
  public void size4x4_upperBoundary() {
    Board board = new Board(Size.FourByFour);
    board.setMove(17, Mark.O);
  }

  @Test
  public void size4x4_needsForMarksInALineForAWinner() {
    Board board = new Board(Size.FourByFour);
    board.setMove(1, Mark.X);
    board.setMove(2, Mark.X);
    board.setMove(3, Mark.X);
    board.setMove(4, Mark.X);

    assertTrue(board.hasWinner());
    assertEquals(Mark.X, board.getWinner());
  }

  @Test
  public void size4x4_needsForMarksInALineForAWinner_column() {
    Board board = new Board(Size.FourByFour);
    board.setMove(1, Mark.O);
    board.setMove(5, Mark.O);
    board.setMove(9, Mark.O);
    board.setMove(13, Mark.O);

    assertTrue(board.hasWinner());
    assertEquals(Mark.O, board.getWinner());
  }

  @Test
  public void size4x4_needsForMarksInALineForAWinner_diagonal() {
    Board board = new Board(Size.FourByFour);
    board.setMove(1, Mark.O);
    board.setMove(6, Mark.O);
    board.setMove(11, Mark.O);
    board.setMove(16, Mark.O);

    assertTrue(board.hasWinner());
    assertEquals(Mark.O, board.getWinner());
  }

  @Test
  public void newBoardHasAllFreeLocations() {
    Board board = new Board(Size.ThreeByThree);

    assertEquals(9, board.getFreeLocations().size());
  }

  @Test
  public void freeLocationsContainIndices() {
    Board board=  new Board();
    board.setMove(1, Mark.O);

    assertEquals(2, (int) board.getFreeLocations().get(0));
  }

  @Test
  public void knowsFreeLocations() {
    Board board=  new Board();
    board.setMove(1, Mark.O);
    board.setMove(9, Mark.X);

    assertEquals(7, board.getFreeLocations().size());
  }

  private void prepareFullBoard() {
    for (int i = 1; i <= 9; i++) {
      board.setMove(i, Mark.X);
    }
  }
}
