import java.io.IOException;

public class ClientWelcome implements ClientState{
    private int number;

    public ClientWelcome(int number) {
        this.number = number;
    }

    public ClientState execute(CountdownClient connection) {
        try {
            String s = connection.receive();

            if (s == null)
                return new ClientError("Connection closed by Server!");

            if (!ClientProtocol.isValidWelcome(s))
                return new ClientError("No valid Welcome-String received!");

            return new ClientFrom(number);
        } catch (IOException e) {
            return new ClientError("Could not receive for Welcome-String!");
        }
    }

    public boolean isEndingState() {
        return false;
    }

    public boolean isErrorState() {
        return false;
    }
}
