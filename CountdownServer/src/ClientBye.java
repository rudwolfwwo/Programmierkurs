import java.io.IOException;

public class ClientBye implements ClientState{
    @Override
    public ClientState execute(CountdownClient connection) {
        try {
            String msg = connection.receive();
            if (!ClientProtocol.isValidBye(msg))
                return new ClientError("fehlerhafte Abschiedsnachricht");
            return new ClientEnd();
        } catch (IOException e) {
            return new ClientError("Abschiedsnachricht konnte nicht empfangen werden");
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
