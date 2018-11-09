package core;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

public class Main {

	//static FileWriter writer;
    private static final String SAMPLE_CSV_FILE = "./sample.csv";
	/*
	 * Does not have the expansive formating and error handling
	 * of Apache Commons CSV (which is best for Microsoft Excel) or other open-source projects
	 * however I wanted to display the knowledge of fundamentals.
	*/
	
    public static void toCSVStandard(Writer write, ArrayList<String> values) throws IOException {

        boolean first = true;
        StringBuilder sb = new StringBuilder();
        for (String value : values) {
            if (!first) {
                sb.append(',');
            }
            sb.append(value);
            first = false;
        }
        sb.append("\n");
        write.append(sb.toString());
    }

    public static void usingHomebrewCSV(ArrayList<String> list) throws IOException
    {
    	//basic file writing declaration and initialization
    	String location = ".\\Homebrew.cvs";
    	FileWriter writer = new FileWriter(location);   
    	
    	/*
    	 * The default Collections.sort provided by java.util.Collections 
    	 * sorts in ascending order with great efficieny
    	 */
    	Collections.sort(list);
    	
    	//make the String Collection comply with CSV standard
    	toCSVStandard(writer, list);
    	
    	//close & flush
        writer.flush();
        writer.close();
    };
    
    	/*
    	 * using Apache, I decide to include it, since you stated your company uses Excel files
    	 * and Apache is a pretty popular.
    	 */
    public static void usingApacheCommonCSV(ArrayList<String> list) throws IOException {
    	String location = ".\\ApacheExample.cvs";
    	FileWriter writer = new FileWriter(location);  
    	
        try (
                CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT);
            ) {
        	
                csvPrinter.printRecord(list);
                csvPrinter.flush();            
            }
    };

    
	public static void main(String[] args) throws IOException {
	
	//create a collection and populate it with strings
	ArrayList<String> list = new ArrayList<String>();
	list.add("Abba");
	list.add("Cabba");
	list.add("Dabba");
	list.add("Fabba");
	list.add("Caaba");
	list.add("Babba");
	
	//using the CSV converter I made
	usingHomebrewCSV(list);
	
	//using the one provided by Apache
	usingApacheCommonCSV(list);
	

	}

}
