public class ClientFrom implements ClientState{
    private int number;
    public ClientFrom(int number) {
        this.number = number;
    }
    @Override
    public ClientState execute(CountdownClient connection) {
        connection.send(ClientProtocol.getFrom() + number);
        return new ClientStart();
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
