package SpeisendePhilosophen;

public class Ball {
    private boolean taken = false;
    public static void main(String[] args) {
        Ball ball = new Ball();
        Person p1 = new Person(ball,"Person 1");
        Person p2 = new Person(ball,"Person 2");
        Person p3 = new Person(ball,"Person 3");
    }
    public synchronized void getBall() {
        while (taken) {
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        taken = true;
    }
    public synchronized void returnBall() {
        if (taken) {
            taken = false;
            notify();
        }
    }
}
