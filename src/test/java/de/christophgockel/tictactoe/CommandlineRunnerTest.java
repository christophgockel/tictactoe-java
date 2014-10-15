package de.christophgockel.tictactoe;

import de.christophgockel.tictactoe.fakes.FakeGame;
import de.christophgockel.tictactoe.fakes.FakeOutput;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class CommandlineRunnerTest {
  private FakeOutput output;
  private FakeGame game;
  private CommandlineRunner runner;

  @Before
  public void setup() {
    output = new FakeOutput();
    game = new FakeGame(output);
    runner = new CommandlineRunner(game);
  }

  @Test
  public void asksTheGameIfItIsReadyToBePlayed() {
    runner.play();

    assertTrue(game.isPlayableHasBeenCalled);
  }

  @Test
  public void playsTheNextRoundWhenGameIsReady() {
    game.setIsPlayableReturnValues(true);
    runner.play();

    assertTrue(game.nextRoundHasBeenCalled);
  }

  @Test
  public void doesNotPlayTheNextRoundWhenGameIsNotReady() {
    game.setIsPlayableReturnValues(false);
    runner.play();

    assertFalse(game.nextRoundHasBeenCalled);
  }

  @Test
  public void playsRoundsConsecutively() {
    game.setIsPlayableReturnValues(true, true, false);
    runner.play();

    assertEquals(2, game.nextRoundCallTimes);
  }
}
