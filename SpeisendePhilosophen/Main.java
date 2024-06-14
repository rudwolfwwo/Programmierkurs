package SpeisendePhilosophen;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class Main {
    public static void main(String[] args) {
        BlockingQueue<String> k = new ArrayBlockingQueue<String>(5);
        new Anfrage(k);
        new Bearbeitung(k);
    }
}
