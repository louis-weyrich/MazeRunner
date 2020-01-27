package com.sos.mazerunner.strategy;

import java.util.List;

import com.sos.mazerunner.Cell;
import com.sos.mazerunner.util.GridArray;

public interface StrategyExecutor
{
	public void getCell(Cell cell, GridArray <Cell> grid, List <Cell> nextCellList);
}
