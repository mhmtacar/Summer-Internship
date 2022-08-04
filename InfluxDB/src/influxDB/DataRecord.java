package influxDB;

import java.time.Instant;

public class DataRecord {
	
       private Instant time;
       private String recordName;
       private int messageId;
       private String data;
       
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
