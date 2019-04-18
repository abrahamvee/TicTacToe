
import java.util.Arrays;
import java.util.Scanner;


public class PlayTicTacToe
{

	final static int BOARD_SIZE=9;
	
	
	public static void main(String[] args) throws Exception
	{
		
		TicTacToeGame game1 = new TicTacToeGame();
		Scanner in = new Scanner(System.in);
		int cell = 0, moveNumber = 0, emptyCells=9;
		boolean succesfulTurn = false, gameOver = false, notFirstTurn=false;
		boolean tie = false;
		game1.setInitialTurn();
		//game1.printSavedBoard();
		System.out.println("Welcome to Tic-Tac-Toe. Use the following numbers to select a cell.");
		game1.getGrid().printBoardAid();
	
		while(!gameOver) {
			if(notFirstTurn) {
			game1.changeTurn();
			} 
			succesfulTurn = false;
			if(game1.turn.getTurn()==1) {
				System.out.println("Where do you want to place your X: ");
				
				cell = in.nextInt();
				
				do {
					succesfulTurn=game1.getGrid().setX(cell, moveNumber);
					if(succesfulTurn==false) {
						System.out.println("Invalid cell. Try other.");
						cell = in.nextInt();
					}
				}while(!succesfulTurn);
				gameOver=game1.checkGameOver();
			}
			else if (game1.turn.getTurn()==0){
				System.out.println("The computers turn!!!");
				do {
					succesfulTurn=game1.getGrid().setO(game1.computersChoice(moveNumber), moveNumber);
				}while(!succesfulTurn);
				gameOver=game1.checkGameOver();
				}
			
			game1.getGrid().printBoard();
			moveNumber++;
			notFirstTurn = true;
			emptyCells--;
			if(emptyCells==0 && gameOver==false) {
				gameOver = true;
				tie = true;
			}
			
			}
		System.out.println(game1.declareWinner(tie));
		in.close();
	}

	
		
	}


