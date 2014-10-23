package de.christophgockel.tictactoe.game;

import de.christophgockel.tictactoe.helpers.BoardHelper;
import org.junit.Before;
import org.junit.Test;

import java.util.Map;

import static de.christophgockel.tictactoe.game.Board.Size;
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
    board = BoardHelper.createBoardWithMoves(Mark.X, 1);
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
    board = BoardHelper.createBoardWithMoves(Mark.X, 1, 2, 3);
    assertTrue(board.hasWinner());
  }

  @Test
  public void knowsWinningConditionForFirstRow() {
    board = BoardHelper.createBoardWithMoves(Mark.X, 1, 2, 3);
    assertEquals(Mark.X, board.getWinner());
  }

  @Test
  public void knowsWinningConditionForSecondRow() {
    board = BoardHelper.createBoardWithMoves(Mark.X, 4, 5, 6);
    assertEquals(Mark.X, board.getWinner());
  }

  @Test
  public void knowsWinningConditionForThirdRow() {
    board = BoardHelper.createBoardWithMoves(Mark.X, 7, 8, 9);
    assertEquals(Mark.X, board.getWinner());
  }

  @Test
  public void knowsWinningConditionForFirstColumn() {
    board = BoardHelper.createBoardWithMoves(Mark.O, 1, 4, 7);
    assertEquals(Mark.O, board.getWinner());
  }

  @Test
  public void knowsWinningConditionForSecondColumn() {
    board = BoardHelper.createBoardWithMoves(Mark.O, 2, 5, 8);
    assertEquals(Mark.O, board.getWinner());
  }

  @Test
  public void knowsWinningConditionForThirdColumn() {
    board = BoardHelper.createBoardWithMoves(Mark.O, 3, 6, 9);
    assertEquals(Mark.O, board.getWinner());
  }

  @Test
  public void knowsWinningConditionForFirstDiagonal() {
    board = BoardHelper.createBoardWithMoves(Mark.X, 1, 5, 9);
    assertEquals(Mark.X, board.getWinner());
  }

  @Test
  public void knowsWinningConditionForSecondDiagonal() {
    board = BoardHelper.createBoardWithMoves(Mark.O, 3, 5, 7);

    assertEquals(Mark.O, board.getWinner());
  }

  @Test(expected = Board.InvalidMove.class)
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

  @Test(expected = Board.InvalidMove.class)
  public void throwsExceptionWhenPlacingInvalidMove_upperBound() {
    board.setMove(10, Mark.O);
  }

  @Test(expected = Board.InvalidMove.class)
  public void throwsExceptionWhenPlacingInvalidMove_alreadyOccupiedSpot() {
    board.setMove(2, Mark.O).setMove(2, Mark.X);
  }

  @Test
  public void isNotPlayableWhenWinnerIsAvailable() {
    board = BoardHelper.createBoardWithMoves(Mark.O, 1, 4, 7);
    assertFalse(board.isPlayable());
  }

  @Test
  public void canBeOfSize4x4() {
    Board board = new Board(Size.FourByFour);
    assertEquals(16, board.getMarks().size());
  }

  @Test(expected = Board.InvalidMove.class)
  public void size4x4_lowerBoundary() {
    Board board = new Board(Size.FourByFour);
    board.setMove(0, Mark.O);
  }

  @Test(expected = Board.InvalidMove.class)
  public void size4x4_upperBoundary() {
    Board board = new Board(Size.FourByFour);
    board.setMove(17, Mark.O);
  }

  @Test
  public void size4x4_needsForMarksInALineForAWinner() {
    board = BoardHelper.createBoardWithMoves(Size.FourByFour, Mark.X, 1, 2, 3, 4);
    assertTrue(board.hasWinner());
    assertEquals(Mark.X, board.getWinner());
  }

  @Test
  public void size4x4_needsForMarksInALineForAWinner_column() {
    board = BoardHelper.createBoardWithMoves(Size.FourByFour, Mark.O, 1, 5, 9, 13);
    assertTrue(board.hasWinner());
    assertEquals(Mark.O, board.getWinner());
  }

  @Test
  public void size4x4_needsForMarksInALineForAWinner_diagonal() {
    board = BoardHelper.createBoardWithMoves(Size.FourByFour, Mark.O, 1, 6, 11, 16);
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
    board = BoardHelper.createBoardWithMoves(Mark.O, 1);

    assertEquals(2, (int) board.getFreeLocations().get(0));
  }

  @Test
  public void knowsFreeLocations() {
    board = BoardHelper.createBoardWithMoves(Mark.X, 1, 9);

    assertEquals(7, board.getFreeLocations().size());
  }

  @Test
  public void settingAMoveDoesNotKeepState() {
    Board board1 = new Board();
    Board board2 = board1.setMove(1, Mark.X);

    assertEquals(9, board1.getFreeLocations().size());
    assertEquals(8, board2.getFreeLocations().size());
  }

  @Test
  public void providesAvailableBoardSizes() {
    Map<Integer, Size> sizes = Board.getAvailableSizes();

    assertEquals(Size.values().length, sizes.size());
  }

  @Test
  public void knowsItsSideLength() {
    Board board = new Board(Size.FourByFour);

    assertEquals(Size.FourByFour.getSideLength(), board.getSideLength());
  }

  @Test
  public void knowsHowManyMovesWereMade() {
    Board board = new Board().setMove(1, Mark.X).setMove(2, Mark.O);

    assertEquals(2, board.getMoveCount());
  }

  private void prepareFullBoard() {
    board = BoardHelper.createBoardWithMoves(Mark.X, 1, 2, 3, 4, 5, 6, 7, 8, 9);
  }
}
