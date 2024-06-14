package TicTacToe.src.gui;

import javax.swing.JFrame;

import TicTacToe.src.data.*;

/**
 * The main user interface for a Tic Tac Toe Game.
 * 
 * @author Patrizia Schalk
 * @version 1.0
 */

@SuppressWarnings("serial")
public class TicTacToeFrame extends JFrame {
	
	private static Token userToken = Token.X;
	private TicTacToe field;
	private Robot bot;
	private String playerName;
	private boolean userMove;
	
	/**
	 * Initializes the user interface for a game of Tic Tac Toe. This frame contains 
	 * a game field in form of a JPanel, the user can interact with the game field 
	 * by clicking on the respective cells to leave a X or O token.
	 * 
	 * @param playerName		the name of the player, used to call him by his name in the startup and end menu
	 * @param fieldSize			the size of the game field in pixels. The game field is always a square
	 */
	public TicTacToeFrame(String playerName, int fieldSize) {
		super("Tic Tac Toe");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		this.playerName = playerName;
		field = new TicTacToe();
		// Set the strategy of the computer
		bot = new Robot(new MinimaxStrategy());
		
		GamePanel gameField = new GamePanel(this, field, fieldSize);
		add(gameField);
		
		pack();
		setVisible(true);
		
		// start by asking the player if the player or the bot should start
		new StartupDialog(this, playerName);
	}
	
	/**
	 * Performs a move of the computer player by: 
	 * 1. checking whether the user has already won the game or there is no space on the field left,
	 * 2. using the computer strategy to calculate the position where the computer wants to leave its token,
	 * 3. checking whether the computer has won after its move.
	 */
	public void performComputerMove() {
		if(field.whoHasWon() != Token.NONE || field.getEmptyPositions().size() == 0) {
			// the user has won with his last move or there was a draw.
			new EndDialog(this, playerName, field.whoHasWon(), field);
		} else {
			Position pos = bot.calculateNextMove(field);
			field.putToken(pos, getComputerToken());
			setUserMove(true);
			if(field.whoHasWon() != Token.NONE || field.getEmptyPositions().size() == 0) {
				// the computer has won with its last move or there was a draw.
				new EndDialog(this, playerName, field.whoHasWon(), field);
			}
		}
	}

	/**
	 * Returns the token the user has chosen as his token in the game.
	 * 
	 * @return					the token the user leaves in a cell when clicking
	 */
	public static Token getUserToken() {
		return userToken;
	}

	/**
	 * Sets the token the user has chosen as his token in the game.
	 * 
	 * @param userTok			the token the user wants to leave in a cell when clicking
	 */
	public void setUserToken(Token userTok) {
		userToken = userTok;
	}
	
	/**
	 * Returns the token the computer uses in the game, which is the inverse of the token the user chose.
	 * 
	 * @return					O if getUserToken() returns X, and X otherwise
	 */
	public static Token getComputerToken() {
		return userToken == Token.X ? Token.O : Token.X;
	}

	/**
	 * Returns whether it's the users turn to leave a token in the game field.
	 * 
	 * @return					a boolean indicating whether the program is waiting for user input
	 */
	public boolean isUserMove() {
		return userMove;
	}

	/**
	 * Sets the variable userMove to the specified value.
	 * 
	 * @param userMove			true, if the user is the next one to move, false otherwise
	 */
	public void setUserMove(boolean userMove) {
		this.userMove = userMove;
	}

}
