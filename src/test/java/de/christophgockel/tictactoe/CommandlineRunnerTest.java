package de.christophgockel.tictactoe;

import de.christophgockel.tictactoe.fakes.FakeOutput;
import de.christophgockel.tictactoe.fakes.FakePlayer;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CommandlineRunnerTest {
  private Board board;
  private FakeOutput output;
  private CommandlineRunner runner;
  private FakePlayer playerOne;
  private FakePlayer playerTwo;

  @Before
  public void setup() {
    playerOne = new FakePlayer(Mark.X);
    playerTwo = new FakePlayer(Mark.O);
    board = new Board();
    output = new FakeOutput();
    Game game = new Game(playerOne, playerTwo, board, output);
    runner = new CommandlineRunner(game);
  }

  @Test
  public void doesNotPlayAnythingIfTheGameIsFinished() {
    prepareFinishedBoard();

    runner.play();

    assertEquals(null, output.announcedPlayer);
  }

  @Test
  public void playsUntilTheGameIsOver() {
    playerOne.setNextMovesToPlay(1, 2, 3);
    playerTwo.setNextMovesToPlay(4, 5);

    runner.play();

    assertEquals(playerOne.getMark(), output.announcedWinner);
  }

  private void prepareFinishedBoard() {
    board.setMove(1, Mark.X);
    board.setMove(2, Mark.X);
    board.setMove(3, Mark.X);
  }
}
