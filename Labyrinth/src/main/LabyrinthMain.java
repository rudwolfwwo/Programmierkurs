package main;

import data.AldousBroder;
import data.Kruskal;
import gui.LabyrinthWindow;

public class LabyrinthMain {

	public static void main(String[] args) {
		LabyrinthWindow window = new LabyrinthWindow(110);
		window.getLabyrinth().setSolver(new Kruskal());
	}
	
}
