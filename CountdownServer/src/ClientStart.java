import java.io.IOException;

public class ClientStart implements ClientState{
    @Override
    public ClientState execute(CountdownClient connection) {
        try {
            if (ClientProtocol.isValidStart(connection.receive()))
                return new ClientCount();
            else
                return new ClientError("Startnachricht hat die falsche Form");
        } catch (IOException e) {
            return new ClientError("Startnachricht konnte nicht empfangen werden");
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
