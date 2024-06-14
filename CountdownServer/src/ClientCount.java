import java.io.IOException;

public class ClientCount implements ClientState{

    @Override
    public ClientState execute(CountdownClient connection) {
        try {
            String msg = connection.receive();
            if (!ClientProtocol.isValidNumber(msg))
                return new ClientError("Zählernachricht ist fehlerhaft");
            if (ClientProtocol.extractZaehler(msg) > 0)
                return new ClientCount();
            else
                return new ClientBye();
        } catch (IOException e) {
            return new ClientError("Zählernachricht konnte nicht empfangen werden");
        }
    }

    @Override
    public boolean isEndingState() {
        return false;
    }

    @Override
    public boolean isErrorState() {
        return false;
    }
}
