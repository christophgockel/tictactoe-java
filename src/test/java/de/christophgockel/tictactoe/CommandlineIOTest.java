package de.christophgockel.tictactoe;

import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.hamcrest.core.StringContains.containsString;
import static org.junit.Assert.assertThat;

public class CommandlineIOTest {
  private CommandlineIO io;
  private ByteArrayOutputStream output;

  @Before
  public void setup() {
    output = new ByteArrayOutputStream();
    io = new CommandlineIO(new PrintStream(output));
  }

  @Test
  public void printsDrawMessage() {
    io.showDraw();

    assertThat(stdout(), containsString("Game ended in a draw."));
  }

  private String stdout() {
    return output.toString();
  }
}
