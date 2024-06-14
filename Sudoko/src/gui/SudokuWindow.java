package gui;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.*;

import data.BacktrackSolver;
import data.BranchAndBoundSolver;
import data.Sudoku;

@SuppressWarnings("serial")
public class SudokuWindow extends JFrame implements ActionListener, PropertyChangeListener{
	
	private JPanel canvas;
	private JButton start;
	private Sudoku board;
	private final int width = 30; // width of a sudoku cell
	private final int height = 30; // height of a sudoku cell
	
	public SudokuWindow(Sudoku b, int size, Color[] colors) {
		super("Sudoku-Solver");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		
		this.board = b;
		// listen to changes on the board, so we can draw them whenever they happen
		board.addPropertyChangeListener(this);
		
		canvas = new JPanel() {
			@Override
			public void paintComponent(Graphics g) {
				super.paintComponent(g);
				Graphics2D g2d = (Graphics2D) g;
				int normal = 1;
				int thick = 3;
				float offset = 3;
				g2d.setFont(new Font("TimesRoman", Font.PLAIN, 18));
				
				for (int i = 0; i < size; i++) {
					for (int j = 0; j < size; j++) {
						// draw the respective color of the board entry
						g2d.setColor(colors[board.getBoardEntry(i, j)]);
						g2d.fill(new Rectangle2D.Float(j * width, i * height, width, height));
						// only put a number into a cell if it is not 0
						if (board.getBoardEntry(i, j) != 0) {
							// draw the board entry
							g2d.setColor(Color.black);
							float x = (float) (j + 0.5) * width;
							float y = (float) (i + 0.5) * height;
							int entry = board.getBoardEntry(i, j);
							// offset is used to center the numbers
							if(entry <= 9) g2d.drawString("" + entry, x - offset, y + 2 * offset);
							else g2d.drawString("" + entry, x - (float) 2.5 * offset, y + 2 * offset);
						}
					}
				}
				// draw the sudoku grid
				g2d.setColor(Color.black);
				for (int i = 0; i <= size; i++) {
					if (i % (int) Math.floor(Math.sqrt(size)) == 0) g2d.setStroke(new BasicStroke(thick));
					else g2d.setStroke(new BasicStroke(normal));
					g2d.draw(new Line2D.Float(i * width, 0, i * width, size * height));
					g2d.draw(new Line2D.Float(0, i * height, size * width, i * height));
				}
			}
		};
		canvas.setPreferredSize(new Dimension(size * width, size * height));
		add(canvas,BorderLayout.NORTH);
		
		start = new JButton("Start!");
		start.addActionListener(this);
		add(start,BorderLayout.SOUTH);
		
		pack();
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(start)) {
			start.setEnabled(false);
			// Start a new Thread for event handling, since otherwise the repaint-calls
			// are just collected and executed at the end of the event handling
/* Die folgenden Zeilen wieder einfügen, sobald Aufgabe 1d) gelöst wurde: */
			new Thread() {
				public void run() {
					board.setSolver(new BranchAndBoundSolver());
					if (!board.solve())
						JOptionPane.showMessageDialog(null,"Das gegebene Sudokufeld ist nicht lösbar.");
					else
						JOptionPane.showMessageDialog(null,"Das Sudoku wurde gelöst!");
				}
			}.start();
		}
	}
	
	@Override
	public void propertyChange(PropertyChangeEvent evt) {
			canvas.repaint();
	}

}
