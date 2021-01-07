package roboti;

import java.util.Random;

public class Task {
	
	private Field.Status status;
	
	public Task() {
		status=Field.Status.getRandomStatus();	
		while(status==Field.Status.EMPTY && status==Field.Status.RAINBOW)
			status=Field.Status.getRandomStatus();		
	}

	public Field.Status getStatus() {
		return status;
	}

	public void setStatus(Field.Status status) {
		this.status = status;
	}
	
	public boolean gameCompleted(Map map) {
				int i;
				if(status==Field.Status.RED1 || status==Field.Status.RED2 || status==Field.Status.RED3 || status==Field.Status.RED4) {
					i=1;
				}
				else if(status==Field.Status.BLUE1 || status==Field.Status.BLUE2 || status==Field.Status.BLUE3 || status==Field.Status.BLUE4) {
					i=0;
				}
				else if(status==Field.Status.YELLOW1 || status==Field.Status.YELLOW2 || status==Field.Status.YELLOW3 || status==Field.Status.YELLOW4) {
					i=2;
				}
				else if(status==Field.Status.GREEN1 || status==Field.Status.GREEN2 || status==Field.Status.GREEN3 || status==Field.Status.GREEN4) {
					i=3;
				}
				else 
					i=-1;
				
				for(int j=0;j<16;j++) {
					for(int k=0;k<16;k++) {
						if(status==map.getFieldMatrix()[j][k].getStatus()) {
							if(map.getPlayers()[i].getRow()==j && map.getPlayers()[i].getColumn()==k)
								return true;
						}							
					}
				}		
				return false;
	}	
	
	public static void main(String[] args) {
		System.out.println(new Task());
	}

	@Override
	public String toString() {
		return "Task [status=" + status + "]";
	}
}
