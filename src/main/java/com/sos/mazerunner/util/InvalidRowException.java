/*
 * File: InvalidRowException.java
 *
 */
package com.sos.mazerunner.util;


/**
 * ...
 * 
 * @author louis.weyrich
 */
public class InvalidRowException extends Exception
{

    /**
	 * 
	 */
	private static final long serialVersionUID = -5142277747044838503L;

	/**
     * 
     */
    public InvalidRowException()
    {
        // do nothing
    }

    /**
     * @param arg0
     */
    public InvalidRowException(String arg0)
    {
        super(arg0);
        // do nothing
    }

    /**
     * @param arg0
     */
    public InvalidRowException(Throwable arg0)
    {
        super(arg0);
        // do nothing
    }

    /**
     * @param arg0
     * @param arg1
     */
    public InvalidRowException(String arg0, Throwable arg1)
    {
        super(arg0, arg1);
        // do nothing
    }

}
