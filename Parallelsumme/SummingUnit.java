package Parallelsumme;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.*;
import java.util.stream.IntStream;

public class SummingUnit {
    private ArrayList<Integer> summands;
    private ExecutorService executorService;
    public SummingUnit(ArrayList<Integer> summands, ExecutorService executorService) {
        setSummands(summands);
        this.executorService = executorService;
    }
    public ArrayList<Integer> getSummands() {
        return summands;
    }
    private void setSummands(ArrayList<Integer> summands) {
        this.summands = summands;
    }
    public ExecutorService getExecutorService() {
        return this.executorService;
    }
    private void setExecutorService(ExecutorService executorService) {
        this.executorService = executorService;
    }
    public int sum() throws InterruptedException, ExecutionException {
        while (summands.size() != 1) {
            ArrayList<Integer> arr = new ArrayList<>();
            if (summands.size() % 2 == 1) {
                arr.add(summands.get(0));
                summands.remove(0);
            }
            for (int i = 1; i < summands.size(); i = i + 2) {
                Future<Integer> y = executorService.submit(new PairSum(summands.get(i), summands.get(i - 1)));
                arr.add(y.get());
            }
            summands = arr;
            System.out.println(summands);
        }
        return summands.get(0);
    }
    public static void main (String[] args) {
        ArrayList<Integer> arr = new ArrayList<>();
        IntStream.iterate(1, i -> i + 2).limit(10).forEach(arr::add);
        /*
        Random r = new Random();
        for (int i = 1; i < r.nextInt(0,100); i = i + 2) {
            arr.add(i);
        }*/
        System.out.println(arr);
        int ergebnis = 0;
        SummingUnit s = new SummingUnit(arr, Executors.newCachedThreadPool());
        try {
            ergebnis = s.sum();
        } catch (InterruptedException | ExecutionException e) {
            System.out.println("Etwas ist schiefgegangen!");
        }
        System.out.println("Die Summe ist " + ergebnis);
        assert(ergebnis == arr.size()*arr.size());
        //assert(false);
        s.getExecutorService().shutdown();
    }
}
