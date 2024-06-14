package SpeisendePhilosophen;

public class Philosoph extends Thread {
    private Boolean gabel1;
    private Boolean gabel2;
    private String name;
    public Philosoph(String name, Boolean links, Boolean rechts) {
        this.gabel1 = links;
        this.gabel2 = rechts;
        this.name = name;
    }
    @Override
    public void run () {
        for (int i = 0; i < 5; i++) {
            if (gabel2) {
                gabel2 = false;
                System.out.println(name + " konnte sich die rechte Gabel nehmen!");
            }
            while (!gabel1) {
                try {
                    if (gabel2) {
                        gabel2 = false;
                        System.out.println(name + " konnte sich die rechte Gabel nehmen!");
                    }
                    Thread.sleep(10);
                    System.out.println(name + " wartet!");
                    gabel2 = false;
                    System.out.println(name + "hat die rechte Gabel zurückgelegt!");
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            gabel1 = false;
            System.out.println(name + " konnte sich die linke Gabel nehmen!");
            System.out.println(name + " konnte fünf Mal essen!");
        }
    }
}
