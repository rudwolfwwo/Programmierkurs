package SpeisendePhilosophen;

public class Tisch {

    public static void main (String[] args) {
        Boolean b1 = true,b2 = true,b3 = true,b4 = true, b5 = true;
        Philosoph p1 = new Philosoph("p1",b1,b2);
        Philosoph p2 = new Philosoph("p2",b2,b3);
        Philosoph p3 = new Philosoph("p3",b3,b4);
        Philosoph p4 = new Philosoph("p4",b4,b5);
        Philosoph p5 = new Philosoph("p5",b5,b1);
        p1.start();
        p2.start();
        p3.start();
        p4.start();
        p5.start();
    }
}
