/*
 * File: CSVParser.java
 *
 */
package com.sos.mazerunner.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;



/**
 * ...
 * 
 * @author louis.weyrich
 */
public class CSVParser implements Iterable <RowSet<String>>
{
    
    private List <RowSet<String>> rowData;
    private String [] headers = new String [0];
    @SuppressWarnings("unused")
	private int columnCount = 0;
    

    
    public CSVParser(File file, String [] headers, char delimiter) 
    throws FileNotFoundException, ParseException
    {
        this(file, delimiter, false);
        columnCount = headers.length;
        setHeaders(headers);
    }
    
    /**
     * @throws FileNotFoundException 
     * 
     */
    public CSVParser(File file, char delimiter, boolean hasHeaders) 
    throws FileNotFoundException, ParseException
    {
        rowData = new ArrayList <RowSet<String>> ();
        
        try
        {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            String row = null;
            
            while((row = bufferedReader.readLine()) != null){
                RowSet <String> dataList = new RowSet <String> ();
                
                int previousPosition = 0;
                boolean isQuoted = false;
                
                for(int index = 0; index < row.length(); index++){
                    char character = row.charAt(index);
                    
                    if(character == delimiter && !isQuoted){
                        String data = row.substring(previousPosition, index).trim();
                        dataList.addData(data);
                        previousPosition = index+1;
                        if(rowData.size() == 0){
                            columnCount++;
                        }
                    }else if(character == '\'' || character == '\"'){
                        isQuoted = !isQuoted;
                    }else if(index == (row.length()-1) && character != delimiter){
                        String data = row.substring(previousPosition, index+1).trim();
                        dataList.addData(data);
                        previousPosition = index+1;
                        if(rowData.size() == 0){
                            columnCount++;
                        }
                    }
                }
                
                
                if(hasHeaders && headers.length == 0){
                    headers = new String [dataList.size()];
                    for(int index = 0; index < dataList.size(); index++){
                        headers[index] = dataList.get(index);
                    }
                }else{
                    dataList.setHeaders(headers);
                    rowData.add(dataList);
                }
                
            }
            
            bufferedReader.close();
        }
        catch (IOException e)
        {
           throw new ParseException(e.getMessage(), rowData.size());
        }
        
        
        
    }
    
    public void setHeaders(String [] headers){
        this.headers = headers;
    }
    
    public String [] getHeaders(){
        return headers;
    }

    public RowSet <String> nextRow(){
        RowSet <String> row = rowData.get(0);
        rowData.remove(0);
        return row;
    }

    public boolean hasData(){
        return rowData.size() > 0;
    }
    
    public int getRowCount(){
        return rowData.size();
    }

    /**
     * @see java.lang.Iterable#iterator()
     */
    public Iterator<RowSet<String>> iterator()
    {
        return rowData.iterator();
    }

}
