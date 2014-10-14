package de.christophgockel.tictactoe;

public enum Mark {
  O, X;

  private Mark opponent;

  static {
    X.opponent = O;
    O.opponent = X;
  }

  public Mark getOpponent() {
    return opponent;
  }
}
