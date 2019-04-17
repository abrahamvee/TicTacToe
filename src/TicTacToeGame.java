import java.util.Random;

public class TicTacToeGame
{
	
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
	
	public String declareWinner(boolean tie) {
		String statement= "test";
		if(turn.getTurn()==1 && tie==false) {
			statement="You won!!!";
		}
		else if(turn.getTurn()==0 && tie==false) {
			statement="You lost!!!";
		}
		return statement;
	}
	
}
