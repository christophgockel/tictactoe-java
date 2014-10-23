package de.christophgockel.tictactoe.helpers;

import de.christophgockel.tictactoe.game.Board;
import de.christophgockel.tictactoe.game.Mark;

public class BoardHelper {
  public static Board createBoardWithMoves(Mark mark, int... moves) {
    return createBoardWithMoves(Board.Size.ThreeByThree, mark, moves);
  }

  public static Board createBoardWithMoves(Board.Size size, Mark mark, int... moves) {
    Board board = new Board(size);

    for (int move : moves) {
      board = board.setMove(move, mark);
    }

    return board;
  }
}
