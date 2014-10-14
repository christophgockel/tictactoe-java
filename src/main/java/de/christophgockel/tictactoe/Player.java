package de.christophgockel.tictactoe;

public interface Player {
  Board nextMove(Board board);
  Mark getMark();
}
