public class ServerStart implements ServerState {
    private int number;
    public ServerStart(int number) {
        this.number = number;
    }

    @Override
    public ServerState execute(CountdownServer connection) {
        connection.send(ServerProtocol.getStart(number));
        return new ServerCount(number);
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
