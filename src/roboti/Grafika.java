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
	private Map map = new Map("mapa1.txt");

	@Override
	public void draw(View view) {
			
		DrawingUtils.clear(view, Color.WHITE);		
		map.getFieldMatrix()[1][2].setStatus(Status.RED1);
		
//		map.moveLeft(Player.Color.RED);
//		map.moveUp(Player.Color.RED);
//		map.moveLeft(Player.Color.RED);
//		map.moveDown(Player.Color.RED);
						
				
		drawMap(map,view);							
		view.setTransformation(Transformation.translation(new Vector(-8*scaleDouble,-8*scaleDouble)));
	}
	
	
	public void drawMap(Map map,View view) {
		for (int i=0;i<16;i++) {
			for(int j=0;j<16;j++) {				
				if(map.getFieldMatrix()[i][j].getStatus()==Status.EMPTY) {
				
					if((i==7 || i==8) && (j==7 || j==8)) {					
						view.setFill(Color.DARKGREY);
						view.fillRect(scale.mul(new Vector(j,i)),scale);;				
					}
					else {
						view.setStroke(Color.WHITE);
						view.setFill(Color.YELLOWGREEN);
						view.fillRect(scale.mul(new Vector(j,i)),scale);
						view.strokeRect(scale.mul(new Vector(j,i)),scale);
					}																									
				}
			}								
		}
		
		for (int i=0;i<16;i++) {
			for(int j=0;j<16;j++) {						
				if(map.getFieldMatrix()[i][j].getStatus()!=Status.EMPTY) {
					drawStatus(map.getFieldMatrix()[i][j],view); 											
				}
				drawWalls(map.getFieldMatrix()[i][j],view); 
			}
		}
		
		for(int i=0;i<map.getPlayers().length;i++) {
			drawPlayers(map.getPlayers()[i],view);
		}
	}
	
	// Za sada crta samo jedno polje
	private void drawStatus(Field f,View view) {
		if (f.getStatus()==Status.RED1) {
			view.setFill(Color.RED);		
			view.drawImage(scale.mul(new Vector(f.getColumn(),f.getRow())),new Image("images/Red11.png"));
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
		if(event.isKeyPress(KeyCode.UP)) {
			
		}
		if(event.isKeyPress(KeyCode.DOWN)) {
			
		}
		if(event.isKeyPress(KeyCode.RIGHT)) {
			
		}
		if(event.isKeyPress(KeyCode.LEFT)) {
			
		}

	}
		
	public static void main(String[] args) {
		DrawingApplication.launch(1000,850);
	}
	
			
	
}
