package distCalc;


import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException; 
/**
 * The class with the function that handles reading input from the txt file
 * @author Saradha
 *
 */
public class JsonFileParser {

	/**
	 * The function that parses the text file 
	 * Returns an ArrayList of JSONObjects
	 * @param file The path of the input file that has the customer records
	 *
	 */
	public ArrayList<JSONObject> parseJSON(String file) throws Exception{
		//Storing the customer records as a list of JSONobjects
		ArrayList<JSONObject> jsonCustomerList=new ArrayList<JSONObject>();
		JSONObject obj;
		String line = null;
		//Using try-catch blocks to handle checked exceptions with file handling and parsing
		try {
			FileReader fileReader = new FileReader(file);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			while((line = bufferedReader.readLine()) != null) {
				obj = (JSONObject) new JSONParser().parse(line);
				jsonCustomerList.add(obj);
			}
			bufferedReader.close(); 
		}catch(Exception ex) {
			System.out.println("Caught Exception in JsonFileParser.class. Exception is: " + ex.getMessage());   
			throw ex;
		}
		return jsonCustomerList;   
	}
}