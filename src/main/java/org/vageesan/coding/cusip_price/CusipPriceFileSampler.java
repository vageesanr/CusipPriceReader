package org.vageesan.coding.cusip_price;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.Random;

public class CusipPriceFileSampler {
	
	public static void main(String args[]) {
		CusipPriceFileSampler main = new CusipPriceFileSampler();
		main.writeSampleFile();
	}

	public void writeSampleFile()  {
		
		File file1 = getFileFromResources("cusipWrite.txt");
        try {
			FileWriter file = new FileWriter(file1);
	        Random r = new Random();
	    	//	StringBuilder cusip = new StringBuilder("");
	
	    	for(int i=1; i<10000; i++) {
		    	StringBuilder cusip = new StringBuilder("");
	        	if(i!=1)
	            	cusip.append("\nCU");
	        	else
	        		cusip.append("CU");
	        	int len = Integer.toString(i).length();
	        	for(int j=1;j<=6-len;j++) {
	        		cusip.append("0");
	        	}
	        	cusip.append(i);
	        	System.out.print(cusip.toString());
	        	file.write(cusip.toString());
	        	for(int k=1; k<r.nextInt(100); k++) {
	        		double price = 1.0+(r.nextDouble()*499);
	        		System.out.print("\n"+Double.toString(price));
	        		file.write("\n"+Double.toString(price));
	        		cusip.append("\n").append(Double.toString(price));
	        	}
	        }
	    	file.close();
        }catch(IOException e) {
        	e.printStackTrace();
        }
        
        //writeToFile(file1, cusip.toString());
	}
	
	public void writeToFile(File file1, String data) {
    	FileWriter fr = null;
        BufferedWriter br = null;
		try{
            fr = new FileWriter(file1.getPath());
            br = new BufferedWriter(fr);
            br.write("Sample");
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            try {
                br.close();
                fr.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
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
	
}
