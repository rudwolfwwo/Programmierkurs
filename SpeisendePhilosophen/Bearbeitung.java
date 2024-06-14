package SpeisendePhilosophen;

import java.util.concurrent.BlockingQueue;

public class Bearbeitung extends Thread {
    BlockingQueue<String> k;
    public Bearbeitung(BlockingQueue<String> k ) {
        this.k = k;
        this.start();
    }
    @Override
    public void run() {
        try {
            System.out.println("Bearbeitung bereit!");
            String s = k.take();
            if (s.equals("Hallo")) {
                k.put("Wie");
                k.put("Geht");
                k.put("Es");
                k.put("Dir");
            }
            System.out.println("Die Bearbeitung wartet!");
            Thread.sleep(1700);

            System.out.println(k.take());
        } catch (InterruptedException e) {}
    }
}
