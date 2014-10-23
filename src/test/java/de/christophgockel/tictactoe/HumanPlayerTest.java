package de.christophgockel.tictactoe;

import de.christophgockel.tictactoe.doubles.StubInput;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

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
    input.move = 2;

    Board newBoard = player.nextMove(board);

    assertEquals(player.getMark(), nthMarkOnBoard(2, newBoard));
  }

  private Mark nthMarkOnBoard(int location, Board board) {
    return board.getMarks().get(location);
  }
}
