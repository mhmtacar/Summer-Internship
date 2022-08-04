package test;

import java.util.List;

import influxDB.Log;
import influxDB.InfluxDBRecorder;

public class Test8 {
	
	public static void main(String[] args) {
		
		InfluxDBRecorder obj=new InfluxDBRecorder();
		List<Log> queryResult4=obj.getLogsByLevel(6);
		
		for(Log result : queryResult4) {
			System.out.printf(result.getTime() + "   ");
			System.out.printf(result.getLogType() + "   ");
			System.out.printf(result.getLogLevel() + "   ");
			System.out.println(result.getLog());
		}
		
		System.out.println();
		
	}

}
