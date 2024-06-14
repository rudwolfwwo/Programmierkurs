package data;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;

public class Sudoku {
	
	private final int SIZE;
	private int[] board;
	private SolvingStrategy strat;
	
	// add a property change support so that other classes can listen to changes
	private PropertyChangeSupport changes = new PropertyChangeSupport(this);
	
	/**
	 * A sudoku board, implemented as an array of integers in the range of 0 to size
	 * @param size 		the width and height of the sudoku board, which must be a square number
	 */
	public Sudoku(int size) {
		if ((Math.sqrt(size) - Math.floor(Math.sqrt(size)) != 0))
			throw new IllegalArgumentException("Illegal size " + size + ": This size is not a square number.");
		this.SIZE = size;
		// initialize an empty sudoku board, where every entry is 0
		board = new int[SIZE * SIZE];
	}
	
	/**
	 * Returns the board entry of the internal array
	 * @param index 	an index in the range of 0 to SIZE*SIZE-1
	 * @return			the board-entry at the given index
	 */
	public int getBoardEntry(int index) {
		if (index < 0 || index >= SIZE * SIZE) 
			throw new IllegalArgumentException("Illegal index " + index + ": This parameter must be in range of 0 to " + (SIZE * SIZE - 1) + ".");
		return board[index];
	}
	
	/**
	 * Returns the board entry at the specified row and column
	 * @param row		the number of the row of the entry, in the range of 0 to SIZE-1
	 * @param column	the number of the column of the entry, in the range of 0 to SIZE-1
	 * @return			the board-entry at the given row and the given column
	 */
	public int getBoardEntry(int row, int column) {
		if (row < 0 || row >= SIZE)
			throw new IllegalArgumentException("Illegal row " + row + ": This parameter must be in range of 0 to " + (SIZE - 1) + ".");
		else if(column < 0 || column >= SIZE)
			throw new IllegalArgumentException("Illegal column " + column + ": This parameter must be in range of 0 to " + (SIZE - 1) + ".");
		else
			return board[row * SIZE + column];
	}
	
	/**
	 * Sets the board entry at the given index in the internal array
	 * @param index		the index in the internal array, in the range of 0 to SIZE*SIZE-1, whose entry should be changed 
	 * @param value		the new value of the board entry (may be the old entry), in the range of 0 to SIZE
	 */
	public void setBoardEntry(int index, int value) {
		if (index < 0 || index >= SIZE * SIZE) 
			throw new IllegalArgumentException("Illegal index " + index + ": This parameter must be in range of 0 to " + (SIZE * SIZE - 1) + ".");
		else if (value < 0 || value > SIZE) 
			throw new IllegalArgumentException("Illegal value " + value + ": This parameter must be in range of 0 to " + SIZE + ".");
		else { 
			int old = board[index];
			board[index] = value;
			changes.firePropertyChange("SudokuCell", old, value);
		}
	}
	
	/**
	 * Sets the board entry at the given row and column to the new value
	 * @param row		the number of the row of the entry, in the range of 0 to SIZE-1
	 * @param column	the number of the column of the entry, in the range of 0 to SIZE-1
	 * @param value		the new value of the board entry (may be the old entry), in the range of 0 to SIZE
	 */
	public void setBoardEntry(int row, int column, int value) {
		if (row < 0 || row >= SIZE)
			throw new IllegalArgumentException("Illegal row " + row + ": This parameter must be in range of 0 to " + (SIZE - 1) + ".");
		else if (column < 0 || column >= SIZE)
			throw new IllegalArgumentException("Illegal column " + column + ": This parameter must be in range of 0 to " + (SIZE - 1) + ".");
		else if (value < 0 || value > SIZE) 
			throw new IllegalArgumentException("Illegal value " + value + ": This parameter must be in range of 0 to " + SIZE + ".");
		else {
			int old = board[row * SIZE + column];
			board[row * SIZE + column] = value;
			changes.firePropertyChange("SudokuCell", old, value);
		}
	}
	
	/**
	 * Returns the size of the sudoku board
	 * @return			the number of rows resp. columns of the sudoku board
	 */
	public int getBoardSize() {
		return SIZE;
	}
	
	/**
	 * Checks if this board is a valid sudoku board:
	 * In each row every number from 1 to SIZE is featured at most once,
	 * in each column every number from 1 to SIZE is featured at most once,
	 * if we divide the board into small boards of size sqrt(SIZE), every 
	 * number from 1 to SIZE is featured at most once in this small board.
	 * @return			true, if the board is a valid sudoku board, false otherwise
	 */
	public boolean checkBoard() {
		ArrayList<Integer> seen = new ArrayList<Integer>();
		// check each row of the sudoku board, ignore zeros
		for (int row = 0; row < SIZE; row++) {
			for (int column = 0; column < SIZE; column++) {
				if (seen.contains(getBoardEntry(row, column))) return false;
				else 
					if (getBoardEntry(row,column) != 0)
						seen.add(getBoardEntry(row, column));
			}
			seen.clear();
		}
		// check each column of the sudoku board, ignore zeros
		for (int column = 0; column < SIZE; column++) {
			for (int row = 0; row < SIZE; row++) {
				if (seen.contains(getBoardEntry(row, column))) return false;
				else 
					if (getBoardEntry(row,column) != 0) 
						seen.add(getBoardEntry(row, column));
			}
			seen.clear();
		}
		// check each big cell of the sudoku board, ignore zeros
		int sqrtsize = (int)Math.floor(Math.sqrt(SIZE));
		for (int i = 0; i < sqrtsize; i++) {
			for (int j = 0; j < sqrtsize; j++) {
				for (int row = 0; row < sqrtsize; row++) {
					for (int column = 0; column < sqrtsize; column++) {
						if (seen.contains(getBoardEntry(row + i * sqrtsize, column + j * sqrtsize))) 
							return false;
						else
							if (getBoardEntry(row + i * sqrtsize, column + j * sqrtsize) != 0)
								seen.add(getBoardEntry(row + i * sqrtsize, column + j * sqrtsize));
					}
				}
				seen.clear();
			}
		}
		return true;
	}
	
	/**
	 * Tries to solve the sudoku board using the set solving strategy.
	 * If no solving strategy is registered, false is returned.
	 * @return			true, if this sudoku board is solvable, false otherwise or if there is no solving strategy registered for this board
	 */
	public boolean solve() {
		if (strat != null) return strat.solve(this,0);
		else return false;
	}
	
	/**
	 * Sets the solving strategy for this sudoku board.
	 * @param strat		an implementation of a solving strategy
	 */
	public void setSolver(SolvingStrategy strat) {
		this.strat = strat;
	}
	
	/**
	 * Returns the solving strategy that is registered for this sudoku board.
	 * @return			the solving strategy registered for this sudoku board.
	 */
	public SolvingStrategy getSolver() {
		return strat;
	}
	
	/**
	 * Adds a PropertyChangeListener to this class
	 * @param l			The PropertyChangeListener that should react to changes of this class
	 */
	public void addPropertyChangeListener(PropertyChangeListener l) {
		changes.addPropertyChangeListener(l);
	}
	
	/**
	 * Removes a PropertyChangeListener from this class
	 * @param l			The PropertyChangeListener that was registered to listen to changes of this class
	 */
	public void removePropertyChangeListener(PropertyChangeListener l) {
		changes.removePropertyChangeListener(l);
	}
	
}
