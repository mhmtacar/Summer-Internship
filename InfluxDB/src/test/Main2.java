package test;

import java.time.Instant;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.util.List;

import influxDB.DataRecord;
import influxDB.I_ReplayRadar;
import influxDB.InfluxDBRecorder;
import influxDB.ReplayRadar;

public class Main2 {

public static void main(String[] args) throws InterruptedException {
	
	    ReplayRadar replayRadar = new ReplayRadar();
		
		InfluxDBRecorder obj=new InfluxDBRecorder(replayRadar);
		obj.startReplay("radar1");
		
	}

}
