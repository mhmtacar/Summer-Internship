package influxDB;
import java.time.Duration;
import java.time.Instant;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.influxdb.InfluxDB;
import org.influxdb.InfluxDBFactory;
import org.influxdb.dto.Point;
import org.influxdb.dto.Query;
import org.influxdb.dto.QueryResult;
import org.influxdb.dto.QueryResult.Result;
import org.influxdb.dto.QueryResult.Series;
import org.influxdb.impl.InfluxDBMapper;

public class InfluxDBRecorder {

    private InfluxDB influxDB;
    private ReplayRadar replayRadar;
    
    public InfluxDBRecorder() {
		influxDB = InfluxDBFactory.connect("http://localhost:8086", "root", "root");
		String databaseName = "proje";
		influxDB.query(new Query("CREATE DATABASE " + databaseName));
		influxDB.setDatabase(databaseName);
	}
    
	public InfluxDBRecorder(ReplayRadar replayRadar) {
		influxDB = InfluxDBFactory.connect("http://localhost:8086", "root", "root");
		String databaseName = "proje";
		influxDB.query(new Query("CREATE DATABASE " + databaseName));
		influxDB.setDatabase(databaseName);
		this.replayRadar=replayRadar;
	}
	
    public void recordData(String recordName, int messageId, String data) {
    	RadarRecord record=new RadarRecord();
        record.setTime(Instant.now());
        record.setRecordName(recordName);
        record.setMessageId(messageId);
        record.setData(data);
        Point point = Point.measurementByPOJO(record.getClass()).addFieldsFromPOJO(record).build();
        influxDB.write(point);
    }
        
        
	public List<Record> getRecords() {
		
		QueryResult queryResult = influxDB.query(new Query("SELECT * FROM radarRecord"));
		List<List<Object>> databaseNames = queryResult.getResults().get(0).getSeries().get(0).getValues();
		Record record;
		List<Record> records = new ArrayList<>();
		List<String> recordNames= new ArrayList<>();
		boolean same_record_name=false;
		
		 if (databaseNames != null) {
		  for (List<Object> database : databaseNames) {
			  
			  for(int i=0;i<recordNames.size();i++) {
				  if(database.get(3).toString().equals(recordNames.get(i))) {
					  same_record_name=true;
					  break;
				  }
			  }
			  
			  if(!same_record_name) {
			  record= new Record();
			  record.setTime(Instant.parse(database.get(0).toString()));
			  record.setRecordName(database.get(3).toString());
		      records.add(record);
		      recordNames.add(database.get(3).toString());
			  }
			  same_record_name=false;
		  }
		 }
		 
		 return records;
		
	}
	
	
	public List<Record> getRecordsByTime(Instant startTime, Instant endTime) {
		
		String start="'" + startTime + "'";
		String end="'" + endTime + "'";
		QueryResult queryResult = influxDB.query(new Query("SELECT * FROM radarRecord WHERE time >= " + start + " AND time <= " + end));
		List<List<Object>> databaseNames = null;
		
		if(queryResult.getResults().get(0).getSeries()!=null) {
		databaseNames = queryResult.getResults().get(0).getSeries().get(0).getValues();
		}
		
		Record record;
		List<Record> records = new ArrayList<>();
		List<String> recordNames= new ArrayList<>();
		boolean same_record_name=false;
		
		if (databaseNames != null) {
			  for (List<Object> database : databaseNames) {
				  
				  for(int i=0;i<recordNames.size();i++) {
					  if(database.get(3).toString().equals(recordNames.get(i))) {
						  same_record_name=true;
						  break;
					  }
				  }
				  
				  if(!same_record_name) {
					  record= new Record();
					  record.setTime(Instant.parse(database.get(0).toString()));
					  record.setRecordName(database.get(3).toString());
				      records.add(record);
				      recordNames.add(database.get(3).toString());
					  }
					  same_record_name=false;
			  }
			 }
	
			 return records;
	}
	
	
	public List<DataRecord> getDataByMessageId(String recordName, int messageId) {
		
		String str="'"+ recordName + "'";
		QueryResult queryResult = influxDB.query(new Query("SELECT * FROM radarRecord WHERE recordName = " + str + " AND messageId = " + messageId));
		List<List<Object>> databaseNames=null;
		
		if(queryResult.getResults().get(0).getSeries()!=null) {
		databaseNames = queryResult.getResults().get(0).getSeries().get(0).getValues();
		}
		
		DataRecord data;
		List<DataRecord> datas = new ArrayList<>();
		
		 if (databaseNames != null) {
		  for (List<Object> database : databaseNames) {
			  data= new DataRecord();
		      data.setTime(Instant.parse(database.get(0).toString()));
		      data.setRecordName(recordName);
		      data.setMessageId(messageId);
		      data.setData(database.get(1).toString());
		      datas.add(data);
		  }
		 }
		 
		 return datas;
		
	}
	
