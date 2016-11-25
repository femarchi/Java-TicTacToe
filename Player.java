
public class Player {
	
	public char X_or_O;
	public String name;
	private int nextMove[] = new int[2];
	
	
	Player(char X_or_O, String name){
		this.X_or_O = X_or_O;
		this.name = name;
	}
	
	//default player chooses randomly
	public int[] getNextMove(GameBoard board){ 
		
		nextMove[0] = (int)(Math.random()*board.getSize());
		nextMove[1] = (int)(Math.random()*board.getSize());
		
		while(board.getCharAt(nextMove[0], nextMove[1]) != ' '){
			getNextMove(board);
		}
		
		return nextMove;
	}
	
	
	
}
