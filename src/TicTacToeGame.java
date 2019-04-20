import java.util.Random;
import java.util.ArrayList;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * TicTacToeGame represents a tic tac toe game.
 * @author Abraham Vergara Estrella
 * @version April 20, 2019
 *
 */

public class TicTacToeGame implements Serializable

{
	public static ArrayList<Grid> wonMatches = new ArrayList<Grid>(); //Holds the previous matches
	String fileName = "ticTacToe_learning_losses_final.ser";
	Random rand = new Random(); 
	final int BOARD_SIZE_LENGTH=3;
	private boolean gameOver = false, tie = false;
	private Grid grid; //grid of current game play
	private Grid gridTemp; // grid used to see if there is a loosing match
	Turn turn; //Whose turn is it, computer or user.
	
	/**
	 * Represents the current turn, user or computer.
	 *
	 */
	public enum Turn{
		/**
		 * User has a turn_id of 1.
		 */
		User(1),
		/**
		 * Computer has a turn_id of 0.
		 */
		Computer(0);
		private int turn_id;
		
		Turn(int turnID){
			turn_id = turnID;
		}
		
		/**
		 * getTurn returns the turnID.
		 * @return turn_ID integer that represents who's turn is it.
		 */
		public int getTurn() {
			return turn_id;
		}
		
	}
	
	/**
	 * Initializes the tic tac toe game.
	 * @throws Exception related to the reading of the file where the saved boards are.
	 */
	public TicTacToeGame() throws Exception {
		 grid = new Grid();
		 gridTemp = new Grid();
		 readBoards();	
	}
	
	/**
	 * setTie used to indicate that the game has ended in a tie.
	 */
	public void setTie() {
		tie = true;
		gameOver = true;
	}
	/**
	 * computersChoice used to indicate the cell where the computer is going to place their mark,
	 * if there aren't any previous loosing boards, it uses a random number. Else it chooses a cell, and places it
	 * in a dummy grid which it compares it against the database of loosing boards if it finds a match it chooses another 
	 * cell.
	 * @return Number of cell where the computer is going to play.
	 */
	public int computersChoice() {
		int cellToUse=0, i=0, numberToAvoid=-1;
		boolean foundInReferenceBoard = false;
		Grid previousBoard = new Grid();
			if(wonMatches.isEmpty()) { 
				cellToUse = rand.nextInt(9);
			}
			else if(!wonMatches.isEmpty()) { //if previous matches found
				grid.copyIntoGrid(gridTemp); //copy grid into dummy grid
				cellToUse = rand.nextInt(9);// try a random cell
				gridTemp.setO(cellToUse);
				
				do {   										// cycle through wonMatches looking for a previous matching board
					if(gridTemp.equals(wonMatches.get(i))) {//if found try another cell
						previousBoard=wonMatches.get(i);
						foundInReferenceBoard = true;
						numberToAvoid = cellToUse;
						cellToUse= rand.nextInt(9);
						i++;
					}
				}while(!foundInReferenceBoard && numberToAvoid==cellToUse && i<wonMatches.size()); // stop until the cell to use does not makes the board to be a loosing combo
			}
			return cellToUse;		
	}
			
	/**
	 * changeTurn changes the current turn to the opposite player.
	 */
	
	public void changeTurn() {
		if(turn.getTurn()==1) {
			turn=Turn.Computer;
		}
		else if(turn.getTurn()==0) {
			turn=Turn.User;
		}
	}
	
	/**
	 * getGrid used to obtain the grid beneath the board.
	 * @return grid which is the base of the board.
	 */
	public Grid getGrid() {
		return grid;
	}
	
