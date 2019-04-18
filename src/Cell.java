import java.io.Serializable;

public class Cell implements Serializable
{
	private cellContent cell;
	private int orderInGamePlay;
	private int cellID;
	
	public enum cellContent{
		O(0,"O"),X(1,"X"),E(-1," ");
		private int content_ID;
		private String representation;
		
		cellContent(int contentID,String rep){
			content_ID = contentID;
			representation = rep;
		}
		
		public int getContentID() {
			return content_ID;
		}
		
	}
	
	public Cell(int cellID) {
		cell = cellContent.E;
		orderInGamePlay = -1;
		this.cellID = cellID;
	}
	
	public void setX() {
		cell = cellContent.X;
	}
	
	public void setO() {
		cell = cellContent.O;
	}
	
	public void cleanCell() {
		cell = cellContent.E;
	}
	public String getCell() {
		return cell.representation;
	}
	
	public int getCellID() {
		return cell.content_ID;
	}
	
	public int getOrder() {
		return orderInGamePlay;
	}
	public void saveOrder(int order) {
		orderInGamePlay = order;
	}
}
