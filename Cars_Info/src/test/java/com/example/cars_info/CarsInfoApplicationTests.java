package com.example.cars_info;

import com.example.cars_info.model.echo_park_db.CarDetails;
import com.example.cars_info.service.EchoParkService;
import com.fasterxml.jackson.databind.JsonNode;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.apache.commons.io.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class CarsInfoApplicationTests {

	@Autowired private EchoParkService echoParkService;

	@Test
	void contextLoads() {
		String jsonString = "{'vin': '1C4RJFAG1JC178969', 'stock_id': 'PJC178969', 'year': '2018', 'trim': 'Cherokee', 'kbb_price': '$37,870', 'echopark_price': '$28,919', 'available_location': 'EchoPark Syracuse (Cicero)', 'detailed_specs': [' : 1-touch down', ' : 1-touch up', ' : Air conditioning', ' : Auto-dimming rearview mirror', ' : Automatic temperature control', ' : Driver door bin', ' : Driver vanity mirror', ' : Front beverage holders', ' : Front dual zone A/C', ' : Illuminated entry', ' : Navigation system', ' : Overhead console', ' : Passenger door bin', ' : Passenger vanity mirror', ' : Power windows', 'Proximity key : doors and push button start', ' : Rear beverage holders', ' : Rear door bins', ' : Remote keyless entry', ' : Speed control', ' : Telescoping steering wheel', ' : Tilt steering wheel', ' : Voice recorder', ' : Alloy wheels', ' : Four wheel independent suspension', ' : Front anti-roll bar', ' : Power steering', ' : Rear anti-roll bar', 'Tires : all-terrain', '1st row LCD monitors : 2', 'AM/FM radio : SiriusXM', 'Primary LCD size : 8.4\"', ' : Radio data system', 'Satellite radio trial duration with new vehicle purchase (months) : 12', 'Smart device integration : Apple CarPlay/Android Auto', ' : Steering wheel mounted audio controls', 'Wireless phone connectivity : Uconnect w/Bluetooth', 'Front center armrest : w/storage', 'Front seats : bucket', ' : Leather shift knob', ' : Leather upholstery', 'Max seating capacity : 5', ' : Power 4-way driver lumbar support', ' : Power driver seat', ' : Rear seat center armrest', 'Rear seats : split-bench', 'Rear seats Folding position : fold forward seatback', ' : Split folding rear seat', 'Cylinder configuration : V-6', 'Drive type : four-wheel', 'Engine liters : 3.6', 'Engine location : front', 'Fuel economy city : 18mpg', 'Fuel economy combined : 21mpg', 'Fuel economy highway : 25mpg', 'Fuel tank capacity : 24.6gal.', 'Horsepower : 293hp @ 6,400RPM', ' : Manual-shift auto', ' : Mode select transmission', 'Number of valves : 24', 'Recommended fuel : Regular Unleaded', ' : Sequential multi-point fuel injection', 'Torque : 260 lb.-ft. @ 4,000RPM', 'Transmission : 8 speed automatic', ' : Variable valve control', 'Approach angle : 26 deg', 'Departure angle : 24 deg', 'Ground clearance (max) : 254mm (10.0\")', 'Ground clearance (min) : 218mm (8.6\")', 'Ramp breakover angle : 19 deg', 'Bumpers : body-color', ' : Heated door mirrors', ' : Power door mirrors', ' : Spoiler', 'Tailpipe finisher : chrome', ' : Trailer sway control', 'Compression ratio : 10.20 to 1', 'Curb weight : 2,098kg (4,625lbs)', 'Engine bore x stroke : 96.0mm x 83.0mm (3.78\" x 3.27\")', 'Engine displacement : 3.6 L', 'Engine horsepower : 293hp @ 6,400RPM', 'Engine torque : 260 lb.-ft. @ 4,000RPM', 'Exterior body width : 1,943mm (76.5\")', 'Exterior height : 1,760mm (69.3\")', 'Exterior length : 4,821mm (189.8\")', 'Front headroom : 1,013mm (39.9\")', 'Front hiproom : 1,448mm (57.0\")', 'Front legroom : 1,024mm (40.3\")', 'Front shoulder room : 1,491mm (58.7\")', 'GVWR : 2,948kg (6,500lbs)', 'Interior cargo volume : 1,028 L (36 cu.ft.)', 'Interior maximum cargo volume : 1,934 L (68 cu.ft.)', 'Passenger volume : 2,985L (105.4 cu.ft.)', 'Payload : 621kg (1,370lbs)', 'Rear headroom : 996mm (39.2\")', 'Rear hiproom : 1,427mm (56.2\")', 'Rear legroom : 980mm (38.6\")', 'Rear shoulder room : 1,473mm (58.0\")', 'Towing capacity : 1,588kg (3,500lbs)', \"Turning radius : 5.7m (18.5')\", 'Wheelbase : 2,916mm (114.8\")', ' : Compass', ' : Configurable', ' : Delay-off headlights', 'Display : digital/analog', 'Exterior parking camera rear : ParkView yes', ' : Front fog lights', ' : Front reading lights', ' : Fully automatic headlights', ' : Low tire pressure warning', ' : Outside temperature display', 'Parking sensors : rear', 'Rear collision : mitigation', ' : Rear reading lights', ' : Rear window defroster', ' : Rear window wiper', ' : Speed sensitive wipers', ' : Tachometer', ' : Trip computer', ' : Variably intermittent wipers', ' : 4 wheel disc brakes', ' : ABS brakes', ' : Anti-whiplash front head restraints', ' : Brake assist', ' : Dual front impact airbags', ' : Dual front side impact airbags', ' : Electronic stability', ' : Ignition disable', ' : Knee airbag', ' : Occupant sensing airbag', ' : Overhead airbag', ' : Panic alarm', ' : Perimeter/approach lights', ' : Tracker system', ' : Traction control']}\n";
		JsonNode jsonNode = echoParkService.validateJSON(jsonString);
		Assertions.assertNotNull(jsonNode);
	}

	@Test
	void testValidJsonString() {
		String file = "/Users/sreekar/Desktop/scrape.json";
		try {
			String jsonString = FileUtils.readFileToString(new File(file),"UTF-8");
			System.out.println(jsonString);
			JsonNode jsonNode = echoParkService.validateJSON(jsonString);
			Assertions.assertNotNull(jsonNode);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Test
	void testCarDetailsObject () {
		String file = "/Users/sreekar/Desktop/scrape.json";
		try {
			String jsonString = FileUtils.readFileToString(new File(file),"UTF-8");
			JsonNode jsonNode = echoParkService.validateJSON(jsonString);
			CarDetails carDetails = new CarDetails();
			echoParkService.mapJsonToCarObj(jsonNode, carDetails);

			System.out.println(carDetails);
			Assertions.assertNotNull(carDetails);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Test
	void testIntegerExtraction() {
		String file = "/Users/sreekar/Desktop/scrape.json";
		try {
			String jsonString = FileUtils.readFileToString(new File(file),"UTF-8");
			JsonNode jsonNode = echoParkService.validateJSON(jsonString);

			Integer fuelEconomy = echoParkService.extractNumber(jsonNode.get("fuel_economy_combined").asText());
			Integer price = echoParkService.extractNumber(jsonNode.get("echopark_price").asText());
			Integer mileage = echoParkService.extractNumber(jsonNode.get("mileage").asText());

			String[] hpText = jsonNode.get("horsepower").asText().split("@");
			Integer hp = echoParkService.extractNumber(hpText[0]);
			Integer hpRpm = echoParkService.extractNumber(hpText[1]);

			String[] tqText = jsonNode.get("torque").asText().split("@");;
			Integer tq = echoParkService.extractNumber(tqText[0]);
			Integer tqRpm = echoParkService.extractNumber(tqText[1]);

			Integer length = echoParkService.extractNumber(jsonNode.get("exterior_length").asText().split("\\(")[0]);

			Float engineDisplacement = echoParkService.extractEngineDisplacement(jsonNode.get("engine_displacement").asText());

			testIntegerValue(fuelEconomy);
			testIntegerValue(price);
			testIntegerValue(mileage);
			testIntegerValue(hp);
			testIntegerValue(hpRpm);
			testIntegerValue(tq);
			testIntegerValue(tqRpm);
			testIntegerValue(length);
			testFloatValue(engineDisplacement);
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}

	void testFloatValue (float value){
		System.out.println(value);
		Assertions.assertNotEquals(value, 0.0f);
	}

	void testIntegerValue(int value) {
		System.out.println(value);
		Assertions.assertNotEquals(value, -1);
	}

	@Test
	void testStockLinkSave() {
		List<String> urls = new ArrayList<>();
		urls.add("http://dummy:1000");
		urls.add("http://dummy:1001");
		urls.add("http://dummy:1002");
		urls.add("http://dummy:1003");
		urls.add("http://dummy:1004");
		int urlsSaved = echoParkService.saveUrlsInDatabase(urls);
		Assertions.assertTrue(urlsSaved>0);
	}

}
