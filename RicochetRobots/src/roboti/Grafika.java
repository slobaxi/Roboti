package roboti;




import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import mars.drawingx.application.DrawingApplication;
import mars.drawingx.drawing.Drawing;
import mars.drawingx.drawing.DrawingUtils;
import mars.drawingx.drawing.View;
import mars.drawingx.gadgets.annotations.GadgetAnimation;
import mars.geometry.Transformation;
import mars.geometry.Vector;
import roboti.Field.Status;
import mars.input.InputEvent;
import mars.input.InputState;

public class Grafika implements Drawing {
	
	private Vector scale=new Vector(50.0);
	private double scaleDouble = 50.0;
	private Map map = new Map("Mapa1.04.txt");
	private Player.Color selectedPlayer=Player.Color.RED; 
	private Task task = new Task();
	
	@Override
	public void draw(View view) {
		DrawingUtils.clear(view, Color.WHITE);
		if(task.gameCompleted(map)) {
			view.drawImage(scale.mul(new Vector(0,0)),new Image("images/victory.jpg"));				
	/*		try {			
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	*/		
	//		map = new Map("Mapa1.04.txt");
	//		task = new Task();			
		}
		else {				
			drawMap(view);							
			view.setTransformation(Transformation.translation(new Vector(-8*scaleDouble,-8*scaleDouble)));	
		}
	}
	
	
	public void drawMap(View view) {		
		for (int i=0;i<16;i++) {
			for(int j=0;j<16;j++) {	
				if(map.getFieldMatrix()[i][j].getStatus()!=Status.EMPTY) {
					drawStatus(map.getFieldMatrix()[i][j],view);			
				}
			}
		}		
		for (int i=0;i<16;i++) {
			for(int j=0;j<16;j++) {				
				if(map.getFieldMatrix()[i][j].getStatus()==Status.EMPTY) {				
					if((i==7 || i==8) && (j==7 || j==8)) {					
						view.setFill(Color.DARKGREY);
						view.fillRect(scale.mul(new Vector(j,i)),scale);;				
					}
					else {
						view.setStroke(Color.WHITE);
						view.setFill(Color.LIGHTBLUE);						
						view.fillRect(scale.mul(new Vector(j,i)),scale);
						view.strokeRect(scale.mul(new Vector(j,i)),scale);
					}																									
				}			
			}								
		}			
		for (int i=0;i<16;i++) {
			for(int j=0;j<16;j++) {										
				drawWalls(map.getFieldMatrix()[i][j],view); 
			}
		}		
		for(int i=0;i<map.getPlayers().length;i++) {
			drawPlayers(map.getPlayers()[i],view);
		}
		drawTask(view);
	}
	
	private void drawTask(View view) {
		
		Field f = new Field(7,7);
		f.setStatus(task.getStatus());
		drawStatus(f,view);
	}
	
	// Crta sva polja
	private void drawStatus(Field f,View view) {
		if (f.getStatus()==Status.RED1) {		
			view.drawImage(scale.mul(new Vector(f.getColumn(),f.getRow())),new Image("images/1Red.png"));
		}
		else if (f.getStatus()==Status.RED2) {	
			view.drawImage(scale.mul(new Vector(f.getColumn(),f.getRow())),new Image("images/2Red.png"));
		}
		else if (f.getStatus()==Status.RED3) {	
			view.drawImage(scale.mul(new Vector(f.getColumn(),f.getRow())),new Image("images/3Red.png"));
		}
		else if (f.getStatus()==Status.RED4) {	
			view.drawImage(scale.mul(new Vector(f.getColumn(),f.getRow())),new Image("images/4Red.png"));
		}
		else if (f.getStatus()==Status.BLUE1) {	
			view.drawImage(scale.mul(new Vector(f.getColumn(),f.getRow())),new Image("images/1Blue.png"));
		}
		else if (f.getStatus()==Status.BLUE2) {	
			view.drawImage(scale.mul(new Vector(f.getColumn(),f.getRow())),new Image("images/2Blue.png"));
		}
		else if (f.getStatus()==Status.BLUE3) {	
			view.drawImage(scale.mul(new Vector(f.getColumn(),f.getRow())),new Image("images/3Blue.png"));
		}
		else if (f.getStatus()==Status.BLUE4) {	
			view.drawImage(scale.mul(new Vector(f.getColumn(),f.getRow())),new Image("images/4Blue.png"));
		}
		else if (f.getStatus()==Status.YELLOW1) {	
			view.drawImage(scale.mul(new Vector(f.getColumn(),f.getRow())),new Image("images/1Yellow.png"));
		}
		else if (f.getStatus()==Status.YELLOW2) {	
			view.drawImage(scale.mul(new Vector(f.getColumn(),f.getRow())),new Image("images/2Yellow.png"));
		}
		else if (f.getStatus()==Status.YELLOW3) {	
			view.drawImage(scale.mul(new Vector(f.getColumn(),f.getRow())),new Image("images/3Yellow.png"));
		}
		else if (f.getStatus()==Status.YELLOW4) {	
			view.drawImage(scale.mul(new Vector(f.getColumn(),f.getRow())),new Image("images/4Yellow.png"));
		}
		else if (f.getStatus()==Status.GREEN1) {	
			view.drawImage(scale.mul(new Vector(f.getColumn(),f.getRow())),new Image("images/1Green.png"));
		}
		else if (f.getStatus()==Status.GREEN2) {	
			view.drawImage(scale.mul(new Vector(f.getColumn(),f.getRow())),new Image("images/2Green.png"));
		}
		else if (f.getStatus()==Status.GREEN3) {	
			view.drawImage(scale.mul(new Vector(f.getColumn(),f.getRow())),new Image("images/3Green.png"));
		}
		else if (f.getStatus()==Status.GREEN4) {	
			view.drawImage(scale.mul(new Vector(f.getColumn(),f.getRow())),new Image("images/4Green.png"));
		}
	}
	
