/*
 * File: RowSet.java
 *
 */
package com.sos.mazerunner.util;

import java.io.Serializable;
import java.util.Iterator;




/**
 * ...
 * 
 * @author louis.weyrich
 */
public class RowSet <E> implements Iterable <E>, Serializable
{
    private static final long serialVersionUID = -6610361039632414239L;

    @SuppressWarnings("unchecked")
    private E [] data = (E[]) new Object[0];
    private String [] headers = new String [0];
    private int size = 0;
    

    /**
     * 
     */
    public RowSet()
    {
        // Do Nothing
    }

    /**
     * 
     */
    public RowSet(int initialSize)
    {
        test(initialSize);
    }

    /**
     * 
     */
    public RowSet(String [] headers)
    {
        setHeaders(headers);
        test(headers.length);
    }

    protected void test(int count)
    {
        if(size + count >= data.length){
            @SuppressWarnings("unchecked")
            E [] tempArray = (E[]) new Object[size+count];
            if(size > 0)
            {
                System.arraycopy(data, 0, tempArray, 0, size);
            }
            
            data = tempArray;
        }
    }
    
    public void setHeaders(String [] headers)
    {
        this.headers = headers;
    }
    
    public String [] getHeaders()
    {
        return headers;
    }
    
    public void addData(E data) throws NullPointerException, DataException
    {
        if(headers.length > 0)
        {
            if(size+1 > headers.length)
            {
                throw new DataException("This surpasses the header column count("+headers.length+")");
            }
        }
        
        if(data != null)
        {
            test(1);
            this.data[size++] = data;
        }
        else
        {
            throw new NullPointerException("Data is null at column("+size+")");
        }
    }
    
    public E get(int index)
    {
        if(index >= size)
            throw new ArrayIndexOutOfBoundsException("Index("+index+") is out of bounds. Size("+size+")");
        
        return data[index];
    }
    
    
    public E get(String headerName)
    {
        int index = getHeaderIndex(headerName);
        
        if(index < 0)
        {
            throw new InvalidColumnException("Header ("+headerName+") does not exist.");
        }
        
        return get(index);
    }

    public int getInteger(int index)
    {
        E value = get(index);
        return new Integer(value.toString()).intValue();
     }
    
    
    public long getLong(int index)
    {
        E value = get(index);
        return new Long(value.toString()).longValue();
     }

    
    public float getFloat(int index)
    {
        E value = get(index);
        return new Float(value.toString()).floatValue();
     }

    
    public String getString(int index)
    {
        E value = get(index);
        return value.toString();
     }
    
    
    public boolean getBoolean(int index)
    {
        E value = get(index);
        return new Boolean(value.toString()).booleanValue();
     }
    
    
    public int getInteger(String headerName)
    {
        int index = getHeaderIndex(headerName);
        
        if(index < 0)
            throw new InvalidColumnException("Header ("+headerName+") does not exist.");
        
        return getInteger(index);
     }
    
    
    public long getLong(String headerName)
    {
        int index = getHeaderIndex(headerName);
        
        if(index < 0)
            throw new InvalidColumnException("Header ("+headerName+") does not exist.");
        
        return getLong(index);
     }

    
    public float getFloat(String headerName)
    {
        int index = getHeaderIndex(headerName);
        
        if(index < 0)
            throw new InvalidColumnException("Header ("+headerName+") does not exist.");
        
        return getFloat(index);
     }

    
    public String getString(String headerName)
    {
        int index = getHeaderIndex(headerName);
        
        if(index < 0)
            throw new InvalidColumnException("Header ("+headerName+") does not exist.");
        
        return getString(index);
     }
    
    
    public boolean getBoolean(String headerName)
    {
        int index = getHeaderIndex(headerName);
        
        if(index < 0)
            throw new InvalidColumnException("Header ("+headerName+") does not exist.");
        
        return getBoolean(index);
     }


    public int getHeaderIndex(String headerName)
    {
        int index = -1;
        for(String header : headers)
        {
            if(!header.equals(headerName))
            {
                index++;
            }
            else
            {
                index++;
                break;
            }
        }
        return index;
    }
    
    public Iterator <E> dataIterator()
    {
        return new Iterator <E> ()
        {
            
            private int currentIndex = 0;

            public boolean hasNext()
            {
                return (currentIndex < size);
            }

            public E next()
            {
                if(currentIndex > size)
                    throw new ArrayIndexOutOfBoundsException("Index is out of bounds for data");
                
                return data[currentIndex++];
            }

            public void remove()
            {
                throw new UnsupportedOperationException("Remove is not supported for Data Iterator.");
            }
            
        };
    }
    
    public Iterator <String> headerIterator()
    {
        return  new Iterator <String> ()
        {
            
            private int currentIndex = 0;

            public boolean hasNext()
            {
                return currentIndex < headers.length;
            }

            public String next()
            {
                if(currentIndex + 1 > headers.length)
                    throw new ArrayIndexOutOfBoundsException("Index is out of bounds for headers");
                
                return headers[currentIndex++];
            }

            public void remove()
            {
                throw new UnsupportedOperationException("Remove is not supported for Header Iterator.");
            }
            
        };
    }
    
    @Override
    public int hashCode()
    {
        return super.hashCode();
    }
    
    public int size()
    {
        return size;
    }

    /**
     * @see java.lang.Iterable#iterator()
     */
    public Iterator<E> iterator()
    {
        return dataIterator();
    }
}
