package influxDB;

public class ReplayRadar implements I_ReplayRadar {

	@Override
	public void data(DataRecord data) {
		System.out.printf(data.getTime() + "   ");
		System.out.printf(data.getRecordName() + "   ");
		System.out.printf(data.getMessageId() + "   ");
		System.out.println(data.getData());
	}
	
	@Override
	public void replayFinish() {
		System.out.println("Replay finished");
	}
	
}
