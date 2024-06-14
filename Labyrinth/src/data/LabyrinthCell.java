package data;

import java.awt.Point;

public class LabyrinthCell {

	private final Point position;
	private LabyrinthWall left = null;
	private LabyrinthWall top = null;
	private LabyrinthWall right = null;
	private LabyrinthWall bottom = null;
	
	/**
	 * A labyrinth cell, a space in the labyrinth that is not occupied by a labyrinth wall.
	 * Adjacent to a labyrinth cell are up to four labyrinth walls.
	 * @param position		the position of this labyrinth cell in the plane.
	 */
	public LabyrinthCell(Point position) {
		this.position = position;
	}
	
	/**
	 * Sets the left wall of this labyrinth cell.
	 * @param wall			the labyrinth wall that should be on the left of this labyrinth cell
	 */
	public void setLeftWall(LabyrinthWall wall) {
		this.left = wall;
	}
	
	/**
	 * Sets the top wall of this labyrinth cell.
	 * @param wall			the labyrinth wall that should be at the top of this labyrinth cell
	 */
	public void setTopWall(LabyrinthWall wall) {
		this.top = wall;
	}
	
	/**
	 * Sets the right wall of this labyrinth cell.
	 * @param wall			the labyrinth wall that should be on the right of this labyrinth cell
	 */
	public void setRightWall(LabyrinthWall wall) {
		this.right = wall;
	}
	
	/**
	 * Sets the bottom wall of this labyrinth cell.
	 * @param wall			the labyrinth wall that should be at the bottom of this labyrinth cell
	 */
	public void setBottomWall(LabyrinthWall wall) {
		this.bottom = wall;
	}
	
	/**
	 * A getter for the left wall adjacent to this labyrinth cell.
	 * @return				the labyrinth wall on the left of this labyrinth cell
	 */
	public LabyrinthWall getLeftWall() {
		return left;
	}
	
	/**
	 * A getter for the top wall adjacent to this labyrinth cell.
	 * @return				the labyrinth wall at the top of this labyrinth cell
	 */
	public LabyrinthWall getTopWall() {
		return top;
	}
	
	/**
	 * A getter for the right wall adjacent to this labyrinth cell.
	 * @return				the labyrinth wall on the right of this labyrinth cell
	 */
	public LabyrinthWall getRightWall() {
		return right;
	}
	
	/**
	 * A getter for the bottom wall adjacent to this labyrinth cell.
	 * @return				the labyrinth wall at the bottom of this labyrinth cell
	 */
	public LabyrinthWall getBottomWall() {
		return bottom;
	}
	
	/**
	 * A getter for the position of this labyrinth cell in the plane
	 * @return				the two-dimensional position of this labyrinth cell 
	 */
	public Point getPosition() {
		return position;
	}
	
	/**
	 * Checks whether this labyrinth cell equals the given Object, i.e. whether the 
	 * given Object is an instance of the LabyrinthCell class and if so, whether the 
	 * position of the given LabyrinthCell is exactly the same as for this labyrinth cell.
	 */
	@Override
	public boolean equals(Object o) {
		if (!(o instanceof LabyrinthCell))
			return false;
		LabyrinthCell other = (LabyrinthCell) o;
		return (this.getPosition().getX() == other.getPosition().getX()) 
			&& (this.getPosition().getY() == other.getPosition().getY());
	}
	
	/**
	 * Gives a String representation of this labyrinth cell.
	 */
	public String toString() {
		return "Labyrinth Cell at ("+position.x+", "+position.y+")";
	}
}
