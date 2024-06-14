package  TakeawayGame.src.data;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

/**
 * A class representing a token on the game field.
 * 
 * @author Patrizia Schalk
 * @version 1.0
 */

public class Token {
	
	private int xPosition;
	private int yPosition;
	private int size;
	private Color color;
	
	/**
	 * A constructor for a token specifying the position on the game field. 
	 * The size of the token is automatically set to 40 by this constructor 
	 * and the color of the token is chosen at random.
	 * 
	 * @param xPosition		the x-coordinate of the position of this token on the game field
	 * @param yPosition		the y-coordinate of the position of this token on the game field
	 */
	public Token(int xPosition, int yPosition) {
		setxPosition(xPosition);
		setyPosition(yPosition);
		setSize(40);
		Random rng = new Random();
		setColor(new Color(rng.nextInt(256), rng.nextInt(256), rng.nextInt(256)));
	}
	
	/**
	 * A constructor for a token specifying the position on the game field
	 * and the size of the token. The color of the token is chosen at random.
	 * 
	 * @param xPosition		the x-coordinate of the position of this token on the game field
	 * @param yPosition		the y-coordinate of the position of this token on the game field
	 * @param size			the size of the token
	 */
	public Token(int xPosition, int yPosition, int size) {
		setxPosition(xPosition);
		setyPosition(yPosition);
		setSize(size);
		Random rng = new Random();
		setColor(new Color(rng.nextInt(256), rng.nextInt(256), rng.nextInt(256)));
	}
	
	/**
	 * A constructor for a token specifying the position on the game field,
	 * the size of the token and its color.
	 * 
	 * @param xPosition		the x-coordinate of the position of this token on the game field
	 * @param yPosition		the y-coordinate of the position of this token on the game field
	 * @param size			the size of the token
	 * @param color			the color of the token
	 */
	public Token(int xPosition, int yPosition, int size, Color color) {
		setxPosition(xPosition);
		setyPosition(yPosition);
		setSize(size);
		setColor(color);
	}

	/**
	 * Returns the x-coordinate of the position of this token on the game field.
	 * 
	 * @return				the x-coordinate of the position of this token on the game field
	 */
	public int getxPosition() {
		return xPosition;
	}

	/**
	 * Sets the x-coordinate of the position of this token on the game field.
	 * 
	 * @param xPosition		the x-coordinate of the position of this token on the game field
	 */
	public void setxPosition(int xPosition) {
		this.xPosition = xPosition;
	}

	/**
	 * Returns the y-coordinate of the position of this token on the game field.
	 * 
	 * @return				the y-coordinate of the position of this token on the game field
	 */
	public int getyPosition() {
		return yPosition;
	}

	/**
	 * Sets the y-coordinate of the position of this token on the game field.
	 * 
	 * @param xPosition		the y-coordinate of the position of this token on the game field
	 */
	public void setyPosition(int yPosition) {
		this.yPosition = yPosition;
	}

	/**
	 * Returns the size of this token.
	 * 
	 * @return				the size of this token
	 */
	public int getSize() {
		return size;
	}

	/** 
	 * Checks whether the given parameter would be a legal size for a token, e.g. whether it is greater than 0.
	 * 
	 * @param size			a candidate for a size of a token to be checked
	 * @return				true if size > 0, false otherwise
	 */
	public static boolean checkSize(int size) {
		if(size <= 0) return false;
		return true;
	}
	
	/**
	 * Sets the size of this token if it is illegal. This method calls checkSize(size) to check this.
	 * If the specified size is not legal, an unchecked IllegalArgumentException is thrown, since the 
	 * user should not be able to change the size of a token (indicating a program error).
	 * 
	 * @param size			the value that should be the size of the token
	 */
	public void setSize(int size) {
		if(!checkSize(size)) throw new IllegalArgumentException();
		this.size = size;
	}

	/**
	 * Returns the color of this token.
	 * 
	 * @return				the color of this token
	 */
	public Color getColor() {
		return color;
	}

	/**
	 * Sets the color of this token.
	 * 
	 * @param color			the color this token should have
	 */
	public void setColor(Color color) {
		this.color = color;
	}
	
	public boolean equals(Object o) {
		if(o.getClass().equals(Token.class)) {
			Token t = (Token) o;
			return t.getxPosition() == this.getxPosition() && t.getyPosition() == this.getyPosition()
				&& t.getSize() == this.getSize() && t.getColor().equals(this.getColor());
		}
		return false;
	}
	
	/**
	 * Specifies how an instance of the class token is drawn on the game field.
	 * 
	 * @param g				the Graphics-object which can draw the token on a component
	 */
	public void draw(Graphics g) {
		// draw lighter outer segment of the button
		g.setColor(color);
		g.fillOval(xPosition - size/2, yPosition - size/2, size, size);
		g.setColor(Color.BLACK);
		g.drawOval(xPosition - size/2, yPosition - size/2, size, size);
		int innerBorderSize = (int) Math.floor(0.75 * size);
		// draw darker inner segment of the button
		g.setColor(color.darker());
		g.fillOval(xPosition - innerBorderSize/2, yPosition - innerBorderSize/2, innerBorderSize, innerBorderSize);
		g.setColor(Color.BLACK);
		g.drawOval(xPosition - innerBorderSize/2, yPosition - innerBorderSize/2, innerBorderSize, innerBorderSize);
		// draw the button holes
		int buttonHoleSize = (int) Math.floor(0.2 * size);
		int holeDistance = (int) Math.floor(0.37 * innerBorderSize);
		// bottom left button hole
		g.fillOval(xPosition - buttonHoleSize/2 - holeDistance/2, yPosition - buttonHoleSize/2 + holeDistance/2, buttonHoleSize, buttonHoleSize);
		// top left button
		g.fillOval(xPosition - buttonHoleSize/2 - holeDistance/2, yPosition - buttonHoleSize/2 - holeDistance/2, buttonHoleSize, buttonHoleSize);
		// bottom right button hole
		g.fillOval(xPosition - buttonHoleSize/2 + holeDistance/2, yPosition - buttonHoleSize/2 + holeDistance/2, buttonHoleSize, buttonHoleSize);
		// top right button
		g.fillOval(xPosition - buttonHoleSize/2 + holeDistance/2, yPosition - buttonHoleSize/2 - holeDistance/2, buttonHoleSize, buttonHoleSize);
	}
	
}
