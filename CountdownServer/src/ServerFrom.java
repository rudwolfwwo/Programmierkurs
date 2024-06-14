import java.io.IOException;

public class ServerFrom implements ServerState{
    @Override
    public ServerState execute (CountdownServer connection) {
        try {
            String s = connection.receive();
            if (ServerProtocol.isValidFrom(s))
                return new ServerStart(ServerProtocol.extractNumber(s));
            else
                return new ServerError("Die Countdownanfrage hat eine falsche Form");
        } catch (IOException e) {
            return new ServerError("Die Countdownanfrage konnte nicht empfangen werden");
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
