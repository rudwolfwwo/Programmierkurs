public class ClientError implements ClientState{
    private String errMsg;

    public ClientError(String errMsg) {
        this.errMsg = errMsg;
    }

    public ClientState execute(CountdownClient connection) {
        throw new UnsupportedOperationException();
    }

    public String getErrMsg() {
        return errMsg;
    }

    public boolean isEndingState() {
        return true;
    }

    public boolean isErrorState() {
        return true;
    }
}
