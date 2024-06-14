public class ServerWelcome implements ServerState {
    @Override
    public ServerState execute (CountdownServer connection) {
        connection.send(ServerProtocol.getWelcome());
        return new ServerFrom();
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
