package com.sos.mazerunner.strategy;

import java.util.List;

public abstract class Strategy
{
	private String name;
	
	public Strategy(String name)
	{
		this.name = name;
	}
	
	public String getStrategyName()
	{
		return this.name;
	}
	
	public abstract List <StrategyExecutor> getStrategy();
}
