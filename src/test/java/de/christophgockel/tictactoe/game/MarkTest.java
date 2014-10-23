package de.christophgockel.tictactoe.game;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class MarkTest {
  @Test
  public void definesTwoMarks() {
    assertNotNull(Mark.X);
    assertNotNull(Mark.O);
  }

  @Test
  public void knowsTheOpponentMark() {
    assertEquals(Mark.O, Mark.X.getOpponent());
    assertEquals(Mark.X, Mark.O.getOpponent());
  }
}
