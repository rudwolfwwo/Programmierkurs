package TakeawayGame.src.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import  TakeawayGame.src.data.RandomStrategy;
import  TakeawayGame.src.data.Robot;
import  TakeawayGame.src.data.TokenContainer;
import  TakeawayGame.src.data.WinningStrategy;
import  TakeawayGame.src.main.Main;

/**
 * The main frame for a Takeaway Game, which is also used for event handling in the frame.
 * 
 * @author Patrizia Schalk
 * @version 1.0
 */

@SuppressWarnings("serial")
public class TakeawayFrame extends JFrame implements ActionListener {
	
	private ArrayList<JButton> buttons;
	private Robot bot;
	private String playerName;
	private boolean userMove;

	/**
	 * The constructor for the main frame the user interacts with.
	 * This frame features a game board in form of a JPanel, as well as three buttons.
	 * 
	 * @param width			The width of the game field featured in this frame
	 * @param height		The height of the game field featured in this frame
	 * @param playerName	The name of the player playing the game, used for command line outputs and info-boxes.
	 */
	public TakeawayFrame(int width, int height, String playerName) {
		super("Ein Wegnehm-Spiel");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		buttons = new ArrayList<JButton>();
		
		this.playerName = playerName;
		// Set the strategy of the computer
		bot = new Robot(new WinningStrategy(Main.minTake, Main.maxTake));
		
		GamePanel gameField = new GamePanel(width, height);
		add(gameField, BorderLayout.CENTER);
		
		JPanel southPanel = new JPanel();
		southPanel.setLayout(new FlowLayout());
		for(int i = Main.minTake; i <= Main.maxTake; i++) {
			JButton b = new JButton(""+i);
			buttons.add(b);
			b.addActionListener(this);
			southPanel.add(b);
		}
		add(southPanel, BorderLayout.SOUTH);
		
		pack();
		setVisible(true);
		
		// start by asking the player if the player or the bot should start
		new StartupDialog(this, playerName);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// Start a new Thread for each event, since event handling 
		// just collects all repaint-calls and executes them after 
		// event handling, so Thread.sleep does not block the 
		// repaint calls for user input anymore.
		new Thread(() -> performMove(Integer.parseInt(e.getActionCommand()))).start();
	}
	
	/**
	 * Performs a user move by removing the specified number of tokens from the game field.
	 * After that, the computer performs its move after waiting for one second.
	 * 
	 * @param number		The number of tokens the player wants to remove
	 */
	private void performMove(int number) {
		boolean won = removeTokens(number);
		System.out.println(playerName + ": Remove " + number + " tokens.");
		if(!won) {
			setUserMove(false);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {}
			int botMove = bot.calculateNextMove();
			removeTokens(botMove);
			System.out.println("BOT: Remove " + botMove + " tokens.");
			setUserMove(true);
		}
	}
	
	/**
	 * Performs a single move by the computer. This method is only used if the computer should make the first move.
	 */
	public void performBotMove() {
		int botMove = bot.calculateNextMove();
		removeTokens(botMove);
		System.out.println("BOT: Remove " + botMove + " tokens.");
		setUserMove(true);
	}
	
	/**
	 * Removes the specified number of tokens from the gamefield and returns whether it was possible to remove at least one token.
	 * The return value is used to deter the computer from trying to perform a move when there are no tokens left.
	 * 
	 * @param number		The number of tokens that should be removed from the game field
	 * @return				A boolean indicating whether it was possible to remove at least one token
	 */
	private boolean removeTokens(int number) {
		TokenContainer container = TokenContainer.instance();
		for(int i = 0; i < number; i++)
			container.unlinkAnyToken();
		repaint();
		if(container.getSize() < Main.minTake) {
			System.out.println("END: " + (userMove ? playerName : "BOT") + " wins.");
			new EndDialog(this, playerName, userMove);
			return true;
		}
		return false;
	}
	
	/**
	 * Disables all buttons in the main frame, so they cannot be clicked by the user anymore.
	 */
	public void disableAllButtons() {
		for(JButton b : buttons) {
			b.setEnabled(false);
		}
	}
	
	/**
	 * Enables all buttons in the main frame, so the user can click on them.
	 */
	public void enableAllButtons() {
		for(JButton b : buttons) {
			b.setEnabled(true);
		}
	}

	/**
	 * Returns whether it's the user's turn to remove tokens or not.
	 * 
	 * @return				A boolean indicating whether the program is waiting for user input
	 */
	public boolean isUserMove() {
		return userMove;
	}

	/**
	 * Sets attribute userMove to the specified value. If this value is true, this method automatically 
	 * enables all buttons, so the user can interact with them. If it is false, this method disables all 
	 * buttons, so the user can't click on them anymore.
	 * 
	 * @param userMove		The value that the attribute userMove should be set to
	 */
	public void setUserMove(boolean userMove) {
		this.userMove = userMove;
		if(userMove) {
			enableAllButtons();
		} else {
			disableAllButtons();
		}
	}
	
}
