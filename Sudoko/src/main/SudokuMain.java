package main;

import java.awt.Color;

import data.Sudoku;
import gui.SudokuWindow;

public class SudokuMain {
	
	private static Sudoku getBoardWithSize4() {
		Sudoku board = new Sudoku(4);
		board.setBoardEntry(0, 0, 2);
		board.setBoardEntry(1, 1, 1);
		board.setBoardEntry(2, 0, 4);
		board.setBoardEntry(2, 2, 2);
		board.setBoardEntry(3, 2, 3);
		return board;
	}
	
	private static Sudoku getBoardWithSize9() {
		Sudoku board = new Sudoku(9);
		board.setBoardEntry(0, 0, 3);
		board.setBoardEntry(0, 3, 6);
		board.setBoardEntry(0, 7, 9);
		board.setBoardEntry(1, 1, 4);
		board.setBoardEntry(1, 4, 2);
		board.setBoardEntry(1, 7, 5);
		board.setBoardEntry(2, 1, 8);
		board.setBoardEntry(2, 4, 7);
		board.setBoardEntry(2, 6, 1);
		board.setBoardEntry(2, 7, 6);
		board.setBoardEntry(3, 0, 9);
		board.setBoardEntry(3, 3, 3);
		board.setBoardEntry(3, 5, 4);
		board.setBoardEntry(3, 6, 7);
		board.setBoardEntry(4, 1, 5);
		board.setBoardEntry(4, 4, 8);
		board.setBoardEntry(4, 7, 2);
		board.setBoardEntry(5, 2, 1);
		board.setBoardEntry(5, 3, 9);
		board.setBoardEntry(5, 8, 6);
		board.setBoardEntry(6, 1, 2);
		board.setBoardEntry(6, 2, 7);
		board.setBoardEntry(6, 4, 3);
		board.setBoardEntry(6, 7, 4);
		board.setBoardEntry(7, 1, 9);
		board.setBoardEntry(7, 4, 6);
		board.setBoardEntry(7, 7, 1);
		board.setBoardEntry(8, 1, 3);
		board.setBoardEntry(8, 5, 5);
		board.setBoardEntry(8, 8, 8);
		return board;
	}
	
