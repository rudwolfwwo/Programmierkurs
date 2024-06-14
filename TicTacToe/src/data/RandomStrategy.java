package TicTacToe.src.data;

import TicTacToe.src.main.Main;

import java.util.Random;

public class RandomStrategy implements Strategy {
    @Override
    public Position calculateNextMove(TicTacToe field) {
        Random r = new Random();
        /*
        outLoop:
        for (int i = 0; i < Main.gameSize; i++) {
            for (int j = 0; j < Main.gameSize; j++) {
                if (field.getTokenAt(new Position(i,j)) != Token.NONE) break outLoop;
            }
        }*/
        if (field.getEmptyPositions().size() == 0) return null;
        return field.getEmptyPositions().get(r.nextInt(field.getEmptyPositions().size()));
    }
}
