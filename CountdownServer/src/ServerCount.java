public class ServerCount implements ServerState{
    private int number;
    public ServerCount(int number) {
        this.number = number;
    }

    @Override
    public ServerState execute(CountdownServer connection) {
        connection.send("" + number);
            try {
                if (number > 0) {
                    Thread.sleep(1000);
                    number--;
                    return new ServerCount(number);
                } else
                    return new ServerBye();
            } catch (InterruptedException e) {
                return new ServerError("Thread wurde unterbrochen");
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
