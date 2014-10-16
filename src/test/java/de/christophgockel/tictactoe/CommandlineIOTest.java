package de.christophgockel.tictactoe;

import org.junit.Before;
import org.junit.Test;

import java.io.*;

import static org.hamcrest.core.StringContains.containsString;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class CommandlineIOTest {
  private CommandlineIO io;
  private ByteArrayOutputStream output;
  private DummyInputStream input;

  @Before
  public void setup() throws IOException {
    input = new DummyInputStream();
    output = new ByteArrayOutputStream();

    io = new CommandlineIO(input, new PrintStream(output));
  }

  @Test
  public void printsDrawMessage() {
    io.showDraw();

    assertThat(stdout(), containsString("Game ended in a draw."));
  }

  @Test
  public void printsWinnerX() {
    io.showWinner(Mark.X);

    assertThat(stdout(), containsString("Winner: X"));
  }

  @Test
  public void printsWinnerO() {
    io.showWinner(Mark.O);

    assertThat(stdout(), containsString("Winner: O"));
  }

  @Test
  public void printsEmptyBoard() {
    Board board = new Board();

    io.show(board);

    assertThat(stdout(), containsString("1 | 2 | 3"));
    assertThat(stdout(), containsString("4 | 5 | 6"));
    assertThat(stdout(), containsString("7 | 8 | 9"));
  }

  @Test
  public void printsBoardWithContent() {
    Board board = new Board();
    board.setMove(1, Mark.X);
    board.setMove(5, Mark.O);
    board.setMove(9, Mark.X);

    io.show(board);

    assertThat(stdout(), containsString("X | 2 | 3"));
    assertThat(stdout(), containsString("4 | O | 6"));
    assertThat(stdout(), containsString("7 | 8 | X"));
  }

  @Test
  public void asksForNextMove() {
    io.getMove();

    assertThat(stdout(), containsString("Next move:"));
  }

  @Test
  public void getsNextMoveFromStdin() throws IOException {
    input.valueToBeRead = 42;
    assertEquals(42, io.getMove());
  }

  private String stdout() {
    return output.toString();
  }

  private class DummyInputStream extends InputStream {
    public int valueToBeRead;

    @Override
    public int read() throws IOException {
      return valueToBeRead;
    }
  }
}
