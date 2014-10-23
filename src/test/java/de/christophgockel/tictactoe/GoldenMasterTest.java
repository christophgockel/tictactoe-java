package de.christophgockel.tictactoe;

import de.christophgockel.tictactoe.doubles.FakePlayer;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static de.christophgockel.tictactoe.Mark.O;
import static de.christophgockel.tictactoe.Mark.X;
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
}
