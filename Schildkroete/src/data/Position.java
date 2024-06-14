package Schildkroete.src.data;

public class Position {
	
	private int x;
	private int y;
	
	/**
	 * A constructor for a Position that initializes the x- and y-value both with 0.
	 */
	public Position() {
		this.setX(0);
		this.setY(0);
	}
	
	/**
	 * A constructor for a Position that initializes the x- and y-value with the given parameter values.
	 * @param x the value for the horizontal position
	 * @param y the value for the vertical position
	 */
	public Position(int x, int y) {
		this.setX(x);
		this.setY(y);
	}
	
	/**
	 * A constructor for a Position that copies the attributes of the Position given as a parameter.
	 * @param p An existing Position-object whose attributes should be copied
	 */
	public Position(Position p) {
		this.setX(p.getX());
		this.setY(p.getY());
	}

	/**
	 * Returns the x-value of this Position.
	 * @return the x-value of this Position
	 */
	public int getX() {
		return x;
	}

	/**
	 * Sets the x-value of this Position.
	 * @param x the new x-value for this Position
	 */
	private void setX(int x) {
		this.x = x;
	}

	/**
	 * Returns the y-value of this Position.
	 * @return the y-value of this Position
	 */
	public int getY() {
		return y;
	}

	/**
	 * Sets the y-value of this Position.
	 * @param y the new y-value for this Position
	 */
	private void setY(int y) {
		this.y = y;
	}
	
	/**
	 * Increases the x-value by the given amount.
	 * @param amount value that should be added to the x-value of this Position
	 */
	public void addToX(int amount) {
		this.x = this.x + amount;
	}
	
	@Override
	public String toString() {
		return "Position: (" + this.x + ", " + this.y + ")";
	}

}
