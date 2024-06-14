package TicTacToe.src.data;

import TicTacToe.src.main.Main;

/**
 * A class to store positions of cells in the game field.
 * 
 * @author Patrizia Schalk
 * @version 1.0
 */

public class Position {

	private int x;
	private int y;
	
	/**
	 * The constructor for a position containing an x and a y coordinate.
	 * The y-coordinate specifies the row of the game field, 
	 * the x-coordinate specifies the column of the game field.
	 * With this, a Position-object defines a cell in the game field.
	 * 
	 * @param x			the column of the game field where the specified cell is found
	 * @param y			the row of the game field where the specified cell is found
	 */
	public Position(int x, int y) {
		setX(x);
		setY(y);
	}

	/**
	 * Returns the x-coordinate of this position, where the leftmost position has x-coordinate 0.
	 * 
	 * @return			the x-coordinate of this position
	 */
	public int getX() {
		return x;
	}
	
	/**
	 * Checks if the given parameter is a legal x-coordinate on the game field, i.e. if it is 
	 * greater or equal to 0 and less than the size of the game field.
	 * 
	 * @param x			the potential x-coordinate that is to be checked
	 * @return			true, if x is in the bounds of the columns for the game field, false otherwise
	 */
	private static boolean checkX(int x) {
		return ((x >= 0) && (x < Main.gameSize));
	}

	/**
	 * Sets the x-coordinate of this position.
	 * 
	 * @param x			the x-coordinate this position should have after calling this method
	 */
	public void setX(int x) {
		if(!checkX(x)) throw new IllegalArgumentException("Value "+x+" not valid for an x-position on the tic tac toe grid.");
		this.x = x;
	}

	/**
	 * Returns the y-coordinate of this position, where the topmost position has y-coordinate 0.
	 * 
	 * @return			the y-coordinate of this position
	 */
	public int getY() {
		return y;
	}
	
	/**
	 * Checks if the given parameter is a legal y-coordinate on the game field, i.e. if it is 
	 * greater or equal to 0 and less than the size of the game field.
	 * 
	 * @param y			the potential y-coordinate that is to be checked
	 * @return			true, if y is in the bounds of the rows for the game field, false otherwise
	 */
	private static boolean checkY(int y) {
		return ((y >= 0) && (y < Main.gameSize));
	}

	/**
	 * Sets the y-coordinate of this position.
	 * 
	 * @param y			the y-coordinate this position should have after calling this method
	 */
	public void setY(int y) {
		if(!checkY(x)) throw new IllegalArgumentException("Value "+y+" not valid for a y-position on the tic tac toe grid.");
		this.y = y;
	}
	
	@Override
	public boolean equals(Object o) {
		if(o.getClass().equals(Position.class)) {
			Position p = (Position) o;
			return p.getX() == this.getX() && p.getY() == this.getY();
		}
		return false;
	}

}
