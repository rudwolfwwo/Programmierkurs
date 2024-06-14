package data;

public class BacktrackSolver implements SolvingStrategy {
    @Override
    public boolean solve(Sudoku board, int i) {
        if (i == board.getBoardSize()*board.getBoardSize())
            return board.checkBoard();
        if (board.getBoardEntry(i) == 0) {
            for (int j = 1; j <= board.getBoardSize(); j++) {
                board.setBoardEntry(i, j);
                if (solve(board,i+1)) return true;
                board.setBoardEntry(i, 0);
            }
            return false;
        }
        return solve(board, i+1);
    }
}
