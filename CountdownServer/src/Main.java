// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        new CountdownServer();
        CountdownClient c = new CountdownClient(5);
        Thread t1 = c.getThread();
        while (!t1.isInterrupted()) {

        }
        new CountdownClient(10);

    }
}