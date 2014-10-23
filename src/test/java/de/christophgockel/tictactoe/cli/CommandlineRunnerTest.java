package de.christophgockel.tictactoe.cli;

import de.christophgockel.tictactoe.helpers.BoardHelper;
import de.christophgockel.tictactoe.doubles.FakePlayer;
import de.christophgockel.tictactoe.doubles.SpyOutput;
import de.christophgockel.tictactoe.game.Board;
import de.christophgockel.tictactoe.game.Game;
import de.christophgockel.tictactoe.game.Mark;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.nio.charset.StandardCharsets.UTF_8;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class CommandlineRunnerTest {
  private FakePlayer playerOne;
  private FakePlayer playerTwo;
  private SpyOutput output;
  private CommandlineRunner runner;

  private StubUI ui;

  @Before
  public void setup() {
    ByteArrayInputStream inputStream = new ByteArrayInputStream("".getBytes(UTF_8));
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

    output = new SpyOutput();
    playerOne = new FakePlayer(Mark.X);
    playerTwo = new FakePlayer(Mark.O);

    ui = new StubUI(inputStream, new PrintStream(outputStream));
    runner = new CommandlineRunner(ui);
  }

  @Test
  public void setsUpANewGame() {
    ui.setChoicesToBeMade(1, 1);

    assertNotNull(runner.createGame());
  }

  @Test
  public void doesNotPlayAnythingIfTheGameIsFinished() {
    ui.setChoicesToBeMade(1, 1);

    Board board = BoardHelper.createBoardWithMoves(Mark.X, 1, 2, 3);
    runner.play(new Game(playerOne, playerTwo, board, output));

    assertEquals(null, output.announcedPlayer);
  }


  @Test
  public void playsUntilTheGameIsOver() {
    playerOne.setNextMovesToPlay(1, 2, 3);
    playerTwo.setNextMovesToPlay(4, 5);

    runner = new CommandlineRunner(null);
    runner.play(new Game(playerOne, playerTwo, new Board(), output));

    assertEquals(playerOne.getMark(), output.announcedWinner);
  }

  private class StubUI extends CommandlineUI {
    public List<Integer> inputChoices;

    public StubUI(InputStream input, PrintStream output) {
      super(input, output);
    }

    public void setChoicesToBeMade(Integer ... choices) {
      inputChoices = new ArrayList<>(Arrays.asList(choices));
    }

    @Override
    public int getInputChoice() {
      return inputChoices.remove(0);
    }
  }
}