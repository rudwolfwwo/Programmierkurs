package TicTacToe.src.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

import TicTacToe.src.data.TicTacToe;
import TicTacToe.src.data.Token;

/**
 * A Dialog used when a game is finished and there is a winner, a loser or a draw.
 * 
 * @author Patrizia Schalk
 * @version 1.0
 */

@SuppressWarnings("serial")
public class EndDialog extends JDialog {
	
	/**
	 * The constructor for this dialog. Displays a message indicating whether the user, the 
	 * computer, or nobody has won the last game and gives the player the option to start 
	 * another game or to quit the program entirely.
	 * 
	 * @param parent		the TicTacToeFrame that opened this dialog
	 * @param playerName	the name of the player, used to call him directly by his name
	 * @param winner		the token used by the winner of the last game, or NONE if the last game ended in a draw
	 * @param field			the TicTacToe game, used to clear all Xs and Os for a new game
	 */
	public EndDialog(TicTacToeFrame parent, String playerName, Token winner, TicTacToe field) {
		super(parent, "Spielende!", true);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		String message1;
		String message2;
		if(winner.equals(TicTacToeFrame.getUserToken())) {
			message1 = "Klasse, du hast gewonnen, " + playerName + "!";
			message2 = "Möchtest du eine Revance?";
		} else if(winner.equals(TicTacToeFrame.getComputerToken())) {
			message1 = "Leider verloren, " + playerName + ".";
			message2 = "Aber wie wäre es mit einer Revance?";
		} else {
			message1 = "Unentschieden! Da haben wir uns aber nichts geschenkt!";
			message2 = "Sollen wir noch einmal spielen?";
		}
		
		JLabel label1 = new JLabel(message1);
		add(label1, BorderLayout.NORTH);
		JLabel label2 = new JLabel(message2);
		add(label2, BorderLayout.CENTER);
		
		JPanel southPanel = new JPanel();
		southPanel.setLayout(new FlowLayout());
		JButton ok = new JButton("Okay!");
		ok.addActionListener(e -> {dispose(); field.resetField(); parent.repaint(); new StartupDialog(parent, playerName);});
		southPanel.add(ok);
		JButton abort = new JButton("Nein, danke.");
		abort.addActionListener(e -> System.exit(0));
		southPanel.add(abort);
		add(southPanel, BorderLayout.SOUTH);
		
		pack();
		setVisible(true);
	}

}
