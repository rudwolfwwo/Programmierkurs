package TicTacToe.src.data;

import java.util.ArrayList;

import TicTacToe.src.main.Main;

/**
 * A class that manages a field to play Tic Tac Toe on.
 * 
 * @author Patrizia Schalk
 * @version 1.0
 */

public class TicTacToe {
	
	private Token[][] field;
	
	/**
	 * The constructor of this class, which creates a new array
	 * as the game field and fills it with NONE-Tokens indicating 
	 * that there are no Tokens in the respective cell.
	 */
	public TicTacToe() {
		resetField();
	}
	
	/**
	 * Returns the token that is stored at the specified position
	 * 
	 * @param p			a position on the game field
	 * @return			the token that is stored at position p
	 */
	public Token getTokenAt(Position p) {
		return field[p.getX()][p.getY()];
	}
	
	/**
	 * Puts the specified token on the specified position on the game field.
	 * 
	 * @param p			a position on the game field
	 * @param token		the token that should be stored at position p
	 * @return			a boolean indicating whether the position was free
	 */
	public boolean putToken(Position p, Token token) {
		if(field[p.getX()][p.getY()].equals(Token.NONE)) {
			field[p.getX()][p.getY()] = token;
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * Resets the game field by dropping the old array and 
	 * constructing a new one, which is initialized with NONE-Tokens.
	 */
	public void resetField() {
		this.field = new Token[Main.gameSize][Main.gameSize];
		for(int i = 0; i < Main.gameSize; i++) {
			for(int j = 0; j < Main.gameSize; j++) {
				this.field[i][j] = Token.NONE;
			}
		}
	}
	
	/**
	 * Returns all positions on the game field where there is no player-token stored yet.
	 * 
	 * @return			an ArrayList containing all positions on the game field that do not contain a X- or O-Token.
	 */
	public ArrayList<Position> getEmptyPositions() {
		ArrayList<Position> choices = new ArrayList<Position>();
		for(int i = 0; i < Main.gameSize; i++) {
			for(int j = 0; j < Main.gameSize; j++) {
				if(getTokenAt(new Position(i, j)) == Token.NONE) choices.add(new Position(i,j));
			}
		}
		return choices;
	}
	
	/**
	 * Returns whether there is no player-token on the game field yet.
	 * 
	 * @return			true if there is no X- or O-Token on the game field, false otherwise
	 */
	public boolean isEmpty() {
		for(int i = 0; i < Main.gameSize; i++) {
			for(int j = 0; j < Main.gameSize; j++) {
				if(getTokenAt(new Position(i,j)) != Token.NONE) return false;
			}
		}
		return true;
	}
	
	/**
	 * Returns a copy of this field in form of a TicTacToe-Object.
	 * This is used to put tokens in cells without changing the actual game field.
	 * 
	 * @return			a TicTacToe Object containing the exact same Tokens as this game field does
	 */
	public TicTacToe copy() {
		TicTacToe copy = new TicTacToe();
		for(int i = 0; i < Main.gameSize; i++) {
			for(int j = 0; j < Main.gameSize; j++) {
				Position currentPos = new Position(i,j);
				copy.putToken(currentPos, this.getTokenAt(currentPos));
			}
		}
		return copy;
	}
	public Token whoHasWon(){
		//vertikal
		for (int i = 0; i < Main.gameSize; i++) {
			for (int j = 0; j < Main.gameSize; j++) {
				if (!(getTokenAt(new Position(i,j)) == Token.X)) break;
				else if (j == Main.gameSize - 1) {
					System.out.println("1");
					return Token.X;

				}
			}
		}
		//horizontal
		for (int i = 0; i < Main.gameSize; i++) {
			for (int j = 0; j < Main.gameSize; j++) {
				if (!(getTokenAt(new Position(j,i)) == Token.X)) break;
				else if (j == Main.gameSize - 1) {
					System.out.println("2");
					return Token.X;
				}
			}
		}
		//diagonal
		for (int i = 0; i < Main.gameSize; i++) {
			if (!(getTokenAt(new Position(i,i)) == Token.X)) break;
			else if (i == Main.gameSize - 1) {
				System.out.println("3");
				return Token.X;
			}
		}
		for (int i = Main.gameSize -1; i >= 0; i--) {
			int j = Main.gameSize - 1 - i;
			if (!(getTokenAt(new Position(j, i)) == Token.X)) break;
			else if (j == Main.gameSize -1) {
				System.out.println("4");
				return Token.X;
			}
		}

		//vertikal
		for (int i = 0; i < Main.gameSize; i++) {
			for (int j = 0; j < Main.gameSize; j++) {
				if (!(getTokenAt(new Position(i,j)) == Token.O)) break;
				else if (j == Main.gameSize - 1) return Token.O;
			}
		}
		//horizontal
		for (int i = 0; i < Main.gameSize; i++) {
			for (int j = 0; j < Main.gameSize; j++) {
				if (!(getTokenAt(new Position(j,i)) == Token.O)) break;
				else if (j == Main.gameSize - 1) return Token.O;
			}
		}
		//diagonal
		for (int i = 0; i < Main.gameSize; i++) {
			if (!(getTokenAt(new Position(i,i)) == Token.O)) break;
			else if (i == Main.gameSize - 1) return Token.O;
		}
		for (int i = Main.gameSize -1; i >= 0; i--) {
			int j = Main.gameSize - 1 - i;
			if (!(getTokenAt(new Position(j, i)) == Token.O)) break;
			else if (j == Main.gameSize -1) {
				System.out.println("4");
				return Token.O;
			}
		}
		return Token.NONE;
	}
}
