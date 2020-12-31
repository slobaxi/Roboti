package roboti;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

import roboti.Field.Status;
import roboti.Player.Color;



public class Map {
	
	private String mapName;
	private Player[] players;		
	private Field[][] fieldMatrix= new Field[16][16];
			
	
	// Konstruktor koji prvai neophodne delove mape zidove okolo i zid na sredini
	// Stavlja igrace na pocetne pozicije (Ovo cu mozda promeniti kasnije)
	
	public Map() {
				
		players = new Player[4];
		players[0] = new Player(6,7,Color.BLUE);
		players[1] = new Player(9,8,Color.RED);
		players[2] = new Player(7,9,Color.YELLOW);
		players[3] = new Player(8,6,Color.GREEN);
		
		for(int i=0;i<16;i++) {
			for (int j=0;j<16;j++) {								
				fieldMatrix[i][j]= new Field(false,false,false,false,Status.EMPTY);
				fieldMatrix[i][j].setRow(i);
				fieldMatrix[i][j].setColumn(j);
			}
		}
		
		for(int i=0;i<16;i++) {
			for (int j=0;j<16;j++) {
				if (i==0) {
					fieldMatrix[i][j].setDownwall(true);				
				}
				if(j==0) {
					fieldMatrix[i][j].setLeftwall(true);
				}
				if(i==15) {
					fieldMatrix[i][j].setUpwall(true);					
				}
				if(j==15) {
					fieldMatrix[i][j].setRightwall(true);					
				}
			}
		}		
		fieldMatrix[7][7].setUpwall(false);
		fieldMatrix[7][7].setDownwall(true);
		fieldMatrix[7][7].setLeftwall(true);
		fieldMatrix[7][7].setRightwall(false);		
		fieldMatrix[7][8].setUpwall(false);
		fieldMatrix[7][8].setDownwall(true);
		fieldMatrix[7][8].setLeftwall(false);
		fieldMatrix[7][8].setRightwall(true);		
		fieldMatrix[8][7].setUpwall(true);
		fieldMatrix[8][7].setDownwall(false);
		fieldMatrix[8][7].setLeftwall(true);
		fieldMatrix[8][7].setRightwall(false);
		fieldMatrix[8][8].setUpwall(true);
		fieldMatrix[8][8].setDownwall(false);
		fieldMatrix[8][8].setLeftwall(false);
		fieldMatrix[8][8].setRightwall(true);
	}
	
	
	// Konstuktor koji ucitava mapa iz fajla, za sada cita samo zidove polja kasnije cu dodati da cita i status
	public Map(String fileName) {
		this();
		try {			
			
			BufferedReader br = new BufferedReader(new FileReader(fileName));
			String s = br.readLine();
			mapName=s;
			String line=br.readLine();
			for(int i=0;i<16;i++) {
				for (int j=0;j<16;j++) {					
					String[] split=line.trim().split(",");
					
					if(split[0].equals("false")) {
						fieldMatrix[i][j].setUpwall(false);						
					}
					else 
						fieldMatrix[i][j].setUpwall(true);
					
					if(split[1].equals("false")) {
						fieldMatrix[i][j].setDownwall(false);						
					}
					else 
						fieldMatrix[i][j].setDownwall(true);
					
					if(split[2].equals("false")) {
						fieldMatrix[i][j].setRightwall(false);						
					}
					else 
						fieldMatrix[i][j].setRightwall(true);
					
					if(split[3].equals("false")) {
						fieldMatrix[i][j].setLeftwall(false);						
					}
					else 
						fieldMatrix[i][j].setLeftwall(true);
					
					line=br.readLine();
				}				
			}	
			br.close();
		}
		catch (IOException e) {
			e.printStackTrace();			
		}		
	}
	
	// Metoda koja cuva mapu u fajl, sacuva mape koje su rucno napravljene
	
	public void SaveMap(String name) {								
		try {
			BufferedWriter bf = new BufferedWriter(new FileWriter(name+".txt"));
			bf.write(name);
			for(int i=0;i<16;i++) {
				for (int j=0;j<16;j++) {
					String s = fieldMatrix[i][j].toString();
					bf.newLine();
					bf.write(s);
				}
			}
			bf.close();
		} catch (IOException e) {
			System.out.println("An error occurred.");
		    e.printStackTrace();
		}				
	}
	
	
	// Pomeranje igraca napred nazat levo ili desno, sve dok ne naleti na zid (za sada prolazi kroz druge igrace)
	
	public void moveUp(Player.Color c) {
		for(int i=0;i<players.length;i++) {
			if(players[i].getColor()==c) {
				int row=players[i].getRow();
				int column=players[i].getColumn();
				while(fieldMatrix[row][column].isUpwall()==false) {	
					row++;
				}
				players[i].setRow(row);
			}
		}
	}
			
	public void moveDown(Player.Color c) {
		for(int i=0;i<players.length;i++) {
			if(players[i].getColor()==c) {
				int row=players[i].getRow();
				int column=players[i].getColumn();
				while(fieldMatrix[row][column].isDownwall()==false) {
					row--;
				}
				players[i].setRow(row);
			}
		}
	}
	
	public void moveLeft(Player.Color c) {
		for(int i=0;i<players.length;i++) {
			if(players[i].getColor()==c) {
				int row=players[i].getRow();
				int column=players[i].getColumn();
				while(fieldMatrix[row][column].isLeftwall()==false) {
					column--;
				}
				players[i].setColumn(column);
			}
		}
	}
	
	public void moveRight(Player.Color c) {
		for(int i=0;i<players.length;i++) {
			if(players[i].getColor()==c) {
				int column=players[i].getRow();
				int row=players[i].getColumn();
				while(fieldMatrix[column][row].isRightwall()==false) {
					row++;
				}
				players[i].setColumn(row);
			}
		}
	}
	
	
	// Metoda koja na svako polje dodaje zidove koji fale, 
	// (npr ako je zid desno na [i][j], doda automacki zid levo na [i][j+1])
	
	private void setWalls() {
		for(int i=0;i<16;i++) {
			for(int j=0;j<16;j++) {
				
				if(fieldMatrix[i][j].isDownwall()) {
					if(i!=0) {
						fieldMatrix[i-1][j].setUpwall(true);
					}					
				}
				
				if(fieldMatrix[i][j].isLeftwall()) {
					if(j!=0) {
						fieldMatrix[i][j-1].setRightwall(true);
					}
				}
				if(fieldMatrix[i][j].isRightwall()) {
					if(j!=15) {
						fieldMatrix[i][j+1].setLeftwall(true);
					}
					
				}
				if(fieldMatrix[i][j].isUpwall()) {
					if(i!=15) {
						fieldMatrix[i+1][j].setDownwall(true);
					}					
				}								
			}				
		}
	}	
	
	
	public String getMapName() {
		return mapName;
	}

	public void setMapName(String mapName) {
		this.mapName = mapName;
	}

	public Player[] getPlayers() {
		return players;
	}

	public void setPlayers(Player[] players) {
		this.players = players;
	}
	
	public Field[][] getFieldMatrix() {
		return fieldMatrix;
	}

	public void setFieldMatrix(Field[][] fieldMatrix) {
		this.fieldMatrix = fieldMatrix;
	}				
}
