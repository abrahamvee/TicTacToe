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
	public static int gameWonByComputer = 0;
	public static ArrayList<Grid> wonMatches = new ArrayList<Grid>();
	String fileName = "ticTacToe_learning.ser";
	 
	
	Random rand = new Random();
	final int BOARD_SIZE_LENGTH=3;
	private boolean gameOver = false;
	private Grid grid;
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
	
	public TicTacToeGame() {
		 grid = new Grid();
		 
	}
	
	
	public int computersChoice() {
		return rand.nextInt(9);
	
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
		
		for(int i=0;i<BOARD_SIZE_LENGTH;i=i+3) {
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
	
	public String declareWinner(boolean tie)throws Exception {
		String statement= "Tie";
		if(turn.getTurn()==1 && tie==false) {
			statement="You won!!!";
			
		}
		else if(turn.getTurn()==0 && tie==false) {
			gameWonByComputer++;
			statement="You lost!!!";
			wonMatches.add(grid);
			saveBoard(grid);	
	}	
		return statement;
}
	
	private void saveBoard(Grid gridToSave) throws Exception{
		ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(fileName));
		os.writeObject(wonMatches);
		os.close();
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
