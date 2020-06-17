package distCalc;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Properties;

import org.json.simple.JSONObject;

/**
 * The class with the main() which serves as the entry
 * Invokes methods from JsonFileParser and DistanceCalculator classes
 * Configurable parameters are fetched from a properties file
 * @author Saradha
 *
 */
public class Invoker {
	private static String PROPFILE_PATH="resources\\configuration.properties";

	public static void main(String[] args) {
		JsonFileParser jsonFileParser=new JsonFileParser();
		DistanceCalculator distanceCalculator=new DistanceCalculator();
		Properties prop = new Properties();

		//Using try-catch blocks to handle checked and runtime exceptions, if any
		try{
			FileInputStream fileInputStream= new FileInputStream(PROPFILE_PATH);
			prop.load(fileInputStream);

			String filename=prop.getProperty("filename");
			Double distanceLimit=Double.parseDouble(prop.getProperty("distanceLimit"));
			Double officeLatitude=Double.parseDouble(prop.getProperty("officeLatitude"));
			Double officeLongitude=Double.parseDouble(prop.getProperty("officeLongitude"));

			//Invoking method that reads customer records as a list of JSONObjects from txt file 
			ArrayList<JSONObject> jsonList=jsonFileParser.parseJSON(filename);

			//Invoking method to identify and output customers within the specified distance
			Map<Long,String> invitees=distanceCalculator.selectCustomersInRadius(jsonList,distanceLimit,officeLatitude,officeLongitude);


			//Displaying the customers in the format - 'user_id, name'
			for(Map.Entry<Long, String> entry : invitees.entrySet()){
				System.out.println(entry.getKey()+", " + entry.getValue()); 
			} 

		}catch(Exception ex) {
			System.out.println("Caught Exception" + ex.getMessage());   
		} 
	}
}
