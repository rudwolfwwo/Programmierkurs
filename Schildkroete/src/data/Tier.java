package Schildkroete.src.data;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public abstract class Tier {
	
	protected final int updateFrequency = 500;
	
	private Position starting_position;
	private Position current_position;
	private int running_speed;
	private BufferedImage image;
	private int spriteSize;
	
	/**
	 * The constructor of the Animal class.
	 * @param starting_position the position where the animal starts the race
	 * @param running_speed number of pixels the animal moves in each step
	 * @param pathToImage the path to the image for the animal
	 * @param spriteSize the preferred size in which the image for the animal should be drawn
	 * @throws IOException if the path to the image is incorrect or couldn't be read
	 */
	protected Tier(Position starting_position, int running_speed, String pathToImage, int spriteSize) throws IOException{
		setStartingPosition(starting_position);
		setCurrentPosition(new Position(starting_position));
		setRunningSpeed(running_speed);
		loadImage(pathToImage);
		setSpriteSize(spriteSize);
	}

	/**
	 * Returns the starting position with which the constructor of this animal was called.
	 * @return the starting position with which the constructor of this animal was called
	 */
	public Position getStartingPosition() {
		return starting_position;
	}

	/**
	 * Sets the starting position with which the constructor of this animal was called.
	 * @param starting_position the starting position with which the constructor of this animal was called
	 */
	private void setStartingPosition(Position starting_position) {
		this.starting_position = starting_position;
	}

	/**
	 * Returns the current position of this animal.
	 * @return the current position of this animal
	 */
	public Position getCurrentPosition() {
		return current_position;
	}

	/**
	 * Sets the current position of this animal.
	 * @param current_position the new position for this animal
	 */
	protected void setCurrentPosition(Position current_position) {
		this.current_position = current_position;
	}

	/**
	 * Returns the running speed of this animal.
	 * @return the number of pixels the animal skips with each step
	 */
	public int getRunningSpeed() {
		return running_speed;
	}

	/**
	 * Sets the running speed of this animal.
	 * @param running_speed the number of pixels the animal skips with each step
	 */
	private void setRunningSpeed(int running_speed) {
		this.running_speed = running_speed;
	}
	
	/**
	 * Returns the BufferedImage for this animal.
	 * @return the BufferedImage for this animal
	 */
	public BufferedImage getImage() {
		return image;
	}
	
	/**
	 * Loads a BufferedImage from the given path.
	 * @param pathToImage the path to the image that should be converted to a BufferedImage
	 * @throws IOException if the path to the image is incorrect or couldn't be read
	 */
	private void loadImage(String pathToImage) throws IOException {
		this.image = ImageIO.read(new File(pathToImage));
	}
	
	/**
	 * Returns the size of the sprite for this animal. The x- and y-dimension of the sprite are always equal.
	 * @return the size of the sprite for this animal
	 */
	public int getSpriteSize() {
		return spriteSize;
	}

	/**
	 * Sets the size of the sprite for this animal. The x- and y-dimension of the sprite are always equal.
	 * @param spriteSize the value for the x- and y-dimension of the sprite for this animal
	 */
	private void setSpriteSize(int spriteSize) {
		this.spriteSize = spriteSize;
	}

	/**
	 * Shifts the position of this animal by the number of pixels stored in running_speed.
	 */
	public void makeStep() {
		current_position.addToX(running_speed);
	}
	
	/**
	 * Resets the position of this animal to the starting position for which the constructor was called.
	 */
	public void resetAnimal() {
		setCurrentPosition(new Position(starting_position));
	}
	
	/**
	 * Draws the BufferedImage as a graphical representation for this animal.
	 * @param g the Graphics-object used for drawing
	 */
	public void draw(Graphics g) {
		int x = getCurrentPosition().getX();
		int y = getCurrentPosition().getY();
		g.drawImage(getImage(), x, y, spriteSize, spriteSize, null);
	}
	
}
