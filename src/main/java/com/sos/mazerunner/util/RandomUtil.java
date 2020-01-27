package com.sos.mazerunner.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomUtil
{
	private List <Integer> numberList;
	private int min, max;
	
		
	public RandomUtil(int min, int max)
	{
		this.max = max;
		this.min = min;
	}
	

	public static int generateRandom(int min, int max)
	{
		return new Random().nextInt(max - min) + min;
	}


	public int generateRandom()
	{
		return RandomUtil.generateRandom(getMin(), getMax());
	}
	
	public int generateUniqueRandom()
	{
		if(numberList == null)
		{
			numberList = new ArrayList <Integer> ();
		}
		
		int randomNumber = generateRandom();
		
		if(numberList.contains(new Integer(randomNumber)))
		{
			randomNumber = generateUniqueRandom();
		} 
		
		numberList.add(new Integer(randomNumber));
		
		return randomNumber;
	}
	
	public int getMin()
	{
		return min;
	}

	public void setMin(int min)
	{
		this.min = min;
	}

	public int getMax()
	{
		return max;
	}

	public void setMax(int max)
	{
		this.max = max;
	}

}
