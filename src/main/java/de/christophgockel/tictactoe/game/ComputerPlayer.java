package de.christophgockel.tictactoe.game;

public class ComputerPlayer implements Player {
  private final Mark mark;
  private int initialMoveCount;

  public ComputerPlayer(Mark mark) {
    this.mark = mark;
  }

  @Override
  public boolean isReady() {
    return true;
  }

  @Override
  public Board nextMove(Board board) {
    initialMoveCount = board.getMoveCount();

    int move = bestMove(board);
    return board.setMove(move, mark);
  }

  @Override
  public Mark getMark() {
    return mark;
  }

  private int bestMove(Board board) {
    RatedMove move = negamax(board, -1.0, 1.0, mark);
    return move.move;
  }

  private RatedMove negamax(Board board, double alpha, double beta, Mark mark) {
    int bestMove = -1;
    double bestScore = -1;

    if (isRateable(board)) {
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

  private boolean isRateable(Board board) {
    return !board.isPlayable() || maximumSearchDepthReached(board);
  }

  private boolean maximumSearchDepthReached(Board board) {
    return searchDepthOf(board) > board.getSideLength() + 3;
  }

  private int searchDepthOf(Board board) {
    return board.getMoveCount() - initialMoveCount;
  }

  private double score(Board board, Mark mark) {
    return score(board) * (board.getWinner() == mark ? 1 : -1);
  }

  private double score(Board board) {
    return 1.0 / board.getMoveCount();
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
