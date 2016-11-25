
public class GameManager {
	
	boolean finished = false;
	char winner = ' '; //No winner
	char turn = 'X'; //Starting player
	
	public static void main(String[] args) {
		
		GameBoard board = new GameBoard();
		
		board.printBoard();
		
		Player p1 = new Player('X', "X");
		Player p2 = new Player('O', "O");
		GameManager game = new GameManager();
		
		game.play(p1, p2, board);
		
	}
	
	private void play(Player X, Player O, GameBoard board){
		
		int moveCount = 0;
		while(winner == ' ' && moveCount < 9){
			
			moveCount++;
			
			if(turn == 'X'){
				int[] move = X.getNextMove(board);
				board.setCharAt('X', move[0],move[1]);
				turn = 'O'; //give turn to other player
			}else if(turn == 'O'){
					int[] move = O.getNextMove(board);
					board.setCharAt('O', move[0],move[1]);
					turn = 'X'; //give turn to other player
			}
			
			board.printBoard();
			getWinner(board);
		}
		
		//print winner
		switch(winner){
			case 'X': System.out.println("The winner is " + X.name); break;
			case 'O': System.out.println("The winner is " + O.name); break;
			case ' ': System.out.println("Draw"); break;
			default: System.out.println();
		}
		
	}
	
	private char getWinner(GameBoard board){
		boolean winnerFound = false;
		int row = 0;
		int col = 0;
		char possibleWinner;
		boolean stop = false;
		
		//assume 3x3 board
		//check rows
		while(row < 3 && !winnerFound){
			possibleWinner = board.getCharAt(row,0);
			stop = false;
			while(col < 2 && !stop){
			
				if(possibleWinner == ' ') stop = true;
				if(board.getCharAt(row, col+1) != possibleWinner){
					stop = true;
				}
				col++;
				if(col == 2 && !stop){
					winnerFound = true;
					winner = possibleWinner;
				}
			}
			col = 0;
			row++;
		}
		row = 0; //reset row position
		
		//check columns
		while(col < 3 && !winnerFound){
			possibleWinner = board.getCharAt(0, col);
			stop = false;
			while(row < 2 && !stop){
				if(possibleWinner == ' ') stop = true;
				if(board.getCharAt(row+1, col) != possibleWinner){
					stop = true;
				}
				row++;
				if(row == 2 && !stop){
					winnerFound = true;
					winner = possibleWinner;
				}
			}
			row = 0;
			col++;
			
		}		
		col = 0; //reset col position
		
		//check top-left to bottom-right diagonal
		stop = false;
		possibleWinner = board.getCharAt(0, 0);
		while(col < 2 && row < 2 && !stop && !winnerFound){
			if(possibleWinner == ' ') stop = true;
			if(board.getCharAt(row+1, col+1) != possibleWinner){
				stop = true;
			}
			row++;
			col++;
			if(col == 2 && row == 2 && !stop){
				winnerFound = true;
				winner = possibleWinner;
			}
		}
		
		//check top-right to bottom-left diagonal
		row = 0;
		col = 2;
		stop = false;
		possibleWinner = board.getCharAt(0, 2);
		while(col > 0 && row < 2 && !stop && !winnerFound){
			if(possibleWinner == ' ') stop = true;
			if(board.getCharAt(row+1, col-1) != possibleWinner){
				stop = true;
			}
			row++;
			col--;
			if(col == 0 && row == 2 && !stop){
				winnerFound = true;
				winner = possibleWinner;
			}
		}
		
		return winner;		
	}

}
