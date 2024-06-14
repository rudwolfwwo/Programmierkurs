package Schildkroete.src.gui;

import java.awt.Graphics;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JPanel;

import Schildkroete.src.data.Hase;
import Schildkroete.src.data.Schildkroete;

@SuppressWarnings("serial")
public class RacePanel extends JPanel implements PropertyChangeListener {
	
	private int sizeX;
	private int sizeY;
	private int spriteSize;
	private Hase hase;
	private Schildkroete schildkroete;
	
	public RacePanel(int sizeX, int sizeY, int spriteSize, Hase hase, Schildkroete schildkroete) {
		this.sizeX = sizeX;
		this.sizeY = sizeY;
		this.spriteSize = spriteSize;
		this.hase = hase;
		this.hase.addPropertyChangeListener(this);
		this.schildkroete = schildkroete;
		this.schildkroete.addPropertyChangeListener(this);
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.fillRect(spriteSize, 25, 3, sizeY - 50);
		g.fillRect(sizeX - spriteSize, 25, 3, sizeY - 50);
		hase.draw(g);
		schildkroete.draw(g);
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		repaint();
	}
	
}
