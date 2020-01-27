package com.sos.mazerunner.strategy;

import java.util.List;

import com.sos.mazerunner.Cell;
import com.sos.mazerunner.CellType;
import com.sos.mazerunner.util.GridArray;

public class TopStrategyExecutor implements StrategyExecutor
{

	public TopStrategyExecutor()
	{
		// TODO Auto-generated constructor stub
	}

	@Override
	public void getCell(Cell cell, GridArray<Cell> grid, List<Cell> nextCellList)
	{
		if(cell.getRow() >  0)
		{
			Cell topCell = grid.getData(cell.getRow() - 1, cell.getColumn());
			if(topCell.getType() == CellType.PATH || topCell.getType() == CellType.GOAL)
			{
				nextCellList.add(topCell);
			}
		}

	}

}