	public void startReplay(String recordName) throws InterruptedException {
		
	  Thread t1= new Thread() {
			
		public void run() {
				
		String str="'"+ recordName + "'";
		QueryResult queryResult = influxDB.query(new Query("SELECT * FROM radarRecord WHERE recordName = " + str));
		List<List<Object>> databaseNames = null;
		List<List<Object>> databaseNames2 = null;
		
		if(queryResult.getResults().get(0).getSeries()!=null) {
			databaseNames = queryResult.getResults().get(0).getSeries().get(0).getValues();
			databaseNames2 = queryResult.getResults().get(0).getSeries().get(0).getValues();
		}
	
		DataRecord data;
		DataRecord data2 = null;
		int count=0;
		int count2=0;
		
		if (databaseNames != null) {
			  for (List<Object> database : databaseNames) {
				  data= new DataRecord();
			      data.setTime(Instant.parse(database.get(0).toString()));
			      data.setRecordName(recordName);
			      float messageId=Float.parseFloat(database.get(2).toString());
			      data.setMessageId((int)messageId);
			      data.setData(database.get(1).toString());
			      
			      if(count>0) {
			    	  for (List<Object> database2 : databaseNames2) {
			    		  data2= new DataRecord();
					      data2.setTime(Instant.parse(database2.get(0).toString()));
					      data2.setRecordName(recordName);
					      float messageId2=Float.parseFloat(database2.get(2).toString());
					      data2.setMessageId((int)messageId2);
					      data2.setData(database2.get(1).toString());
			    		 if(count2==count-1) 
			    			 break;
			    		  count2++;
			    	  }
			      }
			      
			      
			      if(count==0) {
			    	  replayRadar.data(data);
			      }
			      
			      else {
			    	  
			    	  long x=ChronoUnit.MILLIS.between(data2.getTime(), data.getTime());
			    	  try {
						Thread.sleep(x);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			    	  replayRadar.data(data);
			      }
			      
			      count++;
			      count2=0;
			      
			  }
			  
		}
		       replayRadar.replayFinish();
			}
		};
		
		t1.start();
			 
	}
	
	//long x=Math.abs(data.getTime().getLong(ChronoField.SECOND_OF_MINUTE)- data2.getTime().getLong(ChronoField.SECOND_OF_MINUTE));
	//System.out.println(x);
	
	
	//System.out.println(data2.getTime());
	  
	  //Instant x=data.getTime().minusSeconds(data2.getTime().getLong(ChronoField.INSTANT_SECONDS));
	  //Instant x=data.getTime().minus(data2.getTime().getLong(ChronoField.INSTANT_SECONDS));
	//long y=x.getLong(ChronoField.INSTANT_SECONDS);
	  //Duration res = Duration.between(data.getTime(), data2.getTime());
	  //long seconds = res.getSeconds();
	  //System.out.println(seconds);
	
	public void recordLog(String logType, int logLevel, String log) {
		LogRecord record=new LogRecord();
        record.setTime(Instant.now());
        record.setLogType(logType);
        record.setLogLevel(logLevel);
        record.setLog(log);
        Point point = Point.measurementByPOJO(record.getClass()).addFieldsFromPOJO(record).build();
        influxDB.write(point);
	}
	
	
	public List<Log> getLogsByType(String logType) {
		
		String str="'"+ logType + "'";
		QueryResult queryResult = influxDB.query(new Query("SELECT * FROM logRecord WHERE logType = " + str));
		List<List<Object>> databaseNames=null;
		
		if(queryResult.getResults().get(0).getSeries()!=null) {
			databaseNames = queryResult.getResults().get(0).getSeries().get(0).getValues();
		}
		
		Log log;
		List<Log> logs = new ArrayList<>();
		
		 if (databaseNames != null) {
		  for (List<Object> database : databaseNames) {
			  log= new Log();
			  log.setTime(Instant.parse(database.get(0).toString()));
			  log.setLogType(logType);
			  float logLevel=Float.parseFloat(database.get(2).toString());
			  log.setLogLevel((int)logLevel);
			  log.setLog(database.get(1).toString());
		      logs.add(log);
		  }
		 }
		 
		 return logs;
	}
	
	public List<Log> getLogsByTime(Instant startTime, Instant endTime) {
		
		String start="'" + startTime + "'";
		String end="'" + endTime + "'";
		QueryResult queryResult = influxDB.query(new Query("SELECT * FROM logRecord WHERE time >= " + start + " AND time <= " + end));
		List<List<Object>> databaseNames = null;
		
		if(queryResult.getResults().get(0).getSeries()!=null) {
			databaseNames = queryResult.getResults().get(0).getSeries().get(0).getValues();
		}
		
		Log log;
		List<Log> logs = new ArrayList<>();
		
		if (databaseNames != null) {
			  for (List<Object> database : databaseNames) {
				  log= new Log();
				  log.setTime(Instant.parse(database.get(0).toString()));
				  log.setLogType(database.get(3).toString());
				  float logLevel=Float.parseFloat(database.get(2).toString());
				  log.setLogLevel((int)logLevel);
				  log.setLog(database.get(1).toString());
			      logs.add(log);
			  }
			 }
			 
			 return logs;
	}
	
	public List<Log> getLogsByLevel(int logLevel) {
		
		QueryResult queryResult = influxDB.query(new Query("SELECT * FROM logRecord WHERE logLevel = " + logLevel));
		List<List<Object>> databaseNames=null;
		
		if(queryResult.getResults().get(0).getSeries()!=null) {
			databaseNames = queryResult.getResults().get(0).getSeries().get(0).getValues();
		}
		
		Log log;
		List<Log> logs = new ArrayList<>();
		
		 if (databaseNames != null) {
		  for (List<Object> database : databaseNames) {
			  log= new Log();
			  log.setTime(Instant.parse(database.get(0).toString()));
			  log.setLogType(database.get(3).toString());
			  log.setLogLevel(logLevel);
			  log.setLog(database.get(1).toString());
		      logs.add(log);
		  }
		 }
		 
		 return logs;
	}
	
	
	public void startReplay2(String logType) throws InterruptedException {
		
		Thread t1= new Thread() {
			
			public void run() {
				
			String str="'"+ logType + "'";
			QueryResult queryResult = influxDB.query(new Query("SELECT * FROM logRecord WHERE logType = " + str));
			List<List<Object>> databaseNames = null;
			List<List<Object>> databaseNames2 = null;
			
			if(queryResult.getResults().get(0).getSeries()!=null) {
				databaseNames = queryResult.getResults().get(0).getSeries().get(0).getValues();
				databaseNames2 = queryResult.getResults().get(0).getSeries().get(0).getValues();
			}
		
			Log log;
			Log log2 = null;
			int count=0;
			int count2=0;
			
			if (databaseNames != null) {
				  for (List<Object> database : databaseNames) {
					  log= new Log();
				      log.setTime(Instant.parse(database.get(0).toString()));
				      log.setLogType(logType);
				      float logLevel=Float.parseFloat(database.get(2).toString());
				      log.setLogLevel((int)logLevel);
				      log.setLog(database.get(1).toString());
				      
				      if(count>0) {
				    	  for (List<Object> database2 : databaseNames2) {
				    		  log2= new Log();
						      log2.setTime(Instant.parse(database2.get(0).toString()));
						      log2.setLogType(logType);
						      float logLevel2=Float.parseFloat(database2.get(2).toString());
						      log2.setLogLevel((int)logLevel2);
						      log2.setLog(database2.get(1).toString());
				    		 if(count2==count-1) 
				    			 break;
				    		  count2++;
				    	  }
				      }
				      
				      
				      if(count==0) {
				    	  System.out.printf(log.getTime() + "   ");
						  System.out.printf(log.getLogType() + "   ");
						  System.out.printf(log.getLogLevel() + "   ");
						  System.out.println(log.getLog());
				    	  
				      }
				      
				      else {
				    	  
				    	  long x=ChronoUnit.MILLIS.between(log2.getTime(), log.getTime());
				    	  try {
								Thread.sleep(x);
						} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
						}
				    	  System.out.printf(log.getTime() + "   ");
						  System.out.printf(log.getLogType() + "   ");
						  System.out.printf(log.getLogLevel() + "   ");
						  System.out.println(log.getLog());
				      }
				      
				      count++;
				      count2=0;
				      
				  }
				  
			}
			
			}
		};
		
		t1.start();
				
	}
	
}
