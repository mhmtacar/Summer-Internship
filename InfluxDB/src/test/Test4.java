package test;

import java.util.List;

import influxDB.DataRecord;
import influxDB.InfluxDBRecorder;

public class Test4 {

	public static void main(String[] args) {
		
		InfluxDBRecorder obj=new InfluxDBRecorder();
		List<DataRecord> queryResult=obj.getDataByMessageId("radarRecord2",3);
		
		for(DataRecord result : queryResult) {
			System.out.printf(result.getTime() + "   ");
			System.out.printf(result.getRecordName() + "   ");
			System.out.printf(result.getMessageId() + "   ");
			System.out.println(result.getData());
		}
		
		System.out.println();
		
	}
	
}
