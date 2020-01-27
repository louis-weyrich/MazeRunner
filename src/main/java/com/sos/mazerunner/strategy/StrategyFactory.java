package com.sos.mazerunner.strategy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StrategyFactory
{
	private static StrategyFactory instance;
	private Map <String, Strategy> strategyMap;
	
	private StrategyFactory()
	{
		this.strategyMap = new HashMap <String, Strategy> ();
		for(String strategyName : buildStrategyList())
		{
			addStrategy(strategyName, this.strategyMap);
		}
		
	}
	
	public static StrategyFactory instance()
	{
		if(instance == null)
		{
			instance = new StrategyFactory();
		}
		
		return instance;
	}
	
	private void addStrategy(String name, Map <String, Strategy> strategyMap)
	{
		Strategy strategy = new Strategy(name) {

			@Override
			public List<StrategyExecutor> getStrategy()
			{
				List <StrategyExecutor> strategyList = new ArrayList <StrategyExecutor> ();
				
				for(int index = 0; index < name.length(); index++)
				{
					switch(name.charAt(index))
					{
						case 'L' : {
							strategyList.add(new LeftStrategyExecutor());
							break;
						}
						case 'R' : {
							strategyList.add(new RightStrategyExecutor());
							break;
						}
						case 'T' : {
							strategyList.add(new TopStrategyExecutor());
							break;
						}
						case 'B' : {
							strategyList.add(new BottomStrategyExecutor());
							break;
						}
						default : {
							
						}
					}
				}

				return strategyList;
			}
			
		};
		
		strategyMap.put(strategy.getStrategyName(), strategy);
	}
	
	public Strategy getStrategyByName(String name)
	{
		return this.strategyMap.get(name);
	}
	
	public List <String> buildStrategyList()
	{
		List <String> strategyList = new ArrayList <String> ();
		
		// LEFT
		strategyList.add("LRTB");
		strategyList.add("LRBT");
		strategyList.add("LTRB");
		strategyList.add("LTBR");
		strategyList.add("LBTR");
		strategyList.add("LBRT");
		
		//RIGHT
		strategyList.add("RLTB");
		strategyList.add("RLBT");
		strategyList.add("RTLB");
		strategyList.add("RTBL");
		strategyList.add("RBTL");
		strategyList.add("RBLT");
		
		// TOP
		strategyList.add("TRLB");
		strategyList.add("TRBL");
		strategyList.add("TLRB");
		strategyList.add("TLBR");
		strategyList.add("TBRL");
		strategyList.add("TBLR");
		
		// BOTTOM
		strategyList.add("BRTL");
		strategyList.add("BRLT");
		strategyList.add("BTLR");
		strategyList.add("BTRL");
		strategyList.add("BLTR");
		strategyList.add("BLRT");
		
		return strategyList;
	}

}
