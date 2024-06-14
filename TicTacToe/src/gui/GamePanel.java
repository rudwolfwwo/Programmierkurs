package TicTacToe.src.gui;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;

import TicTacToe.src.data.Position;
import TicTacToe.src.data.TicTacToe;
import TicTacToe.src.data.Token;

/**
 * A subclass of JPanel used to draw tokens on a game field.
 * 
 * @author Patrizia Schalk
 * @version 1.0
 */

@SuppressWarnings("serial")
public class GamePanel extends JPanel {
	
	private TicTacToe field;
	private final int totalSize;
	
	/**
	 * The constructor of the game field. Sets the size of the game field to the specified value
	 * and sets the background color to white. This game field listens for mouse clicked events 
	 * and puts the token of the player on the clicked cell if it is not already occupied.
	 * 
	 * @param parent		the TicTacToeFrame containing this game field
	 * @param field			the TicTacToe game including the Xs and Os that are already on the field
	 * @param totalSize		the width and height of the game field
	 */
	public GamePanel(TicTacToeFrame parent, TicTacToe field, int totalSize) {
		super();
		this.field = field;
		this.totalSize = totalSize;
		setPreferredSize(new Dimension(totalSize, totalSize));
		setBackground(Color.WHITE);
		this.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(parent.isUserMove()) {
					int cellSize = totalSize/3;
					boolean success = field.putToken(new Position(e.getX()/cellSize, e.getY()/cellSize), TicTacToeFrame.getUserToken());
					if(success) {
						repaint();
						parent.setUserMove(false);
						parent.performComputerMove();
					}
				}
			}
		});
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		g2.setStroke(new BasicStroke(3));
		g2.setColor(Color.BLACK);
		g2.drawLine(totalSize/3, 0, totalSize/3, totalSize);
		g2.drawLine(2*totalSize/3, 0, 2*totalSize/3, totalSize);
		g2.drawLine(0, totalSize/3, totalSize, totalSize/3);
		g2.drawLine(0, 2*totalSize/3, totalSize, 2*totalSize/3);
		int size = 50;
		g2.setFont(new Font("XOXO", Font.BOLD, size));
		g2.setColor(Color.BLUE);
		for(int i = 0; i < 3; i++) {
			for(int j = 0; j < 3; j++) {
				Token currentToken = field.getTokenAt(new Position(i, j));
				if(currentToken != Token.NONE) {
					g2.drawString(""+currentToken, i*(totalSize/3)+(totalSize/6)-(2*size/5), j*(totalSize/3)+(totalSize/6)+(2*size/5));
				}
			}
		}
	}

}
