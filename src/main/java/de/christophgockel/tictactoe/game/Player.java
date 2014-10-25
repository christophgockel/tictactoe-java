package de.christophgockel.tictactoe.game;

public interface Player {
  boolean isReady();
  Board nextMove(Board board);
  Mark getMark();
}
