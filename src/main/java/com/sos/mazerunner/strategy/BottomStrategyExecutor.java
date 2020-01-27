package com.sos.mazerunner.strategy;

import java.util.List;

import com.sos.mazerunner.Cell;
import com.sos.mazerunner.CellType;
import com.sos.mazerunner.util.GridArray;

public class BottomStrategyExecutor implements StrategyExecutor
{

	public BottomStrategyExecutor()
	{
		// TODO Auto-generated constructor stub
	}

	@Override
	public void getCell(Cell cell, GridArray<Cell> grid, List<Cell> nextCellList)
	{
		if(cell.getRow() < grid.rowSize() - 1)
		{
			Cell bottomCell = grid.getData(cell.getRow() + 1, cell.getColumn());
			if(bottomCell.getType() == CellType.PATH || bottomCell.getType() == CellType.GOAL)
			{
				nextCellList.add(bottomCell);
			}
		}

	}

}
