package  TakeawayGame.src.data;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class TokenContainer implements Iterable<Token>{
    private static TokenContainer unique;
    private ArrayList<Token> tokens = new ArrayList<>();
    private TokenContainer(){
    }
    public static TokenContainer instance() {
        if (unique == null)
            unique = new TokenContainer();
        return unique;
    }
    public void linkToken(Token token) {
        tokens.add(token);
    }
    public void unlinkToken(Token token) {
        if (getSize() != 0 && tokens.contains(token))
            tokens.remove(token);
    }
    public ArrayList<Token> getLinkToken() {
        return tokens;
    }
    public void unlinkAnyToken() {
        if (getSize() != 0) {
            Random r = new Random();
            unlinkToken(tokens.get(r.nextInt(0, getSize())));
        }
    }
    public void clear() {
        if (getSize() != 0 && tokens != null)
            tokens.clear();
    }
    public int getSize() {
        return tokens.size();
    }
    public Iterator<Token> iterator() {
        return tokens.iterator();
    }
}
