public class Node {
    private int ID;
    private Color color = Color.WHITE;
    public Node(int ID) {
        setID(ID);
    }
    public int getID() {
        return ID;
    }
    private boolean checkID(int ID) {
        return ID > 0;
    }
    private void setID(int ID) {
        if (checkID(ID))
            this.ID = ID;
    }
    public Color getColor () {
        return this.color;
    }
    public void setColor(Color color) {
        this.color = color;
    }
    public boolean isWhite() {
        return color == Color.WHITE;
    }

}
