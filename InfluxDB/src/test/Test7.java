package test;

import java.time.Instant;
import java.util.List;

import influxDB.Log;
import influxDB.InfluxDBRecorder;

public class Test7 {

	public static void main(String[] args) {

		InfluxDBRecorder obj=new InfluxDBRecorder();
		List<Log> queryResult=obj.getLogsByTime(Instant.parse("2021-09-01T15:09:38.862Z"),Instant.parse("2021-09-01T15:09:38.989Z"));
		
		for(Log result : queryResult) {
			System.out.printf(result.getTime() + "   ");
			System.out.printf(result.getLogType() + "   ");
			System.out.printf(result.getLogLevel() + "   ");
			System.out.println(result.getLog());
		}
		
		System.out.println();
		
	}
	
}
