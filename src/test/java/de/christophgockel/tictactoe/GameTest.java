package de.christophgockel.tictactoe;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class GameTest {
  private FakePlayer playerOne;
  private FakePlayer playerTwo;
  private FakeBoard board;
  private Input input;
  private FakeOutput output;
  private Game game;

  @Before
  public void setup() {
    input     = new DummyInput();
    playerOne = new FakePlayer(Mark.X, input);
    playerTwo = new FakePlayer(Mark.O, input);
    board     = new FakeBoard();
    output    = new FakeOutput();

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
  public void doesNotAskThePlayerForItsNextMoveWhenNotPlayable() {
    board.isPlayable = false;
    game.nextRound();

    assertFalse(playerOne.nextMoveHasBeenCalled);
  }

  @Test
  public void displaysTheBoardAfterRoundHasBeenPlayed() {
    board.isPlayable = true;
    output.showBoardHasBeenCalled = false;
    game.nextRound();

    assertTrue(output.showBoardHasBeenCalled);
  }

  @Test
  public void switchesPlayersAfterRounds() {
    board.isPlayable = true;
    game.nextRound();
    game.nextRound();

    assertTrue(playerTwo.nextMoveHasBeenCalled);
  }

  private class DummyInput implements Input {
    @Override
    public int getMove() {
      return 0;
    }
  }

  private class FakePlayer implements Player {
    public boolean nextMoveHasBeenCalled;

    public FakePlayer(Mark mark, Input input) {
      nextMoveHasBeenCalled = false;
    }

    @Override
    public Board nextMove(Board board) {
      nextMoveHasBeenCalled = true;
      return board;
    }
  }

  private class FakeOutput implements Output {
    public boolean showBoardHasBeenCalled;

    public FakeOutput() {
      showBoardHasBeenCalled = false;
    }

    @Override
    public void show(Board board) {
      showBoardHasBeenCalled = true;
    }
  }

  private class FakeBoard implements Board {
    public boolean isPlayableHasBeenCalled;
    public boolean isPlayable;

    public FakeBoard() {
      isPlayableHasBeenCalled = false;
      isPlayable = false;
    }

    @Override
    public boolean isPlayable() {
      isPlayableHasBeenCalled = true;
      return isPlayable;
    }
  }
}
