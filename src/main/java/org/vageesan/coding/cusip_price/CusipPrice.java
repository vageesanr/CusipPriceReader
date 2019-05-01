package org.vageesan.coding.cusip_price;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;
import org.apache.commons.lang.StringUtils;

public class CusipPrice 
{
public static void main(String args[]) throws IOException {
		
		CusipPrice main = new CusipPrice();
        File file1 = main.getFileFromResources("cusipWrite.txt");
        

        if(file1==null) {
        	System.out.println("No File 1 found");
        }
        Map<String, Double> mapfile1  = main.getFirstFileMap(file1);
        for(String key : mapfile1.keySet()) {
        	System.out.println("Cusip: "+key+"\tLast Price: "+mapfile1.get(key));
        }

        
        
	}

	
	 // get file from classpath, resources folder
    public File getFileFromResources(String fileName) {

        ClassLoader classLoader = getClass().getClassLoader();

        URL resource = classLoader.getResource(fileName);
        if (resource == null) {
            throw new IllegalArgumentException("file is not found!");
        } else {
            return new File(resource.getFile());
        }

    }
    
    public Map<String, Double> getFirstFileMap(File file1) throws IOException{
    	Map<String, Double> mapfile1 = new HashMap<String, Double>();
		LineIterator it = FileUtils.lineIterator(file1, "UTF-8");
		double lastPrice=0.0;
		String prevCusipString=null;
    	try {

    		while (it.hasNext()) {
    			String line = it.nextLine();
    			if(StringUtils.isAlphanumeric(line) && line.length()==8) {
    				if(prevCusipString!=null) {
        				mapfile1.put(prevCusipString, lastPrice);
    				}
    				prevCusipString=line.trim();
    			}else {
    				try {
    					lastPrice=Double.parseDouble(line.trim());
    				}catch(NumberFormatException nfe) {
    					System.out.println(line+" is not a valid price");
    				}
    			}
            }
    		mapfile1.put(prevCusipString, lastPrice);
        }
    	finally {
    		it.close();
        }
    	return mapfile1;
    }
    

}
