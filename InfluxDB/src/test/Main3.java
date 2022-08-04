package test;

import influxDB.InfluxDBRecorder;

public class Main3 {

public static void main(String[] args) throws InterruptedException {
		
		InfluxDBRecorder obj=new InfluxDBRecorder();
		obj.recordLog("logType1",1,"testLog1");
		Thread.sleep(2000);
		obj.recordLog("logType1",1,"testLog2");
		Thread.sleep(1000);
		obj.recordLog("logType1",2,"testLog3");
		Thread.sleep(500);
		obj.recordLog("logType1",2,"testLog4");
		Thread.sleep(2000);
		obj.recordLog("logType1",2,"testLog5");
		Thread.sleep(1000);
		obj.recordLog("logType1",3,"testLog6");
		Thread.sleep(500);
		obj.recordLog("logType1",3,"testLog7");
		Thread.sleep(2000);
		obj.recordLog("logType1",4,"testLog8");
		Thread.sleep(1000);
		obj.recordLog("logType1",4,"testLog9");
		Thread.sleep(500);
		obj.recordLog("logType1",4,"testLog10");
		
	}
}
