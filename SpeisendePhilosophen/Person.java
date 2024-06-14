package SpeisendePhilosophen;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Person implements Runnable{
    String name;
    Ball ball;
    Thread t = new Thread(this);
    public Person(Ball ball,String name) {
        this.name = name;
        this.ball = ball;
        t.start();
    }

    @Override
    public void run() {
        Random r = new Random();
        while (!t.isInterrupted()) {
            try {
                Thread.sleep(Math.abs(r.nextInt(1000,5000)));
            } catch (InterruptedException i) {
                t.interrupt();
            }
            /*synchronized(ball) {
                System.out.println(name + " hat den Ball und spricht");
            }*/
                ball.getBall();
                System.out.println(name + " hat den Ball und spricht");
                ball.returnBall();
            System.out.println("Der Ball ist wieder freigegeben");
        }
    }
}
