package com.sos.mazerunner.strategy;

import java.util.List;

import com.sos.mazerunner.Cell;
import com.sos.mazerunner.CellType;
import com.sos.mazerunner.util.GridArray;

public class RightStrategyExecutor implements StrategyExecutor
{

	public RightStrategyExecutor()
	{
		// TODO Auto-generated constructor stub
	}

	@Override
	public void getCell(Cell cell, GridArray<Cell> grid, List<Cell> nextCellList)
	{
		if(cell.getColumn() < grid.columnSize() - 1)
		{
			Cell rightCell = grid.getData(cell.getRow(), cell.getColumn() + 1);
			if(rightCell.getType() == CellType.PATH || rightCell.getType() == CellType.GOAL)
			{
				nextCellList.add(rightCell);
			}
		}
	}

}
