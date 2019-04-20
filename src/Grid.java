import java.io.Serializable;

public class Grid implements Serializable
{
	private Cell grid[] = new Cell[9];
	final static int GRID_SIZE=9;
	private int emptyCells = 9, moveNumber = 0;
	
	public Grid() {
		for(int i=0;i<grid.length;i++) {
			grid[i]= new Cell(i);
		}
		
	}
	
	public int getCellID(int cellNumber) {
		return grid[cellNumber].getCellID();
	}
	
	public int getCellOrder(int cellNumber) {
		return grid[cellNumber].getOrder();
	}
	
	public void emptyCell(int cellNumber) {
		grid[cellNumber].cleanCell();
	}
	
	public int getMoveOrderNumber() {
		return moveNumber;
	}
	public boolean setX(int cell) {
		if(grid[cell].getCell().trim().isEmpty()) {
			grid[cell].setX();
			grid[cell].saveOrder(moveNumber);
			moveNumber++;
			emptyCells--;
			return true;
		}
		else {
			return false;
		}	
	}
	public void copyIntoGrid(Grid gridTemp){
		for(int i=0;i<GRID_SIZE;i++) {
			if(grid[i].getCellID()==1) {
				gridTemp.setX(i);
			}
			else if(grid[i].getCellID()==0) {
				gridTemp.setO(i);
			}
			else if(grid[i].getCellID()==-1) {
				gridTemp.emptyCell(i);
			}
				
			}
	}
	public boolean setO(int cell) {
		if(grid[cell].getCell().trim().isEmpty()) {
			grid[cell].setO();
			grid[cell].saveOrder(moveNumber);
			moveNumber++;
			emptyCells--;
			return true;
		}
		else {
			return false;
		}
	}
	
	public void resetBoard() {
		for(int i=0;i<GRID_SIZE;i++) {
			grid[i].cleanCell();
		}
		moveNumber = 0;
		emptyCells = 9;
	}
	
	public int getEmptyCells() {
		return emptyCells;
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

