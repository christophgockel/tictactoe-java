package de.christophgockel.tictactoe;

import de.christophgockel.tictactoe.fakes.FakeBoard;
import de.christophgockel.tictactoe.fakes.FakeInput;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class HumanPlayerTest {
  private Player player;
  private FakeInput input;
  private FakeBoard board;

  @Before
  public void setup() {
    input = new FakeInput();
    player = new HumanPlayer(Mark.X, input);
    board = new FakeBoard();
  }

  @Test
  public void hasAMark() {
    assertEquals(Mark.X, player.getMark());
  }

  @Test
  public void getsItsNextMoveFromItsInput() {
    player.nextMove(board);

    assertTrue(input.getMoveHasBeenCalled);
  }

  @Test
  public void placesItsMoveOnTheBoard() {
    input.move = 2;

    player.nextMove(board);

    assertTrue(board.setMoveHasBeenCalled);
    assertEquals(2, board.lastMove);
  }
}
