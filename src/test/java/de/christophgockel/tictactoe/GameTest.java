package de.christophgockel.tictactoe;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import de.christophgockel.tictactoe.fakes.FakeBoard;
import de.christophgockel.tictactoe.fakes.FakeOutput;
import de.christophgockel.tictactoe.fakes.FakePlayer;

public class GameTest {
  private FakePlayer playerOne;
  private FakePlayer playerTwo;
  private FakeBoard board;
  private FakeOutput output;
  private Game game;

  @Before
  public void setup() {
    playerOne = new FakePlayer(Mark.X);
    playerTwo = new FakePlayer(Mark.O);
    board     = new FakeBoard();
    output    = new FakeOutput();

    board.setHasWinnerReturnValues(false);

    game = new Game(playerOne, playerTwo, board, output);
  }

  @Test
  public void creatingNewGameOutputsBoard() {
    assertTrue(output.showBoardHasBeenCalled);
  }

  @Test
  public void asksTheBoardForCompletionOnNextRound() {
    game.nextRound();
    assertTrue(board.isPlayableHasBeenCalled);
  }

  @Test
  public void asksThePlayerForItsNextMoveWhenPlayable() {
    board.setIsPlayableValues(true);
    game.nextRound();

    assertTrue(playerOne.nextMoveHasBeenCalled);
  }

  @Test
  public void displaysTheBoardAfterRoundHasBeenPlayed() {
    output.showBoardHasBeenCalled = false;
    game.nextRound();

    assertTrue(output.showBoardHasBeenCalled);
  }

  @Test
  public void switchesPlayersAfterRounds() {
    board.setHasWinnerReturnValues(false, false);
    game.nextRound();
    game.nextRound();

    assertTrue(playerTwo.nextMoveHasBeenCalled);
  }

  @Test
  public void printsWinningMessageWhenThereIsAWinner() {
    board.setHasWinnerReturnValues(true);

    game.nextRound();

    assertTrue(output.showWinnerHasBeenCalled);
  }

  @Test
  public void printsWinningMessageWhenPlayerOneIsWinner() {
    board.setHasWinnerReturnValues(true);

    game.nextRound();

    assertEquals(playerOne.getMark(), output.announcedWinner);
  }

  @Test
  public void printsWinningMessageWhenPlayerTwoIsWinner() {
    board.setHasWinnerReturnValues(false, true);

    game.nextRound();
    game.nextRound();

    assertEquals(playerTwo.getMark(), output.announcedWinner);
  }

  @Test
  public void printsDrawMessageWhenThereIsNoWinner() {
    board.setHasWinnerReturnValues(false, false);
    board.setIsPlayableValues(true, false);

    game.nextRound();

    assertTrue(output.announcedDraw);
  }

  @Test (expected = Game.Over.class)
  public void throwsWhenTryingToPlayFinishedGame() {
    board.setIsPlayableValues(false);
    game.nextRound();
  }

  @Test
  public void asksTheBoardIfItIsPlayable() {
    game.isPlayable();
    assertTrue(board.isPlayableHasBeenCalled);
  }

  @Test
  public void announcesNextPlayerAsLongAsGameIsRunning() {
    game.nextRound();
    assertTrue(output.showNextPlayerHasBeenCalled);
  }
}
