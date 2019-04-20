import java.io.Serializable;

/**
 * Cell class represents a cell in a tic-tac-toe grid.
 * It stores the X or O, the order in which it was placed and 
 * and a numeric representation -1 for empty, 1 for X and 0 for O.
 * @author Abraham Vergara Estrella
 * @version April 20, 2019
 *
 */
public class Cell implements Serializable
{
	private cellContent cell; // The X or O
	private int orderInGamePlay; // Order in which the cell was placed.
	private int cellID; //Numeric representation.
	
	/**
	 * 
	 * Possible value of a cell X or O.
	 */
	public enum cellContent{
		/**
		 * Represents O.
		 */
		O(0,"O"),
		/**
		 * Represents X.
		 */
		X(1,"X"),
		/**
		 * Represents an empty cell.
		 */
		E(-1," ");
		
		private int content_ID;
		private String representation;
		
		/**
		 * Initializes cell content.
		 * @param contentID is the numerical representation of the cell's content.
		 * @param rep is the actual cell content "X" or "O".
		 */
		 private cellContent(int contentID,String rep){
			content_ID = contentID;
			representation = rep;
		}
		/**
		 * getContentID returns a numerical value representing the cell's content.
		 * @return content_ID numerical value representing the cell's content.
		 */
		public int getContentID() {
			return content_ID;
		}
		
	}
	/**
	 * Initializes a cell with a particular cellID.
	 * @param cellID an int that represents the cell's position in a grid.
	 */
	public Cell(int cellID) {
		cell = cellContent.E;
		orderInGamePlay = -1;
		this.cellID = cellID;
	}
	
	/**
	 * setX sets the cell's content to X.
	 */
	public void setX() {
		cell = cellContent.X;
	}
	
	/**
	 * setO sets the cell's content to O.
	 */
	public void setO() {
		cell = cellContent.O;
	}
	
	/**
	 * cleanCell cleans the cell's content.
	 */
	public void cleanCell() {
		cell = cellContent.E;
	}
	/**
	 * getCells returns the cell's representation "X" or "O". 
	 * @return  cell.representation which is a String.
	 */
	public String getCell() {
		return cell.representation;
	}
	/**
	 * getCellID returns the cell's numerical representation 
	 * @return cell.content_ID which is an integer 1 for X, 0 for O and -1 for empty.
	 */
	
	public int getCellID() {
		return cell.content_ID;
	}
	/**
	 * getOrder returns the cell's order in game play.
	 * @return an integer with the cell's number
	 */
	public int getOrder() {
		return orderInGamePlay;
	}
	
	/**
	 * saveOrder sets the cell's order in which it has been played.
	 * @param order the number in which the cell is played.
	 */
	public void saveOrder(int order) {
		orderInGamePlay = order;
	}
}
