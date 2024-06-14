package  TakeawayGame.src.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

import  TakeawayGame.src.main.Main;

/**
 * The startup dialog used to ask the user who should make the first move in the game.
 * 
 * @author Patrizia Schalk
 * @version 1.0
 */

@SuppressWarnings("serial")
public class StartupDialog extends JDialog {
	
	/**
	 * The constructor for the starting dialog. Refreshes the game field of the main frame 
	 * and asks the user who should take the first turn. This dialog is closed when the 
	 * user has made a choise using the buttons, but does not close when using the X button.
	 * 
	 * @param parent			The parent of this dialog, an instance of the class TakeAwayFrame
	 * @param playerName		The name of the player that plays the game, so he can be mentioned 
	 * 							directly when asking for a decision.
	 */
	public StartupDialog(TakeawayFrame parent, String playerName) {
		super(parent, "Neues Spiel", true);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		
		Main.setGameField();
		JLabel label = new JLabel("So, " + playerName +". Wer von uns soll beginnen?");
		add(label, BorderLayout.CENTER);
		JPanel southPanel = new JPanel();
		southPanel.setLayout(new FlowLayout());
		JButton me = new JButton("Ich.");
		me.addActionListener(e -> {parent.setUserMove(true); parent.repaint(); dispose();});
		southPanel.add(me);
		JButton you = new JButton("Du.");
		you.addActionListener(e -> {parent.setUserMove(false); parent.repaint(); parent.performBotMove(); dispose();});
		southPanel.add(you);
		add(southPanel, BorderLayout.SOUTH);
		
		pack();
		setVisible(true);
	}

}
