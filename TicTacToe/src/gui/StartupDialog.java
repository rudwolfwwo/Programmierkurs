package TicTacToe.src.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

import TicTacToe.src.data.Token;

/**
 * The startup dialog used to ask the user who should make the first move in the game and which token he would prefer - X or O.
 * 
 * @author Patrizia Schalk
 * @version 1.0
 */

@SuppressWarnings("serial")
public class StartupDialog extends JDialog {

	/**
	 * The constructor of this dialog, which greets the player and gives him 
	 * the choice to play as X or O, as well as if he wants to make the first move.
	 * 
	 * @param parent		the TicTacToeFrame that opened this dialog
	 * @param playerName	the name of the player, used to greet him directly
	 */
	public StartupDialog(TicTacToeFrame parent, String playerName) {
		super(parent, "Neues Spiel", true);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		
		String message1 = "Hallo, " + playerName + "!";
		JLabel northLabel = new JLabel(message1);
		add(northLabel, BorderLayout.NORTH);
		
		JPanel centerPanel = new JPanel();
		centerPanel.setLayout(new FlowLayout());
		String message2 = "Möchtest du lieber X oder lieber O sein?";
		JLabel centerLabel = new JLabel(message2);
		centerPanel.add(centerLabel);
		JComboBox<Token> choice = new JComboBox<Token>();
		choice.addItem(Token.X);
		choice.addItem(Token.O);
		centerPanel.add(choice);
		add(centerPanel);
		
		JPanel southPanel = new JPanel();
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new FlowLayout());
		JLabel southLabel = new JLabel("Wer von uns soll beginnen?");
		southPanel.add(southLabel, BorderLayout.CENTER);
		JButton me = new JButton("Ich.");
		me.addActionListener(e -> {parent.setUserMove(true); parent.setUserToken((Token) choice.getSelectedItem()); parent.repaint(); dispose();});
		buttonPanel.add(me);
		JButton you = new JButton("Du.");
		you.addActionListener(e -> {parent.setUserMove(false); parent.setUserToken((Token) choice.getSelectedItem()); parent.repaint(); parent.performComputerMove(); dispose();});
		buttonPanel.add(you);
		southPanel.add(buttonPanel, BorderLayout.SOUTH);
		add(southPanel, BorderLayout.SOUTH);
		
		pack();
		setVisible(true);
	}
	
}
