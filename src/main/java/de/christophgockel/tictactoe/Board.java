package de.christophgockel.tictactoe;

public interface Board {
  public boolean isPlayable();
  boolean isWinner(Mark mark);
}
