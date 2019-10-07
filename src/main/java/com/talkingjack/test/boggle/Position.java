package com.talkingjack.test.boggle;

public class Position {
	public Position( int row, int col ){
		this.row = row;
		this.col = col;
	}
	private int row, col;
	public int incrementRow(){ return ++row; }
	public int incrementCol(){ return ++col; }
	public int decrementRow(){ return --row; }
	public int decrementCol(){ return --col; }
	public int getRow(){ return row; }
	public int getCol(){ return col; }
}
