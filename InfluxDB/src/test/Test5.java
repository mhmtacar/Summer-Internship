package test;

import influxDB.InfluxDBRecorder;

public class Test5 {

    public static void main(String[] args) {
		
	    InfluxDBRecorder obj=new InfluxDBRecorder();
		obj.recordLog("log_type1",1,"testLog1");
		obj.recordLog("log_type1",1,"testLog2");
		obj.recordLog("log_type1",2,"testLog3");
		obj.recordLog("log_type1",2,"testLog4");
		obj.recordLog("log_type1",2,"testLog5");
		obj.recordLog("log_type2",3,"testLog6");
		obj.recordLog("log_type2",3,"testLog7");
		obj.recordLog("log_type2",3,"testLog8");
		obj.recordLog("log_type2",4,"testLog9");
		obj.recordLog("log_type2",4,"testLog10");
		obj.recordLog("log_type3",5,"testLog11");
		obj.recordLog("log_type3",5,"testLog12");
		obj.recordLog("log_type3",6,"testLog13");
		obj.recordLog("log_type3",6,"testLog14");
		obj.recordLog("log_type3",6,"testLog15");
		
	}

}
