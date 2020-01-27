/*
 * File: DataException.java
 *
 */
package com.sos.mazerunner.util;


/**
 * ...
 * 
 * @author louis.weyrich
 */
public class DataException extends RuntimeException
{

    /**
	 * 
	 */
	private static final long serialVersionUID = -3562076638080740665L;

	/**
     * 
     */
    public DataException()
    {
        // do nothing
    }

    /**
     * @param arg0
     */
    public DataException(String arg0)
    {
        super(arg0);
        // do nothing
    }

    /**
     * @param arg0
     */
    public DataException(Throwable arg0)
    {
        super(arg0);
        // do nothing
    }

    /**
     * @param arg0
     * @param arg1
     */
    public DataException(String arg0, Throwable arg1)
    {
        super(arg0, arg1);
        // do nothing
    }

}
