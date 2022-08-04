package test;

import influxDB.InfluxDBRecorder;

public class Main4 {
	
public static void main(String[] args) throws InterruptedException {
		
		InfluxDBRecorder obj=new InfluxDBRecorder();
		obj.startReplay2("logType1");
		
	}

}
