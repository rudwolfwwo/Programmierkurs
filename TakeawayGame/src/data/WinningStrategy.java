package  TakeawayGame.src.data;

public class WinningStrategy implements Strategy {
    private int min;
    private int max;
    public WinningStrategy(int min, int max) {
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
        if (TokenContainer.instance().getSize() % (max +1) == 0)
            return min;
        else
            return TokenContainer.instance().getSize() % (max+1);
    }
}
