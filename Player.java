

public class Player {
	
	private char X_or_O;
	private String name;
	private int nextMove[] = new int[2]; //row and col positions
	
	Player(){}
	
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
	
	public String getName(){
		return name;
	}
	
	public void setName(String n){
		name = n;
	}
	
	public char getX_or_O(){
		return X_or_O;
	}
	
	public void setX_or_O(char xo){
		X_or_O = xo;
	}
	
}
