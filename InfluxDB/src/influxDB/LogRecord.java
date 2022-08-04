package influxDB;
import java.time.Instant;
import java.util.concurrent.TimeUnit;

import org.influxdb.annotation.Column;
import org.influxdb.annotation.Measurement;
import org.influxdb.annotation.TimeColumn;

@Measurement(name = "logRecord",timeUnit = TimeUnit.SECONDS)
public class LogRecord {

	@TimeColumn
    @Column(name = "time")
    private Instant time;

    @Column(name = "logType")
    private String logType;

    @Column(name = "logLevel")
    private int logLevel;
    
    @Column(name = "log")
    private String log;

    public Instant getTime() {
        return time;
    }

    public void setTime(Instant time) {
        this.time = time;
    }

    public String getLogType() {
        return logType;
    }

    public void setLogType(String logType) {
        this.logType = logType;
    }

    public int getLogLevel() {
        return logLevel;
    }

    public void setLogLevel(int logLevel) {
        this.logLevel = logLevel;
    }
    
    public String getLog() {
        return log;
    }

    public void setLog(String log) {
        this.log = log;
    }
}

