import java.io.Serializable;

public class Grid implements Serializable
{
	private Cell grid[] = new Cell[9];
	final static int GRID_SIZE=9;
	
	
	public Grid() {
		for(int i=0;i<grid.length;i++) {
			grid[i]= new Cell(i);
		}
		
	}
	
	public int getCellID(int cellNumber) {
		return grid[cellNumber].getCellID();
	}
	
	public boolean setX(int cell,int moveNumber) {
		cell=cell-1;
		if(grid[cell].getCell().trim().isEmpty()) {
			grid[cell].setX();
			grid[cell].saveOrder(moveNumber);
			return true;
		}
		else {
			return false;
		}	
	}
	
	public boolean setO(int cell, int moveNumber) {
		if(grid[cell].getCell().trim().isEmpty()) {
			grid[cell].setO();
			grid[cell].saveOrder(moveNumber);
			return true;
		}
		else {
			return false;
		}
	}
	
	public void cleanBoard() {
		for(int i=0;i<GRID_SIZE;i++) {
			grid[i].cleanCell();
		}
	}
	
	public void printBoard() {
		for(int i=0;i<GRID_SIZE;i++) {
			if((i+1)%3==0 && i<8) {
				System.out.print(grid[i].getCell());
				System.out.println("\n----------");
			}
			else if(i==8)  {
				System.out.println(grid[i].getCell());
			}
			else {
				System.out.print(grid[i].getCell()+ " |");
			}
		}
	}
	
	public void printBoardAid() {
		for(int i=0;i<GRID_SIZE;i++) {
			if((i+1)%3==0 && i<8) {
				System.out.print((i+1));
				System.out.println("\n----------");
			}
			else if(i==8)  {
				System.out.println((i+1));
			}
			else {
				System.out.print((i+1) + " |");
			}
		}
	}
	
	
	
}

