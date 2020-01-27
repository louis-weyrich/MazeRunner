/**
 * 
 */
package com.sos.mazerunner;

import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import com.sos.mazerunner.strategy.Strategy;
import com.sos.mazerunner.strategy.StrategyExecutor;
import com.sos.mazerunner.strategy.StrategyFactory;
import com.sos.mazerunner.util.GridArray;

/**
 * @author louisweyrich
 *
 */
public class Maze extends JPanel
{

	private GridArray <Cell> grid;
	private List <Cell> visited;
	private Strategy strategy;
	
	
	/**
	 * 
	 */
	public Maze()
	{
		visited = new ArrayList <Cell> ();
	}
	
	/**
	 * 
	 * @return
	 */
	public GridArray <Cell> getGridArray()
	{
		return grid;
	}
	
	/**
	 * 
	 * @param grid
	 */
	public void setGridArray(GridArray <Cell> grid)
	{
		this.grid = grid;
	}
	
	public void setStrategy(Strategy strategy)
	{
		this.strategy = strategy;
	}

	/**
	 * 
	 * @param cell
	 */
	public void addCell(Cell cell)
	{
		grid.addData(cell);
	}
	
	/**
	 * 
	 */
	public void clearResults()
	{
		visited.clear();
	}
	
	/**
	 * 
	 */
	@Override
	public void paint(Graphics g)
	{
		super.paint(g);
		
		if(grid != null)
		{
			Dimension size = this.getSize();
			int cellWidth = (int)size.width/grid.columnSize();
			int cellHeight = (int)size.height/grid.rowSize();
			
			for(int row = 0; row < grid.rowSize(); row++)
			{
				for(int column = 0; column < grid.columnSize(); column++)
				{
					Cell cell = grid.getData(row, column);
					if(cell != null)
					{
						g.setColor(cell.getType().getColor());
						g.fillRect(column*cellWidth, row*cellHeight, cellWidth, cellHeight);
					}
				}
			}
		}
	}
	
	/**
	 * 
	 */
	public SolveResults solve()
	{
		clearResults();
		List <Cell> entrances = findEntrance();
		int pathCount = 0;
		for(Cell cell : entrances)
		{
			SearchResults result = travelPath(cell);
			if(result.isFound())
			{
				tracePath(result.getPath());
				pathCount = result.getPath().size();
				break;
			}
			else
			{
				this.visited.addAll(result.getPath());
				result.getPath().clear();
			}
		}
		
		
		SolveResults solveResults = new SolveResults();
		solveResults.setColumns(grid.columnSize());
		solveResults.setRows(grid.rowSize());
		solveResults.setPathCount(pathCount);
		solveResults.setVisitedCount(this.visited.size());
		if(this.strategy != null)
		{
			solveResults.setStrategyName(this.strategy.getStrategyName());
		}
		else
		{
			solveResults.setStrategyName("LRTB");
		}
		
		this.clearResults();
		repaint();
		
		return solveResults;
	}
	
	 
	
