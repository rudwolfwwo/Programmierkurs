package gui;

public class WageThread implements Runnable {
    private int x, y;
    private gui.BankAccount b;
    private Thread t = new Thread(this);
    public WageThread(gui.BankAccount b, int x, int y) {
        this.x = x;
        this.y = y;
        this.b = b;
        t.start();
    }

    @Override
    public void run() {
        while (t != null && !t.isInterrupted()) {
            b.deposit(x);
            try {
                Thread.sleep(y);
            } catch (InterruptedException e) {
            }
        }
    }
}
