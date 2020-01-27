/*
 * File: InvalidColumnException.java
 *
 */
package com.sos.mazerunner.util;


/**
 * ...
 * 
 * @author louis.weyrich
 */
public class InvalidColumnException extends RuntimeException
{

    /**
	 * 
	 */
	private static final long serialVersionUID = 9196378034417568361L;

	/**
     * 
     */
    public InvalidColumnException()
    {
        // do nothing
    }

    /**
     * @param arg0
     */
    public InvalidColumnException(String arg0)
    {
        super(arg0);
        // do nothing
    }

    /**
     * @param arg0
     */
    public InvalidColumnException(Throwable arg0)
    {
        super(arg0);
        // do nothing
    }

    /**
     * @param arg0
     * @param arg1
     */
    public InvalidColumnException(String arg0, Throwable arg1)
    {
        super(arg0, arg1);
        // do nothing
    }

}
