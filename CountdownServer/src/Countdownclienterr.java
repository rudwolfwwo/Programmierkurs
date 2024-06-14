import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketException;


    public class Countdownclienterr {

        public static void main(String[] args) {
            new CountdownServer();

            try {
                /* Schritt 1: Client-Socket für Verbindung mit Countdown-Server erstellen*/
                Socket socket = new Socket("localhost", 4444);
                System.out.println("Client startet and connected");

                /*Schritt 2: Datenstroeme erzeugen */
                BufferedReader input =
                        new BufferedReader(
                                new InputStreamReader(
                                        socket.getInputStream()));

                PrintWriter output =
                        new PrintWriter(
                                socket.getOutputStream(), true);

                /* Schritt 3: Nachrichten austauschen gemaess Protokoll*/

                /* Kennung empfangen */
                String answer = input.readLine();
                System.out.println("Client <- " + answer);
                /* Kennung überprüfen */
                if (answer == null || !answer.matches("Countdown - Welcome"))
                    throw new UnsupportedOperationException();

                /* Countdownanfrage senden */
                int c = 10;
                output.println("Countdown from " + c);
                System.out.println("Client -> Countdown from " + c);

                /* Countdownbestätigung empfangen */
                answer = input.readLine();
                System.out.println("Client <- " + answer);
                /* Countdownbestätigung überprüfen */
                if (answer == null || !answer.matches("Starting Countdown from \\d+"))
                    throw new UnsupportedOperationException();

                /* (Wiederholt) Zähler empfangen */
                answer = input.readLine();
                System.out.println("Client <- " + answer);
                /* Zähler überprüfen und extrahieren */
                if (answer == null || !answer.matches("\\d+"))
                    throw new UnsupportedOperationException();
                c = Integer.parseInt(answer);

                while (c > 0) {
                    answer = input.readLine();
                    System.out.println("Client <- " + answer);
                    if (answer == null || !answer.matches("\\d+"))
                        throw new UnsupportedOperationException();
                    c = Integer.parseInt(answer);
                }

                /* Verabschiedung empfangen */
                answer = input.readLine();
                System.out.println("Client <- " + answer);
                /* Verabschiedung überprüfen */
                if (answer == null || !answer.matches("Countdown finished - Bye"))
                    throw new UnsupportedOperationException();

                /* Schritt 4: Client schließen */
                socket.close();
                System.out.println("Client terminated");
            } catch (SocketException e) {

            } catch (NumberFormatException e) {

            } catch (IOException e) {

            }

        }

    }

    /*
     * TODO:
     * - Fehlende Objektorientierung
     * - Fehlende Ausnahmebehandlung
     * - Speicherlecks
     * - Verarbeitung blockiert andere Aufgaben: Thread
     * - Nachrichten sind im Code verstreut: Klasse ClientProtocol
     * - Langer unübersichtlicher Code mit vielen Verzweigungen: State-Pattern
     */


