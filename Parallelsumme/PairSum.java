package Parallelsumme;

import java.util.concurrent.Callable;

public class PairSum implements Callable<Integer> {
    private int value1;
    private int value2;
    public PairSum(int value1, int value2) {
        setValue1(value1);
        setValue2(value2);
    }
    public int getValue1() {
        return value1;
    }
    public int getValue2() {
        return value2;
    }
    private void setValue1(int value1) {
        this.value1 = value1;
    }
    private void setValue2(int value2) {
        this.value2 = value2;
    }

    @Override
    public Integer call() {
        return value1 + value2;
    }
}