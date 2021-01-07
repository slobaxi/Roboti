package roboti;

import java.util.Random;

import roboti.Player.Color;

public class Field {
	
	private int row;
	private int column;
	
	private boolean upwall,downwall,leftwall,rightwall;
	private Status status;
	
	public Field(boolean upwall, boolean downwall, boolean leftwall, boolean rightwall, Status status) {
		super();
		this.upwall = upwall;
		this.downwall = downwall;
		this.leftwall = leftwall;
		this.rightwall = rightwall;
		this.status = status;
	}
	
	public Field(int xcord, int ycord) {
		super();
		this.row = xcord;
		this.column = ycord;
	}

	public enum Status {
		EMPTY,
		RED1,RED2,RED3,RED4,
		GREEN1,GREEN2,GREEN3,GREEN4,
		YELLOW1,YELLOW2,YELLOW3,YELLOW4,
		BLUE1,BLUE2,BLUE3,BLUE4,
		RAINBOW;
		
		@Override
		public String toString() {
			switch(this) {
			
			  case EMPTY: return "EMPTY";
			  
		      case RED1: return "RED1";
		      case RED2: return "RED2";
		      case RED3: return "RED3";
		      case RED4: return "RED4";
		      
		      case GREEN1: return "GREEN1";
		      case GREEN2: return "GREEN2";
		      case GREEN3: return "GREEN3";
		      case GREEN4: return "GREEN4";
		      
		      case YELLOW1: return "YELLOW1";
		      case YELLOW2: return "YELLOW2";
		      case YELLOW3: return "YELLOW3";
		      case YELLOW4: return "YELLOW4";
		      
		      case BLUE1: return "BLUE1";
		      case BLUE2: return "BLUE2";
		      case BLUE3: return "BLUE3";
		      case BLUE4: return "BLUE4";
		      case RAINBOW: return "RAINBOW";
		      
		      default: throw new IllegalArgumentException();
		    }
		}
		public static Status getRandomStatus() {
	        Random random = new Random();
	        return values()[random.nextInt(values().length)];
	    }
	}
	
	
	
	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return upwall+","+downwall+","+rightwall+","+leftwall+","+status;
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

	public boolean isUpwall() {
		return upwall;
	}

	public void setUpwall(boolean upwall) {
		this.upwall = upwall;
	}

	public boolean isDownwall() {
		return downwall;
	}

	public void setDownwall(boolean downwall) {
		this.downwall = downwall;
	}

	public boolean isLeftwall() {
		return leftwall;
	}

	public void setLeftwall(boolean leftwall) {
		this.leftwall = leftwall;
	}

	public boolean isRightwall() {
		return rightwall;
	}

	public void setRightwall(boolean rightwall) {
		this.rightwall = rightwall;
	}
	
}

