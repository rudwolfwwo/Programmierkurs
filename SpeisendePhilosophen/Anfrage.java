package SpeisendePhilosophen;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

public class Anfrage extends Thread {
    BlockingQueue<String> k;
    public Anfrage(BlockingQueue<String> k ) {
        this.k = k;
        this.start();
    }
    @Override
    public void run() {
        try {
            System.out.println("Die Anfrage überlegt sich was sie anfrägt");
            Thread.sleep(1500);
            k.add("Hallo");
            Thread.sleep(1500);
            for (int i = 0; i < 4; i++) {
                System.out.println(k.take());
            }
            k.offer("GUuuuuuut",1, TimeUnit.SECONDS);
        } catch (InterruptedException e) {}
    }
}
