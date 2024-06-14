package  TicTacToe.src.data;

import TicTacToe.src.gui.TicTacToeFrame;
import java.util.Arrays;

public class MinimaxStrategy implements Strategy {
    private int minimax(TicTacToe field,boolean userMove) {
        if (field.whoHasWon() == TicTacToeFrame.getUserToken()) return -1;
        else if (field.whoHasWon() == Token.NONE) {
            if (field.getEmptyPositions().size() == 0) return 0;
        }
        else return 1;

        int[] i = new int[field.getEmptyPositions().size()];
        for (Position p : field.getEmptyPositions()) {
            TicTacToe copy = field.copy();
            if (userMove)
                copy.putToken(p,TicTacToeFrame.getUserToken());
            else
                copy.putToken(p,TicTacToeFrame.getComputerToken());
            i[field.getEmptyPositions().indexOf(p)] = minimax(copy,!userMove);
        }
        if (userMove) return i[getMinimumIndex(i)];
        else return i[getMaximumIndex(i)];
    }
    private static int getMaximumIndex(int[] array) {
        for (int i = 0; i < array.length; i++)
            if (array[i] == Arrays.stream(array).max().getAsInt()) return i;
        return 0; //Illegal ProgrammState
    }
    private static int getMinimumIndex(int[] array) {
        for (int i = 0; i < array.length; i++)
            if (array[i] == Arrays.stream(array).min().getAsInt()) return i;
        return 0; //Illegal ProgrammState
    }
    @Override
    public Position calculateNextMove(TicTacToe field) {
        if (field.isEmpty()) return new Position(1,1);
        int[] i = new int[field.getEmptyPositions().size()];
        for (Position p : field.getEmptyPositions()) {
            TicTacToe copy = field.copy();
            copy.putToken(p,TicTacToeFrame.getComputerToken());
            i[field.getEmptyPositions().indexOf(p)] = minimax(copy,true);
        }
        return field.getEmptyPositions().get(getMaximumIndex(i));
    }
}
