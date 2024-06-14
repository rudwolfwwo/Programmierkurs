public class ServerError implements ServerState{
    private String msg;
    public ServerError(String msg) {
        this.msg = msg;
    }
    public String getMsg() {
        return msg;
    }

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
        return true;
    }
}
