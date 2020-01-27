package com.sos.mazerunner.test.util;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import com.sos.mazerunner.util.GridArray;
import com.sos.mazerunner.util.GridArray.GridCell;

class GridArrayTest
{

	@Test
	void testGridArrayIntInt()
	{
		GridArray <Value> grid = new GridArray <Value> (10,10);
		int count = 1;
		
		for(int row = 0; row < 10; row++)
		{
			for(int column = 0; column < 10; column++)
			{
				grid.putData(row, column, new Value(count++));
			}
		}
		
		Value value = grid.getData(5, 5);
		assertNotNull(value);
		assertTrue(value.getValue() == 56);
		Object [][] array = grid.toGridArray();
		assertTrue(array.length == 10);
		
	}

	@Test
	void testGridArrayTArrayArray()
	{
		int count = 1;
		Value [][] data = new Value[10][10];
		
		for(int row = 0; row < 10; row++)
		{
			for(int column = 0; column < 10; column++)
			{
				data[row][column] = new Value(count++);
			}
		}
		
		GridArray <Value> grid = new GridArray <Value> (data);
		assertTrue(grid.gridSize() == 100);
		assertTrue(grid.rowSize() == 10);
		assertTrue(grid.columnSize() == 10);
		assertNotNull(grid.getData(5, 5));
		Value value = grid.getData(5, 5);
		assertTrue(value.getValue() == 56);
	}

	@Test
	void testGetRowData()
	{
		int count = 1;
		Value [][] data = new Value[10][10];
		
		for(int row = 0; row < 10; row++)
		{
			for(int column = 0; column < 10; column++)
			{
				data[row][column] = new Value(count++);
			}
		}
		
		GridArray <Value> grid = new GridArray <Value> (data);
		Object [] rowData = grid.getRowData(3);
		assertTrue(rowData.length == 10);
		assertTrue( ((Value)rowData[9]).getValue() == 40);
	}

	@Test
	void testGetColumnData()
	{
		int count = 1;
		Value [][] data = new Value[10][10];
		
		for(int row = 0; row < 10; row++)
		{
			for(int column = 0; column < 10; column++)
			{
				data[row][column] = new Value(count++);
			}
		}
		
		GridArray <Value> grid = new GridArray <Value> (data);
		Object [] columnData = grid.getColumnData(9);
		assertTrue(columnData.length == 10);
		assertTrue( ((Value)columnData[9]).getValue() == 100);
	}

	@Test
	void testPutData()
	{
		int count = 1;
		Value [][] data = new Value[10][10];
		
		for(int row = 0; row < 10; row++)
		{
			for(int column = 0; column < 10; column++)
			{
				data[row][column] = new Value(count++);
			}
		}
		
		GridArray <Value> grid = new GridArray <Value> (data);
		Value value = grid.putData(9, 9, new Value(1000));
		assertNotNull(value);
		assertTrue(value.getValue() == 100);
		assertTrue(grid.getData(9, 9).getValue() == 1000);
	}

	@Test
	void testAddDataT()
	{
		GridArray <Value> grid = new GridArray <Value> (10,10);
		int count = 1;
		
		for(int row = 0; row < 10; row++)
		{
			for(int column = 0; column < 10; column++)
			{
				grid.addData(new Value(count++));
			}
		}
		
		Value value = grid.getData(9, 9);
		assertNotNull(value);
		assertTrue(value.getValue() == 100);
		value = grid.getData(5, 5);
		assertNotNull(value);
		assertTrue(value.getValue() == 56);
	}

	@Test
	void testAddRowData()
	{
		fail("Not yet implemented");
	}

	@Test
	void testLocationOf()
	{
		GridArray <Value> grid = new GridArray <Value> (10,10);
		int count = 1;
		
		for(int row = 0; row < 10; row++)
		{
			for(int column = 0; column < 10; column++)
			{
				grid.addData(new Value(count++));
			}
		}
		
		GridCell cell = grid.locationOf(new Value(56));
		assertNotNull(cell);
		assertTrue(cell.getRow() == 5);
		assertTrue(cell.getColumn() == 5);
	}

	@Test
	void testGridSize()
	{
		GridArray <Value> grid = new GridArray <Value> (10,10);
		assertTrue(grid.gridSize() == 100);
	}

	@Test
	void testToGridArray()
	{
		fail("Not yet implemented");
	}

}
