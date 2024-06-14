public interface ServerState {
    ServerState execute(CountdownServer connection);
    boolean isEndingState();
    boolean isErrorState();
}
