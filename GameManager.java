import java.io.*;

public class GameManager {
	
	boolean finished = false;
	char winner = ' '; //No winner
	char turn = 'X'; //Starting player
	
	public static void main(String[] args) {
		
		GameBoard board = new GameBoard(3);
		GameManager game = new GameManager();
		
		board.printBoard();
		
		RandomPlayer p1 = new RandomPlayer();
		RandomPlayer p2 = new RandomPlayer();
		
		p1.setName(game.getPlayerName("Player 1")); //get input from user
		p1.setX_or_O('X');
		p2.setName(game.getPlayerName("Player 2")); 
		p1.setX_or_O('O');
		
		
		//Player p1 = new Player('X', "Player1");
		//Player p2 = new Player('O', "Player2");
		
		
		game.play(p1, p2, board);
		
	}
	
	private void play(RandomPlayer X, RandomPlayer O, GameBoard board){
		
		int moveCount = 0;
		int boardSize = board.getSize();
		int maxMoves = boardSize * boardSize;
		while(winner == ' ' && moveCount < maxMoves){
			
			moveCount++;
			
			if(turn == 'X'){
				int[] move = X.getNextMove(board);
				System.out.println(X.getName() + " plays\n");
				board.setCharAt('X', move[0],move[1]);
				turn = 'O'; //give turn to other player
			}else if(turn == 'O'){
					int[] move = O.getNextMove(board);
					System.out.println(O.getName() + " plays\n");
					board.setCharAt('O', move[0],move[1]);
					turn = 'X'; //give turn to other player
			}
			
			board.printBoard();
			getWinner(board);
		}
		
		//print winner
		switch(winner){
			case 'X': System.out.println("The winner is " + X.getName()); break;
			case 'O': System.out.println("The winner is " + O.getName()); break;
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
	
	private String getPlayerName(String player){
		String inputLine = null;
		System.out.print("Enter "+ player+ "'s name: ");
		try{
			BufferedReader n = new BufferedReader(new InputStreamReader(System.in));
			inputLine = n.readLine();
			if(inputLine.length() == 0) return null;
		}catch (IOException e){
			System.out.println("IOException: " + e);
		}
		return inputLine;
	}

}
