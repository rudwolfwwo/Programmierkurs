package gui;

public class BillThread implements Runnable {
    private int x, y;
    private gui.BankAccount b;
    private Thread t = null;
    public BillThread(gui.BankAccount b, int x, int y) {
        this.x = x;
        this.y = y;
        this.b = b;
        t = new Thread(this);
        t.start();
    }

    @Override
    public void run() {
        while (t != null && !t.isInterrupted()) {
            b.draw(x);
            try {
                Thread.sleep(y);
            } catch (InterruptedException e) {
            }
        }
    }
}
