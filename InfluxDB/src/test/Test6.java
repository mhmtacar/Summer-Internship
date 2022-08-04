package test;

import java.util.List;

import influxDB.Log;
import influxDB.InfluxDBRecorder;

public class Test6 {

	public static void main(String[] args) {
		
		InfluxDBRecorder obj=new InfluxDBRecorder();
		List<Log> queryResult3=obj.getLogsByType("log_type2");
		
		for(Log result : queryResult3) {
			System.out.printf(result.getTime() + "   ");
			System.out.printf(result.getLogType() + "   ");
			System.out.printf(result.getLogLevel() + "   ");
			System.out.println(result.getLog());
		}
		
		System.out.println();
		
	}
	
}
