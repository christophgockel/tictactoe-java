package de.christophgockel.tictactoe;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

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

    board.setIsWinnerReturnValues(false);

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
    board.isPlayable = true;
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
    board.setIsWinnerReturnValues(false, false);
    game.nextRound();
    game.nextRound();

    assertTrue(playerTwo.nextMoveHasBeenCalled);
  }

  @Test
  public void printsWinningMessageWhenThereIsAWinner() {
    board.setIsWinnerReturnValues(true);

    game.nextRound();

    assertTrue(output.showWinnerHasBeenCalled);
  }

  @Test
  public void printsWinningMessageWhenPlayerOneIsWinner() {
    board.setIsWinnerReturnValues(true);

    game.nextRound();

    assertEquals(playerOne.getMark(), output.announcedWinner);
  }

  @Test
  public void printsWinningMessageWhenPlayerTwoIsWinner() {
    board.setIsWinnerReturnValues(false, true);

    game.nextRound();

    assertEquals(playerTwo.getMark(), output.announcedWinner);
  }

  @Test
  public void printsDrawMessageWhenThereIsNoWinner() {
    board.setIsWinnerReturnValues(false, false);

    game.nextRound();

    assertTrue(output.announcedDraw);
  }

  @Test (expected = Game.Over.class)
  public void throwsWhenTryingToPlayFinishedGame() {
    board.isPlayable = false;
    game.nextRound();
  }

  private class FakePlayer implements Player {
    public boolean nextMoveHasBeenCalled;
    private Mark mark;

    public FakePlayer(Mark mark) {
      this.mark = mark;
      nextMoveHasBeenCalled = false;
    }

    @Override
    public Board nextMove(Board board) {
      nextMoveHasBeenCalled = true;
      return board;
    }

    @Override
    public Mark getMark() {
      return mark;
    }
  }

  private class FakeOutput implements Output {
    public boolean showBoardHasBeenCalled;
    public boolean showWinnerHasBeenCalled;
    public Mark announcedWinner;
    public boolean announcedDraw;

    public FakeOutput() {
      showBoardHasBeenCalled = false;
      showWinnerHasBeenCalled = false;
      announcedDraw = false;
    }

    @Override
    public void show(Board board) {
      showBoardHasBeenCalled = true;
    }

    @Override
    public void showWinner(Mark mark) {
      showWinnerHasBeenCalled = true;
      announcedWinner = mark;
    }

    @Override
    public void showDraw() {
      announcedDraw = true;
    }
  }

  private class FakeBoard implements Board {
    public boolean isPlayableHasBeenCalled;
    public boolean isPlayable;
    public List<Boolean> isWinnerValues;

    public FakeBoard() {
      isPlayableHasBeenCalled = false;
      isPlayable = true;
      isWinnerValues = new ArrayList<Boolean>();
    }

    public void setIsWinnerReturnValues(boolean... values) {
      isWinnerValues.clear();

      for (boolean value : values) {
        isWinnerValues.add(value);
      }
    }

    @Override
    public boolean isPlayable() {
      isPlayableHasBeenCalled = true;
      return isPlayable;
    }

    @Override
    public boolean isWinner(Mark mark) {
      try {
        return isWinnerValues.remove(0);
      } catch (IndexOutOfBoundsException e) {
        return false;
      }
    }
  }
}
