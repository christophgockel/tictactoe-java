package de.christophgockel.tictactoe;

import org.junit.Before;
import org.junit.Test;

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
}
