public interface ClientState {
    ClientState execute(CountdownClient connection);
    boolean isEndingState();
    boolean isErrorState();
}
