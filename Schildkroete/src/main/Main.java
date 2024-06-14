package Schildkroete.src.main;
import java.io.IOException;

import Schildkroete.src.data.Hase;
import Schildkroete.src.data.Position;
import Schildkroete.src.data.Schildkroete;
import Schildkroete.src.gui.RaceWindow;

public class Main {
	
	public static void main(String[] args) {
		Hase hase = null;
		Schildkroete schildkroete = null;
		int fieldWidth = 700;
		int spriteSize = 100;
		int fieldHeight = 300;
		
		try {
			hase = new Hase(new Position(0,30), 30, "Schildkroete/src/data/img/bunny.png", spriteSize, 50);
			schildkroete = new Schildkroete(new Position(0,170), 15, "Schildkroete/src/data/img/turtle.png", spriteSize);
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(0);
		}
		new RaceWindow(fieldWidth, fieldHeight, spriteSize, hase, schildkroete);
	}
	
}