	/**
	 * resetGame used to restart a Game, sets the gameOver and tie variable to false,
	 * and cleans the board.
	 */
	public void resetGame() {
		grid.resetBoard();
		tie = false;
		gameOver = false;
	}
	/**
	 * setIntitialTurn sets the initial turn with a random number.
	 */
	public void setInitialTurn() {
		int turnToSet = rand.nextInt(2);
		if(turnToSet==0) {
			turn=Turn.Computer;
		}
		else if(turnToSet==1) {
			turn=Turn.User;
		}
	}
	/**
	 * checkGameOver checks the board for winning game combinations if it founds one it sets the gameOver variable as true.
	 * @return gameOver true if it found a game winning combination.
	 */
	public boolean checkGameOver() {
		for(int i=0;i<7;i=i+3) {
			if(grid.getCellID(i)==grid.getCellID(i+1) && grid.getCellID(i)==grid.getCellID(i+2) && grid.getCellID(i)==1) {
				gameOver = true;
			}
			else if(grid.getCellID(i)==grid.getCellID(i+1) && grid.getCellID(i)==grid.getCellID(i+2) && grid.getCellID(i)==0) {
				gameOver = true;
			}
		}
		for(int i=0;i<BOARD_SIZE_LENGTH;i=i+1) {
			if(grid.getCellID(i)==grid.getCellID(i+3) && grid.getCellID(i)==grid.getCellID(i+6) && grid.getCellID(i)==1) {
				gameOver = true;		
			}
			else if(grid.getCellID(i)==grid.getCellID(i+3) && grid.getCellID(i)==grid.getCellID(i+6) && grid.getCellID(i)==0) {
				gameOver = true;
			}
		}
		
		if(grid.getCellID(0)==grid.getCellID(4) && grid.getCellID(0)==grid.getCellID(8) && grid.getCellID(0)==1) {
			gameOver = true;
		}
		else if(grid.getCellID(2)==grid.getCellID(4) && grid.getCellID(2)==(grid.getCellID(6)) && grid.getCellID(2)==1) {
			gameOver = true;
		}
		
		else if(grid.getCellID(0)==grid.getCellID(4) && grid.getCellID(0)==grid.getCellID(8) && grid.getCellID(0)==0) {
			gameOver = true;
		}
		else if(grid.getCellID(2)==grid.getCellID(4) && grid.getCellID(2)==(grid.getCellID(6)) && grid.getCellID(2)==0) {
			gameOver = true;
		}
		
		return gameOver;
	}
	/**
	 * declareWinner declares a winner, computer or user depending on whose turn it is.
	 * @return statement String that contains the winning statement.
	 * @throws Exception from the reading of the file that contains the loosing combinations.
	 */
	public String declareWinner()throws Exception {
		boolean foundLastMove = false;
		String statement= "Tie";
		int i=8;
		if(turn.getTurn()==1 && tie==false) {
			statement="You won!!!";
			grid.copyIntoGrid(gridTemp);
			do {
				if(gridTemp.getCellOrder(i)!=-1) {
					moveOrderTemp = gridTemp.getCellOrder(i);
					gridTemp.emptyCell(i);
					foundLastMove= true;
				}else {
					i--;
				}
			}while(!foundLastMove || i==0);
			wonMatches.add(gridTemp);
			saveBoard();
			
		}
		else if(turn.getTurn()==0 && tie==false) {
			statement="You lost!!!";	
	}	
		else if(tie==true) {
			statement = "It's a tie!!";
		}
		return statement;
}
	/**
	 * saveBoard saves a board to a .ser file.
	 * @throws Exception if fileName not found.
	 */
	private void saveBoard() throws Exception{
		ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(fileName));
		os.writeObject(wonMatches);
		os.close();
	}
	
	/**
	 * getLostMatches returns the number of lost matches that the computer has saved.
	 * @return wonMatches.size() number of saved matches in array List.
	 */
	public int getLostMatches() {
		return wonMatches.size();
	}
	
	/**
	 * printSavedBoard prints all the saved boards from the file.
	 * @throws Exception generated by not founding the file when reading it.
	 */
	public void printSavedBoard() throws Exception {
		readBoards();
		if(!wonMatches.isEmpty()) {
		for(int i=0;i<wonMatches.size();i++) {
			wonMatches.get(i).printBoard();
		}
		}
		System.out.println("Those were the saved boards.");
	}
	/**
	 * readBoards reads a file with Grid objects and saves them to an array list. 
	 * @throws Exception that occurs if the file is not found.
	 */
	public void readBoards() throws Exception {
		try {
		ObjectInputStream is = new ObjectInputStream(new FileInputStream(fileName));
		wonMatches = (ArrayList<Grid>) is.readObject();
		is.close();
		}catch(FileNotFoundException e) {
			
		}
		
	}
}
