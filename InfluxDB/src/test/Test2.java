package test;

import java.util.List;

import influxDB.InfluxDBRecorder;
import influxDB.Record;

public class Test2 {
	
	public static void main(String[] args) {

		InfluxDBRecorder obj=new InfluxDBRecorder();
		List<Record> queryResult=obj.getRecords();
		
		for(Record result : queryResult) {
			System.out.printf(result.getTime() + "   ");
			System.out.println(result.getRecordName());
		}
		
		System.out.println();
	
	}
	
}
