package influxDB;
import java.time.Instant;
import java.util.concurrent.TimeUnit;

import org.influxdb.InfluxDB;
import org.influxdb.annotation.Column;
import org.influxdb.annotation.Measurement;
import org.influxdb.annotation.TimeColumn;

@Measurement(name = "radarRecord",timeUnit = TimeUnit.SECONDS)
public class RadarRecord {
	
	@TimeColumn
    @Column(name = "time")
    private Instant time;

    @Column(name = "recordName")
    private String recordName;

    @Column(name = "messageId")
    private int messageId;
    
    @Column(name = "data")
    private String data;
    
   
    public Instant getTime() {
        return time;
    }

    public void setTime(Instant time) {
        this.time = time;
    }

    public String getRecordName() {
        return recordName;
    }

    public void setRecordName(String recordName) {
        this.recordName = recordName;
    }

    public int getMessageId() {
        return messageId;
    }

    public void setMessageId(int messageId) {
        this.messageId = messageId;
    }
    
    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
    
    
}
