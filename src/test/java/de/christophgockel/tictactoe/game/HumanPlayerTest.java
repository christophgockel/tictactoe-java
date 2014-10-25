package de.christophgockel.tictactoe.game;

import de.christophgockel.tictactoe.doubles.StubInput;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class HumanPlayerTest {
  private Player player;
  private Mark mark;
  private StubInput input;
  private Board board;

  @Before
  public void setup() {
    input = new StubInput();
    mark = Mark.X;
    player = new HumanPlayer(mark, input);
    board = new Board();
  }

  @Test
  public void hasAMark() {
    assertEquals(mark, player.getMark());
  }

  @Test
  public void placesItsMoveOnTheBoard() {
    input.setNextMove(2);

    Board newBoard = player.nextMove(board);

    assertEquals(player.getMark(), nthMarkOnBoard(2, newBoard));
  }

  @Test
  public void isReadyIfInputCanProvideAMove() {
    input.enableNextMove();
    assertTrue(player.isReady());
  }

  @Test
  public void isNotReadyWhenInputCannotProvideAMove() {
    input.doNotProvideNextMove();
    assertFalse(player.isReady());
  }

  private Mark nthMarkOnBoard(int location, Board board) {
    return board.getMarks().get(location);
  }
}
