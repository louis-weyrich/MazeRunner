package com.sos.mazerunner;

import java.awt.Color;

public enum CellType
{
	WALL(Color.BLACK, 'W'), PATH(Color.WHITE, 'P'), GOAL(Color.YELLOW, 'G'),
	VISITED(Color.darkGray, 'V'), TRAVELED(Color.green, 'T'), FOUND(Color.red, 'F');
	
	private Color color;
	private char typeChar;
	
	private CellType(Color color, char typeChar)
	{
		this.color = color;
		this.typeChar = typeChar;
	}
	
	public char getTypeChar()
	{
		return typeChar;
	}

	public Color getColor() 
	{
		return this.color;
	}
}
