package gui;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.text.Position;

import data.*;

@SuppressWarnings("serial")
public class LabyrinthWindow extends JFrame implements ActionListener, PropertyChangeListener {

	private Labyrinth labyrinth;
	private JButton start;
	private JButton solve;
	private ArrayList<Point> draw = new ArrayList<>();

	public LabyrinthWindow(int size) {
		super("Let's create a labyrinth!");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(new BorderLayout());

		labyrinth = new Labyrinth(size);
		labyrinth.addPropertyChangeListener(this);
		labyrinth.getWallContainer().addPropertyChangeListener(this);

		//int cellwidth = 20;
		int cellwidth = 10;
		JPanel canvas = new JPanel() {
			@Override
			public void paintComponent(Graphics g) {
				super.paintComponent(g);
				Graphics2D g2d = (Graphics2D) g;
				int thick = 3;
				// thicken the stroke size
				g2d.setStroke(new BasicStroke(thick));
				// fill background white
				g2d.setColor(Color.WHITE);
				g2d.fill(new Rectangle2D.Float(0, 0, size * cellwidth, size * cellwidth));

/* Die folgenden Zeilen wieder einfügen, sobald Aufgabe 2b) gelöst wurde. */
				/*if (labyrinth.getSolver() instanceof AldousBroder) {
					AldousBroder ABcreator = (AldousBroder) labyrinth.getSolver();
					// (Aldous Broder) color unvisited cells light gray
					g2d.setColor(Color.LIGHT_GRAY);
					g2d.fill(new Rectangle2D.Float(0, 0, size * cellwidth, size * cellwidth));
					// (Aldous Broder) color visited cells white
					g2d.setColor(Color.WHITE);
					for (LabyrinthCell cell : ABcreator.getVisited()) {
						int x = cell.getPosition().x;
						int y = cell.getPosition().y;
						g2d.fill(new Rectangle2D.Float(x * cellwidth, y * cellwidth, cellwidth, cellwidth));
					}*/
					// (Aldous Broder) color the current cell cyan
					if (labyrinth.getCurrent() != null) {
						g2d.setColor(Color.CYAN);
						int x = labyrinth.getCurrent().getPosition().x;
						int y = labyrinth.getCurrent().getPosition().y;
						g2d.fill(new Rectangle2D.Float(x * cellwidth, y * cellwidth, cellwidth, cellwidth));
					}
					if (labyrinth.getPath().size() > 0) {
						g2d.setColor(new Color(63, 114, 222));
						try {
							for (LabyrinthCell cell : labyrinth.getPath()) {
								int x = cell.getPosition().x;
								int y = cell.getPosition().y;
								g2d.fill(new Rectangle2D.Float(x * cellwidth, y * cellwidth, cellwidth, cellwidth));
							}
						} catch (ConcurrentModificationException e) {}
					}
					if (Solver.getStart() != null && Solver.getEnd() != null) {
						g2d.setColor(Color.RED);
						int x = Solver.getStart().getPosition().x;
						int y = Solver.getStart().getPosition().y;
						g2d.fill(new Rectangle2D.Float(x * cellwidth, y * cellwidth, cellwidth, cellwidth));
						g2d.setColor(Color.ORANGE);
						int z = Solver.getEnd().getPosition().x;
						int a = Solver.getEnd().getPosition().y;
						g2d.fill(new Rectangle2D.Float(z * cellwidth, a * cellwidth, cellwidth, cellwidth));
					}

				//}

				// draw the border around the labyrinth in black
				g2d.setColor(Color.BLACK);
				g2d.draw(new Rectangle2D.Float(0, 0, size * cellwidth, size * cellwidth));

				// draw every remaining wall in black
				try {
					labyrinth.getWallContainer().mutex.lock();
					for (LabyrinthWall wall : labyrinth.getWallContainer()) {
						if (wall.getLeftNeighbor() != null && wall.getRightNeighbor() != null) {
							Point pos = wall.getLeftNeighbor().getPosition();
							g2d.draw(new Line2D.Float((pos.x + 1) * cellwidth, pos.y * cellwidth, (pos.x + 1) * cellwidth,
								     (pos.y + 1) * cellwidth));
						}
						if (wall.getTopNeighbor() != null && wall.getBottomNeighbor() != null) {
							Point pos = wall.getTopNeighbor().getPosition();
							g2d.draw(new Line2D.Float(pos.x * cellwidth, (pos.y + 1) * cellwidth, (pos.x + 1) * cellwidth,
								     (pos.y + 1) * cellwidth));
						}
					}
				}
				finally {
					labyrinth.getWallContainer().mutex.unlock();
				}
				for (Point p : draw) {
					g.drawRect((int) p.getX(),(int)p.getY(),1,1);
				}
			}
		};
		canvas.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				draw.add(new Point(e.getX(),e.getY()));
				repaint();
			}
		});
		canvas.setPreferredSize(new Dimension(size * cellwidth, size * cellwidth));
		add(canvas, BorderLayout.NORTH);

		start = new JButton("Start!");
		start.addActionListener(this);

		solve = new JButton("Solve!");
		solve.addActionListener(this);
		solve.setEnabled(false);

		JPanel south = new JPanel(new FlowLayout());
		south.add(start);
		south.add(solve);

		add(south, BorderLayout.SOUTH);

		pack();
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(start)) {
			start.setEnabled(false);
			// Start a new Thread for event handling, since otherwise the repaint-calls
			// are just collected and executed at the end of the event handling
/* Die folgenden Zeilen wieder einfügen, sobald Aufgabe 2b) gelöst wurde. */
			new Thread() {
				public void run() {
					labyrinth.solve();
					repaint();
				}
			}.start();
		} else if (e.getSource().equals(solve)) {
			new Thread() {
				public void run() {
					solve.setEnabled(false);
					Solver s = new Solver(labyrinth);
					repaint();
					if (!s.solveLabyrinth(labyrinth, Solver.getStart()))
						JOptionPane.showMessageDialog(null,"Das gegebene Labyrinth ist nicht lösbar.");
					else
						JOptionPane.showMessageDialog(null,"Das Labyrinth wurde gelöst!");
				}
			}.start();
			repaint();
		}
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		if (evt.getPropertyName().equals("Labyrinth finished")) {
			JOptionPane.showMessageDialog(null,"Das Labyrinth wurde erstellt!");
			solve.setEnabled(true);
		} else if (evt.getPropertyName().equals("LabyrinthWall removed")
				|| evt.getPropertyName().equals("current cell changed")) {
			repaint();
		}
	}

	public Labyrinth getLabyrinth() {
		return this.labyrinth;
	}
}