	// Za zadato polje crta zidove
	private void drawWalls(Field f,View view) {
		
		int x=f.getRow();
		int y=f.getColumn();
		
		if (f.isDownwall()) {
			view.setStroke(Color.BLACK);
			view.setLineWidth(3.0);
			view.strokeLine(scale.mul(new Vector(y,x)),
					scale.mul(new Vector(y+1,x)));			
		}
		if (f.isUpwall()) {
			view.setStroke(Color.BLACK);
			view.setLineWidth(3.0);
			view.strokeLine(scale.mul(new Vector(y,x+1)),
					scale.mul(new Vector(y+1,x+1)));
			
		}
		if (f.isLeftwall()) {
			view.setStroke(Color.BLACK);
			view.setLineWidth(3.0);
			view.strokeLine(scale.mul(new Vector(y,x)),
					scale.mul(new Vector(y, x+1)));
			
		}
		if (f.isRightwall()) {
			view.setStroke(Color.BLACK);
			view.setLineWidth(3.0);
			view.strokeLine(scale.mul(new Vector(y+1,x)),
					scale.mul(new Vector(y+1, x+1)));		
		}				
	}
	
	private void drawPlayers(Player p,View view) {
		int x=p.getRow();
		int y=p.getColumn();
		Player.Color c = p.getColor();
		
		switch(c) {
		case BLUE:
			view.setFill(Color.BLUE);
			view.fillCircleCentered(scale.mul(new Vector(y+0.5, x+0.5)),scaleDouble*0.4);
			break;
		case RED:
			view.setFill(Color.RED);
			view.fillCircleCentered(scale.mul(new Vector(y+0.5, x+0.5)),scaleDouble*0.4);
			break;
		case YELLOW:
			view.setFill(Color.YELLOW);
			view.fillCircleCentered(scale.mul(new Vector(y+0.5, x+0.5)),scaleDouble*0.4);
			break;
		case GREEN:
			view.setFill(Color.GREEN);
			view.fillCircleCentered(scale.mul(new Vector(y+0.5, x+0.5)),scaleDouble*0.4);
			break;
		default:
			break;			
		}		
	}
	
	
	
	@Override
	public void receiveEvent(View view, InputEvent event, InputState state, Vector pointerWorld, Vector pointerViewBase) {
		if(event.isKeyPress(KeyCode.DIGIT1)) {
			selectedPlayer=Player.Color.BLUE;
			
		}
		if(event.isKeyPress(KeyCode.DIGIT2)){
			selectedPlayer=Player.Color.RED;
			
		}
		if(event.isKeyPress(KeyCode.DIGIT3)) {
			selectedPlayer=Player.Color.YELLOW;
		}
		if(event.isKeyPress(KeyCode.DIGIT4)) {
			selectedPlayer=Player.Color.GREEN;	
		}
				
		if(event.isKeyPress(KeyCode.UP)) {
			map.moveUp(selectedPlayer);											
		}
		if(event.isKeyPress(KeyCode.DOWN)) {
			map.moveDown(selectedPlayer);				
		}
		if(event.isKeyPress(KeyCode.RIGHT)) {
			map.moveRight(selectedPlayer);	
		}
		if(event.isKeyPress(KeyCode.LEFT)) {
			map.moveLeft(selectedPlayer);				
		}

	}
		
	public static void main(String[] args) {
		DrawingApplication.launch(1100,950);
	}
	
			
	
}
