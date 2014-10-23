package de.christophgockel.tictactoe.cli;

import de.christophgockel.tictactoe.doubles.FakePlayer;
import de.christophgockel.tictactoe.game.Board;
import de.christophgockel.tictactoe.game.Game;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static de.christophgockel.tictactoe.game.Mark.O;
import static de.christophgockel.tictactoe.game.Mark.X;
import static org.junit.Assert.assertEquals;

public class GoldenMasterTest{

  @Test
  public void test() {
    FakePlayer playerOne = new FakePlayer(X);
    playerOne.setNextMovesToPlay(1,2,3);

    FakePlayer playerTwo = new FakePlayer(O);
    playerTwo.setNextMovesToPlay(4,5);

    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    Game game = new Game(playerOne, playerTwo, new Board(), new CommandlineUI(System.in, new PrintStream(baos)));

    CommandlineRunner runner = new CommandlineRunner(null);
    runner.play(game);

    String expected = "1 | 2 | 3\n" +
      "4 | 5 | 6\n" +
      "7 | 8 | 9\n" +
      "Next Player: X\n" +
      "X | 2 | 3\n" +
      "4 | 5 | 6\n" +
      "7 | 8 | 9\n" +
      "Next Player: O\n" +
      "X | 2 | 3\n" +
      "O | 5 | 6\n" +
      "7 | 8 | 9\n" +
      "Next Player: X\n" +
      "X | X | 3\n" +
      "O | 5 | 6\n" +
      "7 | 8 | 9\n" +
      "Next Player: O\n" +
      "X | X | 3\n" +
      "O | O | 6\n" +
      "7 | 8 | 9\n" +
      "Next Player: X\n" +
      "X | X | X\n" +
      "O | O | 6\n" +
      "7 | 8 | 9\n" +
      "Winner: X\n";

   assertEquals(expected, baos.toString());
  }

  @Test
  public void printsBoardAgainAfterDraw() {
    FakePlayer playerOne = new FakePlayer(X);
    playerOne.setNextMovesToPlay(1, 2, 6, 7, 9);


    FakePlayer playerTwo = new FakePlayer(O);
    playerTwo.setNextMovesToPlay(3, 4, 5, 8);

    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    Game game = new Game(playerOne, playerTwo, new Board(), new CommandlineUI(System.in, new PrintStream(baos)));

    CommandlineRunner runner = new CommandlineRunner(null);
    runner.play(game);

    String expected = "1 | 2 | 3\n" +
      "4 | 5 | 6\n" +
      "7 | 8 | 9\n" +
      "Next Player: X\n" +
      "X | 2 | 3\n" +
      "4 | 5 | 6\n" +
      "7 | 8 | 9\n" +
      "Next Player: O\n" +
      "X | 2 | O\n" +
      "4 | 5 | 6\n" +
      "7 | 8 | 9\n" +
      "Next Player: X\n" +
      "X | X | O\n" +
      "4 | 5 | 6\n" +
      "7 | 8 | 9\n" +
      "Next Player: O\n" +
      "X | X | O\n" +
      "O | 5 | 6\n" +
      "7 | 8 | 9\n" +
      "Next Player: X\n" +
      "X | X | O\n" +
      "O | 5 | X\n" +
      "7 | 8 | 9\n" +
      "Next Player: O\n" +
      "X | X | O\n" +
      "O | O | X\n" +
      "7 | 8 | 9\n" +
      "Next Player: X\n" +
      "X | X | O\n" +
      "O | O | X\n" +
      "X | 8 | 9\n" +
      "Next Player: O\n" +
      "X | X | O\n" +
      "O | O | X\n" +
      "X | O | 9\n" +
      "Next Player: X\n" +
      "X | X | O\n" +
      "O | O | X\n" +
      "X | O | X\n" +
      "Game ended in a draw.\n";

    assertEquals(expected, baos.toString());
  }
}
