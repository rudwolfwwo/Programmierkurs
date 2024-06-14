package  TakeawayGame.src.data;

import java.util.Random;

public class RandomStrategy implements Strategy {
    private Random rng;
    private int min;
    private int max;
    public RandomStrategy(int min, int max) {
        rng = new Random();
        setMin(min);
        setMax(max);
    }
    public int getMin() {
        return min;
    }
    public int getMax() {
        return max;
    }
    public void setMin(int min) {
        this.min = min;
    }
    public void setMax(int max) {
        this.max = max;
    }
    @Override
    public int calculateNextMove() {
        return rng.nextInt(min,max+1);
    }
}
