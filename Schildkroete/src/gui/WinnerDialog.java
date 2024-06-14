package Schildkroete.src.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class WinnerDialog extends JDialog {

	public WinnerDialog(JFrame owner, ActionListener listener, String winner) {
		super(owner, "Das Rennen ist vorbei!");
		JLabel message = new JLabel(winner);
		add(message, BorderLayout.NORTH);
		JLabel again = new JLabel("\nSoll ein neues Rennen gestartet werden?");
		add(again, BorderLayout.CENTER);
		JPanel buttons = new JPanel();
		buttons.setLayout(new FlowLayout());
		JButton yes = new JButton("Ja!");
		yes.addActionListener(listener);
		buttons.add(yes);
		JButton no = new JButton("Nein.");
		no.addActionListener(listener);
		buttons.add(no);
		add(buttons, BorderLayout.SOUTH);
		
		pack();
		setVisible(true);
	}
	
}
