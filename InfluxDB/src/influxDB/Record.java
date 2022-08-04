package influxDB;

import java.time.Instant;

public class Record {
	
	private Instant time;
    private String recordName;
    
    public void setTime(Instant time) {
 	   this.time=time;
    }
    
    public Instant getTime() {
 	   return time;
    }
    
    public void setRecordName(String recordName) {
 	   this.recordName=recordName;
    }
    
    public String getRecordName() {
 	   return recordName;
    }

}
