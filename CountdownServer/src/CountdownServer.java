import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class CountdownServer implements Runnable {

    private ServerState state;
    private Thread talk;
    private PrintWriter out;
    private Socket client;
    private BufferedReader in;
    private ServerSocket server;
    private final int port = 4444;
    public CountdownServer() {
        init();
    }
    public void init() {
        try {
            server = new ServerSocket(port);
            start();
            System.out.println("Serververbindung aufgebaut und Serververarbeitung gestartet");
        } catch (IOException e) {
            System.err.println("Fehler beim Aufbau der Serververbindung");
        }
    }
    public void start() {
        talk = new Thread(this);
        talk.start();
    }
    public void finish() {
        if (server != null) {
            try {
                server.close();
                System.out.println("Die Verbindung konnte erfolgreich unterbrochen werden");
            } catch (IOException e) {
                System.err.println("Die Verbindung konnte nicht unterbrochen werden");
            }
        }
    }
    public void send(String s) {
        out.println(s);
        System.out.println("Server: --> " + s);
    }
    public String receive() throws IOException {
        String request = in.readLine();
        System.out.println("\"" + request + "\" vom Server empfangen");
        return request;
    }

    @Override
    public void run() {
        while (true) {
            try {
                client = server.accept();
                System.out.println("Der Client konnte verbunden werden");
                out = new PrintWriter(
                        client.getOutputStream(), true);
                in = new BufferedReader(
                                new InputStreamReader(
                                        client.getInputStream()));
                state = new ServerWelcome();
                while (!state.isEndingState()) {
                        state = state.execute(this);
                }
                if (state.isErrorState())
                    System.err.println(((ServerError) state).getMsg());
            } catch (IOException e) {
                System.err.println("Der Client konnte nicht verbunden werden");
                finish();
            }

        }
    }
}
