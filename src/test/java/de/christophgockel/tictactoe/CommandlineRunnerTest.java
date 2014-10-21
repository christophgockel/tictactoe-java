package de.christophgockel.tictactoe;

import de.christophgockel.tictactoe.fakes.FakeGame;
import de.christophgockel.tictactoe.fakes.FakeOutput;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CommandlineRunnerTest {
  private FakeGame game;
  private CommandlineRunner runner;

  @Before
  public void setup() {
    game = new FakeGame(new FakeOutput());
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
