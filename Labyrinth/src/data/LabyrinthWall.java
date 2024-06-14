package data;

import java.awt.Point;

public class LabyrinthWall {
	
	private final LabyrinthCell neighbor1;
	private final LabyrinthCell neighbor2;
	
	/**
	 * A labyrinth wall, drawn as a horizontal or vertical line. 
	 * Every labyrinth wall has two adjacent cells in the labyrinth, 
	 * which we call neighbors.
	 * @param neighbor1		the first neighboring cell of the wall
	 * @param neighbor2		the second neighboring cell of the wall
	 */
	public LabyrinthWall(LabyrinthCell neighbor1, LabyrinthCell neighbor2) {
		if (checkNeighbors(neighbor1, neighbor2)) {
			this.neighbor1 = neighbor1;
			this.neighbor2 = neighbor2;
		}
		else throw new IllegalArgumentException();
	}
	
	/**
	 * Checks if the two given labyrinth cells are both adjacent to 
	 * each other, i.e. if their position differs by exactly
	 * one on the x- or y-axis.
	 * @param neighbor1		the first labyrinth cell 
	 * @param neighbor2		the second labyrinth cell
	 * @return				true, if the two labyrinth cells are adjacent to each other, false otherwise.
	 */
	private boolean checkNeighbors(LabyrinthCell neighbor1, LabyrinthCell neighbor2) {
		Point p1 = neighbor1.getPosition();
		Point p2 = neighbor2.getPosition();
		return (Math.abs(p1.x - p2.x) == 1 && Math.abs(p1.y - p2.y) == 0) 
				|| (Math.abs(p1.x - p2.x) == 0 && Math.abs(p1.y - p2.y) == 1);
	}
	
	/**
	 * A getter for the first neighbor of this labyrinth wall.
	 * For internal use only.
	 * @return				one of the labyrinth cells adjacent to this labyrinth wall.
	 */
	private LabyrinthCell getNeighbor1() {
		return neighbor1;
	}
	
	/**
	 * A getter for the second neighbor of this labyrinth wall.
	 * For internal use only.
	 * @return				one of the labyrinth cells adjacent to this labyrinth wall.
	 */
	private LabyrinthCell getNeighbor2() {
		return neighbor2;
	}
	
	/**
	 * A getter for the neighboring labyrinth cell to the left of this labyrinth wall.
	 * If this labyrinth wall is drawn horizontally (i.e. no left neighbor exists), null is returned. 
	 * @return				the labyrinth cell to the left of this wall, if this wall has a vertical orientation, null otherwise
	 */
	public LabyrinthCell getLeftNeighbor() {
		double x1 = neighbor1.getPosition().getX();
		double x2 = neighbor2.getPosition().getX();
		if (x1 == x2)
			return null;
		else
			return (x1 < x2) ? neighbor1 : neighbor2;
	}
	
	/**
	 * A getter for the neighboring labyrinth cell at the top of this labyrinth wall.
	 * If this labyrinth wall is drawn vertically (i.e. no top neighbor exists), null is returned. 
	 * @return				the labyrinth cell at the top of this wall, if this wall has a horizontal orientation, null otherwise
	 */
	public LabyrinthCell getTopNeighbor() {
		double y1 = neighbor1.getPosition().getY();
		double y2 = neighbor2.getPosition().getY();
		if(y1 == y2)
			return null;
		else
			return (y1 < y2) ? neighbor1 : neighbor2;
	}
	
	/**
	 * A getter for the neighboring labyrinth cell to the right of this labyrinth wall.
	 * If this labyrinth wall is drawn horizontally (i.e. no right neighbor exists), null is returned. 
	 * @return				the labyrinth cell to the right of this wall, if this wall has a vertical orientation, null otherwise
	 */
	public LabyrinthCell getRightNeighbor() {
		double x1 = neighbor1.getPosition().getX();
		double x2 = neighbor2.getPosition().getX();
		if (x1 == x2)
			return null;
		else
			return (x1 > x2) ? neighbor1 : neighbor2;
	}
	
	/**
	 * A getter for the neighboring labyrinth cell at the bottom of this labyrinth wall.
	 * If this labyrinth wall is drawn vertically (i.e. no bottom neighbor exists), null is returned. 
	 * @return				the labyrinth cell at the bottom of this wall, if this wall has a horizontal orientation, null otherwise
	 */
	public LabyrinthCell getBottomNeighbor() {
		double y1 = neighbor1.getPosition().getY();
		double y2 = neighbor2.getPosition().getY();
		if (y1 == y2)
			return null;
		else
			return (y1 > y2) ? neighbor1 : neighbor2;
	}
	
	/**
	 * Checks whether this labyrinth wall has a horizontal orientation
	 * @return				true, if this labyrinth wall has a horizontal orientation, false otherwise
	 */
	public boolean isHorizontal() {
		return (getTopNeighbor() != null) || (getBottomNeighbor() != null); 
	}
	
	/**
	 * Checks whether this labyrinth wall has a vertical orientation
	 * @return				true, if this labyrinth wall has a vertical orientation, false otherwise
	 */
	public boolean isVertical() {
		return (getLeftNeighbor() != null) || (getRightNeighbor() != null);
	}
	
	/**
	 * Checks whether this labyrinth wall equals the given Object, i.e. whether the Object is an instance 
	 * of the LabyrinthWall class and (if so) whether it has the same neighbors as this labyrinth wall. 
	 */
	@Override
	public boolean equals(Object o) {
		if (!(o instanceof LabyrinthWall))
			return false;
		LabyrinthWall other = (LabyrinthWall) o;
		return (this.getNeighbor1().equals(other.getNeighbor1()) && this.getNeighbor2().equals(other.getNeighbor2()))
			|| (this.getNeighbor1().equals(other.getNeighbor2()) && this.getNeighbor2().equals(other.getNeighbor1()));
	}
}
