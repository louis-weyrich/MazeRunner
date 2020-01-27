package com.sos.mazerunner;

public class Cell 
{
	
	private int row, column;
	private CellType type;

	public Cell(int row, int column, CellType type)
	{
		super();
		this.row = row;
		this.column = column;
		this.type = type;
	}

	public int getRow()
	{
		return row;
	}

	public int getColumn()
	{
		return column;
	}

	public CellType getType()
	{
		return type;
	}
	
	public void setType(CellType type)
	{
		this.type = type;
	}
	
	public boolean equals(Object src)
	{
		if(src instanceof Cell)
		{
			Cell cell = (Cell)src;
			return(this.row == cell.getRow() && this.column == cell.getColumn());
		}
		return false;
	}
	
	public int hashCode()
	{
		return (row*100)+(column);
	}

}
