package com.sos.mazerunner.util;


/**
 * 
 * @author louisweyrich
 *
 * @param <T>
 */
public class GridArray <T>
{
	
	private T [][] data;
	private int rowSize = 0, columnSize = 0;
	private int currentRow = 0, currentColumn = 0;

	
	/**
	 * 
	 * @param rows
	 * @param columns
	 */
	@SuppressWarnings("unchecked")
	public GridArray(int rows, int columns)
	{
		this.rowSize = rows;
		this.columnSize = columns;
		this.data = (T[][]) new Object[rows][columns];
	}

	/**
	 * 
	 * @param data
	 */
	public GridArray(T [][] data)
	{
		if(data.length > 0 && ((data.length > 0)? data[0].length : 0) > 0)
		{
			this.rowSize = data.length;
			this.columnSize = data[0].length;
			this.data = data;
		}
		else
		{
			throw new IllegalArgumentException(
				"multidimensional array must have at least one row and one "
				+ "column: row("+data.length+") column("+
					((data.length > 0)? data[0].length : 0)+")");
		}
	}
	
	@SuppressWarnings("unchecked")
	public void clearData()
	{
		this.data = (T[][]) new Object[rowSize][columnSize];
	}
	
	/**
	 * 
	 * @param row
	 * @param column
	 * @return
	 * @throws ArrayIndexOutOfBoundsException
	 */
	public T getData(int row, int column) throws ArrayIndexOutOfBoundsException
	{
		if(row >= 0 && row < rowSize && column < columnSize)
		{
			return this.data[row][column];
		}
		else
		{
			throw new ArrayIndexOutOfBoundsException(
				"row("+row+") and column("+column+") is greater than rowSize("+
						rowSize+") and columnSize("+columnSize+")");
		}
	}
	
	/**
	 * 
	 * @param row
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Object[] getRowData(int row) throws ArrayIndexOutOfBoundsException
	{
		if(row >= 0 && row < rowSize)
		{
			T [] rowData = (T[]) new Object[columnSize];
			System.arraycopy(data[row], 0, rowData, 0, rowSize);
			return rowData;
		}
		else
		{
			throw new ArrayIndexOutOfBoundsException(
				"row("+row+") is greater than rowSize("+rowSize+")");
		}
	}
	
	/**
	 * 
	 * @param column
	 * @return
	 * @throws ArrayIndexOutOfBoundsException
	 */
	@SuppressWarnings("unchecked")
	public Object[] getColumnData(int column) throws ArrayIndexOutOfBoundsException
	{
		if(column < columnSize)
		{
			T [] columnData = (T[]) new Object[rowSize];
			for(int rowIndex = 0; rowIndex < rowSize; rowIndex++)
			{
				columnData[rowIndex] = getData(rowIndex, column);
			}
			
			return columnData;
		}
		else
		{
			throw new ArrayIndexOutOfBoundsException(
				"column("+column+") is greater than columnSize("+columnSize+")");
		}
	}
	
	/**
	 * 
	 * @param row
	 * @param column
	 * @param value
	 * @return
	 * @throws ArrayIndexOutOfBoundsException
	 */
	public T putData(int row, int column, T value)
	throws ArrayIndexOutOfBoundsException
	{
		T tempValue = null;
		
		if(row >= 0 && row < rowSize)
		{
			this.currentRow = row;
			this.currentColumn = column;
			
			tempValue = this.data[row][column];
			this.data[row][column] = value;
		}
		else
		{
			throw new ArrayIndexOutOfBoundsException(
				"row("+row+") and column("+column+") is greater than rowSize("+
						rowSize+") and columnSize("+columnSize+")");
		}
		
		return tempValue;
	}

	/**
	 * 
	 * @param value
	 * @throws ArrayIndexOutOfBoundsException
	 */
	public void addData(T value) throws ArrayIndexOutOfBoundsException
	{
		if(currentRow+1 >=rowSize && currentColumn >= columnSize)
		{
			throw new ArrayIndexOutOfBoundsException(
					"row("+currentRow+") and column("+currentRow+") is greater than rowSize("+
							rowSize+") and columnSize("+columnSize+")");
		}
		else if(currentColumn == columnSize && currentRow < rowSize)
		{
			currentColumn = 0;
			this.data[++currentRow][currentColumn++] = value;
		}
		else if(currentColumn < columnSize && currentRow < columnSize)
		{
			this.data[currentRow][currentColumn++] = value;
		}
	}
	
	/**
	 * 
	 * @param row
	 * @param values
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public T[] addRowData(int row, T[] values) throws ArrayIndexOutOfBoundsException
	{
		if(row < rowSize && values.length == columnSize)
		{
			T [] tempRowData = (T []) new Object[columnSize];
			System.arraycopy(data[row], 0, tempRowData, 0, columnSize);
			this.data[row] = values;
			return tempRowData;
		}
		else if(values.length == columnSize && row == rowSize)
		{
			
			T [][] tempGridData = (T[][]) new Comparable [row][columnSize];
			for(int index = 0; index < rowSize; index++)
			{
				System.arraycopy(data[index], 0, tempGridData, 0, rowSize);
			}
			
			this.data[row] = values;
				
			rowSize++;
			return null;
		}
		else
		{
			throw new ArrayIndexOutOfBoundsException(
					"row("+row+") and column("+values.length+
					") is not equal to rowSize("+rowSize+") and columnSize("+
					columnSize+")");
		}
	}
	
	/**
	 * 
	 * @param value
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public GridCell<T> locationOf(T value)
	{	
		for(int row = 0; row < rowSize; row++)
		{
			for(int column = 0; column < columnSize; column++)
			{
				T tempValue = this.data[row][column];
				if(tempValue instanceof Comparable)
				{
					Comparable<T> tempComparable = (Comparable<T>)tempValue;
					if(tempComparable.compareTo(value) == 0)
					{
						return new GridCell<T>(row, column, tempValue);
					}
				}
				else
				{
					if(this.equals(value))
					{
						return new GridCell<T>(row, column, tempValue);
					}
				}
			}
		}
		
		
		return null;
	}
	
	/**
	 * 
	 * @return
	 */
	public int rowSize()
	{
		return this.rowSize;
	}
	
	/**
	 * 
	 * @return
	 */
	public int columnSize()
	{
		return this.columnSize;
	}
	
	/**
	 * 
	 * @return
	 */
	public int gridSize()
	{
		return (this.rowSize * this.columnSize);
	}
	
	/**
	 * 
	 * @return
	 */
	public Object[][] toGridArray()
	{
		return (Object[][])this.data;
	}
	
	
	/**
	 * 
	 * @author louisweyrich
	 *
	 */
	public class GridCell<K>
	{
		private int row, column;
		private K value;
		
		public GridCell(int row, int column, K value)
		{
			this.row = row;
			this.column = column;
			this.value = value;
		}
		
		public int getRow()
		{
			return row;
		}
		
		public int getColumn()
		{
			return column;
		}
		
		public K getValue()
		{
			return value;
		}
	}
	
}
