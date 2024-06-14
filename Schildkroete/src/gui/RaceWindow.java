package Schildkroete.src.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.concurrent.atomic.AtomicBoolean;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import Schildkroete.src.data.Hase;
import Schildkroete.src.data.Schildkroete;

@SuppressWarnings("serial")
public class RaceWindow extends JFrame implements ActionListener, PropertyChangeListener {
	
	private JButton start;
	private int sizeX;
	private Hase hase;
	private Schildkroete schildkroete;
	private AtomicBoolean raceInProgress = new AtomicBoolean(false);
	private WinnerDialog winnerDialog;
	
	public RaceWindow(int sizeX, int sizeY, int spriteSize, Hase hase, Schildkroete schildkroete) {
		super("Hase vs. Schildkröte");
		this.sizeX = sizeX;
		this.hase = hase;
		this.hase.addPropertyChangeListener(this);
		this.schildkroete = schildkroete;
		this.schildkroete.addPropertyChangeListener(this);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		RacePanel racePanel = new RacePanel(sizeX, sizeY, spriteSize, hase, schildkroete);
		racePanel.setPreferredSize(new Dimension(sizeX, sizeY));
		add(racePanel, BorderLayout.CENTER);
		JPanel buttonPanel = new JPanel();
		start = new JButton("Los!");
		start.addActionListener(this);
		buttonPanel.add(start);
		add(buttonPanel, BorderLayout.SOUTH);
		
		pack();
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == start) {
			System.out.println("Das Rennen geht los!");
			start.setEnabled(false);
			raceInProgress.set(true);
			hase.start();
			schildkroete.start();
		}
		else if(e.getActionCommand().equals("Ja!")) {
			hase.reset();
			schildkroete.reset();
			repaint();
			start.setEnabled(true);
			winnerDialog.dispose();
		}
		else if(e.getActionCommand().equals("Nein.")) {
			winnerDialog.dispose();
			dispose();
			System.exit(0);
		}
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		if((evt.getPropertyName().equals("hase_position") &&
				hase.isBehindXPosition(sizeX-(2*hase.getSpriteSize()))) ||
				(evt.getPropertyName().equals("schildkroete_position") &&
						schildkroete.isBehindXPosition(sizeX-(2*schildkroete.getSpriteSize())))) {
			if(raceInProgress.getAndSet(false)) {
				hase.stop();
				schildkroete.stop();
				String winner = "";
				if(hase.getCurrentXPosition() > schildkroete.getCurrentXPosition()) {
					winner = "Der Hase hat gewonnen!";
				}
				else if(hase.getCurrentXPosition() < schildkroete.getCurrentXPosition()) {
					winner = "Die Schildkröte hat gewonnen!";
				}
				else {
					winner = "Das war ein Unentschieden!";
				}
					System.out.println(winner);
					winnerDialog = new WinnerDialog(this, this, winner);
			}
		}
	}
	
}
