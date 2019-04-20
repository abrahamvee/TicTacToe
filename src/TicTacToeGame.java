import java.util.Random;
import java.util.ArrayList;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;


public class TicTacToeGame implements Serializable

{
	public static ArrayList<Grid> wonMatches = new ArrayList<Grid>();
	String fileName = "ticTacToe_learning_losses_final.ser";
	 
	
	Random rand = new Random();
	final int BOARD_SIZE_LENGTH=3;
	private int moveOrderTemp;
	private int emptyCells = 9, moveNumber = 0;
	private boolean gameOver = false, tie = false;
	private Grid grid;
	private Grid gridTemp;
	Turn turn;
	
	public enum Turn{
		User(1),Computer(0);
		private int turn_id;
		
		Turn(int turnID){
			turn_id = turnID;
		}
		
		public int getTurn() {
			return turn_id;
		}
		
	}
	
	public TicTacToeGame() throws Exception {
		 grid = new Grid();
		 gridTemp = new Grid();
		 readBoards();	
	}
	
	public void setTie() {
		tie = true;
		gameOver = true;
	}
	public int computersChoice() {
		int cellToUse=0, i=0, numberToAvoid=-1;
		boolean foundInReferenceBoard = false;
		Grid previousBoard = new Grid();
			if(wonMatches.isEmpty()) {
				cellToUse = rand.nextInt(9);
			}
			else if(!wonMatches.isEmpty()) {
				grid.copyIntoGrid(gridTemp);
				cellToUse = rand.nextInt(9);
				gridTemp.setO(cellToUse);
				
				do {   										// cycle through wonMatches looking for a one that equals gridTemp
					if(gridTemp.equals(wonMatches.get(i))) {
						previousBoard=wonMatches.get(i);
						foundInReferenceBoard = true;
						numberToAvoid = cellToUse;
						cellToUse= rand.nextInt(9);
						i++;
					}
				}while(!foundInReferenceBoard && numberToAvoid==cellToUse && i<wonMatches.size());
			}
			return cellToUse;		
	}
			
	
	
	public void changeTurn() {
		if(turn.getTurn()==1) {
			turn=Turn.Computer;
		}
		else if(turn.getTurn()==0) {
			turn=Turn.User;
		}
	}
	
	public Grid getGrid() {
		return grid;
	}
	
	public void resetGame() {
		grid.resetBoard();
		tie = false;
		gameOver = false;
	}
	public void setInitialTurn() {
		int turnToSet = rand.nextInt(2);
		if(turnToSet==0) {
			turn=Turn.Computer;
		}
		else if(turnToSet==1) {
			turn=Turn.User;
		}
	}
	
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
	
	private void saveBoard() throws Exception{
		ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(fileName));
		os.writeObject(wonMatches);
		os.close();
	}
	
	public int getWonMatches() {
		return wonMatches.size();
	}
	
	public void printSavedBoard() throws Exception {
		readBoards();
		if(!wonMatches.isEmpty()) {
		for(int i=0;i<wonMatches.size();i++) {
			wonMatches.get(i).printBoard();
		}
		}
		System.out.println("Those were the saved boards.");
	}
	
	public void readBoards() throws Exception {
		try {
		ObjectInputStream is = new ObjectInputStream(new FileInputStream(fileName));
		wonMatches = (ArrayList<Grid>) is.readObject();
		is.close();
		}catch(FileNotFoundException e) {
			
		}
		
	}
}
