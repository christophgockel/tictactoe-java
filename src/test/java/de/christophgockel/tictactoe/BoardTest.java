package de.christophgockel.tictactoe;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static de.christophgockel.tictactoe.Board.Size;
import static org.junit.Assert.*;

public class BoardTest {
  private Board board;

  @Before
  public void setup() {
    board = new Board();
  }

  @Test
  public void newBoardIsEmpty() {
    List<Mark> emptyCells = list(null, null, null, null, null, null, null, null, null);
    assertEquals(emptyCells, board.getCells());
  }

  @Test
  public void moveIsPlacedOnBoard() {
    List<Mark> cells = list(null, null, Mark.X, null, null, null, null, null, null);
    board.setMove(3, Mark.X);
    assertEquals(cells, board.getCells());
  }

  @Test
  public void movesAreIndexedStartingFromOne() {
    List<Mark> cells = list(Mark.O, null, null, null, null, null, null, null, null);
    board.setMove(1, Mark.O);
    assertEquals(cells, board.getCells());
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
    assertEquals(16, board.getCells().size());
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
    Board board=  new Board(Size.FourByFour);
    board.setMove(1, Mark.X);
    board.setMove(2, Mark.X);
    board.setMove(3, Mark.X);
    board.setMove(4, Mark.X);

    assertTrue(board.hasWinner());
    assertEquals(Mark.X, board.getWinner());
  }

  @Test
  public void size4x4_needsForMarksInALineForAWinner_column() {
    Board board=  new Board(Size.FourByFour);
    board.setMove(1, Mark.O);
    board.setMove(5, Mark.O);
    board.setMove(9, Mark.O);
    board.setMove(13, Mark.O);

    assertTrue(board.hasWinner());
    assertEquals(Mark.O, board.getWinner());
  }

  @Test
  public void size4x4_needsForMarksInALineForAWinner_diagonal() {
    Board board=  new Board(Size.FourByFour);
    board.setMove(1, Mark.O);
    board.setMove(6, Mark.O);
    board.setMove(11, Mark.O);
    board.setMove(16, Mark.O);

    assertTrue(board.hasWinner());
    assertEquals(Mark.O, board.getWinner());
  }

  private void prepareFullBoard() {
    for (int i = 1; i <= 9; i++) {
      board.setMove(i, Mark.X);
    }
  }

  private List<Mark> list(Mark...marks) {
    return Arrays.asList(marks);
  }
}
