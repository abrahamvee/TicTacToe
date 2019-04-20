import java.io.Serializable;

/**
 * Grid class represents a grid (board), made up of cells for the 
 * tic-tac-toe game.
 * @author Abraham Vergara Estrella
 * @version April 20,2019
 *
 */
public class Grid implements Serializable
{
	private Cell grid[] = new Cell[9]; //Cell array that holds 9 cells.
	private int emptyCells = 9, moveNumber = 0; 
	
	/**
	 * Initializes a Grid object, which makes instances of Cell object. 
	 */
	public Grid() {
		for(int i=0;i<grid.length;i++) {
			grid[i]= new Cell(i);
		}
		
	}
	
	/**
	 * getCellID returns an integer which is the numerical representation of the cell.
	 * @param cellNumber Integer that indicates which cell's representation to return.
	 * @return integer that is the numerical representation of the cell.
	 */
	public int getCellID(int cellNumber) {
		return grid[cellNumber].getCellID();
	}
	
	/**
	 * getCellOrder Returns the number of the order the cell has been played.
	 * @param cellNumber integer that indicates which cell's order is being returned.
	 * @return integer which is the order of the cell in the game play.
	 */
	public int getCellOrder(int cellNumber) {
		return grid[cellNumber].getOrder();
	}
	
	/**
	 * emptyCell Cleans a cell.
	 * @param cellNumber Index to indicate which of the grid's cells is being cleaned. 
	 */
	public void emptyCell(int cellNumber) {
		grid[cellNumber].cleanCell();
	}
	
	/**
	 * setX sets an X in a cell.
	 * @param cell index of the cell in the grid.
	 * @return boolean informative value, true if the X was placed successfully false if it was not.
	 */
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
	/**
	 * copyIntoGrid makes a copy of one grid's contents into another one.
	 * @param gridTemp Grid into which the contents are being copied.
	 */
	public void copyIntoGrid(Grid gridTemp){
		for(int i=0;i<grid.length;i++) {
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
	
	/**
	 * Sets an O in a cell.
	 * @param cell index of the grid's cell.
	 * @return boolean value, true if the O was placed successfully, false if not.
	 */
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
	
	/**
	 * resetBoard cleans the board's content
	 * and resets the variables associate with it.
	 */
	public void resetBoard() {
		for(int i=0;i<grid.length;i++) {
			grid[i].cleanCell();
		}
		moveNumber = 0;
		emptyCells = 9;
	}
	
	/**
	 * getEmptyCells returns how many empty cells are in the grid.
	 * @return emptyCells value.
	 */
	public int getEmptyCells() {
		return emptyCells;
	}
	/**
	 * printBoard prints the Grid's content and adds line to make the board
	 * for the game.
	 */
	public void printBoard() {
		for(int i=0;i<grid.length;i++) {
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
	
	/**
	 * printBoardAid prints the board for the game, but in the cells 
	 * it prints the number keys that can be used for gameplay.
	 */
	public void printBoardAid() {
		for(int i=0;i<grid.length;i++) {
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

