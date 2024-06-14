package  TakeawayGame.src.main;

import  TakeawayGame.src.data.Token;
import  TakeawayGame.src.data.TokenContainer;
import  TakeawayGame.src.gui.TakeawayFrame;

/**
 * A class containing the main method that features a method to initialize the game field.
 * 
 * @author Patrizia Schalk
 * @version 1.0
 */

public class Main {
	
	public static final int numberOfTokens = 21;
	public static final int minTake = 5;
	public static final int maxTake = 6;
	public static final int tokenSize = 40;
	public static final int distanceBetweenTokens = 1;
	
	/**
	 * Initializes the game field by adding Tokens to a container and returning the 
	 * dimensions that the game field should have to fit all the tokens.
	 * 
	 * @return			the dimensions the game field should have to fit all the tokens
	 */
	public static int[] setGameField() {
		int width = 6 * (tokenSize + distanceBetweenTokens);
		int height = 6 * (tokenSize + distanceBetweenTokens);
		
		int currentNumberOfTokens = 0;
		TokenContainer container = TokenContainer.instance();
		container.clear();
		for(int j = 1; j < height/tokenSize; j++) {
			for(int i = 1; i < width/tokenSize; i++) {
				container.linkToken(new Token(i*(tokenSize + distanceBetweenTokens), j*(tokenSize + distanceBetweenTokens), tokenSize));
				currentNumberOfTokens++;
				if(currentNumberOfTokens == numberOfTokens)
					break;
			}
			if(currentNumberOfTokens == numberOfTokens)
				break;
		}
		
		return new int[]{width, height};
	}
	
	public static void main(String[] args) {
		int[] dimensions = setGameField();
		new TakeawayFrame(dimensions[0], dimensions[1], "USER");
	}

}
