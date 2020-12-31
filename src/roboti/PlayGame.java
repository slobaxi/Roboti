package roboti;


//Klasa za testiranje

public class PlayGame {
			
	public static void main(String[] args) {
		Map m = new Map("mapa1.txt");
		System.out.println(m.getPlayers()[0].getRow());
		System.out.println(m.getPlayers()[0].getColumn());
		m.moveDown(Player.Color.BLUE);
		System.out.println(m.getPlayers()[0].getRow());
		System.out.println(m.getPlayers()[0].getColumn());
	}

}