	/**
	 * 
	 * @return
	 */
	public List <Cell> findEntrance()
	{
		List <Cell> cells = new ArrayList <Cell> ();
		
		
		if(strategy == null)
		{
			strategy = StrategyFactory.instance().getStrategyByName("LRTB");
		}

		
		if(grid != null && grid.columnSize() > 0 && grid.rowSize() > 0)
		{
			String name = strategy.getStrategyName();
			for(int index = 0; index < name.length(); index++)
			{
				char character = name.charAt(index);
				
				switch(character)
				{
					case 'T' : {
						Object [] topRowData = grid.getRowData(0);
						for(int column = 0; column < topRowData.length; column++)
						{
							Cell cell = (Cell)topRowData[column];
							if(cell.getType() == CellType.PATH || cell.getType() == CellType.GOAL)
							{
								cells.add(cell);
							}
						}
					}
					
					case 'L' : {
						Object [] leftColumnData = grid.getColumnData(0);
						for(int row = 0; row < leftColumnData.length; row++)
						{
							Cell cell = (Cell)leftColumnData[row];
							if(cell.getType() == CellType.PATH || cell.getType() == CellType.GOAL)
							{
								cells.add(cell);
							}
						}
					}
					
					case 'B' : {
						Object [] bottomRowData = grid.getRowData(grid.rowSize() - 1);
						for(int column = 0; column < bottomRowData.length; column++)
						{
							Cell cell = (Cell)bottomRowData[column];
							if(cell.getType() == CellType.PATH || cell.getType() == CellType.GOAL)
							{
								cells.add(cell);
							}
						}
					}
					
					case 'R' : {
						Object [] rightColumnData = grid.getColumnData(grid.rowSize() - 1);
						for(int row = 0; row < rightColumnData.length; row++)
						{
							Cell cell = (Cell)rightColumnData[row];
							if(cell.getType() == CellType.PATH || cell.getType() == CellType.GOAL)
							{
								cells.add(cell);
							}
						}
					}
				}
			}
		}

		return cells;
	}
	
	/**
	 * 
	 * @param cell
	 * @return
	 */
	public SearchResults travelPath(Cell cell)
	{
		SearchResults result = new SearchResults();
		return move(cell, result);
	}
	
	/**
	 * 
	 * @param cell
	 * @param result
	 * @return
	 */
	public SearchResults move(Cell cell, SearchResults result)
	{
		
		if(cell.getType() == CellType.GOAL)
		{
			result.getPath().add(cell);
			result.setFound(true);
			cell.setType(CellType.FOUND);
			return result;
		}
		else if(cell.getType() == CellType.PATH)
		{
			cell.setType(CellType.VISITED);
			result.getPath().add(cell);
		}
		
		
		this.visited.add(cell);
		List <Cell> avaialblePath = findAvailablePath(cell);
		
		for(Cell nextCell : avaialblePath)
		{
			if(avaialblePath.size() > 1)
			{
				SearchResults decisionResults = move(nextCell, new SearchResults());
				if(decisionResults.isFound())
				{
					result.getPath().addAll(decisionResults.getPath());
					result.setFound(true);
					break;
				}
			}
			else
			{
				result = move(nextCell, result);
				if(result.isFound())
				{
					break;
				}
			}
		}
		return result;
	}
	
	/**
	 * 
	 * @param cell
	 * @return
	 */
	public List<Cell> findAvailablePath(Cell cell)
	{
		List<Cell> availableCells = new ArrayList <Cell> ();
		
		if(strategy == null)
		{
			strategy = StrategyFactory.instance().getStrategyByName("LRTB");
		}
		
		for(StrategyExecutor executor : strategy.getStrategy())
		{
			executor.getCell(cell, grid, availableCells);
		}
		
		
		return availableCells;
	}
	
	/**
	 * 
	 * @param tracePath
	 */
	public void tracePath(List <Cell> tracePath)
	{
		tracePath.stream().forEach(e -> changeCellColor(e));
	}

	/**
	 * 
	 * @param cell
	 */
	public void changeCellColor(Cell cell)
	{
		if(cell.getType() == CellType.PATH || cell.getType() == CellType.VISITED)
		{
			cell.setType(CellType.TRAVELED);
		}
		else if(cell.getType() == CellType.GOAL)
		{
			cell.setType(CellType.FOUND);
		}
	}
	
	
	/**
	 * 
	 * @author louisweyrich
	 *
	 */
	private class SearchResults
	{
		private List <Cell> path;
		private boolean found;
		
		public SearchResults()
		{
			
		}
		
		public List<Cell> getPath()
		{
			if(path == null)
			{
				path = new ArrayList <Cell> ();
			}
			return path;
		}

		public void setPath(List<Cell> path)
		{
			this.path = path;
		}

		public void setFound(boolean found)
		{
			this.found = found;
		}

		public boolean isFound()
		{
			return this.found;
		}
	}
}
