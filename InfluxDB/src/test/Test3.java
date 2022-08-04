package test;

import java.time.Instant;
import java.util.List;

import influxDB.InfluxDBRecorder;
import influxDB.Record;

public class Test3 {

	public static void main(String[] args) {

		InfluxDBRecorder obj=new InfluxDBRecorder();
		List<Record> queryResult=obj.getRecordsByTime(Instant.parse("2021-09-01T14:55:41.693Z"),Instant.parse("2021-09-01T14:55:42.251Z"));
		
		for(Record result : queryResult) {
			System.out.printf(result.getTime() + "   ");
			System.out.println(result.getRecordName());
		}
		
		System.out.println();
		
	}
	
}
