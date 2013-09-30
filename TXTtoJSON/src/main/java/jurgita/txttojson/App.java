package jurgita.txttojson;

/**
 * TXTtoJSON - app converts given txt file(file format: <contry name>;<ISO code>) to JSON format. 
 */

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import jurgita.txttojson.Country;

public class App 
{
	private static String encoding = "UTF8";
	private static String field_separator = ";";
	
	public static void main( String[] args ) throws JSONException, IOException{		
		String input_file;
		try
		{
			 input_file = args[0];
		}catch (ArrayIndexOutOfBoundsException e){
			System.out.println("USAGE: \n"
					+ " java -jar TXTtoJSON-1.0.jar <source_file.txt> ");
			return;
		}
		
		//check if passed argument is a file.
		File f=new File(input_file);
		if ( f.isDirectory() || !f.exists() || f.length()==0 ){
			System.out.println("There is no such file or it is empty! Please check and pass valid *.txt file");
			return;
		}
		//
		String output_file = input_file.replace(".txt", ".json"); 
		
		JSONArray countries = new JSONArray();
		readFromFile(countries, input_file);
		
		JSONObject obj = new JSONObject();
		obj.put("countries", countries);
		
		writeToFile(obj, output_file);
		System.out.println("JSON file "+output_file+" was created!");
}
	/*
	 * JSON object is written to result file.
	 */
	
	private static void writeToFile(JSONObject obj, String output_file) throws IOException, JSONException{
		try {
    		FileWriter file = new FileWriter(output_file);
    		file.write(obj.toString(4));
    		file.flush();
    		file.close();
    	} catch (IOException e) {
    		e.printStackTrace();
    	}
	}//writeToFile
	
	/*
	 * Information is read from a file.
	 */
	
	private static void readFromFile(JSONArray list, String input_file) throws IOException{
		BufferedReader br = null;
		try {
			String sCurrentLine;
			br = new BufferedReader(new InputStreamReader(new FileInputStream(input_file), encoding));
			while ((sCurrentLine = br.readLine()) != null) {
				String item[] = sCurrentLine.split(field_separator);
		        Country con = new Country(item[1], item[0]);
		        list.put(new JSONObject (con));     
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null)
					br.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}	
	}//read from file
}
