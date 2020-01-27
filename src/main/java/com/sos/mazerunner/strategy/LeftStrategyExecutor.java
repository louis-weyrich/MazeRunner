/**
 * 
 */
package com.sos.mazerunner.strategy;

import java.util.List;

import com.sos.mazerunner.Cell;
import com.sos.mazerunner.CellType;
import com.sos.mazerunner.util.GridArray;

/**
 * @author louisweyrich
 *
 */
public class LeftStrategyExecutor implements StrategyExecutor
{

	/**
	 * 
	 */
	public LeftStrategyExecutor()
	{
		// TODO Auto-generated constructor stub
	}

	@Override
	public void getCell(Cell cell, GridArray <Cell> grid, List <Cell> nextCellList)
	{
		
		if(cell.getColumn() >= 0)
		{
			Cell leftCell = grid.getData(cell.getRow(), cell.getColumn() - 1);
			if(leftCell.getType() == CellType.PATH || leftCell.getType() == CellType.GOAL)
			{
				nextCellList.add(leftCell);
			}
		}
		
	}

}
