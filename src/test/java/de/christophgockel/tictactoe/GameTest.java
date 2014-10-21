package de.christophgockel.tictactoe;

import org.junit.Before;
import org.junit.Test;

import de.christophgockel.tictactoe.fakes.FakeOutput;
import de.christophgockel.tictactoe.fakes.FakePlayer;

import static de.christophgockel.tictactoe.Mark.*;
import static org.junit.Assert.*;

public class GameTest {
  private FakePlayer playerOne;
  private FakePlayer playerTwo;
  private Board board;
  private FakeOutput output;
  private Game game;

  @Before
  public void setup() {
    playerOne = new FakePlayer(X);
    playerTwo = new FakePlayer(O);
    board     = new Board();
    output    = new FakeOutput();

    game = new Game(playerOne, playerTwo, board, output);
  }

  @Test
  public void newGameIsPlayable() {
    Game game = new Game(playerOne, playerTwo, new Board(), output);
    assertTrue(game.isPlayable());
  }

  @Test
  public void finishedGameIsNotPlayable() {
    prepareFinishedBoard();
    assertFalse(game.isPlayable());
  }


  @Test
  public void boardChangesWhenPlayerMakesMove() {
    playerOne.setNextMovesToPlay(1);

    game.nextRound();

    assertNotEquals(board, game.getBoard());
  }

  @Test
  public void displaysTheBoardAfterRoundHasBeenPlayed() {
    output.showBoardHasBeenCalled = false;
    playerOne.setNextMovesToPlay(1);
    game.nextRound();

    assertTrue(output.showBoardHasBeenCalled);
  }

  @Test
  public void printsWinningMessageWhenThereIsWinner() {
    playerOne.setNextMovesToPlay(1, 2, 3);
    playerTwo.setNextMovesToPlay(4, 5);

    playRounds(5);

    assertEquals(playerOne.getMark(), output.announcedWinner);
  }

  @Test
  public void printsDrawMessageWhenThereIsNoWinner() {
    playerOne.setNextMovesToPlay(1, 2, 6, 7, 9);
    playerTwo.setNextMovesToPlay(3, 4, 5, 8);

    playRounds(9);

    assertTrue(output.announcedDraw);
  }

  @Test (expected = Game.Over.class)
  public void throwsWhenTryingToPlayFinishedGame() {
    prepareFinishedBoard();
    game.nextRound();
  }

  @Test
  public void announcesNextPlayerAsLongAsGameIsRunning() {
    playerOne.setNextMovesToPlay(1);
    game.nextRound();
    assertTrue(output.showNextPlayerHasBeenCalled);
  }

  @Test
  public void placingAnInvalidMoveShowErrorMessage() {
    playerOne.setNextMovesToPlay(-1);
    game.nextRound();
    assertTrue(output.showInvalidMoveMessageHasBeenCalled);
  }

  private void prepareFinishedBoard() {
    board.setMove(1, playerOne.getMark());
    board.setMove(2, playerOne.getMark());
    board.setMove(3, playerOne.getMark());
  }

  private void playRounds(int turns) {
    for (int i = 0; i < turns; i++) {
      game.nextRound();
    }
  }
}
