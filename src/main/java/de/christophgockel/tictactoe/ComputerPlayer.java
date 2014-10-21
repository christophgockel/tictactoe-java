package de.christophgockel.tictactoe;

public class ComputerPlayer implements Player {
  private final Mark mark;

  public ComputerPlayer(Mark mark) {
    this.mark = mark;
  }

  @Override
  public Board nextMove(Board board) {
    int move = bestMove(board);
    return board.setMove(move, mark);
  }

  private int bestMove(Board board) {
    RatedMove move = negamax(board, -1.0, 1.0, mark);
    return move.move;
  }

  @Override
  public Mark getMark() {
    return mark;
  }

  private RatedMove negamax(Board board, double alpha, double beta, Mark mark) {
    int bestMove = -1;
    double bestScore = -1;

    if (!board.isPlayable()) {
      return new RatedMove(score(board, mark), bestMove);
    }

    for (int move : board.getFreeLocations()) {
      double score = -negamax(board.setMove(move, mark), -beta, -alpha, mark.getOpponent()).score;

      if (score > bestScore) {
        bestScore = score;
        bestMove = move;
      }

      if (score > alpha) {
        alpha = score;
      }

      if (alpha >= beta) {
        break;
      }
    }

    return new RatedMove(bestScore, bestMove);
  }

  private double score(Board board, Mark mark) {
    int movesMade = 9 - board.getFreeLocations().size();

    if (board.hasWinner()) {
      if (board.getWinner() == mark) {
        return 1.0 / movesMade;
      } else {
        return -1.0 / movesMade;
      }
    }
    return 0.0;
  }

  private class RatedMove {
    public final double score;
    public final int move;

    public RatedMove(double score, int move) {
      this.score = score;
      this.move = move;
    }
  }
}
