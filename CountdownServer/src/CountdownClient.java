import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class CountdownClient implements Runnable {
    private ClientState state;
    private Thread talk;
    private PrintWriter out;
    private Socket socket;
    private BufferedReader in;
    private final int port = 4444;
    private int number;
    public CountdownClient(int number) {
        this.number = number;
        init();
    }
    public synchronized void init() {
        try {
            socket = new Socket("localhost", port);
            in = new BufferedReader(
                    new InputStreamReader(
                            socket.getInputStream()));
            out = new PrintWriter(
                    socket.getOutputStream(), true);
            state = new ClientWelcome(number);
            start();
            System.out.println("Clientverbindung aufgebaut und Anfrage gestartet");
        } catch (IOException e) {
            new ClientError("Clientverbindung konnte nicht aufgebaut werden");
        }
    }
    public void start() {
        talk = new Thread(this);
        talk.start();
    }
    public void finish() {
        if (socket != null) {
            try {
                socket.close();
                System.out.println("Die Verbindung konnte erfolgreich unterbrochen werden");
            } catch (IOException e) {
                System.err.println("Die Verbindung konnte nicht unterbrochen werden");
            }
        }
        System.out.println("__________________________________________");
        talk.interrupt();
    }
    public void send(String s) {
        out.println(s);
        System.out.println("Client: <-- " + s);
    }
    public String receive() throws IOException {
        String request = in.readLine();
        System.out.println("\"" + request + "\" vom Client empfangen");
        return request;
    }

    @Override
    public void run() {
        while (!state.isEndingState()) {
            state = state.execute(this);
        }
        if (state.isErrorState())
            System.err.println(((ServerError) state).getMsg());
        finish();
        //System.out.println("__________________________________________");
    }
    public Thread getThread() {
        return talk;
    }
}
