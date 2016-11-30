
public class GameManager {
	
	boolean finished = false;
	char winner = ' '; //No winner
	char turn = 'O'; //Starting player
	
	public static void main(String[] args) {
		
		GameBoard board = new GameBoard(5);
		
		board.printBoard();
		
		Player p1 = new Player('X', "Player1");
		Player p2 = new Player('O', "Player2");
		GameManager game = new GameManager();
		
		game.play(p1, p2, board);
		
	}
	
	private void play(Player X, Player O, GameBoard board){
		
		int moveCount = 0;
		int boardSize = board.getSize();
		int maxMoves = boardSize * boardSize;
		while(winner == ' ' && moveCount < maxMoves){
			
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
		int boardSize = board.getSize();
		char possibleWinner;
		boolean stop = false;
		
		//check rows
		while(row < boardSize && !winnerFound){
			possibleWinner = board.getCharAt(row,0);
			stop = false;
			while(col < boardSize-1 && !stop){
			
				if(possibleWinner == ' ') stop = true;
				if(board.getCharAt(row, col+1) != possibleWinner){
					stop = true;
				}
				col++;
				if(col == boardSize-1 && !stop){
					winnerFound = true;
					winner = possibleWinner;
				}
			}
			col = 0;
			row++;
		}
		row = 0; //reset row position
		
		//check columns
		while(col < boardSize && !winnerFound){
			possibleWinner = board.getCharAt(0, col);
			stop = false;
			while(row < boardSize-1 && !stop){
				if(possibleWinner == ' ') stop = true;
				if(board.getCharAt(row+1, col) != possibleWinner){
					stop = true;
				}
				row++;
				if(row == boardSize-1 && !stop){
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
		while(col < boardSize-1 && row < boardSize-1 && !stop && !winnerFound){
			if(possibleWinner == ' ') stop = true;
			if(board.getCharAt(row+1, col+1) != possibleWinner){
				stop = true;
			}
			row++;
			col++;
			if(col == boardSize-1 && row == boardSize-1 && !stop){
				winnerFound = true;
				winner = possibleWinner;
			}
		}
		
		//check top-right to bottom-left diagonal
		row = 0;
		col = boardSize-1;
		stop = false;
		possibleWinner = board.getCharAt(0, boardSize-1);
		while(col > 0 && row < boardSize-1 && !stop && !winnerFound){
			if(possibleWinner == ' ') stop = true;
			if(board.getCharAt(row+1, col-1) != possibleWinner){
				stop = true;
			}
			row++;
			col--;
			if(col == 0 && row == boardSize-1 && !stop){
				winnerFound = true;
				winner = possibleWinner;
			}
		}
		
		return winner;		
	}

}
