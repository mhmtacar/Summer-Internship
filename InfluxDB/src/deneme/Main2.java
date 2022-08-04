package deneme;
import java.time.Instant;
import java.util.List;
import java.util.logging.Logger;

import org.influxdb.BatchOptions;
import org.influxdb.InfluxDB;
import org.influxdb.InfluxDBFactory;
import org.influxdb.dto.Point;
import org.influxdb.dto.Query;
import org.influxdb.dto.QueryResult;
import org.influxdb.impl.InfluxDBImpl;
import org.influxdb.impl.InfluxDBMapper;

public class Main2 {
	
	private static final Logger LOGGER = Logger.getLogger(Main2.class.getName());
	
	public static void main(String[] args) throws InterruptedException {
		
		InfluxDB influxDB = InfluxDBFactory.connect("http://localhost:8086", "root", "root");
		String databaseName = "test";
		influxDB.query(new Query("CREATE DATABASE " + databaseName));
		influxDB.setDatabase(databaseName);
		

        H2OFeetMeasurement h2OFeetMeasurement = new H2OFeetMeasurement();
        h2OFeetMeasurement.setTime(Instant.now());
        h2OFeetMeasurement.setLevelDescription("below 3 feet");
        h2OFeetMeasurement.setLocation("santa monica");
        h2OFeetMeasurement.setWaterLevel(2.064d);
        
        Point point = Point.measurementByPOJO(h2OFeetMeasurement.getClass()).addFieldsFromPOJO(h2OFeetMeasurement).build();
        influxDB.write(point);
        

        H2OFeetMeasurement h2OFeetMeasurement2 = new H2OFeetMeasurement();
        h2OFeetMeasurement2.setTime(Instant.now());
        h2OFeetMeasurement2.setLevelDescription("between 6 and 9 feet");
        h2OFeetMeasurement2.setLocation("coyote_creek");
        h2OFeetMeasurement2.setWaterLevel(8.12d);
        
        point = Point.measurementByPOJO(h2OFeetMeasurement2.getClass()).addFieldsFromPOJO(h2OFeetMeasurement2).build();
        influxDB.write(point);
        
        
        H2OPhMeasurement h2OPhMeasurement = new H2OPhMeasurement();
        h2OPhMeasurement.setTime(Instant.now());
        h2OPhMeasurement.setLocation("coyote creek");
        h2OPhMeasurement.setpH(3);
        
        Point point2 = Point.measurementByPOJO(h2OPhMeasurement.getClass()).addFieldsFromPOJO(h2OPhMeasurement).build();
        influxDB.write(point2);
        
        
        H2OPhMeasurement h2OPhMeasurement2 = new H2OPhMeasurement();
        h2OPhMeasurement2.setTime(Instant.now());
        h2OPhMeasurement2.setLocation("santa monica");
        h2OPhMeasurement2.setpH(10);
        
        point2 = Point.measurementByPOJO(h2OPhMeasurement2.getClass()).addFieldsFromPOJO(h2OPhMeasurement2).build();
        influxDB.write(point2);
        
        
        H2OTemperatureMeasurement h2OTemperatureMeasurement = new H2OTemperatureMeasurement();
        h2OTemperatureMeasurement.setTime(Instant.now());
        h2OTemperatureMeasurement.setLocation("coyote creek");
        h2OTemperatureMeasurement.setDegrees(60);
        
        Point point3 = Point.measurementByPOJO(h2OTemperatureMeasurement.getClass()).addFieldsFromPOJO(h2OTemperatureMeasurement).build();
        influxDB.write(point3);
        
        
        H2OTemperatureMeasurement h2OTemperatureMeasurement2 = new H2OTemperatureMeasurement();
        h2OTemperatureMeasurement2.setTime(Instant.now());
        h2OTemperatureMeasurement2.setLocation("santa monica");
        h2OTemperatureMeasurement2.setDegrees(70);
        
        point3 = Point.measurementByPOJO(h2OTemperatureMeasurement2.getClass()).addFieldsFromPOJO(h2OTemperatureMeasurement2).build();
        influxDB.write(point3);
        
        
        /*
        Point point = Point.measurementByPOJO(h2OFeetMeasurement.getClass()).addFieldsFromPOJO(h2OFeetMeasurement).build();
        influxDB.write(point);
        point = Point.measurementByPOJO(h2OFeetMeasurement2.getClass()).addFieldsFromPOJO(h2OFeetMeasurement2).build();
        influxDB.write(point);
        Point point2 = Point.measurementByPOJO(h2OPhMeasurement.getClass()).addFieldsFromPOJO(h2OPhMeasurement).build();
        influxDB.write(point2);
        point2 = Point.measurementByPOJO(h2OPhMeasurement2.getClass()).addFieldsFromPOJO(h2OPhMeasurement2).build();
        influxDB.write(point2);
        Point point3 = Point.measurementByPOJO(h2OTemperatureMeasurement.getClass()).addFieldsFromPOJO(h2OTemperatureMeasurement).build();
        influxDB.write(point3);
        point3 = Point.measurementByPOJO(h2OTemperatureMeasurement2.getClass()).addFieldsFromPOJO(h2OTemperatureMeasurement2).build();
        influxDB.write(point3);
        */
        
        
        /*
        List<H2OFeetMeasurement> persistedMeasure = influxDBMapper.query(H2OFeetMeasurement.class);
        System.out.println(persistedMeasure);
        */
        /*
         Point point = Point.measurementByPOJO(h2OFeetMeasurement.getClass()).addFieldsFromPOJO(h2OFeetMeasurement).build();
        influxDB.write(point);
        point = Point.measurementByPOJO(h2OFeetMeasurement2.getClass()).addFieldsFromPOJO(h2OFeetMeasurement2).build();
        influxDB.write(point);
        influxDBMapper.save(h2OFeetMeasurement);
        influxDBMapper.save(h2OFeetMeasurement2);
        influxDBMapper.save(h2OPhMeasurement);
        influxDBMapper.save(h2OPhMeasurement2);
        influxDBMapper.save(h2OTemperatureMeasurement);
        influxDBMapper.save(h2OTemperatureMeasurement2);
        */
        
        Thread.sleep(5_000L);
        
        QueryResult queryResult = influxDB.query(new Query("SELECT * FROM h2o_feet"));
    	System.out.println(queryResult);
    	queryResult = influxDB.query(new Query("SELECT * FROM h2o_pH"));
    	System.out.println(queryResult);
    	queryResult = influxDB.query(new Query("SELECT * FROM h2o_temperature"));
    	System.out.println(queryResult);
    	
    	influxDB.close();
        
/*
        List measurements = influxDBMapper.query(H2OFeetMeasurement.class);

        H2OFeetMeasurement h2OFeetMeasurement1 = (H2OFeetMeasurement) measurements.get(measurements.size()-1);
        assert h2OFeetMeasurement1.getLevelDescription().equals("Just a test");
        */
	}
}
