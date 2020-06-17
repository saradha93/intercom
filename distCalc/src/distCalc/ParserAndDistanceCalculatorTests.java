package distCalc;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.json.simple.JSONObject;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;


/**
 * The class with Junit tests 
 * 
 */
public class ParserAndDistanceCalculatorTests {
	JsonFileParser jsonFileParser=new JsonFileParser();
	DistanceCalculator distanceCalculator=new DistanceCalculator();
	ArrayList<JSONObject> jsonCustomerList=new ArrayList<JSONObject>();
	@Rule
	public final ExpectedException exception = ExpectedException.none();

	/**
	 * The test for the JsonFileParser class. 
	 * The test checks if every customer record is parsed as a JSONObject by asserting the number of records in the text file to the size of the result List
	 */
	@Test 
	public void assertFileParse() throws Exception{
		assertEquals(32,(jsonFileParser.parseJSON("lib\\customers.txt")).size());
	}

	/**
	 * The test for the DistanceCalculator class. 
	 * The test checks if a customer who lies within the distance limit is picked and added to the resultant map. 
	 * This is done by creating a JSONCustomerList with the record of a customer (who is within the distance limit) and checks the size of the resultant map. 
	 */
	@Test
	public void assertSelectCustomersInRadius(){
		JSONObject customerRecord = new JSONObject();
		customerRecord.put("user_id", new Long(5));
		customerRecord.put("name", "Nora Dempsey");
		customerRecord.put("latitude", "53.1302756");
		customerRecord.put("longitude", "-6.2397222");
		jsonCustomerList.add(customerRecord);
		assertEquals(1,(distanceCalculator.selectCustomersInRadius(jsonCustomerList,100.00,53.339428,-6.257664)).size());
	}

	/**
	 * The test to check Exception handling 
	 * The test checks if an Exception is thrown if an incorrect input filename is given - custo.txt 
	 */
	@Test
	public void textExcep() throws Exception{
		exception.expect(Exception.class);
		jsonFileParser.parseJSON("lib\\custo.txt");
	}
}



