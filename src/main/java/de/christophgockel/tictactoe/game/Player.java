package de.christophgockel.tictactoe.game;

public interface Player {
  Board nextMove(Board board);
  Mark getMark();
}
