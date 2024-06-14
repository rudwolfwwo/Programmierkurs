public class ServerBye implements ServerState {
    @Override
    public ServerState execute(CountdownServer connection) {
        connection.send(ServerProtocol.getBye());
        return new ServerEnd();
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
