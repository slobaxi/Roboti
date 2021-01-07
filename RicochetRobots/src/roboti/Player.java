package roboti;

import java.util.Random;

public class Player {
	
	private int row;
	private int column;
	
	public enum Color {
		BLUE,
		YELLOW,
		RED,
		GREEN,
		NEUTRAL;
		
		public static Color getRandomColor() {
	        Random random = new Random();
	        return values()[random.nextInt(values().length)];
	    }
	}
	
	private Color color;

	public Player(int xcord, int ycord, Color color) {
		super();
		this.row = xcord;
		this.column = ycord;
		this.color = color;
	}
		

	public int getRow() {
		return row;
	}

	public void setRow(int xcord) {
		this.row = xcord;
	}

	public int getColumn() {
		return column;
	}

	public void setColumn(int ycord) {
		this.column = ycord;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}
	
}
