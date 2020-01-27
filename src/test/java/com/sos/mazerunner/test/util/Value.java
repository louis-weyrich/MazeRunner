package com.sos.mazerunner.test.util;

public class Value implements Comparable<Value>
{
	private int value;

	public Value(int value)
	{
		this.value = value;
	}

	public int getValue()
	{
		return value;
	}

	@Override
	public int compareTo(Value value)
	{
		int result = 0;
		
		
		if(value.getValue() > this.value)
		{
			result = 1;
		}
		else if(value.getValue() < this.value)
		{
			result = -1;
		}
		
		
		return result;
	}
	
	public int hashCode()
	{
		return value;
	}
	
	public boolean equals(Object src)
	{
		if(src instanceof Value)
		{
			Value tempValue = (Value)src;
			return compareTo(tempValue) == 0;
		}
		
		return false;
	}

}
