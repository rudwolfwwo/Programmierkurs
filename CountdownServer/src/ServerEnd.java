public class ServerEnd implements ServerState {

    @Override
    public ServerState execute(CountdownServer connection) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean isEndingState() {
        return true;
    }

    @Override
    public boolean isErrorState() {
        return false;
    }
}
