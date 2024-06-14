package TicTacToe.src.data;

public interface Strategy {
    Position calculateNextMove(TicTacToe field);
}
