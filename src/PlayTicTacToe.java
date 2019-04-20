
import java.util.Arrays;
import java.util.Scanner;


public class PlayTicTacToe
{

	final static int BOARD_SIZE=9;
	
	
	public static void main(String[] args) throws Exception
	{
		
		TicTacToeGame game1 = new TicTacToeGame();
		Scanner in = new Scanner(System.in);
		int cell = 0;
		boolean succesfulTurn = false, notFirstTurn=false;
		boolean tie = false;
		char continueGamePlay = 'y';
		game1.setInitialTurn();
		//Insert possible log of saved games
		//System.out.println(game1.getWonMatches());
		System.out.println("Welcome to Tic-Tac-Toe. Use the following numbers to select a cell.");
		game1.getGrid().printBoardAid();
		do {
			if(continueGamePlay=='y') {
				
				game1.setInitialTurn();
				game1.resetGame();
			}
			
		while(!game1.checkGameOver()) {
			
			if(notFirstTurn) {
			game1.changeTurn();
			} 
			succesfulTurn = false;
			if(game1.turn.getTurn()==1) {
				System.out.println("Where do you want to place your X: ");
				
				cell = in.nextInt()-1;
				
				do {
					succesfulTurn=game1.getGrid().setX(cell);
					if(succesfulTurn==false) {
						System.out.println("Invalid cell. Try other.");
						cell = in.nextInt()-1;
					}
				}while(!succesfulTurn);
			}
			else if (game1.turn.getTurn()==0){
				System.out.println("The computers turn!!!");
				do {
					succesfulTurn=game1.getGrid().setO(game1.computersChoice());
				}while(!succesfulTurn);
				}
			
			game1.getGrid().printBoard();
			notFirstTurn = true;
			
			
			if(game1.getGrid().getEmptyCells()==0 && game1.checkGameOver()==false) {
				game1.setTie();
			}
			
			}
		System.out.println(game1.declareWinner(tie));
		System.out.println("Do you want to keep playing? Y/N");
		continueGamePlay = in.next().charAt(0);
		
	}while(continueGamePlay!='n');
	
		
	}
}