	private static Sudoku getBoardWithSize16() {
		Sudoku board = new Sudoku(16);
		
		board.setBoardEntry(0, 1, 6);
		board.setBoardEntry(0, 7, 8);
		board.setBoardEntry(0, 8, 11);
		board.setBoardEntry(0, 11, 15);
		board.setBoardEntry(0, 12, 14);
		board.setBoardEntry(0, 15, 16);
		
		board.setBoardEntry(1, 0, 15);
		board.setBoardEntry(1, 1, 11);
		board.setBoardEntry(1, 5, 16);
		board.setBoardEntry(1, 6, 14);
		board.setBoardEntry(1, 10, 12);
		board.setBoardEntry(1, 13, 6);
		
		board.setBoardEntry(2, 0, 13);
		board.setBoardEntry(2, 2, 9);
		board.setBoardEntry(2, 3, 12);
		board.setBoardEntry(2, 8, 3);
		board.setBoardEntry(2, 9, 16);
		board.setBoardEntry(2, 10, 14);
		board.setBoardEntry(2, 12, 15);
		board.setBoardEntry(2, 13, 11);
		board.setBoardEntry(2, 14, 10);
		
		board.setBoardEntry(3, 0, 2);
		board.setBoardEntry(3, 2, 16);
		board.setBoardEntry(3, 4, 11);
		board.setBoardEntry(3, 6, 15);
		board.setBoardEntry(3, 7, 10);
		board.setBoardEntry(3, 8, 1);
		
		board.setBoardEntry(4, 1, 15);
		board.setBoardEntry(4, 2, 11);
		board.setBoardEntry(4, 3, 10);
		board.setBoardEntry(4, 6, 16);
		board.setBoardEntry(4, 7, 2);
		board.setBoardEntry(4, 8, 13);
		board.setBoardEntry(4, 9, 8);
		board.setBoardEntry(4, 10, 9);
		board.setBoardEntry(4, 11, 12);
		
		board.setBoardEntry(5, 0, 12);
		board.setBoardEntry(5, 1, 13);
		board.setBoardEntry(5, 4, 4);
		board.setBoardEntry(5, 5, 1);
		board.setBoardEntry(5, 6, 5);
		board.setBoardEntry(5, 7, 6);
		board.setBoardEntry(5, 8, 2);
		board.setBoardEntry(5, 9, 3);
		board.setBoardEntry(5, 14, 11);
		board.setBoardEntry(5, 15, 10);
		
		board.setBoardEntry(6, 0, 5);
		board.setBoardEntry(6, 2, 6);
		board.setBoardEntry(6, 3, 1);
		board.setBoardEntry(6, 4, 12);
		board.setBoardEntry(6, 6, 9);
		board.setBoardEntry(6, 8, 15);
		board.setBoardEntry(6, 9, 11);
		board.setBoardEntry(6, 10, 10);
		board.setBoardEntry(6, 11, 7);
		board.setBoardEntry(6, 12, 16);
		board.setBoardEntry(6, 15, 3);
		
		board.setBoardEntry(7, 1, 2);
		board.setBoardEntry(7, 5, 10);
		board.setBoardEntry(7, 7, 11);
		board.setBoardEntry(7, 8, 6);
		board.setBoardEntry(7, 10, 5);
		board.setBoardEntry(7, 13, 13);
		board.setBoardEntry(7, 15, 9);
		
		board.setBoardEntry(8, 0, 10);
		board.setBoardEntry(8, 1, 7);
		board.setBoardEntry(8, 2, 15);
		board.setBoardEntry(8, 3, 11);
		board.setBoardEntry(8, 4, 16);
		board.setBoardEntry(8, 8, 12);
		board.setBoardEntry(8, 9, 13);
		board.setBoardEntry(8, 15, 6);
		
		board.setBoardEntry(9, 0, 9);
		board.setBoardEntry(9, 6, 1);
		board.setBoardEntry(9, 9, 2);
		board.setBoardEntry(9, 11, 16);
		board.setBoardEntry(9, 12, 10);
		board.setBoardEntry(9, 15, 11);
		
		board.setBoardEntry(10, 0, 1);
		board.setBoardEntry(10, 2, 4);
		board.setBoardEntry(10, 3, 6);
		board.setBoardEntry(10, 4, 9);
		board.setBoardEntry(10, 5, 13);
		board.setBoardEntry(10, 8, 7);
		board.setBoardEntry(10, 10, 11);
		board.setBoardEntry(10, 12, 3);
		board.setBoardEntry(10, 13, 16);
		
		board.setBoardEntry(11, 0, 16);
		board.setBoardEntry(11, 1, 14);
		board.setBoardEntry(11, 4, 7);
		board.setBoardEntry(11, 6, 10);
		board.setBoardEntry(11, 7, 15);
		board.setBoardEntry(11, 8, 4);
		board.setBoardEntry(11, 9, 6);
		board.setBoardEntry(11, 10, 1);
		board.setBoardEntry(11, 14, 13);
		board.setBoardEntry(11, 15, 8);
		
		board.setBoardEntry(12, 0, 11);
		board.setBoardEntry(12, 1, 10);
		board.setBoardEntry(12, 3, 15);
		board.setBoardEntry(12, 7, 16);
		board.setBoardEntry(12, 8, 9);
		board.setBoardEntry(12, 9, 12);
		board.setBoardEntry(12, 10, 13);
		board.setBoardEntry(12, 13, 1);
		board.setBoardEntry(12, 14, 5);
		board.setBoardEntry(12, 15, 4);
		
		board.setBoardEntry(13, 2, 12);
		board.setBoardEntry(13, 4, 1);
		board.setBoardEntry(13, 5, 4);
		board.setBoardEntry(13, 6, 6);
		board.setBoardEntry(13, 8, 16);
		board.setBoardEntry(13, 12, 11);
		board.setBoardEntry(13, 13, 10);
		
		board.setBoardEntry(14, 2, 5);
		board.setBoardEntry(14, 4, 8);
		board.setBoardEntry(14, 5, 12);
		board.setBoardEntry(14, 6, 13);
		board.setBoardEntry(14, 8, 10);
		board.setBoardEntry(14, 11, 11);
		board.setBoardEntry(14, 12, 2);
		board.setBoardEntry(14, 15, 14);
		
		board.setBoardEntry(15, 0, 3);
		board.setBoardEntry(15, 1, 16);
		board.setBoardEntry(15, 4, 10);
		board.setBoardEntry(15, 7, 7);
		board.setBoardEntry(15, 10, 6);
		board.setBoardEntry(15, 14, 12);
		return board;
	}
	
	private static Sudoku getBoard(int size) {
		switch (size) {
			case 4: return getBoardWithSize4();
			case 9: return getBoardWithSize9();
			case 16: return getBoardWithSize16();
			default: return new Sudoku(size);
		}
	}
	
	public static void main(String[] args) {
		if (args.length != 1 || (!args[0].equals("4") && !args[0].equals("9") && !args[0].equals("16"))) {
			System.err.println("Expected one of the following arguments: 4, 9 or 16.");
			return;
		}
		int size = Integer.parseInt(args[0]);
		boolean colored = true;
		
		Sudoku board = getBoard(size);
		
		Color[] colors = new Color[17];
		for (int i = 0; i <= size; i++) {
			colors[i] = Color.WHITE;
		}
		if (colored) {
			colors[1] = new Color(255,255,150);
			colors[2] = new Color(255,220,140);
			colors[3] = new Color(255,160,160); 
			colors[4] = new Color(220,150,220); 
			colors[5] = new Color(160,160,255); 
			colors[6] = new Color(150,220,255); 
			colors[7] = new Color(150,255,255); 
			colors[8] = new Color(160,255,160); 
			colors[9] = new Color(180,220,180); 
			colors[10] = new Color(230,230,150); 
			colors[11] = new Color(255,200,140);
			colors[12] = new Color(230,140,140);
			colors[13] = new Color(200,100,200);
			colors[14] = new Color(150,150,255);
			colors[15] = new Color(100,255,255);
			colors[16] = new Color(150,240,255);
		}
		
		new SudokuWindow(board, size, colors);
	}
}
