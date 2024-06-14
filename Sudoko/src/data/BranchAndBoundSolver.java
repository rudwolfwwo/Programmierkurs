package data;

public class BranchAndBoundSolver implements SolvingStrategy {
    @Override
    public boolean solve(Sudoku board, int index) {
        if (index == board.getBoardSize()*board.getBoardSize())
            return board.checkBoard();
        if (board.getBoardEntry(index) == 0) {
            for (int j = 1; j <= board.getBoardSize(); j++) {
                board.setBoardEntry(index, j);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                if (board.checkBoard()) {
                    if (solve(board,index+1)) return true;
                }
                board.setBoardEntry(index, 0);
            }
            return false;
        }
        return solve(board, index+1);
    }
}
