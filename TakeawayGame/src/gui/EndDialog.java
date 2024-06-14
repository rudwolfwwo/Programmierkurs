package  TakeawayGame.src.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * A Dialog used when a game is finished and there is a winner or a loser.
 * 
 * @author Patrizia Schalk
 * @version 1.0
 */

@SuppressWarnings("serial")
public class EndDialog extends JDialog {
	
	/**
	 * The constructor for the dialog. Features a prompt indicating whether the 
	 * user has won or lost the last game. With two buttons in this dialog, the 
	 * user can decide whether he wants to play another game (which then issues 
	 * a new startup dialog) or if he wants to quit (which closes the program).
	 * 
	 * @param parent			The parent of this dialog, an instance of the class TakeawayFrame
	 * @param playerName		The name of the user, so he can be mentioned directly
	 * @param userWon			A boolean indicating whether the player has won the last game
	 */
	public EndDialog(TakeawayFrame parent, String playerName, boolean userWon) {
		super(parent, "Spielende!", true);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		String message1 = userWon ? "Du hast gewonnen, " + playerName + "!" : "Leider verloren, " + playerName + ".";
		String message2 = userWon ? "Wie wäre es mit einer Revance?" : "Aber wie wäre es mit einer Revance?";
		
		JLabel label1 = new JLabel(message1);
		add(label1, BorderLayout.NORTH);
		JLabel label2 = new JLabel(message2);
		add(label2, BorderLayout.CENTER);
		
		JPanel southPanel = new JPanel();
		southPanel.setLayout(new FlowLayout());
		JButton ok = new JButton("Okay!");
		ok.addActionListener(e -> {dispose(); new StartupDialog(parent, playerName);});
		southPanel.add(ok);
		JButton abort = new JButton("Nein, danke.");
		abort.addActionListener(e -> System.exit(0));
		southPanel.add(abort);
		add(southPanel, BorderLayout.SOUTH);
		
		pack();
		setVisible(true);
	}
	
}
