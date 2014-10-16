package de.christophgockel.tictactoe;

public class Main {
  public static void main(String[] args) {
    CommandlineIO io = new CommandlineIO(System.in, System.out);
    Player playerOne = new HumanPlayer(Mark.X, io);
    Player playerTwo = new HumanPlayer(Mark.O, io);
    Board board = new Board();
    Game game = new Game(playerOne, playerTwo, board, io);

    CommandlineRunner runner = new CommandlineRunner(game);

    runner.play();
  }
}
