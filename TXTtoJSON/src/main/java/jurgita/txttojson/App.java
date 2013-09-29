package jurgita.txttojson;

/**
 * TXTtoJSON - app converts given txt file to JSON format. 
 */

import java.io.BufferedReader;
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
		String input_file = args[0];
		String output_file = args[1];
		JSONArray countries = new JSONArray();
		
		readFromFile(countries, input_file);
		JSONObject obj = new JSONObject();
		obj.put("countries", countries);
		
		writeToFile(obj, output_file);
    	System.out.print(obj);	 
}
	
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
	
	private static void readFromFile(JSONArray list, String input_file) throws IOException{
		BufferedReader br = null;
		try {
			String sCurrentLine;
			//br = new BufferedReader(new FileReader(input_file));
			br = new BufferedReader(new InputStreamReader(new FileInputStream(input_file), encoding));
			while ((sCurrentLine = br.readLine()) != null) {
				String item[] = sCurrentLine.split(field_separator);
		        Country con = new Country(item[1], item[0]);
		        list.put(new JSONObject (con));     
			}
			br.close();
 
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null)br.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}	
	}//read from file
}
