package influxDB;

import java.time.Instant;

public class Log {

	private Instant time;
	private String logType;
    private int logLevel;
    private String log;
    
    public void setTime(Instant time) {
 	   this.time=time;
    }
    
    public Instant getTime() {
 	   return time;
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
