package ncu.im3069.demo.app;

public class Seat {
	private int id;
	private int rowNum;
	private int colNum;
	public Seat(int id, int rowNum, int colNum) {
		super();
		this.id = id;
		this.rowNum = rowNum;
		this.colNum = colNum;
	}
	public Seat(int rowNum, int colNum) {
		super();
		this.rowNum = rowNum;
		this.colNum = colNum;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getRowNum() {
		return rowNum;
	}
	public void setRowNum(int rowNum) {
		this.rowNum = rowNum;
	}
	public int getColNum() {
		return colNum;
	}
	public void setColNum(int colNum) {
		this.colNum = colNum;
	}
}
