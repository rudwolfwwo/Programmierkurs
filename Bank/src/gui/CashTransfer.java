package gui;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class CashTransfer extends Thread{
    private BlockingQueue<Integer> queue;
    private gui.BankAccount b;
    private int laenge;
    public CashTransfer(int max, gui.BankAccount b, int laenge) {
        queue = new ArrayBlockingQueue<>(max);
        this.b = b;
        this.laenge = laenge;
    }
    public void transfer(int betrag) throws IllegalStateException {
        queue.add(betrag);
    }
    @Override
    public void run() {
        while (!this.isInterrupted()) {
            try {
                int x = queue.take();
                b.draw(x);
                sleep(laenge);
            } catch (InterruptedException e) {
            }
        }
    }
}
