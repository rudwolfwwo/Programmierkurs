package data;

import java.awt.Point;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;

public class Labyrinth {

	private final int SIZE;
	private LabyrinthWallContainer wallContainer;
	private LabyrinthCell[] cells;
	private LabyrinthCreator strat;
	private LabyrinthCell current; // for Aldous Broder algorithm
	private ArrayList<LabyrinthCell> path = new ArrayList<>();

	private PropertyChangeSupport changes = new PropertyChangeSupport(this);

	public Labyrinth(int size) {
		this.SIZE = size;
		initializeCells();
		initializeWalls();
	}

	private void initializeCells() {
		// reserve memory for all cells of the labyrinth
		this.cells = new LabyrinthCell[SIZE * SIZE];
		// create instances of the class LabyrinthCell for
		// each point of the labyrinth
		for(int y = 0; y < SIZE; y++) {
			for(int x = 0; x < SIZE; x++) {
				this.cells[y * SIZE + x] = new LabyrinthCell(new Point(x, y));
			}
		}
	}

	private void initializeWalls() {
		// get the (only) instance of the Container
		this.wallContainer = LabyrinthWallContainer.getInstance();
		// remove any fragments left in the Container
		if (!this.wallContainer.isEmpty()) this.wallContainer.clear();

		// create walls between cells and put them into the Container
		for(int i = 0; i < SIZE * SIZE; i++) {
			if(i % SIZE != 0) {
				// cell is not on the left border
				LabyrinthWall wall = new LabyrinthWall(cells[i - 1], cells[i]);
				// don't do anything if wall is already in the Container
				if (!wallContainer.contains(wall)) {
					cells[i - 1].setRightWall(wall);
					cells[i].setLeftWall(wall);
					wallContainer.linkLabyrinthWall(wall);
				}
			}
			if (i >= SIZE) {
				// cell is not on the top border
				LabyrinthWall wall = new LabyrinthWall(cells[i - SIZE], cells[i]);
				// don't do anything if wall is already in the Container
				if (!wallContainer.contains(wall)) {
					cells[i - SIZE].setBottomWall(wall);
					cells[i].setTopWall(wall);
					wallContainer.linkLabyrinthWall(wall);
				}
			}
			if (i % SIZE != SIZE - 1) {
				// cell is not on the right border
				LabyrinthWall wall = new LabyrinthWall(cells[i], cells[i + 1]);
				// don't do anything if wall is already in the Container
				if (!wallContainer.contains(wall)) {
					cells[i].setRightWall(wall);
					cells[i + 1].setLeftWall(wall);
					wallContainer.linkLabyrinthWall(wall);
				}

			}
			if (i <= (SIZE * SIZE) - SIZE - 1) {
				// cell is not on the bottom border
				LabyrinthWall wall = new LabyrinthWall(cells[i], cells[i + SIZE]);
				// don't do anything if wall is already in the Container
				if (!wallContainer.contains(wall)) {
					cells[i].setBottomWall(wall);
					cells[i + SIZE].setTopWall(wall);
					wallContainer.linkLabyrinthWall(wall);
				}
			}
		}
	}

	public int getIndexOfCell(LabyrinthCell cell) {
		int x = (int) cell.getPosition().getX();
		int y = (int) cell.getPosition().getY();
		return y * SIZE + x;
	}

	public void setCurrent(LabyrinthCell current) {
		this.current = current;
		changes.firePropertyChange("current cell changed", null, current);
	}
	public void addToPath(LabyrinthCell path) {
		this.path.add(path);
		changes.firePropertyChange("current cell changed", null, current);
	}
	public void removeFromPath(LabyrinthCell path) {
		this.path.remove(path);
		changes.firePropertyChange("current cell changed", null, current);
	}
	public ArrayList<LabyrinthCell> getPath() {
		return path;
	}

	public LabyrinthCell getCurrent() {
		return current;
	}

	public int getSize() {
		return SIZE;
	}

	public LabyrinthWallContainer getWallContainer() {
		return wallContainer;
	}

	public LabyrinthCell[] getLabyrinthCells() {
		return cells;
	}
	public LabyrinthCell getLabyrinthCell(int index) {
		return cells[index];
	}

	/**
	 * Creates a labyrinth, starting from a grid that features only walls.
	 */
	public void solve() {
		strat.createLabyrinth(this);
		changes.firePropertyChange("Labyrinth finished", null, null);
	}

	/**
	 * Sets the strategy for creating a labyrinth.
	 * @param strat		an implementation of a labyrinth creator
	 */
	public void setSolver(LabyrinthCreator strat) {
		this.strat = strat;
	}

	/**
	 * Returns the strategy for creating a labyrinth of this class.
	 * @return			the labyrinth creation strategy registered for this labyrinth
	 */
	public LabyrinthCreator getSolver() {
		return strat;
	}

	public void addPropertyChangeListener(PropertyChangeListener l) {
		changes.addPropertyChangeListener(l);
	}

	public void removePropertyChangeListener(PropertyChangeListener l) {
		changes.removePropertyChangeListener(l);
	}

}
