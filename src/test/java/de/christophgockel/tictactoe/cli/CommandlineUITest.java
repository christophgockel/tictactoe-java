package de.christophgockel.tictactoe.cli;

import de.christophgockel.tictactoe.helpers.BoardHelper;
import de.christophgockel.tictactoe.game.Board;
import de.christophgockel.tictactoe.game.Mark;
import de.christophgockel.tictactoe.game.PlayerPairsFactory;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;

import static java.nio.charset.StandardCharsets.UTF_8;
import static org.hamcrest.core.StringContains.containsString;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class CommandlineUITest {
  private ByteArrayOutputStream output;
  private CommandlineUI ui;

  @Before
  public void setup() {
    ByteArrayInputStream input = new ByteArrayInputStream("".getBytes(UTF_8));
    output = new ByteArrayOutputStream();

    ui = new CommandlineUI(input, new PrintStream(output));
  }

  @Test
  public void printsGivenPlayerPairs() {
    prepareInput("1");

    Map<Integer, PlayerPairsFactory.Pair> examplePairs = new HashMap<>();
    examplePairs.put(1, PlayerPairsFactory.Pair.HumanHuman);
    examplePairs.put(2, PlayerPairsFactory.Pair.HumanComputer);

    ui.requestPlayerPair(examplePairs);

    assertThat(stdout(), containsString("1. Human vs. Human"));
    assertThat(stdout(), containsString("2. Human vs. Computer"));
  }

  private void prepareInput(String inputString) {
    ByteArrayInputStream input = new ByteArrayInputStream(inputString.getBytes(UTF_8));
    ui = new CommandlineUI(input, new PrintStream(output));
  }

  @Test
  public void keepsAskingForValidChoiceOfGameMode() {
    prepareInput("42\n0\n2");

    Map<Integer, PlayerPairsFactory.Pair> examplePairs = new HashMap<>();
    examplePairs.put(1, PlayerPairsFactory.Pair.HumanHuman);
    examplePairs.put(2, PlayerPairsFactory.Pair.HumanComputer);

    int choiceMade = ui.requestPlayerPair(examplePairs);

    assertEquals(2, choiceMade);
  }

  @Test
  public void printsGivenBoardSizes() {
    prepareInput("1");

    Map<Integer, Board.Size> exampleSizes = new HashMap<>();
    exampleSizes.put(1, Board.Size.ThreeByThree);
    exampleSizes.put(2, Board.Size.FourByFour);

    ui.requestBoardSize(exampleSizes);

    assertThat(stdout(), containsString("1. 3x3"));
    assertThat(stdout(), containsString("2. 4x4"));
  }

  @Test
  public void keepsAskingForValidChoiceOfBoardSize() {
    prepareInput("test\n0\n1");

    Map<Integer, Board.Size> exampleSizes = new HashMap<>();
    exampleSizes.put(1, Board.Size.ThreeByThree);
    exampleSizes.put(2, Board.Size.FourByFour);

    int choiceMade = ui.requestBoardSize(exampleSizes);

    assertEquals(1, choiceMade);
  }

  @Test
  public void returnsInputChoiceFromInput() {
    InputStream input = new ByteArrayInputStream("12".getBytes(UTF_8));
    CommandlineUI ui = new CommandlineUI(input, new PrintStream(output));

    assertEquals(12, ui.getInputChoice());
  }

  private String stdout() {
    return output.toString();
  }

  @Test
  public void printsDrawMessage() {
    ui.showDraw();

    assertThat(stdout(), containsString("Game ended in a draw."));
  }

  @Test
  public void printsWinnerX() {
    ui.showWinner(Mark.X);

    assertThat(stdout(), containsString("Winner: X"));
  }

  @Test
  public void printsWinnerO() {
    ui.showWinner(Mark.O);

    assertThat(stdout(), containsString("Winner: O"));
  }

  @Test
  public void printsEmptyBoard() {
    Board board = new Board();

    ui.show(board);

    assertThat(stdout(), containsString("1 | 2 | 3"));
    assertThat(stdout(), containsString("4 | 5 | 6"));
    assertThat(stdout(), containsString("7 | 8 | 9"));
  }

  @Test
  public void printsEmpty4x4Board() {
    Board board = new Board(Board.Size.FourByFour);

    ui.show(board);

    assertThat(stdout(), containsString(" 1 |  2 |  3 |  4"));
    assertThat(stdout(), containsString(" 5 |  6 |  7 |  8"));
    assertThat(stdout(), containsString(" 9 | 10 | 11 | 12"));
    assertThat(stdout(), containsString("13 | 14 | 15 | 16"));
  }

  @Test
  public void printsBoardWithContent() {
    Board board = BoardHelper.createBoardWithMoves(Mark.X, 1, 5, 9);

    ui.show(board);

    assertThat(stdout(), containsString("X | 2 | 3"));
    assertThat(stdout(), containsString("4 | X | 6"));
    assertThat(stdout(), containsString("7 | 8 | X"));
  }

  @Test
  public void asksForNextMove() {
    ui.getMove();

    assertThat(stdout(), containsString("Next move:"));
  }

  @Test
  public void getsNextMoveFromItsStandardInput() {
    InputStream input = new ByteArrayInputStream("42".getBytes(UTF_8));
    CommandlineUI ui = new CommandlineUI(input, new PrintStream(output));

    assertEquals(42, ui.getMove());
  }

  @Test
  public void announcesNextPlayerX() {
    ui.showNextPlayer(Mark.X);
    assertThat(stdout(), containsString("Next Player: X"));
  }

  @Test
  public void announcesNextPlayerO() {
    ui.showNextPlayer(Mark.O);
    assertThat(stdout(), containsString("Next Player: O"));
  }

  @Test
  public void showsAnInvalidMoveMessage() {
    ui.showInvalidMoveMessage();
    assertThat(stdout(), containsString("Invalid move."));
  }
}
