package com.sos.mazerunner;

public class SolveResults
{
	private int columns;
	private int rows;
	private int visitedCount;
	private int pathCount;
	private String strategyName;

	public SolveResults()
	{
		// TODO Auto-generated constructor stub
	}

	public int getColumns()
	{
		return columns;
	}

	public void setColumns(int columns)
	{
		this.columns = columns;
	}

	public int getRows()
	{
		return rows;
	}

	public void setRows(int rows)
	{
		this.rows = rows;
	}

	public int getVisitedCount()
	{
		return visitedCount;
	}

	public void setVisitedCount(int visitedCount)
	{
		this.visitedCount = visitedCount;
	}

	public int getPathCount()
	{
		return pathCount;
	}

	public void setPathCount(int pathCount)
	{
		this.pathCount = pathCount;
	}

	public String getStrategyName()
	{
		return strategyName;
	}

	public void setStrategyName(String strategyName)
	{
		this.strategyName = strategyName;
	}
	
	public String toString()
	{
		StringBuilder builder = new StringBuilder();
		
		builder.append("Row[").append(this.rows).append("] columns[")
			.append(this.columns).append("] strategy[").append(this.strategyName)
			.append("] visited[").append(this.visitedCount).append("] pathCount[")
			.append(this.pathCount).append("]");
		
		return builder.toString();
	}

}
