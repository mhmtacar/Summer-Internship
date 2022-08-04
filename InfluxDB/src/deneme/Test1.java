package deneme;


import java.util.concurrent.TimeUnit;

import org.influxdb.InfluxDB;
import org.influxdb.InfluxDBFactory;
import org.influxdb.dto.Point;
import org.influxdb.dto.Query;
import org.influxdb.dto.QueryResult;

public class Test1 {
	
	public static void main(String[] args) {
	
		final String serverURL = "http://127.0.0.1:8086", username = "root", password = "root";
		final InfluxDB influxDB = InfluxDBFactory.connect(serverURL, username, password);
        test1(influxDB);
	    test2(influxDB);
	    test3(influxDB);
	    influxDB.close();
	    
    }
	
	public static void test1(final InfluxDB influxDB) {
		
		String databaseName = "deneme";
		influxDB.query(new Query("CREATE DATABASE " + databaseName));
		influxDB.setDatabase(databaseName);
		
	}
	
	public static void test2(final InfluxDB influxDB) {
		
		String m1="h2o_feet";
		String m2="h2o_pH";
		String m3="h2o_temperature";
		String location1="coyote creek";
		String location2="santa monica";
		
		    influxDB.write(Point.measurement(m1)
			    .time(System.currentTimeMillis(), TimeUnit.MILLISECONDS)
			    .tag("location", location2)
			    .addField("level description", "below 3 feet")
			    .addField("water_level", 2.064d)
			    .build());

			influxDB.write(Point.measurement(m1)
			    .time(System.currentTimeMillis(), TimeUnit.MILLISECONDS)
			    .tag("location", location1)
			    .addField("level description", "between 6 and 9 feet")
			    .addField("water_level", 8.12d)
			    .build());
			
			influxDB.write(Point.measurement(m2)
				    .time(System.currentTimeMillis(), TimeUnit.MILLISECONDS)
				    .tag("location", location1)
				    .addField("pH", 3)
				    .build());
			
			influxDB.write(Point.measurement(m2)
				    .time(System.currentTimeMillis(), TimeUnit.MILLISECONDS)
				    .tag("location", location2)
				    .addField("pH", 10)
				    .build());
			
			influxDB.write(Point.measurement(m3)
				    .time(System.currentTimeMillis(), TimeUnit.MILLISECONDS)
				    .tag("location", location1)
				    .addField("degrees", 60)
				    .build());
			
			influxDB.write(Point.measurement(m3)
				    .time(System.currentTimeMillis(), TimeUnit.MILLISECONDS)
				    .tag("location", location2)
				    .addField("degrees", 70)
				    .build());
			
	}
	
	public static void test3(final InfluxDB influxDB) {
		
		QueryResult queryResult = influxDB.query(new Query("SELECT * FROM h2o_feet"));
		System.out.println(queryResult);
		queryResult = influxDB.query(new Query("SELECT \"level description\",location,water_level FROM h2o_feet"));
		System.out.println(queryResult);
		queryResult = influxDB.query(new Query("SELECT *::field FROM h2o_feet"));
		System.out.println(queryResult);
		queryResult = influxDB.query(new Query("SELECT (water_level * 2) + 4 FROM h2o_feet"));
		System.out.println(queryResult);
		queryResult = influxDB.query(new Query("SELECT * FROM h2o_feet WHERE \"level description\" = 'below 3 feet'"));
		System.out.println(queryResult);
		queryResult = influxDB.query(new Query("SELECT * FROM h2o_pH WHERE pH > 7"));
		System.out.println(queryResult);
		queryResult = influxDB.query(new Query("SELECT pH FROM h2o_pH WHERE location = 'santa_monica'"));
		System.out.println(queryResult);
		queryResult = influxDB.query(new Query("SELECT * FROM h2o_pH WHERE time > now() - 7d"));
		System.out.println(queryResult);
		queryResult = influxDB.query(new Query("SELECT degrees FROM h2o_temperature WHERE location <> 'coyote_creek' AND (degrees < 63 OR degrees > 75)"));
		System.out.println(queryResult);
		queryResult = influxDB.query(new Query("SELECT MEAN(degrees) FROM h2o_temperature GROUP BY location"));
		System.out.println(queryResult);
		queryResult = influxDB.query(new Query("SELECT degrees FROM h2o_temperature WHERE location = 'coyote_creek' AND time >= '2021-08-18T00:00:00.000000000Z'"
				+ "AND time <= '2021-08-25T00:12:00Z'"));
		System.out.println(queryResult);
		queryResult = influxDB.query(new Query("SELECT degrees FROM h2o_temperature WHERE location = 'santa_monica' AND time >= now() - 1h"));
		System.out.println(queryResult);
		
	}
	
	
}
