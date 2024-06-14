package data;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import java.util.concurrent.locks.ReentrantLock;

public class LabyrinthWallContainer implements Iterable<LabyrinthWall>{
	
	private static LabyrinthWallContainer unique = null;
	private ArrayList<LabyrinthWall> walls;
	private PropertyChangeSupport changes = new PropertyChangeSupport(this);
	public ReentrantLock mutex = new ReentrantLock();
	
	/**
	 * A container for labyrinth walls following the singleton-pattern.
	 */
	private LabyrinthWallContainer() {
		this.walls = new ArrayList<LabyrinthWall>();
	}
	
	/**
	 * A getter for the only instance of this container.
	 * @return			the only existing instance of a LabyrinthWallContainer
	 */
	public static LabyrinthWallContainer getInstance() {
		if (unique == null)
			unique = new LabyrinthWallContainer();
		return unique;
	}
	public LabyrinthWall getRandomWall() {
		Random r = new Random();
		return walls.get(r.nextInt(0,walls.size()));
	}

	
	/**
	 * Adds a labyrinth wall to this container
	 * @param wall		the wall that should be added to this container
	 */
	public synchronized void linkLabyrinthWall(LabyrinthWall wall) {
		if (this.walls.contains(wall)) throw new IllegalArgumentException();
		this.walls.add(wall);
	}
	
	/**
	 * Removes a labyrinth wall from this container, if it is featured in this container.
	 * Does nothing otherwise
	 * @param wall		the wall that should be removed from this container
	 */
	public synchronized void unlinkLabyrinthWall(LabyrinthWall wall) {
		if (!this.walls.contains(wall))
			return;
		else {
			try {
				mutex.lock();
				this.walls.remove(wall);
				changes.firePropertyChange("LabyrinthWall removed", wall, null);
			} finally {
				mutex.unlock();
			}
		}
	}
	
	/**
	 * Checks if this container features the given labyrinth wall
	 * @param wall		a labyrinth wall
	 * @return			true, if wall exists in this container, false otherwise.
	 */
	public boolean contains(LabyrinthWall wall) {
		return this.walls.contains(wall);
	}
	
	/**
	 * Checks if this container is empty.
	 * @return			true if this container is empty, false otherwise.
	 */
	public boolean isEmpty() {
		return this.walls.isEmpty();
	}
	
	/**
	 * Empties this container, i.e. removes all labyrinth walls in it.
	 */
	public void clear() {
		this.walls.clear();
	}
	
	/**
	 * Returns an iterator for the container.
	 */
	public Iterator<LabyrinthWall> iterator() {
		return this.walls.iterator();
	}
	
	/**
	 * A method to register a property change listener on this container.
	 * @param l			an instance of a class that wishes to be informed of changes in this container
	 */
	public void addPropertyChangeListener(PropertyChangeListener l) {
		changes.addPropertyChangeListener(l);
	}
	
	/**
	 * A method to remove a registered property change listener on this container.
	 * @param l			an instance of a class that is registered as a property change listener, that should be removed as such
	 */
	public void removePropertyChangeListener(PropertyChangeListener l) {
		changes.removePropertyChangeListener(l);
	}
	
}
