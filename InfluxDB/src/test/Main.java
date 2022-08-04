package test;

import influxDB.DataRecord;
import influxDB.I_ReplayRadar;
import influxDB.InfluxDBRecorder;
import influxDB.ReplayRadar;

public class Main {
	
public static void main(String[] args) throws InterruptedException {
		
	    ReplayRadar replayRadar = new ReplayRadar();
		
		InfluxDBRecorder obj=new InfluxDBRecorder(replayRadar);
		obj.recordData("radar1",1,"testData1");
		Thread.sleep(2000);
		obj.recordData("radar1",1,"testData2");
		Thread.sleep(1000);
		obj.recordData("radar1",1,"testData3");
		Thread.sleep(500);
		obj.recordData("radar1",2,"testData4");
		Thread.sleep(2000);
		obj.recordData("radar1",2,"testData5");
		Thread.sleep(1000);
		obj.recordData("radar1",3,"testData6");
		Thread.sleep(500);
		obj.recordData("radar1",3,"testData7");
		Thread.sleep(2000);
		obj.recordData("radar1",3,"testData8");
		Thread.sleep(1000);
		obj.recordData("radar1",4,"testData9");
		Thread.sleep(500);
		obj.recordData("radar1",4,"testData10");
		
	}

}
