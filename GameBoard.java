
public class GameBoard {

	private char board[][];
	
	GameBoard(int size){ //constructor
		
		board = new char[size][size];			//create a size x size board
		for(int i = 0; i < board.length; i++){	//fill board with empty spaces
			for(int j = 0; j < board[i].length; j++){
				board[i][j] = ' ';
			}
		}
			
	}
	
	public char getCharAt(int row, int col){
		return board[row][col];
	}
	
	public void setCharAt(char player, int row, int col){
		board[row][col] = player;
	}
	
	public int getSize(){
		return board.length;
	}
	
	public void printBoard(){
		
		for(int i = 0; i < board.length; i++){
			for(int j = 0; j < board[i].length; j++){
				if(j < board.length-1){
					if(i < board[i].length-1){
						System.out.print(board[i][j] + "_|_");
					} else {
						System.out.print(board[i][j] + " | ");
					}
				} else {
					System.out.println(board[i][j]);
				}
			}
			
		}
		System.out.println();
		
	}
}
