package distCalc;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Properties;
import java.util.TreeMap;

import org.json.simple.JSONObject;

/**
 * The class with the function that performs the selection of customers based on the distance criterion
 * 
 * @author Saradha
 *
 */
public class DistanceCalculator {
	Map<Long,String> invitees= new TreeMap<Long,String>();
	public static final int EARTH_RADIUS=6371;
	//Radius of the earth has been taken from the mentioned Wikipedia article as 6371 kms (Mean Earth Radius), which is guaranteed correct within 0.5%  */

	/**
	 * The function that filters customers whose distance is within the distance limit. 
	 * Returns a TreeMap that sorts the elements by Key(user_id)
	 * @param customerList An ArrayList of JSONObjects that has customer information
	 * @param distanceLimit The limit within which customers are to be invited
	 * @param officeLatitude The latitude of office location
	 * @param officeLongitude The longitude of office location
	 */
	public Map<Long,String> selectCustomersInRadius(ArrayList<JSONObject> customerList, Double distanceLimit, Double officeLatitude, Double officeLongitude){
		for(JSONObject jsonObj:customerList){
			Long customerId=(Long)jsonObj.get("user_id");
			String customerName=(String)jsonObj.get("name");
			Double customerLatitude=Double.parseDouble((String)jsonObj.get("latitude"));
			Double customerLongitude=Double.parseDouble((String)jsonObj.get("longitude"));

			double distance=calculateDistance(officeLatitude,officeLongitude,customerLatitude,customerLongitude);
			if(distance<=distanceLimit)
				invitees.put(customerId,customerName);
		}
		return invitees;
	}


	/**
	 * The function that calculates Greater Circle Distance between two points 
	 * Using the first formula from - https://en.wikipedia.org/wiki/Great-circle_distance
	 * cAngle = arc-cosine( (sin(long1) sin(long2)) + (cos(long1) cos(long2) cos(Abs difference of latitudes))) ; distance=radius * cAngle
	 * Returns the distance as a double value. 
	 * @param officeLatitude The latitude of office location
	 * @param officeLongitude The longitude of office location
	 * @param customerLatitude The latitude of the customer
	 * @param customerLongitude The longitude of the customer
	 */
	public double calculateDistance(Double officeLatitude,Double officeLongitude,Double customerLatitude,Double customerLongitude){
		//Converting the latitude and longitude to Radians
		officeLatitude=Math.toRadians(officeLatitude);
		officeLongitude=Math.toRadians(officeLongitude);
		customerLatitude=Math.toRadians(customerLatitude);
		customerLongitude=Math.toRadians(customerLongitude);
		double latitudeDifference=customerLatitude-officeLatitude;
		double centralAngle=Math.acos((Math.sin(officeLongitude)*Math.sin(customerLongitude)) + (Math.cos(officeLongitude)*Math.cos(customerLongitude)* Math.cos(Math.abs(latitudeDifference))));
		double distance=centralAngle*EARTH_RADIUS;
		return distance;

	}
}


