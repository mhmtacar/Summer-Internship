package test;

import org.influxdb.annotation.TimeColumn;

import influxDB.InfluxDBRecorder;

public class Test1 {
    
	public static void main(String[] args) {
		
		InfluxDBRecorder obj=new InfluxDBRecorder();
		obj.recordData("radarRecord1",1,"testData1");
		obj.recordData("radarRecord1",1,"testData2");
		obj.recordData("radarRecord1",1,"testData3");
		obj.recordData("radarRecord1",2,"testData4");
		obj.recordData("radarRecord1",2,"testData5");
		obj.recordData("radarRecord2",3,"testData6");
		obj.recordData("radarRecord2",3,"testData7");
		obj.recordData("radarRecord2",4,"testData8");
		obj.recordData("radarRecord2",4,"testData9");
		obj.recordData("radarRecord2",4,"testData10");
		obj.recordData("radarRecord3",5,"testData11");
		obj.recordData("radarRecord3",5,"testData12");
		obj.recordData("radarRecord3",5,"testData13");
		obj.recordData("radarRecord3",6,"testData14");
		obj.recordData("radarRecord3",6,"testData15");
		
	}
	
}


/*
QueryResult queryResult3=getRecordsByTime(influxDB,Instant.parse("2021-08-25T10:21:58Z"),Instant.parse("2021-08-25T10:22:43Z"));
System.out.println(queryResult3);
*/
