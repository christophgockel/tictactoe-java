package de.christophgockel.tictactoe.doubles;

import de.christophgockel.tictactoe.game.Input;

public class StubInput implements Input {
  private int move;
  private boolean canProvideMove;

  public StubInput() {
    move = 0;
    canProvideMove = false;
  }

  @Override
  public boolean canProvideMove() {
    return canProvideMove;
  }

  @Override
  public int getMove() {
    return move;
  }

  public void setNextMove(int move) {
    this.move = move;
  }

  public void enableNextMove() {
    canProvideMove = true;
  }

  public void doNotProvideNextMove() {
    canProvideMove = false;
  }
}
